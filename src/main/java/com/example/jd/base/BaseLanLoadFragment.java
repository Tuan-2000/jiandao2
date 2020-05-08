package com.example.jd.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLanLoadFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mPre;
    public boolean isDataLoaded = false;//请求数据
    private boolean isViewCreated = false;//页面创建

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        mPre = initPre();
        mPre.AttachView(this);
        View inflate = inflater.inflate(getLayoutID(), container, false);
        initView(inflate);
        initListener();
        return inflate;
    }

    protected abstract P initPre();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        isVisibleToUser   是否可见
       if (isVisibleToUser == true && isViewCreated == true && isDataLoaded == false){
           initData();
       }
        super.setUserVisibleHint(isVisibleToUser);
    }

    protected abstract int getLayoutID();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView(View view);
}
