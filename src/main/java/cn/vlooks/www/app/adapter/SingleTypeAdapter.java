/*
 *
 *     Android基础开发个人积累、沉淀、封装、整理共通
 *     Copyright (c) 2016. 闫
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 * /
 */

package cn.vlooks.www.app.adapter;

import android.app.Activity;

import java.util.Map;

import cn.vlooks.www.app.base.AbsViewTemplet;
import cn.vlooks.www.app.base.BaseMultiTypeAdapter;
import cn.vlooks.www.app.bean.BaseModelBean;

/**
 * 普通(一种itemType)的Adapter
 *
 * @author 闫
 * @version 1.0
 *
 */
public class SingleTypeAdapter extends BaseMultiTypeAdapter {

  /**
   * 缓存视图模板集合
   */
  private Map<Integer, Class<? extends AbsViewTemplet>> mViewTemplet;

  public SingleTypeAdapter(Activity mContext) {
    super(mContext);
  }

  @Override
  protected void registeViewTemplet(Map<Integer, Class<? extends AbsViewTemplet>> mViewTemplet) {
    this.mViewTemplet = mViewTemplet;
  }

  @Override
  protected int adjustItemViewType(BaseModelBean model, int position) {
    return 0;
  }

  /**
   * 注册视图模板
   * @param mTemplet
   */
  public void registeViewTemplet(Class<? extends AbsViewTemplet> mTemplet){
    mViewTemplet.put(0,mTemplet);
  }
}
