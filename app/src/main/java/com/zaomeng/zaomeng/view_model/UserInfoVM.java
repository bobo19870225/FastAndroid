package com.zaomeng.zaomeng.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;
import com.zaomeng.zaomeng.utils.http.OkHttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.zaomeng.zaomeng.utils.SystemParameter.baseUrl;

/**
 * Created by Sampson on 2019-05-13.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.UserInfoActivity}
 */
public class UserInfoVM extends BaseViewModel {
    private ApiService apiService;
    public UserDao userDao;
    private String userURL;
    public String oldUserURL;
    public String oldName;
    public final MutableLiveData<String> ldName = new MutableLiveData<>();
    public final MutableLiveData<String> ldPhone = new MutableLiveData<>();
    public final SingleLiveEvent<String> ldUpdateImage = new SingleLiveEvent<>();
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();


    public UserInfoVM(@NonNull Application application, ApiService apiService, UserDao userDao) {
        super(application);
        this.apiService = apiService;
        this.userDao = userDao;
    }

    @Override
    public void init(Object data) {

    }

    public LiveData<List<LoginBean>> getUserInfo() {
        return userDao.getAllUser();
    }

    public LiveData<Resource<Bean<String>>> updateMemberInfo() {

        if (ldName.getValue() == null) {
            action.setValue("toast:请填写昵称");
            return null;
        }
        if (ldName.getValue().equals(oldName) && userURL == null) {//用户未修改
            action.setValue("finish");//直接返回
            return null;
        }
        if (userURL == null) {
            userURL = oldUserURL;
        }
        return apiService.updateMemberInfo(SharedPreferencesUtils.getSessionID(getApplication()),
                ldName.getValue(),
                userURL);


    }

    public void uploadImg(String s) {

        File file = new File(s);
        String postUrl = baseUrl + "uploadFile.json";
        OkHttpUtil.postFile(postUrl, (currentBytes, contentLength, done) -> {
            Log.i("test", "currentBytes==" + currentBytes + "==contentLength==" + contentLength + "==done==" + done);
//            ldProgress.postValue((int) (currentBytes * 100 / contentLength));
        }, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ldUpdateImage.postValue(e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.body() != null) {
                    String result = response.body().string();
                    ldUpdateImage.postValue(result);
                    Gson gson = new Gson();
                    Bean<String> bean = gson.fromJson(result, Bean.class);
                    if (bean != null) {
                        userURL = bean.getBody().getData();
                    } else {
                        userURL = null;
                    }
                }
            }
        }, file);
    }

    public void selectImage() {
        action.setValue("selectImage");
    }

    public void upDateUser(LoginBean loginBean) {
        loginBean.setAvatarURL(userURL);
        loginBean.setShortName(ldName.getValue());
        ExecutorService DB_IO = Executors.newFixedThreadPool(2);
        DB_IO.execute(() -> {
            userDao.upDateUser(loginBean);
            DB_IO.shutdown();//关闭线程
        });
    }

    public void clean() {
        ldName.setValue("");
    }
}
