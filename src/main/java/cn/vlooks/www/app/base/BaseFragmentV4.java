package cn.vlooks.www.app.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment基类
 *
 * @author 闫
 * @version 1.0
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment, IBaseConstant {

    /**
     * 是否已经加载过数据
     */
    private boolean mHasLoadedData = false;

    /**
     * 当前Fragment渲染的视图View
     */
    private View mContentView = null;

    /**
     * 共通操作
     */
    protected Operation mOperation = null;

    /**
     * 依附的Activity
     */
    protected Activity mActivity = null;

    /**
     * 日志输出标志
     */
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 每次切换都会调用判断当前Fragment是否展现在屏幕上,在onAttach、onCreateView之前分别调用一次
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "BaseFragmentV4-->setUserVisibleHint()-->" + isVisibleToUser + mContentView + mHasLoadedData);
        //当Fragment在屏幕上可见并且没有加载过数据时调用
        if (isVisibleToUser && null != mContentView && !mHasLoadedData) {
            loadDataOnce();
            Log.d(TAG, "BaseFragmentV4-->loadDataOnce()");
            mHasLoadedData = true;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 缓存当前依附的activity
        mActivity = activity;
        Log.d(TAG, "BaseFragmentV4-->onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseFragmentV4-->onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onCreateView()");
        // 渲染视图View
        if (null == mContentView) {
            // 初始化参数
            Bundle parms = getArguments();
            if (null == parms) {
                parms = new Bundle();
            }
            initParams(parms);

            //初始化标题
            String strTitle = initTitle();
            if (!TextUtils.isEmpty(strTitle) && mActivity instanceof BaseActivity) {
                ((BaseActivity) mActivity).setWindowTitle(strTitle, Gravity.CENTER);
            }

            View mView = bindView();
            if (null == mView) {
                mContentView = inflater.inflate(bindLayout(), container, false);

            } else {
                mContentView = mView;
            }
            // 控件初始化
            initView(mContentView);
            // 实例化共通操作
            mOperation = new Operation(mActivity);
            // 业务处理
            doBusiness(mActivity);
            //请求网络数据
            setUserVisibleHint(getUserVisibleHint());


        }

        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4-->onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "BaseFragmentV4-->onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "BaseFragmentV4-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "BaseFragmentV4-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "BaseFragmentV4-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "BaseFragmentV4-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "BaseFragmentV4-->onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "BaseFragmentV4-->onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContentView != null && mContentView.getParent() != null) {
            ((ViewGroup) mContentView.getParent()).removeView(mContentView);
        }
    }

    /**
     * 一般情况下不适用，若不写XML布局文件，可以复写改方法返回代码生成的View
     */
    @Override
    public View bindView() {
        Log.d(TAG, "BaseFragmentV4-->bindView()");
        return null;
    }

    /**
     * 初始化Activity标题栏的标题文案
     */
    @Override
    public String initTitle() {
        Log.d(TAG, "BaseFragmentV4-->initTitle()");
        return null;
    }

    @Override
    public void loadDataOnce() {

    }

    /**
     * 查找当前Fragment视图中的view
     *
     * @param id
     * @return
     */
    protected View findViewById(int id) {
        if (null == mContentView) return null;

        return mContentView.findViewById(id);
    }

    /**
     * 获取当前Fragment依附在的Activity
     *
     * @return
     */
    protected Activity gainAttachActivity() {
        return mActivity;
    }

    /**
     * 获取当前Fragment依附在的Activity，该方法已过时，gainAttachActivity替代
     *
     * @return
     */
    @Deprecated
    public Activity getContext() {
        return getActivity();
    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return mOperation;
    }
}
