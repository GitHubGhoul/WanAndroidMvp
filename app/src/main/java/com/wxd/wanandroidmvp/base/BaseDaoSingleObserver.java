package com.wxd.wanandroidmvp.base;

import com.wxd.wanandroidmvp.http.RxExceptionUtil;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class BaseDaoSingleObserver<T> implements SingleObserver<T> {

    private static final String TAG = "BaseSingle";

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onSuccess(@NonNull T response) {
        onSucceed(response);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    public abstract void onSucceed(T data);

    public abstract void onFailure(Throwable e,String errorMsg);
}
