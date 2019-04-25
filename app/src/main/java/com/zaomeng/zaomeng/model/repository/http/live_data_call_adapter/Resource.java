package com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter;

import androidx.annotation.Nullable;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public class Resource<T> {
    private T resource;
    private Throwable error;

    public Resource() {
    }

    public boolean isSuccess() {
        return resource != null && error == null;
    }

    @Nullable
    public T getResource() {
        return resource;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    static <T> Resource<T> success(@Nullable T body) {
        final Resource<T> resource = new Resource<>();
        resource.resource = body;
        return resource;
    }

    static <T> Resource error(@Nullable Throwable error) {
        final Resource<T> resource = new Resource<>();
        resource.error = error;
        return resource;
    }
}

