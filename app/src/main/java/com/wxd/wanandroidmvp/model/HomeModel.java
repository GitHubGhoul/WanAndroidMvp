package com.wxd.wanandroidmvp.model;

import android.content.Context;

import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.IHomeContract;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.http.RequestHelper;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public class HomeModel implements IHomeContract.IHomeModel {

    @Override
    public void getHotKey(LifecycleOwner owner, IBaseListener<List<Hotkey>, String> listener) {
        RequestHelper.hotKey(owner, new BaseSingleObserver<List<Hotkey>>() {
            @Override
            public void onSucceed(List<Hotkey> data) {
                listener.onSucceed(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                listener.onFailed(errorMsg);
            }
        });
    }

    @Override
    public void getBanner(LifecycleOwner owner, IBaseListener<List<Banner>, String> listener) {
        RequestHelper.banner(owner, new BaseSingleObserver<List<Banner>>() {
            @Override
            public void onSucceed(List<Banner> data) {
                listener.onSucceed(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                listener.onFailed(errorMsg);
            }
        });
    }

    @Override
    public void getResponse(LifecycleOwner owner, Integer param, IBaseListener<HomeArticle, String> listener) {
        RequestHelper.homeArticle(owner, param, new BaseSingleObserver<HomeArticle>() {
            @Override
            public void onSucceed(HomeArticle data) {
                listener.onSucceed(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                listener.onFailed(errorMsg);
            }
        });
    }
}
