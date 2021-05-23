package com.wxd.wanandroidmvp.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.wxd.wanandroidmvp.R;
import com.wxd.wanandroidmvp.app.AppDatabase;
import com.wxd.wanandroidmvp.base.BaseDaoSingleObserver;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseSingleObserver;
import com.wxd.wanandroidmvp.base.BaseVBActivity;
import com.wxd.wanandroidmvp.dao.DaoHelper;
import com.wxd.wanandroidmvp.databinding.ActivityMainBinding;
import com.wxd.wanandroidmvp.entity.Login;
import com.wxd.wanandroidmvp.http.RequestHelper;
import com.wxd.wanandroidmvp.ui.fragment.DiscoverFragment;
import com.wxd.wanandroidmvp.ui.fragment.HomeFragment;
import com.wxd.wanandroidmvp.ui.fragment.MineFragment;
import com.wxd.wanandroidmvp.utils.LogUtils;
import com.wxd.wanandroidmvp.utils.ToastUtils;
import com.wxd.wanandroidmvp.view.LoadingDialog;

import java.util.concurrent.Callable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.functions.Action;

public class MainActivity extends BaseVBActivity<ActivityMainBinding, BasePresenter> {

    private HomeFragment homeFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;
    private FragmentTransaction transaction;
    private Fragment mFragment;//当前显示的Fragment
    private long exitTime = 0;

    @Override
    public BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void initData() {
        initializeBottomNavigationBar();
        initFragment();
    }

    private void initializeBottomNavigationBar() {
        //设置模式
        mBinding.bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //设置背景色样式
        mBinding.bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //添加颜色样式
        mBinding.bottomNavigationBar.setActiveColor(R.color.tab_active).setInActiveColor(R.color.tab_inactive);
        //添加tab
        mBinding.bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.tab_discover, "发现"))
                .addItem(new BottomNavigationItem(R.drawable.tab_mine, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

        mBinding.bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        switchFragment(homeFragment);
                        break;
                    case 1:
                        switchFragment(discoverFragment);
                        break;
                    case 2:
                        switchFragment(mineFragment);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        discoverFragment = new DiscoverFragment();
        mineFragment = new MineFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mFrameLayout, homeFragment)
                .commit();
        mFragment = homeFragment;
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.mFrameLayout,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.getInstance().show("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}