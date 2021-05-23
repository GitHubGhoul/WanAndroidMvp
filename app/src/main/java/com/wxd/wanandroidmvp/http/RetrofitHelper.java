package com.wxd.wanandroidmvp.http;

import android.text.TextUtils;

import com.wxd.wanandroidmvp.app.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求封装
 */
public class RetrofitHelper {

    private static final String TAG = "RetrofitHelper";
    //基础url 必须以"/"结尾
    public static final String BASE_URL = "https://www.wanandroid.com/";
    //读超时长
    public static final int READ_TIME_OUT = 5;
    //连接时长
    public static final int CONNECT_TIME_OUT = 5;
    //写超时长
    public static final int WRITE_TIME_OUT = 5;

    private volatile static ApiHelper mApiHelper;

    private RetrofitHelper() {
    }

    /**
     * 单例模式
     */
    public static ApiHelper getApiHelp() {
        if (mApiHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (mApiHelper == null) {
                    mApiHelper = new RetrofitHelper().getRetrofit();
                }
            }
        }
        return mApiHelper;
    }

    public ApiHelper getRetrofit() {
        // 初始化Retrofit
        ApiHelper apiHelper = initRetrofit(initOkHttp()).create(ApiHelper.class);
        return apiHelper;
    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//根据需求更改日志级别
        // 缓存目录
        File file = new File(MyApplication.application.getCacheDir(), "cache");
        // 缓存大小
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(file, cacheSize);
        return new OkHttpClient().newBuilder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(logInterceptor)//添加打印拦截器
                .cache(cache)
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .hostnameVerifier((hostname, session) -> true)//https支持
                .build();
    }

    /**
     * 通过拦截器添加请求头
     * 如果知道具体请求头参数可直接添加
     */
    private static class HeaderInterceptor implements Interceptor {
        //初始化Map集合
        Map<String, String> mParentMap;

        public HeaderInterceptor(Map<String, String> parentMap) {
            mParentMap = parentMap;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            //拿到request
            Request requestFu = chain.request();
            //用request获取网址
            String url = requestFu.url().toString();
            //判断集合是否非空
            if (mParentMap != null && mParentMap.size() > 0) {
                Request.Builder builder = requestFu.newBuilder();
                for (String user : mParentMap.keySet()) {
                    //添加头部参数
                    builder.addHeader(user, mParentMap.get(user));
                }

                Request build = builder.url(url).build();
                return chain.proceed(build);
            }
           /* Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .build();*/
            return chain.proceed(requestFu);

        }
    }

    /**
     * 通过拦截器添加公共请求参数 如果不需要可暂时不添加
     */
    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request.method().equals("POST")) {
                //这里分两种post提交方式
                // FormBoy:以表单方式提交  MultipartBody:是有多个参数(其中包括文件)
                if (request.body() instanceof FormBody) {
                    request = addPostFormParams(request);
                } else if (request.body() instanceof MultipartBody) {
                    request = addPostMultiParams(request);
                }
            } else if (request.method().equals("GET")) {
                request = addGetParams(chain);
            }
            return chain.proceed(request);
        }
    }

    //post附带图片时
    private static Request addPostMultiParams(Request request) {
        // 添加公共参数
        MultipartBody.Builder builder = new MultipartBody.Builder().addFormDataPart("deviceId", "123456");
        MultipartBody oldBody = (MultipartBody) request.body();
        for (int i = 0; i < oldBody.size(); i++) {
            builder.addPart(oldBody.part(i));
        }
        oldBody = builder.build();
        request = request.newBuilder().post(oldBody).build();
        return request;
    }

    //post参数时
    private static Request addPostFormParams(Request request) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        FormBody formBody = (FormBody) request.body();
        //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
        for (int i = 0; i < formBody.size(); i++) {
            bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
        }
        //添加公共参数
        formBody = bodyBuilder
                .addEncoded("deviceId", "123456").build();
        request = request.newBuilder().post(formBody).build();
        return request;
    }

    private static Request addGetParams(Interceptor.Chain chain) {
        Request request;
        Request oldRequest = chain.request();
        // 添加公共参数
        HttpUrl authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .addQueryParameter("deviceId", "123456").build();
        request = oldRequest.newBuilder()
                .url(authorizedUrlBuilder)
                .build();
        return request;
    }

    /**
     * 缓存策略 可根据需求决定是否添加
     */
    private static class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response originResponse = chain.proceed(request);
            if (!TextUtils.isEmpty(request.header("Cache-Control"))) {
                originResponse = originResponse.newBuilder()
                        .removeHeader("pragma")
                        .header("Cache-Control", request.header("Cache-Control"))
                        .build();
            } else {
                //设置响应的缓存时间为60秒，即设置Cache-Control头，并移除pragma消息头，因为pragma也是控制缓存的一个消息头属性
                originResponse = originResponse.newBuilder()
                        .removeHeader("pragma")
                        .header("Cache-Control", "max-age=60")
                        .build();
            }
            return originResponse;
        }
    }
}
