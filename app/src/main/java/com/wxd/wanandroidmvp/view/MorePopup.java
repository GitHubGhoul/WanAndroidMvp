package com.wxd.wanandroidmvp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.wxd.wanandroidmvp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MorePopup {

    private static volatile MorePopup instance;
    private OnItemListener itemListener;
    private final PopupWindow window;
    private Activity activity;

    public static MorePopup getInstance(Activity activity) {
        if (instance == null) {
            synchronized (MorePopup.class) {
                if (instance == null) {
                    instance = new MorePopup(activity);
                }
            }
        }
        return instance;
    }

    private MorePopup(Activity activity) {
        this.activity = activity;
        View view = LayoutInflater.from(activity).inflate(R.layout.popup_more, null);
        //设置PopupWindow的显示大小
        window = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);//设置popupwindow可以获取焦点
        //给pupupwindow设置一个背景,以便点击popupwindow外边时候可以实现消失
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);//设置点击外部消失
        ConstraintLayout answer = view.findViewById(R.id.answer);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.answerClick();
                }
                window.dismiss();
            }
        });
        ConstraintLayout pro = view.findViewById(R.id.project);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.projectClick();
                }
                window.dismiss();
            }
        });
        ConstraintLayout pub = view.findViewById(R.id.pub);
        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.publicClick();
                }
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //setBackgroundAlpha(1f);
            }
        });
    }

    public interface OnItemListener {
        void answerClick();

        void projectClick();

        void publicClick();
    }

    public void setItemListener(OnItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        window.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        window.showAtLocation(parent, gravity, x, y);
    }

}
