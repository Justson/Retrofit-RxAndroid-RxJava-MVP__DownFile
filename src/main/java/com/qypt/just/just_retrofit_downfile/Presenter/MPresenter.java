package com.qypt.just.just_retrofit_downfile.Presenter;

import com.qypt.just.just_retrofit_downfile.Model.TopBean;

/**
 * Created by Administrator on 2016/7/9.
 */
public interface MPresenter<T extends TopBean> {
    void dispatchTask(T t);

    <E> void onTaskResult(E e);
}
