package com.wxd.wanandroidmvp.ui.fragment;

import android.os.Bundle;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseVBFragment;
import com.wxd.wanandroidmvp.contract.ITreeContract;
import com.wxd.wanandroidmvp.databinding.FragmentSquareBinding;
import com.wxd.wanandroidmvp.databinding.FragmentTreeBinding;
import com.wxd.wanandroidmvp.entity.Tree;
import com.wxd.wanandroidmvp.presenter.TreePresenter;
import com.wxd.wanandroidmvp.ui.adapter.HomeArticleAdapter;
import com.wxd.wanandroidmvp.ui.adapter.TreeAdapter;
import com.wxd.wanandroidmvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

public class TreeFragment extends BaseVBFragment<FragmentTreeBinding, TreePresenter> implements ITreeContract.ITreeView {

    private TreeAdapter adapter;

    @Override
    protected TreePresenter bindPresenter() {
        return new TreePresenter(this);
    }

    @Override
    protected void init() {
        mBinding.mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TreeAdapter(new ArrayList<>());
        mBinding.mList.setAdapter(adapter);
        mPresenter.getTreeArticle(this);
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getTreeArticle(getActivity());
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void getData(List<Tree> result) {
        mBinding.refreshLayout.finishRefresh();
        adapter.addData(result);
    }

    @Override
    public void showMsg(String msg) {
        mBinding.refreshLayout.finishRefresh(false);
        ToastUtils.getInstance().show(msg);
    }
}
