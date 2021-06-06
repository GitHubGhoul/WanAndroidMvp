package com.wxd.wanandroidmvp.model;

import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.IHomeContract;
import com.wxd.wanandroidmvp.contract.ISearchContract;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.http.RequestHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.lifecycle.LifecycleOwner;

public class SearchModel implements ISearchContract.ISearchModel {

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
    public void getResponse(LifecycleOwner owner, Map<String, Object> param, IBaseListener<HomeArticle, String> listener) {
        RequestHelper.queryArticle(owner, Integer.parseInt(Objects.requireNonNull(param.get("page")).toString()), Objects.requireNonNull(param.get("k")).toString(), new BaseSingleObserver<HomeArticle>() {
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
