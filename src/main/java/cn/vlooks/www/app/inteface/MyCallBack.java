package cn.vlooks.www.app.inteface;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import cn.vlooks.www.app.application.MApplication;
import cn.vlooks.www.app.tools.ToolAlert;
import cn.vlooks.www.app.tools.ToolNetwork;

public class MyCallBack<String> implements Callback.CommonCallback<String> {

    @Override
    public void onSuccess(String result) {
        //可以根据公司的需求进行统一的请求成功的逻辑处理

        try {
            JSONObject jsonObject = new JSONObject(result.toString());
            if (jsonObject.optString("errcode").equals("-100")) {


                ToolAlert.toastShort("请先登录!");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //可以根据公司的需求进行统一的请求网络失败的逻辑处理

        if (!ToolNetwork.getInstance().init(MApplication.gainContext()).isAvailable()) {


            ToolAlert.toastShort("请检查您的网络");
        } else {


        }


    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }


}
