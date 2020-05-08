package com.example.jd.recommend.presenter;

import com.example.jd.base.BasePresenter;
import com.example.jd.bean.RecommendRecBean;
import com.example.jd.net.INetCallBack;
import com.example.jd.recommend.contract.RecommendRecContract;
import com.example.jd.recommend.view.fragment.RecommendRecFragment;
import com.example.jd.recommend.model.RecommendRecModel;

public class RecommendRecPresenter extends BasePresenter<RecommendRecFragment> implements RecommendRecContract.IRecommendRecPresenter {
    private RecommendRecContract.IRecommendRecModel iRecommendRecModel;

    public RecommendRecPresenter() {
        this.iRecommendRecModel = new RecommendRecModel();
    }

    @Override
    public void getRecommendRec(String id,String start,String number,String point_time) {
        iRecommendRecModel.getRecommendRec(id,start,number,point_time,new INetCallBack<RecommendRecBean>() {
            @Override
            public void onSuccess(RecommendRecBean bean) {
                mView.getRecommendRec(bean);
            }

            @Override
            public void OnError(Throwable throwable) {

            }
        });
    }
}
