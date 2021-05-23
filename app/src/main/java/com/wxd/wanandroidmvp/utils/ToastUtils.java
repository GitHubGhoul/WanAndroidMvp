package com.wxd.wanandroidmvp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wxd.wanandroidmvp.app.MyApplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;

public class ToastUtils {

    private volatile static ToastUtils toastUtils;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private Toast toast;
    private Context context;
    private View rootView;
    private LinearLayout bglayout;
    private TextView mTvToast;
    private ImageView toastImg;
    private float textSize = -1f;//字体大小
    private int textColorId = -1;//字体颜色id
    private int gravity = Gravity.BOTTOM;//显示位置
    private int xOffset = 0;//x偏移量
    private int yOffset = 80;//y偏移量
    private int imgResId = -1;//图片资源id
    private int duration = Toast.LENGTH_SHORT;//显示时间
    private int roundRadius = -1;//背景圆角
    private int bgColor = 0;//背景颜色
    private boolean customView = false;//是否是自定义view
    private int viewDirection = LinearLayout.HORIZONTAL;//图标文字显示方式
    private long cancelTime = -1;

    private ToastUtils() {
        this.context = MyApplication.application;

    }

    public static ToastUtils getInstance() {
        if (toastUtils == null) {
            synchronized (ToastUtils.class) {
                if (toastUtils == null) {
                    toastUtils = new ToastUtils();
                }
            }
        }
        return toastUtils;
    }

    /**
     * 长时间显示Toast
     *
     * @param objMsg
     */
    public ToastUtils showLong(Object objMsg) {
        duration = Toast.LENGTH_LONG;
        show(objMsg);
        return this;
    }

    /**
     * 短时间显示Toast
     *
     * @param objMsg
     */
    public ToastUtils show(final Object objMsg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (ToastUtils.class) {
                    cancelCurrentToast();
                    String msg = "";
                    if (objMsg instanceof String) {
                        msg = objMsg.toString();
                    } else {
                        msg = new Gson().toJson(objMsg);
                    }
                    if (customView) {
                        toast = new Toast(context);
                        setLayoutConf(msg);
                        toast.setView(rootView);//设置自定义的view
                        toast.setGravity(gravity, xOffset, yOffset);
                    } else {
                        toast = Toast.makeText(context.getApplicationContext(), msg, duration);
                    }
                    toast.show();
                    if (cancelTime != -1) {
                        cancelCurrentToast(cancelTime);
                    }
                    customView = false;
                    reset();

                }
            }
        });
        return this;
    }

    /**
     * 配置view样式
     */
    private void setLayoutConf(String msg) {
        rootView = LayoutInflater.from(context.getApplicationContext()).inflate(getLayoutResources("f_toast_layout"), null);//自定义样式，自定义布局文件
        bglayout = (LinearLayout) rootView.findViewById(
                getIdResources("f_toast_bg"));
        mTvToast = (TextView) rootView.findViewById(
                getIdResources("f_toast_tv"));
        toastImg = (ImageView) rootView.findViewById(
                getIdResources("f_toast_iv"));

        LinearLayout.LayoutParams linearParams = null;
        LinearLayout.LayoutParams imgParams = null;
        LinearLayout.LayoutParams tvParams = null;
        GradientDrawable gd = new GradientDrawable();// 创建drawable
        gd.setCornerRadius(roundRadius == -1 ? dip2px(8) : dip2px(roundRadius));
        int fillColor = bgColor == 0 ? getColorResources("toastbg") : bgColor;// 内部填充颜色
        gd.setColor(ContextCompat.getColor(context, fillColor));//添加背景颜色
        bglayout.setBackground(gd);
        bglayout.setOrientation(viewDirection);
        if (viewDirection == LinearLayout.HORIZONTAL) {
            linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            bglayout.setGravity(Gravity.CENTER_HORIZONTAL);
        } else {

            linearParams = new LinearLayout.LayoutParams(dip2px(162), dip2px(110));
            bglayout.setGravity(Gravity.CENTER);

        }
        bglayout.setLayoutParams(linearParams);
        if (imgResId != -1) {
            if (viewDirection == LinearLayout.HORIZONTAL) {
                imgParams = new LinearLayout.LayoutParams(dip2px(32), dip2px(32));
                imgParams.setMargins(0, 0, dip2px(10), 0);
            } else {
                imgParams = new LinearLayout.LayoutParams(dip2px(48), dip2px(48));
                imgParams.setMargins(0, 0, 0, dip2px(6));
            }
            toastImg.setLayoutParams(imgParams);
            toastImg.setBackgroundResource(imgResId);
            toastImg.setVisibility(View.VISIBLE);
        } else {
            toastImg.setVisibility(View.GONE);
        }

        tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (viewDirection == LinearLayout.HORIZONTAL) {
            tvParams.gravity = Gravity.CENTER_VERTICAL;
        } else {
            tvParams.gravity = Gravity.CENTER_HORIZONTAL;
        }
        mTvToast.setTextSize(textSize != -1 ? textSize : 14);
        mTvToast.setTextColor(textColorId != -1 ? textColorId : Color.WHITE);
        mTvToast.setLayoutParams(tvParams);
        if (gravity == Gravity.CENTER) {
            if (yOffset == 80) {
                yOffset = 0;
            }
        }
        mTvToast.setText(msg);
    }

    /**
     * 关闭toast
     */
    public void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
            rootView = null;
            toast = null;
        }
    }

    /**
     * 多少秒关闭toast
     */
    public void cancelCurrentToast(long time) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                    rootView = null;
                    toast = null;
                }
            }
        }, time);

    }


    /**
     * 设置字体大小
     *
     * @param textSize
     * @return
     */
    public ToastUtils setTextSize(float textSize) {
        this.customView = true;
        this.textSize = textSize;
        return this;

    }

    /**
     * 设置字体颜色
     *
     * @param textColorId
     * @return
     */
    public ToastUtils setTextColor(@ColorRes int textColorId) {
        this.customView = true;
        this.textColorId = textColorId;
        return this;

    }

    /**
     * 显示位置
     *
     * @param gravity
     * @return
     */
    public ToastUtils setGravity(int gravity) {
        this.customView = true;
        this.gravity = gravity;
        return this;
    }

    /**
     * x偏移量
     *
     * @param xOffset
     * @return
     */
    public ToastUtils setXOffset(int xOffset) {
        this.customView = true;
        this.xOffset = xOffset;
        return this;
    }

    /**
     * y偏移量
     *
     * @param yOffset
     * @return
     */
    public ToastUtils setYOffset(int yOffset) {
        this.customView = true;
        this.yOffset = yOffset;
        return this;
    }

    /**
     * 设置图片资源id
     *
     * @param imgResId
     * @return
     */
    public ToastUtils setImgResId(@DrawableRes int imgResId) {
        this.customView = true;
        this.imgResId = imgResId;
        return this;
    }

    /**
     * 自定义关闭时间
     *
     * @param cancelTime
     * @return
     */
    public ToastUtils setDuration(long cancelTime) {
        this.cancelTime = cancelTime;
        return this;
    }


    /**
     * 设置圆角大小
     *
     * @param roundRadius
     * @return
     */
    public ToastUtils setRoundRadius(int roundRadius) {
        this.customView = true;
        this.roundRadius = roundRadius;
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param bgColor
     * @return
     */
    public ToastUtils setBgColor(@ColorRes int bgColor) {
        this.customView = true;
        this.bgColor = bgColor;
        return this;
    }

    /**
     * 设置图片文字显示方向 横向，纵向
     *
     * @param viewDirection
     * @return
     */
    public ToastUtils setDirection(@Direction int viewDirection) {
        this.customView = true;
        this.viewDirection = viewDirection;
        return this;
    }

    private void reset() {
        textSize = -1f;//字体大小
        textColorId = -1;//字体颜色id
        gravity = Gravity.BOTTOM;//显示位置
        xOffset = 0;//x偏移量
        yOffset = 80;//y偏移量
        imgResId = -1;//图片资源id
        duration = Toast.LENGTH_SHORT;//显示时间
        roundRadius = -1;//背景圆角
        bgColor = 0;//背景颜色
        viewDirection = LinearLayout.HORIZONTAL;
        cancelTime = -1;
    }

    @IntDef({LinearLayout.HORIZONTAL, LinearLayout.VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(float dipValue) {
        final float scale = MyApplication.application.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int getIdResources(String idName) {
        Resources res = MyApplication.application.getResources();
        return res.getIdentifier(idName, "id", MyApplication.application.getPackageName());
    }

    public static int getColorResources(String colorName) {
        Resources res = MyApplication.application.getResources();
        return res.getIdentifier(colorName, "color", MyApplication.application.getPackageName());
    }

    public static int getLayoutResources(String LayoutName) {
        Resources res = MyApplication.application.getResources();
        return res.getIdentifier(LayoutName, "layout", MyApplication.application.getPackageName());
    }

}
