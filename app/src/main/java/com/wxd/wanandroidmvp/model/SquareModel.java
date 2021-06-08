package com.wxd.wanandroidmvp.model;

import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.ISearchContract;
import com.wxd.wanandroidmvp.contract.ISquareContract;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.entity.Square;
import com.wxd.wanandroidmvp.http.RequestHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.lifecycle.LifecycleOwner;

public class SquareModel implements ISquareContract.ISquareModel {

    @Override
    public void getResponse(LifecycleOwner owner, Integer param, IBaseListener<HomeArticle, String> listener) {
        RequestHelper.square(owner, param, new BaseSingleObserver<HomeArticle>() {
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
