package cn.vlooks.www.app.common;


import cn.vlooks.www.app.application.MApplication;
import cn.vlooks.www.app.tools.ToolAlert;

/**
 * 网络监听Service
 * @author 闫
 * @version 1.0
 *
 */
public class MyNetworkListener extends NetworkStateService {

	@Override
	public void onNoNetwork() {
		ToolAlert.toastShort(MApplication.gainContext(), "OMG 木有网络了~~");
	}

	@Override
	public void onNetworkChange(String networkType) {
		ToolAlert.toastShort(MApplication.gainContext(), "当前网络："+networkType);
	}

}
