package com.example.jd;

import android.util.Log;

import com.example.jd.base.BaseModel;
import com.example.jd.net.INetCallBack;
import com.example.jd.net.NetWorkFactory;
import com.example.jd.net.ParamsUtils;
import com.example.jd.net.api.URLConstants;

import java.util.HashMap;

public class MainModel extends BaseModel implements MainContract.IMainMode {
    public MainModel() {
    }

    @Override
    public <T> void getRecommendList(INetCallBack<T> netCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();//获取公共参数，并把私有参数放进去
        commonParams.put("start", "0");
        commonParams.put("number", "0");
        commonParams.put("point_time ", "0");

        for (String key : commonParams.keySet()) {
            Log.e("TAG", "key=" + key + ",values=" + commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.VEDIO_LIST,commonParams,netCallBack);
    }
}
