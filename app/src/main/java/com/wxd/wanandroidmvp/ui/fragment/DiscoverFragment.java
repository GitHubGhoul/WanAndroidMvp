package com.wxd.wanandroidmvp.ui.fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wxd.wanandroidmvp.base.BasePresenter;
import com.wxd.wanandroidmvp.base.BaseVBFragment;
import com.wxd.wanandroidmvp.databinding.FragmentDiscoverBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DiscoverFragment extends BaseVBFragment<FragmentDiscoverBinding, BasePresenter> {
    
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = Arrays.asList("广场", "体系", "导航");


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void init() {
        fragments.add(new SquareFragment());
        fragments.add(new TreeFragment());
        fragments.add(new NavigationFragment());
        mBinding.viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }
}
