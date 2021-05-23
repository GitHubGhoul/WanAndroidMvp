package com.wxd.wanandroidmvp.dao;

import com.rxjava.rxlife.RxLife;
import com.wxd.wanandroidmvp.app.AppDatabase;
import com.wxd.wanandroidmvp.base.BaseResponse;
import com.wxd.wanandroidmvp.entity.Login;
import com.wxd.wanandroidmvp.http.RetrofitHelper;
import com.wxd.wanandroidmvp.http.RxThreadHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import androidx.lifecycle.LifecycleOwner;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DaoHelper {

    public static void comFromAction(Action action) {
        Completable.fromAction(action)
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public static void comFromCallable(Callable<Object> callable) {
        Completable.fromCallable(callable)
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public static void singleFromFuture(Future<Object> future) {
        Single.fromFuture(future)
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public static void singleFromCallable(Callable<Object> callable) {
        Single.fromCallable(callable)
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public static void maybeFromAction(Action action) {
        Maybe.fromAction(action)
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public static void maybeFromCallable(Callable<Object> callable) {
        Maybe.fromCallable(callable)
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    /**
     * 保存登录数据登录
     */
    public static void setLogin(Login login,SingleObserver<Long> singleObserver) {
        AppDatabase.getInstance()
                .getLoginDao()
                .insertItemSingle(login)
                .compose(RxThreadHelper.singleIO2Main())
                .subscribe(singleObserver);
    }

    /**
     * 获取登录数据
     */
    public static void getLogin(SingleObserver<Login> singleObserver) {
        AppDatabase.getInstance()
                .getLoginDao()
                .getLogin()
                .compose(RxThreadHelper.singleIO2Main())
                .subscribe(singleObserver);
    }
}
