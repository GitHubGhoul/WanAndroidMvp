package com.wxd.wanandroidmvp.ui.adapter;

import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wxd.wanandroidmvp.R;
import com.wxd.wanandroidmvp.entity.HomeArticle;

import java.util.List;

public class HomeArticleAdapter extends BaseQuickAdapter<HomeArticle.DatasBean, BaseViewHolder> implements LoadMoreModule {

    public HomeArticleAdapter(List<HomeArticle.DatasBean> data) {
        super(R.layout.item_home_article, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeArticle.DatasBean datasBean) {
        baseViewHolder.setText(R.id.author, !TextUtils.isEmpty(datasBean.getAuthor()) ? datasBean.getAuthor() : datasBean.getShareUser());
        baseViewHolder.setGone(R.id.top, (datasBean.getType() == 0));
        baseViewHolder.setGone(R.id.fresh, !datasBean.isFresh());
        baseViewHolder.setGone(R.id.tag, datasBean.getTags().size() == 0);
        baseViewHolder.setText(R.id.tag, datasBean.getTags().size() != 0 ? datasBean.getTags().get(0).getName() : "未知");
        baseViewHolder.setText(R.id.date, datasBean.getNiceDate());
        baseViewHolder.setText(R.id.title, Html.fromHtml(datasBean.getTitle()));
        baseViewHolder.setText(R.id.label, datasBean.getSuperChapterName());
    }
}
