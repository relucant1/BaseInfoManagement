package cn.com.teacher.vo;

import cn.com.teacher.entity.ManageUser;
/**
 * Created by zhouzhiyuan on 16/12/30.
 */
public class ManageUserManageRoleVo extends ManageUser{

	private String roleName;
	
	private Integer roleId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
