package com.qypt.just.just_retrofit_downfile.common;

import android.util.Log;

import com.qypt.just.just_retrofit_downfile.Model.ProressBean;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * Created by Administrator on 2016/7/8.
 */
public class DownResponseBody extends ResponseBody {
    private Response response;
    public DownResponseBody(Response response){
        this.response=response;
    }
    @Override
    public MediaType contentType() {
        return response.body().contentType();
    }

    @Override
    public long contentLength() {
        return response.body().contentLength();
    }

    @Override
    public BufferedSource source() {
        Log.i("MainActivity","source:");
        return Okio.buffer(new ForwardingSource(response.body().source()) {
            long readed=0;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {

               long read=super.read(sink,byteCount);
                readed+=read==-1?0:read;
                RxBus.getInstance().onPost(new ProressBean(readed,contentLength()));
                Log.i("MainActivity","send:"+readed);
                return read;
            }
        });
    }


}
