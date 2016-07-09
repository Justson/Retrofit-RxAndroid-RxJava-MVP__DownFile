package com.qypt.just.just_retrofit_downfile.Presenter;

import com.qypt.just.just_retrofit_downfile.IView;
import com.qypt.just.just_retrofit_downfile.Model.DownModel;
import com.qypt.just.just_retrofit_downfile.Model.DownResultBean;
import com.qypt.just.just_retrofit_downfile.Model.ProressBean;
import com.qypt.just.just_retrofit_downfile.Model.TopBean;

/**
 * Created by Administrator on 2016/7/9.
 */
public class MainPresenter implements IPresenter, MPresenter {
    IView iView;
    private final DownModel downModel;

    public MainPresenter(){
        downModel = new DownModel(this);
    }
    @Override
    public <T extends IView> void onAttach(T t) {
        this.iView = t;
    }

    @Override
    public <T extends IView> void dispath(T t) {
        iView = null;
    }

    @Override
    public void dispatchTask(TopBean topBean) {

        if(iView!=null){
            iView.onShow();
        }if(downModel!=null){
            downModel.downFile(topBean);
        }
    }

    @Override
    public void onTaskResult(Object o) {
        if(iView!=null)
        {
            iView.onHide();
            iView.update((DownResultBean)o);
        }



    }
    public void onUpdate(ProressBean proressBean){
        if(iView!=null)
            iView.update(proressBean);
    }

    public void cancelTask(){
        if(downModel!=null){
            downModel.cancelDownLoad();
        }
    }
}
