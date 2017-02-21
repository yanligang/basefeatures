package cn.vlooks.www.app.bean;

import android.os.Bundle;

/**
 * Fragment类型的Tab数据模型
 * 
 * @author 闫
 * @version 1.0
 *
 */
public class TabFragmentBean extends BaseBean {
  
  private static final long serialVersionUID = -814914448481197650L;
  
  /**
   * tab显示文本
   */
  private String label = "";
  
  /**
   * 对应Fragment
   */
  private Class<?> clss;
  
  /**
   * Fragment对应参数
   */
  private Bundle args;
  
  public TabFragmentBean(Class<?> clss) {
    this("",clss);
  }
  
  public TabFragmentBean(String label, Class<?> clss) {
    this(label,clss,null);
  }
  
  public TabFragmentBean(String label, Class<?> clss, Bundle args) {
    this.label = label;
    this.clss = clss;
    this.args = args;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Class<?> getClss() {
    return clss;
  }

  public void setClss(Class<?> clss) {
    this.clss = clss;
  }

  public Bundle getArgs() {
    return args;
  }

  public void setArgs(Bundle args) {
    this.args = args;
  }
  
}
