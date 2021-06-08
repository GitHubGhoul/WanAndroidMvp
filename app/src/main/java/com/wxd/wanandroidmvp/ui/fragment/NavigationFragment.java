package com.wxd.wanandroidmvp.ui.fragment;

import android.os.Bundle;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseVBFragment;
import com.wxd.wanandroidmvp.contract.INavigationContract;
import com.wxd.wanandroidmvp.databinding.FragmentNavigationBinding;
import com.wxd.wanandroidmvp.databinding.FragmentSquareBinding;
import com.wxd.wanandroidmvp.entity.Navigation;
import com.wxd.wanandroidmvp.presenter.NavigationPresenter;
import com.wxd.wanandroidmvp.ui.adapter.NavigationAdapter;
import com.wxd.wanandroidmvp.ui.adapter.TreeAdapter;
import com.wxd.wanandroidmvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

public class NavigationFragment extends BaseVBFragment<FragmentNavigationBinding, NavigationPresenter> implements INavigationContract.INavigationView {

    private NavigationAdapter adapter;

    @Override
    protected NavigationPresenter bindPresenter() {
        return new NavigationPresenter(this);
    }

    @Override
    protected void init() {
        mBinding.mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NavigationAdapter(new ArrayList<>());
        mBinding.mList.setAdapter(adapter);
        mPresenter.getNavigationArticle(this);
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getNavigationArticle(getActivity());
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void getData(List<Navigation> result) {
        mBinding.refreshLayout.finishRefresh();
        adapter.addData(result);
    }

    @Override
    public void showMsg(String msg) {
        mBinding.refreshLayout.finishRefresh(false);
        ToastUtils.getInstance().show(msg);
    }
}
