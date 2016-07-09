package com.qypt.just.just_retrofit_downfile.common;

import android.util.Log;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/7/8.
 */
public class RetrofitManager {
    private static Hashtable<String, Retrofit> table;
    static {
        table = new Hashtable<>();
    }


    public static Retrofit getRetrofitByUrl(String url){
       Retrofit retrofit= table.get(url);
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(url).client(getOkHttpClient()).build();
        }
        return  retrofit;
    }


    //加入拦截器， 对网络进行拦截， 重构请求， 然后传入请求体
    public static OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(50000, TimeUnit.MILLISECONDS)
//        .readTimeout(2000000,TimeUnit.MILLISECONDS)
        .networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.i("MainActivity","intercept");
                Response response=chain.proceed(chain.request());
                return response.newBuilder().body(new DownResponseBody(response)).build();
            }
        });
        Log.i("MainActivity","getOkHttpClient");
        return builder.build();
    }

}
