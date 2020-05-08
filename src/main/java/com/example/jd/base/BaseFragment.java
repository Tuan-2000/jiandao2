package com.example.jd.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jd.R;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mPre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPre = initPre();
        mPre.AttachView(this);
        View inflate = inflater.inflate(getLayoutID(), container, false);
        initView(inflate);
        initData();
        initListener();
        return inflate;
    }

    protected abstract P initPre();

    protected abstract int getLayoutID();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView(View view);
}
