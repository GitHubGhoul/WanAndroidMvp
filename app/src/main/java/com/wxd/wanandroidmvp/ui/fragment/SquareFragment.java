package com.wxd.wanandroidmvp.ui.fragment;

import android.os.Bundle;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseVBFragment;
import com.wxd.wanandroidmvp.contract.ISquareContract;
import com.wxd.wanandroidmvp.databinding.FragmentDiscoverBinding;
import com.wxd.wanandroidmvp.databinding.FragmentSquareBinding;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.presenter.SquarePresenter;
import com.wxd.wanandroidmvp.ui.activity.SearchActivity;
import com.wxd.wanandroidmvp.ui.adapter.HomeArticleAdapter;
import com.wxd.wanandroidmvp.utils.ToastUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

public class SquareFragment extends BaseVBFragment<FragmentSquareBinding, SquarePresenter> implements ISquareContract.ISquareView {

    private int page;
    private HomeArticleAdapter adapter;

    @Override
    protected SquarePresenter bindPresenter() {
        return new SquarePresenter(this);
    }

    @Override
    protected void init() {
        mBinding.mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeArticleAdapter(new ArrayList<>());
        mBinding.mList.setAdapter(adapter);
        mPresenter.getSquareArticle(this, page);
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.getSquareArticle(getActivity(), page);
            }
        });
        mBinding.refreshLayout.setOnLoadMoreListener(new com.scwang.smart.refresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getSquareArticle(getActivity(), page);
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void getData(HomeArticle result) {
        mBinding.refreshLayout.finishRefresh();
        mBinding.refreshLayout.finishLoadMore();
        if (page == 0) {
            adapter.getData().clear();
        }
        adapter.addData(result.getDatas());
    }

    @Override
    public void showMsg(String msg) {
        mBinding.refreshLayout.finishRefresh(false);
        mBinding.refreshLayout.finishLoadMore(false);
        ToastUtils.getInstance().show(msg);
    }
}
