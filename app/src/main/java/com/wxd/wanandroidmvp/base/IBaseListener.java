package com.wxd.wanandroidmvp.base;

public interface IBaseListener<T,F> {

    void onSucceed(T data);

    void onFailed(F msg);
}
