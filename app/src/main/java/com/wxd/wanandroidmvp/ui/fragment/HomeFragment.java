package com.wxd.wanandroidmvp.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wxd.wanandroidmvp.R;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.BaseVBFragment;
import com.wxd.wanandroidmvp.contract.IHomeContract;
import com.wxd.wanandroidmvp.databinding.FragmentHomeBinding;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.http.RequestHelper;
import com.wxd.wanandroidmvp.model.HomeModel;
import com.wxd.wanandroidmvp.presenter.HomePresenter;
import com.wxd.wanandroidmvp.ui.activity.SearchActivity;
import com.wxd.wanandroidmvp.ui.adapter.HomeArticleAdapter;
import com.wxd.wanandroidmvp.utils.LogUtils;
import com.wxd.wanandroidmvp.utils.SoftKeyBoardUtils;
import com.wxd.wanandroidmvp.utils.ToastUtils;
import com.wxd.wanandroidmvp.view.MorePopup;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HomeFragment extends BaseVBFragment<FragmentHomeBinding, HomePresenter> implements IHomeContract.IHomeView {

    private HomeArticleAdapter adapter;
    private MorePopup morePopup;
    private int page;//页数
    private int distance;//滑动距离
    private boolean visible = true;//是否显示

    @Override
    protected HomePresenter bindPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void init() {
        morePopup = MorePopup.getInstance(getActivity());
        mPresenter.hideFABAnimation(mBinding.fab);
        SoftKeyBoardUtils.getInstance(getActivity()).setListener(new SoftKeyBoardUtils.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                mBinding.bg.setVisibility(View.VISIBLE);
            }

            @Override
            public void keyBoardHide(int height) {
                mBinding.bg.setVisibility(View.GONE);
            }
        });
        mBinding.editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = Objects.requireNonNull(mBinding.editSearch.getText()).toString().trim();
                    if(TextUtils.isEmpty(search)){
                        ToastUtils.getInstance().show(getString(R.string.search));
                        return true;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("k",search);
                    startActivity(SearchActivity.class,bundle);
                    return true;
                }
                return false;
            }
        });
        mBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = Objects.requireNonNull(mBinding.editSearch.getText()).toString().trim();
                if(TextUtils.isEmpty(search)){
                    ToastUtils.getInstance().show(getString(R.string.search));
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("k",search);
                startActivity(SearchActivity.class,bundle);
            }
        });
        mBinding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morePopup.showAsDropDown(v, 0, 25);
                morePopup.setItemListener(new MorePopup.OnItemListener() {
                    @Override
                    public void answerClick() {

                    }

                    @Override
                    public void projectClick() {

                    }

                    @Override
                    public void publicClick() {

                    }
                });
            }
        });
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.scrollView.fling(0);
                mBinding.scrollView.smoothScrollTo(0, 0);
            }
        });
        mBinding.scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (distance < -ViewConfiguration.get(getActivity()).getScaledTouchSlop() && !visible) {
                    //显示fab
                    mPresenter.showFABAnimation(mBinding.fab);
                    distance = 0;
                    visible = true;
                } else if (distance > ViewConfiguration.get(getActivity()).getScaledTouchSlop() && visible) {
                    //隐藏fab
                    mPresenter.hideFABAnimation(mBinding.fab);
                    distance = 0;
                    visible = false;
                }
                //向下滑并且可见  或者  向上滑并且不可见
                if (((scrollY-oldScrollY) > 0 && visible) || ((scrollY-oldScrollY) < 0 && !visible)){
                    distance += (scrollY-oldScrollY);
                }
            }
        });
        mBinding.articleList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeArticleAdapter(new ArrayList<>());
        mBinding.articleList.setAdapter(adapter);
        mPresenter.getBanner(this);
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getBanner(getActivity());
            }
        });
        mBinding.refreshLayout.setOnLoadMoreListener(new com.scwang.smart.refresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.getHomeArticle(getActivity(), page);
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void getHotKey(List<Hotkey> hotkey) {

    }

    @Override
    public void getHotKeyFailed(String s) {

    }

    @Override
    public void getBanner(List<Banner> banner) {
        page = 0;
        mPresenter.getHomeArticle(getActivity(), page);
        mBinding.banner.setAdapter(new BannerImageAdapter<Banner>(banner) {
            @Override
            public void onBindView(BannerImageHolder holder, Banner data, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(data.getImagePath())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }

        }).addBannerLifecycleObserver(getActivity()).setBannerGalleryEffect(20, 10);
    }

    @Override
    public void getBannerFailed(String s) {
        mBinding.refreshLayout.finishRefresh(false);
        ToastUtils.getInstance().show(s);
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

    @Override
    public void onPause() {
        super.onPause();
        mBinding.editSearch.setText("");
        mBinding.editSearch.clearFocus();
        SoftKeyBoardUtils.getInstance(getActivity()).hideInputKeyboard(getActivity(),mBinding.editSearch);
    }
}
