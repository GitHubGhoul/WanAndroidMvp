package com.wxd.wanandroidmvp.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public interface IBasePresenter extends LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    default void OnCreate(@NonNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    default void OnResume(@NonNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    default void OnStart(@NonNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    default void OnPause(@NonNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    default void OnStop(@NonNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    default void OnDestroy(@NonNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    default void OnLifeCycleChanged(@NonNull LifecycleOwner owner) {
    }
}
