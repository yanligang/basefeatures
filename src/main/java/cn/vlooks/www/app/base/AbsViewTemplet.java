package cn.vlooks.www.app.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import cn.vlooks.www.app.tools.ToolLog;

/**
 * 模板基础抽象类,具备处理跳转、点击事件能力
 *
 * inflate-->bindLayout-->initView-->fillData
 *
 * @author 闫
 * @version 1.0
 */
public abstract class AbsViewTemplet implements View.OnClickListener {

    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 当前item对应的view视图
     */
    protected View mLayoutView;

    /**
     * item布局父控件
     */
    protected ViewGroup parent;

    /**
     * 当前item对应的type类型
     */
    protected int viewType;

    /**
     * 当前位置索引
     */
    protected int postion;

    /**
     * 当前行数据
     */
    protected Object rowData;

    /**
     * 跳转工具类
     */
    protected Operation forwardTool;

    /**
     * 日志输出标识
     */
    protected final String TAG = this.getClass().getSimpleName();

    public AbsViewTemplet(Context mContext){
        this.mContext = mContext;
        if(mContext instanceof Activity){
            forwardTool = new Operation((Activity)mContext);
        }
    }

    public Context getmContext() {
        return mContext;
    }

    /**
     * 渲染UI布局
     * @param viewType item对应的类型
     * @param postion 当前数据行位置索引
     * @param parent 根节点，没有可以传null
     * @return 当前模板渲染的View
     */
    public View inflate(int viewType, int postion, ViewGroup parent){
        int layout = bindLayout();
        if(null != parent){
            mLayoutView = LayoutInflater.from(mContext).inflate(layout,parent,false);
        }else{
            mLayoutView = LayoutInflater.from(mContext).inflate(layout,parent);
        }
        mLayoutView.setOnClickListener(this);
        this.parent = parent;
        this.viewType = viewType;
        this.postion = postion;
        return mLayoutView;
    }

    /**
     * 缓存住当前行对应的参数,供基类adapter的getView方法中调用
     * @param viewType item对应的类型
     * @param postion 当前数据所在位置索引
     * @param rowData 当前行数据
     */
    void holdCurrentParams(int viewType,int postion,Object rowData){
        this.viewType = viewType;
        this.postion = postion;
        this.rowData = rowData;
    }

    /**
     * 模板布局layout
     */
    public abstract int bindLayout();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 装填数据
     */
    public abstract void fillData(Object model, int postion);


    @Override
    public void onClick(View v) {
        try {
            itemClick(v, postion, rowData);
        }catch (Exception e){
            e.printStackTrace();
            ToolLog.d(TAG, "点击跳转发生异常，原因：" + e.getMessage());
        }
    }

    /**
     * item点击事件
     * @param view 当前点击的view
     * @param postion 当前行位置索引
     * @param rowData 当前行数据模型
     */
    public void itemClick(View view, int postion, Object rowData){

    }

    /**
     * 获取当前item渲染的view
     * @return
     */
    public View getItemLayoutView(){
        return mLayoutView;
    }

    /**
     * 获取当前item对应的viewType
     * @return
     */
    public int getViewType(){
        return viewType;
    }

    /**
     * 查找控件
     * @param id
     * @return
     */
    protected View findViewById(int id){
        if(null != mLayoutView){
            return mLayoutView.findViewById(id);
        }
        return null;
    }

    /**
     * 获取当前行数据
     * @param bean
     * @param <D>
     * @return
     */
    protected <D> D getRowData(Class<D> bean){
        if(null != rowData){
            return (D)rowData;
        }
        return null;
    }

    /**
     * 构建模板实例
     * @param mViewTemplet
     * @param arguments 构造方法形参
     * @param <D>
     * @return
     * @throws Exception
     */
    public static <D extends AbsViewTemplet> D createViewTemplet(Class<D> mViewTemplet, Object... arguments){
        Constructor<?> constructor = findConstructor(mViewTemplet, arguments);
        D mTemplet = null;
        try {
            mTemplet = (D) constructor.newInstance(arguments);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return mTemplet;
    }

    /**
     * 匹配构造器
     * @param mClass
     * @param params
     * @return
     */
    private static Constructor<?> findConstructor(Class<?> mClass, Object... params) {
        for (Constructor<?> constructor : mClass.getConstructors()) {
            Class<?>[] paramsTypes = constructor.getParameterTypes();
            if (paramsTypes.length == params.length) {
                boolean match = true;
                for (int i = 0; i < paramsTypes.length; i++) {
                    if (!paramsTypes[i].isAssignableFrom(params[i].getClass())) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return constructor;
                }
            }
        }
        return null;
    }
}
