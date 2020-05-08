package com.example.jd.topic.model;

import android.util.Log;

import com.example.jd.base.BaseModel;
import com.example.jd.net.INetCallBack;
import com.example.jd.net.NetWorkFactory;
import com.example.jd.net.ParamsUtils;
import com.example.jd.net.api.URLConstants;
import com.example.jd.recommend.contract.RecommendRecContract;
import com.example.jd.topic.contract.TopicContract;

import java.util.HashMap;

public class TopicModel extends BaseModel implements TopicContract.ITopicModel {
    public TopicModel() {
    }

    @Override
    public <T> void getTopicData(int start,int number,int point_time,INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("start",start+"");
        commonParams.put("number",number+"");
        commonParams.put("point_time",point_time+"");

        for (String key : commonParams.keySet()) {
            Log.e("TAG", "key=" + key + ",values=" + commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().get(URLConstants.TOPIC_LIST,commonParams,iNetCallBack);
    }
}
