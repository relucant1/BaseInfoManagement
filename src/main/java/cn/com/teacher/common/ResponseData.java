package cn.com.teacher.common;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/24
 * email：liuxiaowen@teacher.com.cn
 */
public class ResponseData
{
    /**
     * 请求是否成功 1成功 大于1为失败
     */
    private int isSuccess = Integer.MAX_VALUE;

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
