package com.example.jd.recommend.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jd.R;
import com.example.jd.base.BaseFragment;
import com.example.jd.bean.RecommendRecBean;
import com.example.jd.recommend.adapter.RecommendAdapter;
import com.example.jd.recommend.contract.RecommendRecContract;
import com.example.jd.recommend.presenter.RecommendRecPresenter;

import java.util.ArrayList;
import java.util.List;

public class RecommendRecFragment extends BaseFragment<RecommendRecPresenter> implements RecommendRecContract.IRecommendRecView {

    private RecyclerView mRv;
    private RecommendAdapter adapter;
    private String id;
    private int start = 0;
    private int number = 0;
    private int point_time = 0;
    //    private MyAdapter adapter;

    @Override
    protected RecommendRecPresenter initPre() {
        return new RecommendRecPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_recommend_recy;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPre.getRecommendRec(id,start+"",number+"",point_time+"");
//        isDataLoaded = true;
    }

    @Override
    protected void initView(View itemView) {
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        List<RecommendRecBean.ResultData> resultData = new ArrayList<>();
        adapter = new RecommendAdapter(getActivity());
        mRv.setAdapter(adapter);
    }

    @Override
    public void getRecommendRec(RecommendRecBean bean) {
        List<RecommendRecBean.DataBean.ArticleListBean> article_list = bean.getData().getArticle_list();
        List<RecommendRecBean.DataBean.BannerListBean> banner_list = bean.getData().getBanner_list();
        List<RecommendRecBean.DataBean.FlashListBean> flash_list = bean.getData().getFlash_list();
        start = bean.getData().getStart();
        number = bean.getData().getNumber();
        point_time = bean.getData().getPoint_time();

        adapter.setArticleList(article_list);
        adapter.setBannerList(banner_list);
        adapter.setFlashList(flash_list);
    }

}
