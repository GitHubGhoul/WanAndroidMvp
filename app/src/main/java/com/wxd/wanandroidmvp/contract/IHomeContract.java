package com.wxd.wanandroidmvp.contract;

import android.content.Context;

import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.base.IBaseModel;
import com.wxd.wanandroidmvp.base.IBaseView;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;

public interface IHomeContract {

    interface IHomeModel extends IBaseModel<LifecycleOwner, Integer, IBaseListener<HomeArticle,String>>{
        void getHotKey(LifecycleOwner owner,IBaseListener<List<Hotkey>,String> listener);
        void getBanner(LifecycleOwner owner, IBaseListener<List<Banner>, String> listener);
    }

    interface IHomeView extends IBaseView<HomeArticle,String>{
        void getHotKey(List<Hotkey> hotkey);
        void getHotKeyFailed(String s);
        void getBanner(List<Banner> banner);
        void getBannerFailed(String s);
    }
}
