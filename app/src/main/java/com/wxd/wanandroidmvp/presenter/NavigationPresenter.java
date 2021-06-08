package com.wxd.wanandroidmvp.presenter;

import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.IBaseListener;
import com.wxd.wanandroidmvp.contract.INavigationContract;
import com.wxd.wanandroidmvp.entity.Navigation;
import com.wxd.wanandroidmvp.model.NavigationModel;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;

public class NavigationPresenter extends BasePresenter<INavigationContract.INavigationModel, INavigationContract.INavigationView> {

    public NavigationPresenter(INavigationContract.INavigationView view) {
        super(new NavigationModel(), view);
    }

    public void getNavigationArticle(LifecycleOwner owner) {
        mModel.getResponse(owner, null,new IBaseListener<List<Navigation>, String>() {
            @Override
            public void onSucceed(List<Navigation> data) {
                mView.getData(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.showMsg(msg);
            }
        });
    }

}
