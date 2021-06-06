package com.wxd.wanandroidmvp.http;

import android.content.Context;
import android.view.View;

import com.rxjava.rxlife.RxLife;
import com.wxd.wanandroidmvp.base.BaseResponse;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.CollectArticle;
import com.wxd.wanandroidmvp.entity.CollectOutArticle;
import com.wxd.wanandroidmvp.entity.CollectWeb;
import com.wxd.wanandroidmvp.entity.Friend;
import com.wxd.wanandroidmvp.entity.HomeArticle;
import com.wxd.wanandroidmvp.entity.Hotkey;
import com.wxd.wanandroidmvp.entity.IntegralHistory;
import com.wxd.wanandroidmvp.entity.IntegralPersonal;
import com.wxd.wanandroidmvp.entity.IntegralRank;
import com.wxd.wanandroidmvp.entity.Login;
import com.wxd.wanandroidmvp.entity.Navigation;
import com.wxd.wanandroidmvp.entity.Project;
import com.wxd.wanandroidmvp.entity.ProjectArticle;
import com.wxd.wanandroidmvp.entity.ProjectList;
import com.wxd.wanandroidmvp.entity.Register;
import com.wxd.wanandroidmvp.entity.ShareUserArticle;
import com.wxd.wanandroidmvp.entity.Square;
import com.wxd.wanandroidmvp.entity.Todo;
import com.wxd.wanandroidmvp.entity.Tree;
import com.wxd.wanandroidmvp.entity.TreeArticle;
import com.wxd.wanandroidmvp.entity.WxArticle;
import com.wxd.wanandroidmvp.entity.WxArticleChapter;

import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 提交参数方式
 */
public class RequestHelper {

    /**
     * 登录
     */
    public static void login(LifecycleOwner owner, String username, String password, SingleObserver<BaseResponse<Login>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .login(username, password)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 注册
     */
    public static void register(LifecycleOwner owner, String username, String password, String rePassword, SingleObserver<BaseResponse<Register>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .register(username, password, rePassword)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 退出登录
     */
    public static void loginOut(LifecycleOwner owner, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .loginOut()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 首页文章
     */
    public static void homeArticle(LifecycleOwner owner, int page, SingleObserver<BaseResponse<HomeArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .homeArticle(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 置顶文章
     */
    public static void topArticle(LifecycleOwner owner, SingleObserver<BaseResponse<HomeArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .topArticle()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 首页轮播图
     */
    public static void banner(LifecycleOwner owner, SingleObserver<BaseResponse<List<Banner>>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .banner()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 搜索文章
     */
    public static void queryArticle(LifecycleOwner owner, int page, String k, SingleObserver<BaseResponse<HomeArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .queryArticle(page, k)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 搜索作者文章
     */
    public static void queryAuthorArticle(LifecycleOwner owner, int page, String author, SingleObserver<BaseResponse<HomeArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .queryAuthorArticle(page, author)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 热词
     */
    public static void hotKey(LifecycleOwner owner, SingleObserver<BaseResponse<List<Hotkey>>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .hotKey()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 常用网站
     */
    public static void friend(LifecycleOwner owner, SingleObserver<BaseResponse<Friend>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .friend()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 体系
     */
    public static void tree(LifecycleOwner owner, SingleObserver<BaseResponse<Tree>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .tree()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 体系文章
     */
    public static void treeArticle(LifecycleOwner owner, int page, String cid, SingleObserver<BaseResponse<TreeArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .treeArticle(page, cid)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 导航
     */
    public static void navigation(LifecycleOwner owner, SingleObserver<BaseResponse<Navigation>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .navigation()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 项目
     */
    public static void project(LifecycleOwner owner, SingleObserver<BaseResponse<Project>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .project()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 项目文章
     */
    public static void projectArticle(LifecycleOwner owner, int page, String cid, SingleObserver<BaseResponse<ProjectArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .projectArticle(page, cid)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 最新项目
     */
    public static void projectList(LifecycleOwner owner, int page, SingleObserver<BaseResponse<ProjectList>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .projectList(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 微信公众号列表
     */
    public static void wxArticle(LifecycleOwner owner, SingleObserver<BaseResponse<WxArticleChapter>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .wxArticle()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 微信公众号历史数据
     */
    public static void wxArticleHistory(LifecycleOwner owner, int page, int id, SingleObserver<BaseResponse<WxArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .wxArticleHistory(page, id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 微信公众号搜索数据
     */
    public static void wxArticleQuery(LifecycleOwner owner, int page, int id, String k, SingleObserver<BaseResponse<WxArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .wxArticleQuery(page, id, k)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 广场列表
     */
    public static void square(LifecycleOwner owner, int page, SingleObserver<BaseResponse<Square>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .square(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 分享人对应列表
     */
    public static void shareUserArticle(LifecycleOwner owner, int page, int id, SingleObserver<BaseResponse<ShareUserArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .shareUserArticle(page, id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 个人分享列表
     */
    public static void sharePersonalArticle(LifecycleOwner owner, int page, SingleObserver<BaseResponse<ShareUserArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .sharePersonalArticle(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 删除自己的文章
     */
    public static void deletePersonalArticle(LifecycleOwner owner, int id, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .deletePersonalArticle(id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 分享文章
     */
    public static void shareArticle(LifecycleOwner owner, String title, String link, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .shareArticle(title, link)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 问答
     */
    public static void wenda(LifecycleOwner owner, int page, SingleObserver<BaseResponse<HomeArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .wenda(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 收藏文章列表
     */
    public static void collectList(LifecycleOwner owner, int page, SingleObserver<BaseResponse<CollectArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .collectList(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 收藏站内文章
     */
    public static void collectInArticle(LifecycleOwner owner, int id, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .collectInArticle(id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 收藏站外文章
     */
    public static void collectOutArticle(LifecycleOwner owner, String title, String author, String link, SingleObserver<BaseResponse<CollectOutArticle>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .collectOutArticle(title, author, link)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 取消列表收藏
     */
    public static void cancelListCollect(LifecycleOwner owner, int id, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .cancelListCollect(id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 取消我的收藏
     */
    public static void cancelMyCollect(LifecycleOwner owner, int id, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .cancelMyCollect(id, "-1")
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 收藏网站列表
     */
    public static void collectWebList(LifecycleOwner owner, SingleObserver<BaseResponse<CollectWeb>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .collectWebList()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 收藏网站
     */
    public static void collectWeb(LifecycleOwner owner, String name, String link, SingleObserver<BaseResponse<CollectWeb>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .collectWeb(name, link)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 编辑收藏网站
     */
    public static void updateCollectWeb(LifecycleOwner owner, int id, String name, String link, SingleObserver<BaseResponse<CollectWeb>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .updateCollectWeb(id, name, link)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 删除收藏网站
     */
    public static void deleteCollectWeb(LifecycleOwner owner, int id, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .deleteCollectWeb(id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 积分排行榜
     */
    public static void integralRank(LifecycleOwner owner, int page, SingleObserver<BaseResponse<IntegralRank>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .integralRank(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 我的积分
     */
    public static void integralPersonal(LifecycleOwner owner, SingleObserver<BaseResponse<IntegralPersonal>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .integralPersonal()
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 积分获取记录
     */
    public static void integralHistory(LifecycleOwner owner, int page, SingleObserver<BaseResponse<IntegralHistory>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .integralHistory(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 获取todo列表
     */
    public static void todoList(LifecycleOwner owner, int page, SingleObserver<BaseResponse<Todo>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .todoList(page)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 添加todo
     */
    public static void addTodo(LifecycleOwner owner, Map<String, String> map, SingleObserver<BaseResponse<Todo.DatasBean>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .addTodo(map)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 更新todo
     */
    public static void updateTodo(LifecycleOwner owner, Map<String, String> map, SingleObserver<BaseResponse<Todo.DatasBean>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .updateTodo(map)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 更新todo 只更新status
     */
    public static void updateStatusTodo(LifecycleOwner owner, int id, int status, SingleObserver<BaseResponse<Todo.DatasBean>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .updateStatusTodo(id, status)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

    /**
     * 删除todo
     */
    public static void deleteTodo(LifecycleOwner owner, int id, SingleObserver<BaseResponse<Object>> singleObserver) {
        RetrofitHelper.getApiHelp()
                .deleteTodo(id)
                .compose(RxThreadHelper.singleIO2Main())
                .as(RxLife.as(owner))
                .subscribe(singleObserver);
    }

}
