package cn.vlooks.www.app.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.vlooks.www.app.bean.TabFragmentBean;
import cn.vlooks.www.app.tools.ToolLog;


/**
 * 固定数量tab的Fragment类型adapter
 *
 * @author 闫
 * @version 1.0
 *
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

  /** 数据存储集合 **/
  private List<TabFragmentBean> mTabs = new ArrayList<TabFragmentBean>();
  /** Context上下文 **/
  private Activity mActivity;
  /**日志输出标志**/
  protected final String TAG = this.getClass().getSimpleName();

  public BaseFragmentPagerAdapter(FragmentManager fm, Activity mActivity) {
    super(fm);
    this.mActivity = mActivity;
  }

  @Override
  public int getCount() {
      return mTabs.size();
  }

  @Override
  public Fragment getItem(int position) {
    TabFragmentBean info = mTabs.get(position);
    if(null == info || null == info.getClss()){
     ToolLog.w(TAG, "当前tab对应的Fragment类为null");
      return null;
    }
    return Fragment.instantiate(mActivity, info.getClss().getName(), info.getArgs());
  }

  @Override
  public CharSequence getPageTitle(int position) {
    TabFragmentBean info = mTabs.get(position);
    if(null == info || null == info.getLabel()){
     ToolLog.w(TAG, "当前tab对应的label类为null");
      return null;
    }

    return info.getLabel();
  }

  /**
   * 添加数据
   * @param item 数据项
   */
  public boolean addItem(TabFragmentBean object){
      return mTabs.add(object);
  }

  /**
   * 在指定索引位置添加数据
   * @param location 索引
   * @param object 数据
   */
  public void addItem(int location,TabFragmentBean object){
    mTabs.add(location, object);
  }

  /**
   * 集合方式添加数据
   * @param collection 集合
   */
  public boolean addItem(Collection<? extends TabFragmentBean> collection){
      return mTabs.addAll(collection);
  }

  /**
   * 在指定索引位置添加数据集合
   * @param location 索引
   * @param collection 数据集合
   */
  public boolean addItem(int location,Collection<? extends TabFragmentBean> collection){
      return mTabs.addAll(location,collection);
  }

  /**
   * 移除指定对象数据
   * @param object 移除对象
   * @return 是否移除成功
   */
  public boolean removeItem(TabFragmentBean object){
      return mTabs.remove(object);
  }

  /**
   * 移除指定索引位置对象
   * @param location 删除对象索引位置
   * @return 被删除的对象
   */
  public Object removeItem(int location){
      return mTabs.remove(location);
  }

  /**
   * 移除指定集合对象
   * @param collection 待移除的集合
   * @return 是否移除成功
   */
  public boolean removeAll(Collection<? extends TabFragmentBean> collection){
      return mTabs.removeAll(collection);
  }
  
  /**
   * 清空数据
   */
  public void clear() {
    mTabs.clear();
  }
  
  /**
   * 获取Activity方法
   * @return Activity的子类
   */
  public Activity getActivity(){
      return mActivity;
  }
}
