package cn.vlooks.www.app.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.leakcanary.LeakCanary;

import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cn.vlooks.www.app.io.ACache;
import cn.vlooks.www.app.tools.ToolChannel;
import cn.vlooks.www.app.tools.ToolImage;
import cn.vlooks.www.app.tools.ToolLog;
import cn.vlooks.www.app.tools.ToolNetwork;

/**
 * 整个应用程序Applicaiton
 *
 * @author 闫
 * @version 1.0
 */
public abstract class MApplication extends Application {

    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static Context instance;
    /**
     * ImageLoader
     **/
    private ImageLoader mImageLoader;
    /**
     * 渠道ID
     **/
    public static String channelId = "Ajava";
    /**
     * 应用程序版本versionName
     **/
    public static String version = "error";

    /**
     * 应用程序版本versionName
     **/
    public static int versioncode = 0;
    /**
     * 设备ID
     **/
    public static String deviceId = "error";
    /**
     * 整个应用全局可访问数据集合
     **/
    private static Map<String, Object> gloableData = new HashMap<String, Object>();
    /***
     * 寄存整个应用Activity
     **/
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    private boolean Caching = true;

    private static ACache acache;


    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        MApplication app = (MApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this).
                fileNameGenerator(new MyFileNameGenerator())
                .maxCacheSize(1024 * 1024 * 1024).
                        maxCacheFilesCount(20).
                        build();
    }


    public class MyFileNameGenerator implements FileNameGenerator {

        // Constants contain mutable parts (parameter 'sessionToken') and stable video's id (parameter 'videoId').
        // e. g. http://example.com?videoId=abcqaz&sessionToken=xyz987
        public String generate(String url) {
            Uri uri = Uri.parse(url);
            String videoId = uri.getQueryParameter("videoId");
            return videoId + ".mp4";
        }
    }

    /**
     * 对外提供Application Context
     *
     * @return
     */
    public static Context gainContext() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }


    /**
     * 获取网络是否已连接
     *
     * @return
     */
    public static boolean isNetworkReady() {
        return ToolNetwork.getInstance().init(instance).isConnected();
    }

    /**
     * 获取网络是否已连接
     *
     * @return
     */
    public static boolean isNetworkReady(Context mContext) {
        return ToolNetwork.getInstance().init(mContext).isConnected();
    }

    /**
     * 初始化工作
     */
    private void init() {
        //初始化XUtils3
        x.Ext.init(this);
        //设置debug模式
        x.Ext.setDebug(true);
        mImageLoader = ToolImage.init(this);

        try {

//6.0之前这样写
//            //应用程序版本
//            version = SysEnv.getVersionName();
//            //应用程序版本code
//            versioncode = SysEnv.getVersionCode();
//            //设备ID
//            deviceId = SysEnv.DEVICE_ID;
            //获取渠道号
            channelId = ToolChannel.gainChannel(this, ToolChannel.CHANNEL_KEY, "Ajava");

//            float DENSITY_DPI = SysEnv.DENSITY_DPI;
//            float DENSITY = SysEnv.DENSITY;
//            float SCREEN_WIDTH = SysEnv.SCREEN_WIDTH;
//            float SCREEN_HEIGHT = SysEnv.SCREEN_HEIGHT;
//
//            System.out.println("---参数----" + "DENSITY_DPI:" + DENSITY_DPI + "  DENSITY:" + DENSITY + "  SCREEN_WIDTH:" + SCREEN_HEIGHT + "   SCREEN_WIDTH:" + SCREEN_WIDTH);


        } catch (Exception e) {
            Log.e(TAG, "初始化设备ID、获取应用程序版本失败，原因：" + e.getMessage());
        }


        acache = ACache.get(this);
        if (Caching) {
            acache.clear();

            ToolLog.i("--------", "Caching");

        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }




    public static ACache getAcache() {
        return acache;
    }


    /**
     * 获取ImageLoader
     *
     * @return
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /*******************************************************Application数据操作API（开始）********************************************************/

    /**
     * 往Application放置数据（最大不允许超过5个）
     *
     * @param strKey   存放属性Key
     * @param strValue 数据对象
     */
    public static void assignData(String strKey, Object strValue) {
        if (gloableData.size() > 5) {
            throw new RuntimeException("超过允许最大数");
        }
        gloableData.put(strKey, strValue);
    }

    /**
     * 从Applcaiton中取数据
     *
     * @param strKey 存放数据Key
     * @return 对应Key的数据对象
     */
    public static Object gainData(String strKey) {
        return gloableData.get(strKey);
    }

    /*
     * 从Application中移除数据
     */
    public static void removeData(String key) {
        if (gloableData.containsKey(key)) gloableData.remove(key);
    }

    /*******************************************************Application数据操作API（结束）********************************************************/


    /*******************************************Application中存放的Activity操作（压栈/出栈）API（开始）*****************************************/

    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex)
            activitys.remove(taskIndex);
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            Activity mActivity = activitys.get(i).get();
            if (null != mActivity && !mActivity.isFinishing()) {
                mActivity.finish();
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        //finish所有的Activity
        for (WeakReference<Activity> task : activitys) {
            Activity mActivity = task.get();
            if (null != mActivity && !mActivity.isFinishing()) {
                mActivity.finish();
            }
        }
    }

    /**
     * 退出整个APP，关闭所有activity/清除缓存等等
     */
    public abstract void exit();

    /*******************************************Application中存放的Activity操作（压栈/出栈）API（结束）*****************************************/
}
