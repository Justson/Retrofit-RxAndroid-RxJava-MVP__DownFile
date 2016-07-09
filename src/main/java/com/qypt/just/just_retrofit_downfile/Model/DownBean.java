package com.qypt.just.just_retrofit_downfile.Model;

/**
 * Created by Administrator on 2016/7/9.
 */
public class DownBean implements TopBean {
    private String baseUrl;
    private String fileName;

    public DownBean(String baseUrl, String fileName, String lastUrl) {
        this.baseUrl = baseUrl;
        this.fileName = fileName;
        this.lastUrl = lastUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    private String lastUrl;
}
