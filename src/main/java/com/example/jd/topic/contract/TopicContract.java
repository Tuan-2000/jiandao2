package com.example.jd.topic.contract;

import com.example.jd.base.BaseView;
import com.example.jd.bean.TopicBean;
import com.example.jd.net.INetCallBack;

public class TopicContract {
    public static interface ITopicView extends BaseView{
        void getTopicData(TopicBean bean);
    }
    public static interface ITopicPresenter{
        void getTopicData(int start,int number,int point_time);
    }
    public static interface ITopicModel{
        <T> void getTopicData(int start,int number,int point_time,INetCallBack<T> iNetCallBack);
    }
}
