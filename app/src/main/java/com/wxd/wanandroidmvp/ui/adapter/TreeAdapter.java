package com.wxd.wanandroidmvp.ui.adapter;

import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.wxd.wanandroidmvp.R;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Navigation;
import com.wxd.wanandroidmvp.entity.Tree;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TreeAdapter extends BaseQuickAdapter<Tree, BaseViewHolder>{

    public TreeAdapter(List<Tree> data) {
        super(R.layout.item_tree, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Tree tree) {
        baseViewHolder.setText(R.id.title, Html.fromHtml(tree.getName()));
        RecyclerView recyclerView = baseViewHolder.getView(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(getContext());
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        manager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        manager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        manager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new FlexBoxAdapter(getTags(tree)));
    }

    private List<String> getTags(Tree tree){
        List<String> tags = new ArrayList<>();
        for (Tree.ChildrenBean data:tree.getChildren()) {
            tags.add(data.getName());
        }
        return tags;
    }
}
