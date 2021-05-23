package com.wxd.wanandroidmvp.base;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

@Dao
public interface IBaseDao<T> {

    @Insert
    Long insertItem(T item);//插入单条数据

    @Insert
    List<Long> insertItems(List<T> items);//插入list数据

    @Insert
    Single<Long> insertItemSingle(T item);//插入单条数据

    @Insert
    Single<List<Long>> insertItemsSingle(List<T> items);//插入list数据

    @Update
    Integer updateItem(T item);//更新单条数据

    @Update
    void updateItems(List<T> items);//更新list数据

    @Update
    Single<Integer> updateItemSingle(T item);//更新单条数据

    @Update
    void updateItemsSingle(List<T> items);//更新list数据

    @Delete
    Integer deleteItem(T item);//删除单条

    @Delete
    void deleteItems(List<T> items);//删除list数据

    @Delete
    Single<Integer> deleteItemSingle(T item);//删除单条

    @Delete
    void deleteItemsSingle(List<T> items);//删除list数据

}
