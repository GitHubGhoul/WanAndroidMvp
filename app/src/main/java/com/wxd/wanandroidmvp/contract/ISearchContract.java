package com.wxd.wanandroidmvp.contract;

import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.base.IBaseModel;
import com.wxd.wanandroidmvp.base.IBaseView;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;

public interface ISearchContract {

    interface ISearchModel extends IBaseModel<LifecycleOwner, Map<String,Object>, IBaseListener<HomeArticle,String>>{
        void getHotKey(LifecycleOwner owner,IBaseListener<List<Hotkey>,String> listener);
    }

    interface ISearchView extends IBaseView<HomeArticle,String>{
        void getHotKey(List<Hotkey> hotkey);
        void getHotKeyFailed(String s);
    }
}
