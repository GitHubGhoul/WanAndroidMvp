package com.wxd.wanandroidmvp.base;

import com.wxd.wanandroidmvp.http.RxExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class BaseSingleObserver<T> implements SingleObserver<BaseResponse<T>> {

    private static final String TAG = "BaseSingle";

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onSuccess(@NonNull BaseResponse<T> response) {
        //在这边对 基础数据 进行统一处理
        if(response.isSuccess()){
            onSucceed(response.getData());
        }else {
            onFailure(null,response.getErrorMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    public abstract void onSucceed(T data);

    public abstract void onFailure(Throwable e,String errorMsg);
}
