package cn.vlooks.www.app.bean;

import android.os.Bundle;

/**
 * Tab标签信息实体Bean
 * 
 * @author 闫
 * @version 1.0
 * 
 */
public final class TabBean extends TabFragmentBean {

  /**
   * 
   */
  private static final long serialVersionUID = -4735217706594914494L;

  public TabBean(Class<?> clss) {
    super(clss);
  }

  public TabBean(String label, Class<?> clss) {
    super(label, clss);
  }

  public TabBean(String label, Class<?> clss, Bundle args) {
    super(label, clss, args);
  }
}
