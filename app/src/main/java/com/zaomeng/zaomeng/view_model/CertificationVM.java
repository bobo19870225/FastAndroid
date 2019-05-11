package com.zaomeng.zaomeng.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
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
    public final MutableLiveData<String> ldShopType = new MediatorLiveData<>();
    public final MutableLiveData<String> ldShopTypeID = new MediatorLiveData<>();
    public final MediatorLiveData<Resource<Bean<String>>> ldSubmit = new MediatorLiveData<>();
    public final SingleLiveEvent<String> ldToast = new SingleLiveEvent<>();
    //    public final MutableLiveData<Integer> ldProgress = new MediatorLiveData<>();
    private String shopFaceImage;
    private String businessImage;
    private String contactIdCardFaceImage;
    private String contactIdCardBackImage;
    public final MutableLiveData<String[]> ldUpDataImage = new MediatorLiveData<>();
    public CertificationVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void choiceType() {
        action.setValue("choiceType");
    }

    public void choiceArea() {

    }

    public void setShopImage() {
        action.setValue("setShopImage");
    }

    public void setLicenseImage() {
        action.setValue("setLicenseImage");
    }

    public void setIcFront() {
        action.setValue("setIcFront");
    }

    public void setIcBack() {
        action.setValue("setIcBack");
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
            ldToast.setValue("请填写店铺名称");
            return;
        }
        if (ldAddress.getValue() == null) {
            ldToast.setValue("请填写店铺名称");
            return;
        }
        if (ldContact.getValue() == null) {
            ldToast.setValue("请填写店铺名称");
            return;
        }
        if (ldShopTypeID.getValue() == null || ldShopType.getValue() == null) {
            ldToast.setValue("请选择店铺类型");
            return;
        }
        if (ldContactPhone.getValue() == null) {
            ldToast.setValue("请填写店铺地址");
            return;
        }
        if (shopFaceImage == null) {
            ldToast.setValue("请上传店铺照片");
            return;
        }
        if (businessImage == null) {
            ldToast.setValue("请上传营业执照");
            return;
        }
        if (contactIdCardFaceImage == null) {
            ldToast.setValue("请上传身份证正面照");
            return;
        }
        if (contactIdCardBackImage == null) {
            ldToast.setValue("请上传身份证背面照");
            return;
        }
        ldSubmit.addSource(
                apiService.applyMemberShop(
                        SharedPreferencesUtils.getSessionID(getApplication()),
                        ldName.getValue(),
                        ldShopTypeID.getValue(),
                        shopFaceImage,
                        businessImage,
                        ldAddress.getValue(),
                        ldContact.getValue(),
                        ldContactPhone.getValue(),
                        contactIdCardFaceImage,
                        contactIdCardBackImage
                ), ldSubmit::setValue);
    }

    public void uploadImg(String fileUrl, int which) {

        File file = new File(fileUrl);
        String postUrl = baseUrl + "uploadFile.json";
        OkHttpUtil.postFile(postUrl, (currentBytes, contentLength, done) -> {
            Log.i("test", "currentBytes==" + currentBytes + "==contentLength==" + contentLength + "==done==" + done);
//            ldProgress.postValue((int) (currentBytes * 100 / contentLength));
        }, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ldUpDataImage.postValue(new String[]{String.valueOf(which), e.toString()});
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    String result = response.body().string();
                    ldUpDataImage.postValue(new String[]{String.valueOf(which), result});
                    Gson gson = new Gson();
                    switch (which) {
                        case 0:
                            shopFaceImage = setImageUrl(result, gson);
                            break;
                        case 1:
                            businessImage = setImageUrl(result, gson);
                            break;
                        case 2:
                            contactIdCardFaceImage = setImageUrl(result, gson);
                            break;
                        case 3:
                            contactIdCardBackImage = setImageUrl(result, gson);
                            break;
                    }
                }
            }
        }, file);

    }

    private String setImageUrl(String result, Gson gson) {
        Bean bean = gson.fromJson(result, Bean.class);
        if (bean != null) {
            return (String) bean.getBody().getData();
        } else {
            return null;
        }
    }


    public LiveData<Resource<PageBean<GoodsSuperBean>>> getShopType() {
        return apiService.getNodeCategoryList("2a89882dffa242158a5e170215f351ad", 1);
    }
}
