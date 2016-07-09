package com.qypt.just.just_retrofit_downfile.Model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ProressBean implements TopBean, Serializable {
    private boolean isUpdate = true;

    public ProressBean() {

    }

    public ProressBean(long current, boolean isUpdate, long length) {
        this.current = current;
        this.isUpdate = isUpdate;
        this.length = length;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    private long current;

    public long getCurrent() {
        return current;
    }

    public ProressBean(long current, long length) {
        this.current = current;
        this.length = length;
    }

    public long getLength() {

        return length;
    }

    private long length;
}
