package cn.vlooks.www.app.view;

import android.os.Handler;
import android.os.Message;

class ScrollIndicateHandler extends Handler {
	private ImageIndicatorView scrollIndicateView;

	public ScrollIndicateHandler(ImageIndicatorView scrollIndicateView) {
		this.scrollIndicateView = scrollIndicateView;

	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (this.scrollIndicateView != null) {
			scrollIndicateView.refreshIndicateView();
		}
	}
}
