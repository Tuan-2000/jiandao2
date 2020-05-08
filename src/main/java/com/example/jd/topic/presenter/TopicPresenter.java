package com.example.jd.topic.presenter;

import com.example.jd.base.BasePresenter;
import com.example.jd.bean.TopicBean;
import com.example.jd.net.INetCallBack;
import com.example.jd.topic.contract.TopicContract;
import com.example.jd.topic.view.fragment.TopicFragment;
import com.example.jd.topic.model.TopicModel;

public class TopicPresenter extends BasePresenter<TopicFragment> implements TopicContract.ITopicPresenter {
    private TopicContract.ITopicModel iTopicModel;

    public TopicPresenter() {
        this.iTopicModel = new TopicModel();
    }

    @Override
    public void getTopicData(int start,int number,int point_time) {
        iTopicModel.getTopicData(start,number,point_time,new INetCallBack<TopicBean>() {
            @Override
            public void onSuccess(TopicBean bean) {
                mView.getTopicData(bean);
            }

            @Override
            public void OnError(Throwable throwable) {

            }
        });
    }
}
