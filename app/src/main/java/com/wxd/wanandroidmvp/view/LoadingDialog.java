package com.wxd.wanandroidmvp.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wxd.wanandroidmvp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class LoadingDialog extends Dialog {

    public Context mContext;
    private String loadMsg;

    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.loading_dialog);
        this.mContext = context;
    }

    public LoadingDialog(@NonNull Context context,String msg) {
        super(context,R.style.loading_dialog);
        this.mContext = context;
        loadMsg = msg;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        TextView mTextVIew = findViewById(R.id.mTextView);
        mTextVIew.setText(loadMsg);
        setCanceledOnTouchOutside(false);
    }

}
