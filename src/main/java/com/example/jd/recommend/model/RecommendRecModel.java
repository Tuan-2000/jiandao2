package com.example.jd.recommend.model;

import android.util.Log;

import com.example.jd.base.BaseModel;
import com.example.jd.net.INetCallBack;
import com.example.jd.net.NetWorkFactory;
import com.example.jd.net.ParamsUtils;
import com.example.jd.net.api.URLConstants;
import com.example.jd.recommend.contract.RecommendRecContract;

import java.util.HashMap;

public class RecommendRecModel extends BaseModel implements RecommendRecContract.IRecommendRecModel {
    public RecommendRecModel() {
    }


    @Override
    public <T> void getRecommendRec(String id,String start,String number,String point_time,INetCallBack<T> netCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("start",start);
        commonParams.put("number",number);
        commonParams.put("point_time",point_time);

        for (String key : commonParams.keySet()) {
            Log.e("TAG", "key=" + key + ",values=" + commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().get(URLConstants.RECOMMEND_LIST,commonParams,netCallBack);
    }
}
