package com.example.jd.recommend.contract;

import com.example.jd.base.BaseView;
import com.example.jd.bean.SectionBean;
import com.example.jd.net.INetCallBack;

public class RecommendContract {
    public interface IHomeView extends BaseView {
        void getSectionList(SectionBean bean);
    }
    public interface IHomeModel{
        <T> void getSectionList(INetCallBack<T> iNetCallBack);
    }
    public interface IHomePresenter{
        void getSectionList();
    }
}
