package com.jinkan.www.fastandroid.model.http;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Sampson on 2018/4/17.
 * FastAndroid
 */
public class BaseObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";
    private ObserverOnNextListener observerOnNextListener;
    private Context context;

    public BaseObserver(Context context, ObserverOnNextListener observerOnNextListener) {
        this.observerOnNextListener = observerOnNextListener;
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
        //添加业务处理
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onNext(T t) {
        observerOnNextListener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        //添加业务处理
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        //添加业务处理
    }
}
