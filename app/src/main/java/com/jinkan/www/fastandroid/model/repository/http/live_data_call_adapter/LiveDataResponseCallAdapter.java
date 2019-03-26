package com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter;


import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public final class LiveDataResponseCallAdapter<R> implements CallAdapter<R, LiveData<Resource<Response<R>>>> {
    private final Type responseType;

    LiveDataResponseCallAdapter(Type type) {
        this.responseType = type;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public LiveData<Resource<Response<R>>> adapt(Call<R> call) {
        final MutableLiveData<Resource<Response<R>>> liveDataResponse = new MutableLiveData<>();
        call.enqueue(new LiveDataResponseCallAdapter.LiveDataResponseCallCallback(liveDataResponse));
        return liveDataResponse;
    }

    private static class LiveDataResponseCallCallback<T> implements Callback<T> {
        private final MutableLiveData<Response<Resource<T>>> liveData;

        LiveDataResponseCallCallback(MutableLiveData<Response<Resource<T>>> liveData) {
            this.liveData = liveData;
        }

        @Override
        public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
            if (call.isCanceled()) return;
            liveData.postValue(Response.success(Resource.success(response.body())));
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
            if (call.isCanceled()) return;
            liveData.postValue(Response.success(Resource.error(t)));
        }
    }
}
