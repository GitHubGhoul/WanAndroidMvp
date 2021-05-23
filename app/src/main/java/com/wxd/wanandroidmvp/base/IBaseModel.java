package com.wxd.wanandroidmvp.base;

public interface IBaseModel<V, T, L extends IBaseListener> {

    void getResponse(V v, T param, L listener);

}
