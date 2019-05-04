package com.zaomeng.zaomeng.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
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

    public void submit() {

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
