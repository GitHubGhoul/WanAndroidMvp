package com.wxd.wanandroidmvp.presenter;

import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.ISearchContract;
import com.wxd.wanandroidmvp.contract.ISquareContract;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.model.SearchModel;
import com.wxd.wanandroidmvp.model.SquareModel;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;

public class SquarePresenter extends BasePresenter<ISquareContract.ISquareModel, ISquareContract.ISquareView> {

    public SquarePresenter(ISquareContract.ISquareView view) {
        super(new SquareModel(), view);
    }

    public void getSquareArticle(LifecycleOwner owner, Integer page) {
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

}
