package com.wxd.wanandroidmvp.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> implements IBasePresenter {

    public M mModel;
    public V mView;

    public BasePresenter(M model, V view) {
        this.mModel = model;
        this.mView = view;
    }

    /**
     * 我们通过实现IBasePresenter中的OnDestroy方法来解除持有
     *
     * @param owner 生命周期管理者
     */
    @Override
    public void OnDestroy(@NonNull LifecycleOwner owner) {
        //解绑V层 避免导致内存泄漏
        mView = null;
        mModel = null;
    }

}
