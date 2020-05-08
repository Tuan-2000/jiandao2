package com.example.jd.topic.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jd.R;
import com.example.jd.base.BaseFragment;
import com.example.jd.bean.TopicBean;
import com.example.jd.topic.adapter.TopicAdapter;
import com.example.jd.topic.contract.TopicContract;
import com.example.jd.topic.presenter.TopicPresenter;

import java.util.List;

public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.ITopicView {
    private int start = 0;
    private int number = 0;
    private int point_time = 0;
    private RecyclerView mRv;
    private TopicAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_topic;
    }

    @Override
    protected TopicPresenter initPre() {
        return new TopicPresenter();
    }

    @Override
    protected void initView(View itemView) {
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new TopicAdapter(getActivity());
        mRv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPre.getTopicData(start, number, point_time);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getTopicData(TopicBean bean) {
        List<TopicBean.DataBean.ListBean> title_list = bean.getData().getList();
        List<TopicBean.DataBean.BannerListBean> banner_list = bean.getData().getBanner_list();
        adapter.setTitleList(title_list);
        adapter.setBannerList(banner_list);
    }
}
