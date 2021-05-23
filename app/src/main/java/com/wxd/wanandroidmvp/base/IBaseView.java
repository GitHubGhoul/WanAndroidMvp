package com.wxd.wanandroidmvp.base;

public interface IBaseView<T, M> {

    void getData(T result);

    void showMsg(M msg);
}
