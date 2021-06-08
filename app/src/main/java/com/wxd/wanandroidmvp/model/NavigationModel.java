package com.wxd.wanandroidmvp.model;

import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.INavigationContract;
import com.wxd.wanandroidmvp.contract.ITreeContract;
import com.wxd.wanandroidmvp.entity.Navigation;
import com.wxd.wanandroidmvp.entity.Tree;
import com.wxd.wanandroidmvp.http.RequestHelper;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public class NavigationModel implements INavigationContract.INavigationModel{

    @Override
    public void getResponse(LifecycleOwner owner, Object param, IBaseListener<List<Navigation>, String> listener) {
        RequestHelper.navigation(owner, new BaseSingleObserver<List<Navigation>>() {
            @Override
            public void onSucceed(List<Navigation> data) {
                listener.onSucceed(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                listener.onFailed(errorMsg);
            }
        });
    }
}
