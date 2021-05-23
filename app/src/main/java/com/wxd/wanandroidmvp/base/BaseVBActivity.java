package com.wxd.wanandroidmvp.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.wxd.wanandroidmvp.app.AppDatabase;
import com.wxd.wanandroidmvp.app.MyApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseVBActivity<VB extends ViewBinding,P extends BasePresenter> extends AppCompatActivity {

    protected VB mBinding;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type superclass = getClass().getGenericSuperclass();
        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        try {
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class);
            mBinding = (VB) method.invoke(null, getLayoutInflater());
            setContentView(mBinding.getRoot());
        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }
        //初始化mPresenter
        mPresenter = bindPresenter();
        //绑定生命周期
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
        getBundleExtras(getIntent().getExtras());
        initData();
    }

    public VB getBinding(){
        return mBinding;
    }

    public abstract P bindPresenter();

    protected abstract void getBundleExtras(Bundle bundle);

    protected abstract void initData();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Class跳转并关闭界面
     **/
    public void startActivityFinish(Class<?> cls) {
        startActivityFinish(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转并关闭界面
     **/
    public void startActivityFinish(Class<?> cls, Bundle bundle) {
        startActivity(cls, bundle);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
