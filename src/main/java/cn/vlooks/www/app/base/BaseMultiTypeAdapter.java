package cn.vlooks.www.app.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import cn.vlooks.www.app.R;
import cn.vlooks.www.app.bean.BaseModelBean;
import cn.vlooks.www.app.bean.IModelBean;
import cn.vlooks.www.app.tools.ToolLog;

/**
 * 多种类型Item的Adapter基类
 * 
 * @author 闫
 * @version 1.0
 */
public abstract class BaseMultiTypeAdapter extends BaseMAdapter{

  /**
   * item模板映射<K=viewType,V=AbsViewTemplet>
   */
  private Map<Integer,Class<? extends AbsViewTemplet> > mViewTemplet = new HashMap<>();

  /**
   * 日志输出标志
   */
  private final String TAG = this.getClass().getSimpleName();

  public BaseMultiTypeAdapter(Activity mContext) {
    super(mContext);
    registeViewTemplet(mViewTemplet);
  }

  @Override
  public int getItemViewType(int position) {
    BaseModelBean model = getItem(position);
    return adjustItemViewType(model, position);
  }

  @Override
  public int getViewTypeCount() {
    return mViewTemplet.size();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    AbsViewTemplet mTemplet = null;
    int viewType = getItemViewType(position);
    if(null == convertView){
      mTemplet = AbsViewTemplet.createViewTemplet(mViewTemplet.get(viewType), getActivity());
      ToolLog.d(TAG,position + " 实例化item对象-->"+mTemplet.getClass().getSimpleName() + " viewType="+viewType);

      mTemplet.inflate(viewType, position, parent);
      mTemplet.initView();
      convertView = mTemplet.getItemLayoutView();
      convertView.setTag(R.id.view_templet, mTemplet);
    }else{
      mTemplet = (AbsViewTemplet)convertView.getTag(R.id.view_templet);
    }

    //填充数据
    IModelBean rowData = getItem(position);
    mTemplet.holdCurrentParams(viewType,position,rowData);
    mTemplet.fillData(rowData, position);
    return convertView;
  }

  /**
   * 注册viewType以及绑定的Templet
   * @param mViewTemplet
   */
  protected abstract void registeViewTemplet(Map<Integer,Class<? extends AbsViewTemplet> > mViewTemplet);

  /**
   * 根据数据模型返回对应的ViewType
   *
   * @param model 数据模型
   * @param position 当前数据位置
   * @return
   */
  protected abstract int adjustItemViewType(BaseModelBean model, int position);
}
