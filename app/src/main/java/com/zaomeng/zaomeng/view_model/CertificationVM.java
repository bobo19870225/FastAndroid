package com.zaomeng.zaomeng.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;
import com.zaomeng.zaomeng.utils.http.OkHttpUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.zaomeng.zaomeng.utils.SystemParameter.baseUrl;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.CertificationActivity}
 */
public class CertificationVM extends BaseViewModel {
    private ApiService apiService;
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldName = new MediatorLiveData<>();
    public final MutableLiveData<String> ldAddress = new MediatorLiveData<>();
    public final MutableLiveData<String> ldContact = new MediatorLiveData<>();
    public final MutableLiveData<String> ldContactPhone = new MediatorLiveData<>();
    public final MediatorLiveData<Resource<Bean<String>>> ldSubmit = new MediatorLiveData<>();
    public CertificationVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void choiceType() {

    }

    public void choiceArea() {

    }

    public void setShopImage() {
        action.setValue("setShopImage");
//        uploadImg(imageUri);
    }

    public void setLicenseImage() {

    }

    public void setIcFront() {

    }

    public void setIcBack() {

    }

    /**
     * shopCategoryID=a74eeee1-750c-497d-afaa-cd867014f5f8
     * &shopFaceImage=http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg
     * &businessImage=http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg
     * &address=%E4%B8%8A%E6%B5%B7%E8%99%B9%E5%8F%A3&
     * contact=%E5%B0%8F%E9%93%81&
     * contactPhone=13162617998&
     * contactIdCardFaceImage=http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg&
     * contactIdCardBackImage=http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg
     */
    public void submit() {
        if (ldName.getValue() == null) {
            return;
        }
        if (ldAddress.getValue() == null) {
            return;
        }
        if (ldContact.getValue() == null) {
            return;
        }
        if (ldContactPhone.getValue() == null) {
            return;
        }
        ldSubmit.addSource(
                apiService.applyMemberShop(
                        SharedPreerencesUtils.getSessionID(getApplication()),
                        ldName.getValue(),
                        "a74eeee1-750c-497d-afaa-cd867014f5f8",
                        "http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg",
                        "http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg",
                        ldAddress.getValue(),
                        ldContact.getValue(),
                        ldContactPhone.getValue(),
                        "http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg",
                        "http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg"
                ), ldSubmit::setValue);
    }

    public void uploadImg(String fileUrl) {

        File file = new File(fileUrl);
        String postUrl = baseUrl + "uploadFile.json";
        OkHttpUtil.postFile(postUrl, (currentBytes, contentLength, done) -> {
            Log.i("test", "currentBytes==" + currentBytes + "==contentLength==" + contentLength + "==done==" + done);
            int progress = (int) (currentBytes * 100 / contentLength);
//                post_progress.setProgress(progress);
//                post_text.setText(progress + "%");
        }, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("test", "result===" + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("test", "result===" + result);
            }
        }, file);

//        File file = new File(fileUrl);
//        // 创建 RequestBody，用于封装构建RequestBody
////        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
//
//        // MultipartBody.Part  和后端约定好Key，这里的partName是用file
////        MultipartBody.Part body = MultipartBody.Part.createFormData("file", "#" + file.getName(), requestFile);
//        MultipartBody.Part body = MultipartBody.Part.create(requestFile);
//
//        // 添加描述
////        String descriptionString = "hello, 这是文件描述";
////        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
////        Map<String, RequestBody> files = new HashMap<>();
////        files.put("file\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
//        // 执行请求
//        apiService.uploadFile(body).enqueue(new Callback<Bean<List<String>>>() {
//            @Override
//            public void onResponse(@NonNull Call<Bean<List<String>>> call, @NonNull Response<Bean<List<String>>> response) {
//                String s = "test";
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Bean<List<String>>> call, @NonNull Throwable t) {
//                String s = "test";
//            }
//        });

    }


}
