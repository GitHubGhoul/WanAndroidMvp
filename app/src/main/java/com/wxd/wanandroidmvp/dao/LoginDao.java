package com.wxd.wanandroidmvp.dao;

import com.wxd.wanandroidmvp.base.IBaseDao;
import com.wxd.wanandroidmvp.entity.Login;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface LoginDao extends IBaseDao<Login> {

    @Query("select * from Login")
    Single<Login> getLogin();

}
