package com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter;

import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public final class LiveDataBodyCallAdapter<R> implements CallAdapter<R, LiveData<Resource<R>>> {
    private final Type responseType;

    LiveDataBodyCallAdapter(Type type) {
        this.responseType = type;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<Resource<R>> adapt(Call<R> call) {
        final MutableLiveData<Resource<R>> liveDataResponse = new MutableLiveData<>();
        call.enqueue(new LiveDataBodyCallCallback<>(liveDataResponse));
        return liveDataResponse;
    }

    private static class LiveDataBodyCallCallback<T> implements Callback<T> {
        private final MutableLiveData<Resource<T>> liveData;

        LiveDataBodyCallCallback(MutableLiveData<Resource<T>> liveData) {
            this.liveData = liveData;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
            if (call.isCanceled()) return;

            if (response.isSuccessful()) {
                liveData.postValue(Resource.success(response.body()));
            } else {
                liveData.postValue(Resource.error(new HttpException(response)));
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
            if (call.isCanceled()) return;
            liveData.postValue(Resource.error(t));
        }
    }
}
