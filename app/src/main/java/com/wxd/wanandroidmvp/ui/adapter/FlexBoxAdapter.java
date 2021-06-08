package com.wxd.wanandroidmvp.ui.adapter;

import android.graphics.Color;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wxd.wanandroidmvp.R;
import com.wxd.wanandroidmvp.entity.Navigation;

import java.util.List;
import java.util.Random;

public class FlexBoxAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FlexBoxAdapter(List<String> data) {
        super(R.layout.item_flex_box, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String item) {
        baseViewHolder.setText(R.id.tag,item);
        baseViewHolder.setTextColor(R.id.tag, randomColor());
    }

    /**
     * 获取随机rgb颜色值
     */
    private int randomColor(){
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red = random.nextInt(190);
        int green = random.nextInt(190);
        int blue = random.nextInt(190);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green,blue);
    }
}
