package cn.vlooks.www.app.tools;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.util.Log;

/**
 * 数据工具类
 * 
 * @author 闫
 * @version 1.0
 * @link http://www.cnblogs.com/fly100/
 * @email mengzhongyouni@gmail.com
 */
public class ToolData {

  public static final String TAG = "ToolData";
  /**
   * 读取AndroidManifest.xml配置的meta-data数据
   * 
   * @param mContext 上下文
   * @param target Activity/BroadcastReceiver/Service/Application
   * @param key 配置的name
   * @return
   */
  public static String gainMetaData(Context mContext, Class target, String key) {
    String result = "";
    try {
      Log.d(TAG, target.getSuperclass().getName());

      int flags = PackageManager.GET_META_DATA;
      Object obj = target.newInstance();
      if (obj instanceof Activity) {
        ActivityInfo info2 =
            mContext.getPackageManager().getActivityInfo(((Activity) mContext).getComponentName(),
                flags);
        result = info2.metaData.getString(key);
      } else if (obj instanceof Application) {
        ApplicationInfo info1 =
            mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), flags);
        result = info1.metaData.getString(key);
      } else if (obj instanceof Service) {
        ComponentName cn1 = new ComponentName(mContext, target);
        ServiceInfo info3 = mContext.getPackageManager().getServiceInfo(cn1, flags);
        result = info3.metaData.getString(key);
      } else if (obj instanceof BroadcastReceiver) {
        ComponentName cn2 = new ComponentName(mContext, target);
        ActivityInfo info4 = mContext.getPackageManager().getReceiverInfo(cn2, flags);
        result = info4.metaData.getString(key);
      }
    } catch (Exception e) {
      Log.e(TAG, "读取meta元数据失败，原因：" + e.getMessage());
    }
    return result;
  }


}
