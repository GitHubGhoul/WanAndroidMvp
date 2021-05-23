package com.wxd.wanandroidmvp.http;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * 异常统一处理类
 */
public class RxExceptionUtil {

    public static String exceptionHandler(Throwable e){
        String errorMsg = "数据加载失败，请检查网络后重试";
        //此处的确是域名错误，但是显示不友好
        if (e instanceof UnknownHostException) {
            errorMsg = "网络异常，请检查或更换网络后重试!";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "请求服务器超时，请检查网络后重试";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException) {
            errorMsg = "数据解析错误，请检查网络后重试";
        }
        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错，请联系管理员";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求，请联系管理员";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求被重定向到其他页面，请联系管理员";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
