package com.qypt.just.just_retrofit_downfile.Presenter;

import com.qypt.just.just_retrofit_downfile.IView;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface IPresenter {

   <T extends IView>   void onAttach(T t);
   <T extends IView> void dispath(T t);
}
