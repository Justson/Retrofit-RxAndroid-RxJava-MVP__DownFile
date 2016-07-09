package com.qypt.just.just_retrofit_downfile.common;

import com.qypt.just.just_retrofit_downfile.Model.ProressBean;
import com.qypt.just.just_retrofit_downfile.Model.TopBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Justson
 * Created by Administrator on 2016/7/8.
 */
public abstract class DownCallback implements Callback<ResponseBody> {

    private String dir;
    private String fileName;
    private CompositeSubscription compositeSubsctiption = new CompositeSubscription();

    public DownCallback(String dir, String fileName) {
        this.dir = dir;
        this.fileName = fileName;
        initObserver();
    }

    private void initObserver() {
        compositeSubsctiption.add(RxBus.getInstance().toBservable(ProressBean.class).
                onBackpressureBuffer().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ProressBean>() {
            @Override
            public void call(ProressBean proressBean) {
                onProgress(proressBean);
            }
        }));

    }

    protected abstract void onProgress(TopBean topBean);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        try {
            downFile(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downFile(Response<ResponseBody> response) throws Exception {
        ResponseBody responseBody = response.body();
        InputStream is = null;
        OutputStream os = null;
        int length = 0;
        byte[] by = new byte[1024];
        try {
            is = responseBody.byteStream();
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            File temFile = new File(dir, fileName);
            os = new FileOutputStream(temFile);
            while ((length = is.read(by)) != -1) {
                os.write(by, 0, length);
            }
            os.flush();
            onSucess();
        } finally {
            if (is != null)
                is.close();
            if (os != null)
                os.close();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
    }

    public void onSucess() {
        if (compositeSubsctiption != null && compositeSubsctiption.hasSubscriptions())
            compositeSubsctiption.unsubscribe();
    }
}
