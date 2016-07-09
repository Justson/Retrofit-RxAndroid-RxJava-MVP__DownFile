package com.qypt.just.just_retrofit_downfile;

import com.qypt.just.just_retrofit_downfile.Model.TopBean;

/**
 * Created by Administrator on 2016/7/9.
 */
public interface IView {

    void onShow();

    void onHide();

    <T extends TopBean> void update(T t);
}
