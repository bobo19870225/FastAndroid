package com.zaomeng.zaomeng.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
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
    //    public final MutableLiveData<Integer> ldProgress = new MediatorLiveData<>();
    public final MutableLiveData<String> ldUpDataImage = new MediatorLiveData<>();
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
                        SharedPreferencesUtils.getSessionID(getApplication()),
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
//            ldProgress.postValue((int) (currentBytes * 100 / contentLength));
        }, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ldUpDataImage.postValue(e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    String result = response.body().string();
                    ldUpDataImage.postValue(result);
                }
            }
        }, file);

    }


}
