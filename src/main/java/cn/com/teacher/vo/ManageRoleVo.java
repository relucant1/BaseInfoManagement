package cn.com.teacher.vo;

import cn.com.teacher.entity.ManageRole;
/**
 * Created by zhouzhiyuan on 16/12/30.
 */
public class ManageRoleVo extends ManageRole{
	private boolean isCheck;
	
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
}
