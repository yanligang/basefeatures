/*
    ShengDao Android Client, OnDataListener
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package cn.vlooks.www.app.async;


import cz.msebera.android.httpclient.HttpException;

/**
 * [异步数据处理回调接口类]
 *
 **/
public interface OnDataListener {

	/**
	 * 异步耗时方法
	 * @param requsetCode 请求码
	 * @return
	 * @throws HttpException
	 */
	public Object doInBackground(int requsetCode) throws HttpException;

	/**
	 * 打断方法
	 * @param requestCode 请求码
	 * @param result 返回结果 true表示打断，false表示继续执行onSuccess方法
	 */
	public boolean onIntercept(int requestCode, Object result);

	/**
	 * 成功方法（可直接更新UI）
	 * @param requestCode 请求码
	 * @param result 返回结果
	 */
	public void onSuccess(int requestCode, Object result);
	
	/**
	 * 失败方法（可直接更新UI）
	 * @param requestCode 请求码
	 * @param state 返回状态
	 * @param result 返回结果
	 */
	public void onFailure(int requestCode, int state, Object result);
}
