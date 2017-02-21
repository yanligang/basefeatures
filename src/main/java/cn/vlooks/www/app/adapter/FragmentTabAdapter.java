package cn.vlooks.www.app.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentManager;


/**
 * 用于固定数量tab的标签页情况
 * 
 * @author 闫
 * @version 1.0
 *
 */
public class FragmentTabAdapter extends BaseFragmentPagerAdapter {

  public FragmentTabAdapter(FragmentManager fm, Activity mActivity) {
    super(fm, mActivity);
  }

}
