package com.wxd.wanandroidmvp.contract;

import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.base.IBaseModel;
import com.wxd.wanandroidmvp.base.IBaseView;
import com.wxd.wanandroidmvp.entity.Navigation;
import com.wxd.wanandroidmvp.entity.Tree;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public interface INavigationContract {

    interface INavigationModel extends IBaseModel<LifecycleOwner, Object, IBaseListener<List<Navigation>,String>>{
    }

    interface INavigationView extends IBaseView<List<Navigation>,String>{
        
    }
}
