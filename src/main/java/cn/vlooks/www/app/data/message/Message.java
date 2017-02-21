package cn.vlooks.www.app.data.message;


import cn.vlooks.www.app.data.DTB;

/**
 * 消息实体Bean
 * @author 闫
 * @version 1.0
 *
 */
public class Message extends DTB {

	private static final long serialVersionUID = 7491152915368949244L;
	
	/**
	 * 消息ID
	 */
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
