package com.example.jd;

import com.example.jd.base.BasePresenter;
import com.example.jd.bean.RemBean;
import com.example.jd.net.INetCallBack;

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.IMainPresenter {
    private MainContract.IMainMode iMainMode;

    public MainPresenter() {
        iMainMode = new MainModel();
    }

    @Override
    public void getRecommendList() {
        iMainMode.getRecommendList(new INetCallBack<RemBean>() {
            @Override
            public void onSuccess(RemBean remBean) {
                
            }

            @Override
            public void OnError(Throwable throwable) {

            }
        });
    }
}
