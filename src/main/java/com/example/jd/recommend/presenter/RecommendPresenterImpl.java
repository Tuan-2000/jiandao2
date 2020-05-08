package com.example.jd.recommend.presenter;

import com.example.jd.base.BasePresenter;
import com.example.jd.bean.SectionBean;
import com.example.jd.recommend.contract.RecommendContract;
import com.example.jd.recommend.view.fragment.RecommendFragment;
import com.example.jd.recommend.model.RecommendModelImpl;
import com.example.jd.net.INetCallBack;

public class RecommendPresenterImpl extends BasePresenter<RecommendFragment> implements RecommendContract.IHomePresenter {
    private RecommendContract.IHomeModel iHomeModel;

    public RecommendPresenterImpl() {
        iHomeModel = new RecommendModelImpl();
    }

    @Override
    public void getSectionList() {
        iHomeModel.getSectionList(new INetCallBack<SectionBean>() {
            @Override
            public void onSuccess(SectionBean sectionBean) {
                mView.getSectionList(sectionBean);
            }

            @Override
            public void OnError(Throwable throwable) {

            }
        });
    }
}
