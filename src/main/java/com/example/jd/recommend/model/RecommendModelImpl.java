package com.example.jd.recommend.model;

import android.util.Log;

import com.example.jd.base.BaseModel;
import com.example.jd.recommend.contract.RecommendContract;
import com.example.jd.net.INetCallBack;
import com.example.jd.net.NetWorkFactory;
import com.example.jd.net.ParamsUtils;
import com.example.jd.net.api.URLConstants;

import java.util.HashMap;

public class RecommendModelImpl extends BaseModel implements RecommendContract.IHomeModel {
    private RecommendContract.IHomePresenter iHomePresenter;

    public RecommendModelImpl() {
    }


    @Override
    public <T> void getSectionList(INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();

        for (String key : commonParams.keySet()) {
            Log.e("TAG", "key=" + key + ",values=" + commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.SECTION_LIST,commonParams,iNetCallBack);
    }
}
