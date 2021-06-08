package com.wxd.wanandroidmvp.contract;

import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.base.IBaseModel;
import com.wxd.wanandroidmvp.base.IBaseView;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;

public interface ISquareContract {

    interface ISquareModel extends IBaseModel<LifecycleOwner, Integer, IBaseListener<HomeArticle,String>>{
    }

    interface ISquareView extends IBaseView<HomeArticle,String>{
        
    }
}
