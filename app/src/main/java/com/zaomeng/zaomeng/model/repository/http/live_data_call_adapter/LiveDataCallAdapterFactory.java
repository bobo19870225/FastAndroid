package com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    private LiveDataCallAdapterFactory() {
    }

    public static LiveDataCallAdapterFactory create() {
        return new LiveDataCallAdapterFactory();
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException("Response must be parametrized as " +
                    "LiveData<Resource> or LiveData<? extends Resource>");
        }

        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        if (getRawType(responseType) == Response.class) {
            if (!(responseType instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parametrized as " +
                        "LiveData<Response<Resource>> or LiveData<Response<? extends Resource>>");
            }

            return new LiveDataResponseCallAdapter<>(responseType);
        }
        return new LiveDataBodyCallAdapter<>(getParameterUpperBound(0, (ParameterizedType) returnType));
    }
}
