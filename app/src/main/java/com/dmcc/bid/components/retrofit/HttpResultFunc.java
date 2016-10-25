package com.dmcc.bid.components.retrofit;


import com.alibaba.fastjson.JSON;
import com.dmcc.bid.util.StringUtil;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Response;
import rx.functions.Func1;

public class HttpResultFunc<M> implements Func1<Response<String>, M> {

    public HttpResultFunc(Class<M> cls) {
        this.cls = cls;
    }
    Class<M> cls;
    @Override
    public M call(Response<String> stringResponse) {
        Logger.e("服务器响应数据："+ stringResponse.body());
        String jsonStr = stringResponse.body();
        String dataStr = "";
        if (StringUtil.isBlank(jsonStr)) {
            throw new ApiException("未知错误");
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String code = (String) jsonObject.get("resultCode");
            String message = (String) jsonObject.get("message");
            if (!code.equals("0")) {
                throw new ApiException(message);
            }
            Object obj = (Object) jsonObject.get("data");
            if (StringUtil.isBlank(obj.toString())) {
                throw new ApiException("数据为空");
            }
            dataStr = ""+obj.toString()+"";

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(dataStr, cls);
//        return JSON.parseObject(dataStr, (Class<M>) UserBean.class);
    }
}