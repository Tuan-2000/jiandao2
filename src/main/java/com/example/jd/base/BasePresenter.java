package com.example.jd.base;

public class BasePresenter<V extends BaseView> {
    public V mView;

    public void AttachView(V baseView) {
        this.mView = baseView;
    }

    public void disAttachView(){
        this.mView = null;
    }

}
