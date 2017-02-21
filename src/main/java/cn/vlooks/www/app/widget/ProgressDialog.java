package cn.vlooks.www.app.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import cn.vlooks.www.app.R;

/**
 * 自定义对话框
 * @author 闫
 * @version 1.0
 */
public class ProgressDialog extends Dialog {
	
	/**消息TextView*/
	private TextView tvMsg ;
	
	public ProgressDialog(Context context, String strMessage) {
		this(context, R.style.CustomDialog,strMessage);
	}

	public ProgressDialog(Context context, int theme, String strMessage) {
		super(context, theme);
		this.setContentView(R.layout.progress_dialog);
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
	    tvMsg = (TextView) this.findViewById(R.id.tv_message);
	    setMessage(strMessage);
	}

	/**
	 * 设置进度条消息
	 * @param strMessage 消息文本
	 */
	public void setMessage(String strMessage){
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	}
}