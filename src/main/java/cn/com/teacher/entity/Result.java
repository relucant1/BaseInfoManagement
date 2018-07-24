package cn.com.teacher.entity;

/**
 * Created by Qukun on 16/11/15.
 *
 * 接口返回通用对象
 */
public class Result {

	/**
	 * 请求是否成功 0失败 1成功
	 */
	private int isSuccess = 0;

	/**
	 * 具体数据
	 */
	private Object data;

	/**
	 * 错误消息
	 */
	private String msg;

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
