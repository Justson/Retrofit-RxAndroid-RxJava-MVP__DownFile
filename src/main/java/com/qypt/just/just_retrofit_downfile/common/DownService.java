package com.qypt.just.just_retrofit_downfile.common;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface DownService {
    @GET("{lastUrl}")
    public Call<ResponseBody> onDownFile(@Path("lastUrl") String lastUrl);
}
