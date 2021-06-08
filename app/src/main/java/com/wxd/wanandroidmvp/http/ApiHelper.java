package com.wxd.wanandroidmvp.http;

import com.wxd.wanandroidmvp.base.BaseResponse;
import com.wxd.wanandroidmvp.entity.Banner;
import com.wxd.wanandroidmvp.entity.CollectOutArticle;
import com.wxd.wanandroidmvp.entity.CollectArticle;
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
import com.wxd.wanandroidmvp.entity.WxArticleChapter;
import com.wxd.wanandroidmvp.entity.WxArticle;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 请求接口
 */
public interface ApiHelper {

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Single<BaseResponse<Login>> login(@Field("username") String username, @Field("password") String password);

    //注册
    @POST("user/register")
    @FormUrlEncoded
    Single<BaseResponse<Register>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    //退出登录
    @GET("user/logout/json")
    Single<BaseResponse<Object>> loginOut();

    //首页文章列表
    @GET("article/list/{page}/json")
    Single<BaseResponse<HomeArticle>> homeArticle(@Path("page") int page);

    //置顶文章列表
    @GET("article/top/json")
    Single<BaseResponse<HomeArticle>> topArticle();

    //首页轮播图
    @GET("banner/json")
    Single<BaseResponse<List<Banner>>> banner();

    //搜索文章
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Single<BaseResponse<HomeArticle>> queryArticle(@Path("page")int page, @Field("k") String k);

    //搜索作者文章
    @GET("article/query/{page}/json")
    Single<BaseResponse<HomeArticle>> queryAuthorArticle(@Path("page")int page, @Query("author") String author);

    //热词
    @GET("hotkey/json")
    Single<BaseResponse<List<Hotkey>>> hotKey();

    //常用网站
    @GET("friend/json")
    Single<BaseResponse<Friend>> friend();

    //体系
    @GET("tree/json")
    Single<BaseResponse<List<Tree>>> tree();

    //体系下文章
    @GET("article/list/{page}/json")
    Single<BaseResponse<TreeArticle>> treeArticle(@Path("page")int page, @Query("cid") String cid);

    //导航
    @GET("navi/json")
    Single<BaseResponse<List<Navigation>>> navigation();

    //项目分类
    @GET("project/tree/json")
    Single<BaseResponse<Project>> project();

    //项目下文章
    @GET("article/list/{page}/json")
    Single<BaseResponse<ProjectArticle>> projectArticle(@Path("page")int page, @Query("cid") String cid);

    //最新项目
    @GET("article/listproject/{page}/json")
    Single<BaseResponse<ProjectList>> projectList(@Path("page")int page);

    //微信公众号列表
    @GET("wxarticle/chapters/json")
    Single<BaseResponse<WxArticleChapter>> wxArticle();

    //微信公众号历史数据
    @GET("wxarticle/list/{id}/{page}/json")
    Single<BaseResponse<WxArticle>> wxArticleHistory(@Path("page")int page, @Path("id")int id);

    //微信公众号搜索数据
    @GET("wxarticle/list/{id}/{page}/json")
    Single<BaseResponse<WxArticle>> wxArticleQuery(@Path("page")int page, @Path("id")int id, @Query("k") String k);

    //广场列表
    @GET("user_article/list/{page}/json")
    Single<BaseResponse<HomeArticle>> square(@Path("page")int page);

    //分享人对应列表
    @GET("user/{id}/share_articles/{page}/json")
    Single<BaseResponse<ShareUserArticle>> shareUserArticle(@Path("page")int page, @Path("id")int id);

    //自己分享的文章列表
    @GET("user/lg/private_articles/{page}/json")
    Single<BaseResponse<ShareUserArticle>> sharePersonalArticle(@Path("page")int page);

    //删除自己分享的文章
    @POST("lg/user_article/delete/{id}/json")
    Single<BaseResponse<Object>> deletePersonalArticle(@Path("id")int id);

    //分享文章
    @POST("lg/user_article/add/json")
    @FormUrlEncoded
    Single<BaseResponse<Object>> shareArticle(@Field("title") String title, @Field("link") String link);

    //问答
    @GET("wenda/list/{page}/json")
    Single<BaseResponse<HomeArticle>> wenda(@Path("page")int page);

    //收藏列表
    @GET("lg/collect/list/{page}/json")
    Single<BaseResponse<CollectArticle>> collectList(@Path("page")int page);

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    Single<BaseResponse<Object>> collectInArticle(@Path("id")int id);

    //收藏站外文章
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Single<BaseResponse<CollectOutArticle>> collectOutArticle(@Field("title") String title, @Field("author") String author, @Field("link") String link);

    //取消列表收藏
    @POST("lg/uncollect_originId/{id}/json")
    Single<BaseResponse<Object>> cancelListCollect(@Path("id")int id);

    //取消我的收藏
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Single<BaseResponse<Object>> cancelMyCollect(@Path("id")int id, @Field("originId") String originId);

    //收藏网站列表
    @GET("lg/collect/usertools/json")
    Single<BaseResponse<CollectWeb>> collectWebList();

    //收藏网站
    @POST("lg/collect/addtool/json")
    @FormUrlEncoded
    Single<BaseResponse<CollectWeb>> collectWeb(@Field("name") String name, @Field("link") String link);

    //编辑收藏网站
    @POST("lg/collect/updatetool/json")
    @FormUrlEncoded
    Single<BaseResponse<CollectWeb>> updateCollectWeb(@Field("id") int id, @Field("name") String name, @Field("link") String link);

    //删除收藏网站
    @POST("lg/collect/deletetool/json")
    @FormUrlEncoded
    Single<BaseResponse<Object>> deleteCollectWeb(@Field("id") int id);

    //积分排行榜
    @GET("coin/rank/{page}/json")
    Single<BaseResponse<IntegralRank>> integralRank(@Path("page")int page);

    //获取个人积分
    @GET("lg/coin/userinfo/json")
    Single<BaseResponse<IntegralPersonal>> integralPersonal();

    //积分获取记录
    @GET("lg/coin/list/{page}/json")
    Single<BaseResponse<IntegralHistory>> integralHistory(@Path("page")int page);

    //获取todo列表
    @GET("lg/todo/v2/list/{page}/json")
    Single<BaseResponse<Todo>> todoList(@Path("page")int page);

    //添加todo
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    Single<BaseResponse<Todo.DatasBean>> addTodo(@FieldMap Map<String, String> map);

    //更新todo
    @POST("lg/todo/update/0/json")
    @FormUrlEncoded
    Single<BaseResponse<Todo.DatasBean>> updateTodo(@FieldMap Map<String, String> map);

    //只更新todo status
    @POST("lg/todo/done/{id}/json")
    @FormUrlEncoded
    Single<BaseResponse<Todo.DatasBean>> updateStatusTodo(@Path("id")int id, @Field("status") int status);

    //删除todo
    @POST("lg/todo/delete/{id}/json")
    Single<BaseResponse<Object>> deleteTodo(@Path("id")int id);
}