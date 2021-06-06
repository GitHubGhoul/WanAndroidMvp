package com.wxd.wanandroidmvp.presenter;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.IHomeContract;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.model.HomeModel;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public class HomePresenter extends BasePresenter<IHomeContract.IHomeModel, IHomeContract.IHomeView> {

    public HomePresenter(IHomeContract.IHomeView view) {
        super(new HomeModel(), view);
    }

    public void getHomeArticle(LifecycleOwner owner, int page) {
        mModel.getResponse(owner, page, new IBaseListener<HomeArticle, String>() {
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

    public void getBanner(LifecycleOwner owner) {
        mModel.getBanner(owner, new IBaseListener<List<Banner>, String>() {
            @Override
            public void onSucceed(List<Banner> data) {
                mView.getBanner(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.getBannerFailed(msg);
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

    /**
     * 显示fab动画
     */
    public void showFABAnimation(View view){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(400).start();
    }

    /**
     * 隐藏fab的动画
     */

    public void hideFABAnimation(View view){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(400).start();
    }
}
