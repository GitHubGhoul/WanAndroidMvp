package com.wxd.wanandroidmvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wxd.wanandroidmvp.R;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseVBActivity;
import com.wxd.wanandroidmvp.contract.ISearchContract;
import com.wxd.wanandroidmvp.databinding.ActivityMainBinding;
import com.wxd.wanandroidmvp.databinding.ActivitySearchBinding;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.presenter.SearchPresenter;
import com.wxd.wanandroidmvp.ui.adapter.HomeArticleAdapter;
import com.wxd.wanandroidmvp.utils.SoftKeyBoardUtils;
import com.wxd.wanandroidmvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchActivity extends BaseVBActivity<ActivitySearchBinding, SearchPresenter> implements ISearchContract.ISearchView {

    private HomeArticleAdapter adapter;
    private final Map<String, Object> map = new HashMap<>();
    private String k;
    private int page;

    @Override
    public SearchPresenter bindPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {
        k = bundle.getString("k");
        mBinding.editSearch.setText(k);
    }

    @Override
    protected void initData() {
        mBinding.editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    k = Objects.requireNonNull(mBinding.editSearch.getText()).toString().trim();
                    if(TextUtils.isEmpty(k)){
                        ToastUtils.getInstance().show(getString(R.string.search));
                        return true;
                    }
                    SoftKeyBoardUtils.getInstance(SearchActivity.this).hideInputKeyboard(SearchActivity.this,mBinding.editSearch);
                    page = 0;
                    map.put("page", page);
                    map.put("k", k);
                    mPresenter.getSearchArticle(SearchActivity.this, map);
                }
                return false;
            }
        });
        mBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.editSearch.setText("");
            }
        });
        mBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k = Objects.requireNonNull(mBinding.editSearch.getText()).toString().trim();
                if (TextUtils.isEmpty(k)) {
                    ToastUtils.getInstance().show(getString(R.string.search));
                    return;
                }
                SoftKeyBoardUtils.getInstance(SearchActivity.this).hideInputKeyboard(SearchActivity.this,mBinding.editSearch);
                page = 0;
                map.put("page", page);
                map.put("k", k);
                mPresenter.getSearchArticle(SearchActivity.this, map);
            }
        });
        mBinding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mBinding.clear.setVisibility(View.INVISIBLE);
                } else {
                    mBinding.clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.articleList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeArticleAdapter(new ArrayList<>());
        mBinding.articleList.setAdapter(adapter);
        map.put("page", page);
        map.put("k", k);
        mPresenter.getSearchArticle(this, map);
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                map.put("page", page);
                map.put("k", k);
                mPresenter.getSearchArticle(SearchActivity.this, map);
            }
        });
        mBinding.refreshLayout.setOnLoadMoreListener(new com.scwang.smart.refresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                map.put("page", page);
                map.put("k", k);
                mPresenter.getSearchArticle(SearchActivity.this, map);
            }
        });
    }

    @Override
    public void getHotKey(List<Hotkey> hotkey) {

    }

    @Override
    public void getHotKeyFailed(String s) {

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