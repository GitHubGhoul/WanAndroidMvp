package com.wxd.wanandroidmvp.presenter;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.net.VpnManager;
import android.view.View;

import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.IHomeContract;
import com.wxd.wanandroidmvp.contract.ISearchContract;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.model.HomeModel;
import com.wxd.wanandroidmvp.model.SearchModel;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;

public class SearchPresenter extends BasePresenter<ISearchContract.ISearchModel, ISearchContract.ISearchView> {

    public SearchPresenter(ISearchContract.ISearchView view) {
        super(new SearchModel(), view);
    }

    public void getSearchArticle(LifecycleOwner owner, Map<String,Object> map) {
        mModel.getResponse(owner, map, new IBaseListener<HomeArticle, String>() {
            @Override
            public void onSucceed(HomeArticle data) {
                mView.getData(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.showMsg(msg);
            }
        });
    }

    public void getHotKey(LifecycleOwner owner) {
        mModel.getHotKey(owner, new IBaseListener<List<Hotkey>, String>() {
            @Override
            public void onSucceed(List<Hotkey> data) {
                mView.getHotKey(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.getHotKeyFailed(msg);
            }
        });
    }

}
