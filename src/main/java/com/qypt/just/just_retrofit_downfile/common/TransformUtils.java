package com.qypt.just.just_retrofit_downfile.common;

/**
 * Created by Administrator on 2016/7/9.
 */
public final  class TransformUtils {

    public String onTransformMB(long size){
        return String.valueOf(((float)size)/1024f/1024f);
    }

}
