package com.qypt.just.just_retrofit_downfile.Model;

/**
 * Created by Administrator on 2016/7/9.
 */
public class DownResultBean implements TopBean {
    public DownResultBean(Object contetResult, int resultCode) {
        this.contetResult = contetResult;
        this.resultCode = resultCode;
    }

    public Object getContetResult() {

        return contetResult;
    }

    public void setContetResult(Object contetResult) {
        this.contetResult = contetResult;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    private int resultCode;
    private Object contetResult;

}
