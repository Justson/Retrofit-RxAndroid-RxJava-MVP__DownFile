package com.qypt.just.just_retrofit_downfile.Model;

import android.os.Environment;

import com.qypt.just.just_retrofit_downfile.Presenter.MainPresenter;
import com.qypt.just.just_retrofit_downfile.common.DownCallback;
import com.qypt.just.just_retrofit_downfile.common.DownService;
import com.qypt.just.just_retrofit_downfile.common.RetrofitManager;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2016/7/9.
 */
public class DownModel<T extends TopBean> implements IModel<T> {

    private MainPresenter mainPresenter;
    private String path;
    private Call<ResponseBody> call;

    public <E extends MainPresenter> DownModel(E t) {
        this.mainPresenter = t;
    }

    @Override
    public void downFile(T t) {

        if (t instanceof DownBean) {

            DownBean downBean = (DownBean) t;
            String baseUrl = downBean.getBaseUrl();
            String lastUrl = downBean.getLastUrl();
            DownService downService = RetrofitManager.getRetrofitByUrl(baseUrl).create(DownService.class);
            call = downService.onDownFile(lastUrl);
            call.enqueue(new DownCallback(getCachePath(), downBean.getFileName()) {
                @Override
                public void onSucess() {
                    super.onSucess();
                    mainPresenter.onTaskResult(new DownResultBean("下载成功...", 1));
                }

                @Override
                protected void onProgress(TopBean topBean) {
                    if (topBean instanceof ProressBean)
                        mainPresenter.onUpdate((ProressBean) topBean);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    super.onFailure(call, t);
                    mainPresenter.onTaskResult(new DownResultBean("下载失败...", 0));
                }
            });

        }


    }
    public void cancelDownLoad(){
        if(call!=null){
            call.cancel();
        }
    }

    public String getCachePath() {
        String result = this.path;

        if (result == null) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                result = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Retrofit";
                path = result;
            }
        }
        return result;
    }
}
