package com.wxd.wanandroidmvp.model;

import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.ISquareContract;
import com.wxd.wanandroidmvp.contract.ITreeContract;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Tree;
import com.wxd.wanandroidmvp.http.RequestHelper;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public class TreeModel implements ITreeContract.ITreeModel {

    @Override
    public void getResponse(LifecycleOwner owner, Object param, IBaseListener<List<Tree>, String> listener) {
        RequestHelper.tree(owner, new BaseSingleObserver<List<Tree>>() {
            @Override
            public void onSucceed(List<Tree> data) {
                listener.onSucceed(data);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                listener.onFailed(errorMsg);
            }
        });
    }
}
