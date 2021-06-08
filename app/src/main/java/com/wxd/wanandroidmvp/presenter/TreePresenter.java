package com.wxd.wanandroidmvp.presenter;

import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.ITreeContract;
import com.wxd.wanandroidmvp.contract.ITreeContract;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Tree;
import com.wxd.wanandroidmvp.model.TreeModel;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public class TreePresenter extends BasePresenter<ITreeContract.ITreeModel, ITreeContract.ITreeView> {

    public TreePresenter(ITreeContract.ITreeView view) {
        super(new TreeModel(), view);
    }

    public void getTreeArticle(LifecycleOwner owner) {
        mModel.getResponse(owner, null,new IBaseListener<List<Tree>, String>() {
            @Override
            public void onSucceed(List<Tree> data) {
                mView.getData(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.showMsg(msg);
            }
        });
    }

}
