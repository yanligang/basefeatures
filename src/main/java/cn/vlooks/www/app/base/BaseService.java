package cn.vlooks.www.app.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 *  android 系统中的四大组件之一Service基类<br>
 *  Android Service的生命周期 http://www.cnblogs.com/mengdd/archive/2013/03/24/2979944.html
 * @author 闫
 * @version 1.0
 *
 */
public abstract class BaseService extends Service  {

	/**日志输出标志**/
	protected final String TAG = this.getClass().getSimpleName();

	@Override
	public void onCreate() {
		Log.d(TAG, "BaseService-->onCreate()");
		super.onCreate();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "BaseService-->onStart()");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "BaseService-->onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "BaseService-->onDestroy()");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "BaseService-->onBind()");
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "BaseService-->onUnbind()");
		return super.onUnbind(intent);
	}
}
