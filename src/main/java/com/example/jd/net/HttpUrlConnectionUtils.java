package com.example.jd.net;

import java.util.HashMap;

public class HttpUrlConnectionUtils implements INetWork {
    public static volatile HttpUrlConnectionUtils httpUrlConnectionUtils;

    private HttpUrlConnectionUtils() {
    }

    public static HttpUrlConnectionUtils getInstance() {
        if (httpUrlConnectionUtils == null){
            synchronized (HttpUrlConnectionUtils.class){
                if (httpUrlConnectionUtils == null){
                    httpUrlConnectionUtils = new HttpUrlConnectionUtils();
                }
            }
        }
        return httpUrlConnectionUtils;
    }

    @Override
    public <T> void get(String url, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void get(String url, HashMap<String, String> hashMap, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> hashMap, INetCallBack<T> netCallBack) {

    }
}
