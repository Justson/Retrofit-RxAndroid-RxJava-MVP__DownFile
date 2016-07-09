package com.qypt.just.just_retrofit_downfile.Model;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface IModel<T extends TopBean> {

    void downFile(T t);
}
