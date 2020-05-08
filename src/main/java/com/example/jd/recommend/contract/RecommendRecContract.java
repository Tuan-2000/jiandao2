package com.example.jd.recommend.contract;

import com.example.jd.base.BaseView;
import com.example.jd.bean.RecommendRecBean;
import com.example.jd.net.INetCallBack;

public class RecommendRecContract {
    public static interface IRecommendRecView extends BaseView{
        void getRecommendRec(RecommendRecBean bean);
    }
    public static interface IRecommendRecPresenter{
        void getRecommendRec(String id,String start,String number,String point_time);
    }

    public static interface IRecommendRecModel{
        <T> void getRecommendRec(String id,String start,String number,String point_time,INetCallBack<T> netCallBack);
    }
}
