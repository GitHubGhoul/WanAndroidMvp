package com.wxd.wanandroidmvp.contract;

import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.base.IBaseModel;
import com.wxd.wanandroidmvp.base.IBaseView;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Tree;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public interface ITreeContract {

    interface ITreeModel extends IBaseModel<LifecycleOwner, Object, IBaseListener<List<Tree>,String>>{
    }

    interface ITreeView extends IBaseView<List<Tree>,String>{
        
    }
}
