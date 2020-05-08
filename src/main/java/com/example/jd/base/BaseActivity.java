package com.example.jd.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    public P mPre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPre = initPresenter();
        if (mPre != null) {
            mPre.AttachView(this);
        }
        initView();
        initData();
        initListener();
    }

    protected abstract P initPresenter();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPre.disAttachView();
    }
}
