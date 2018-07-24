package cn.com.teacher.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import cn.com.teacher.entity.RoleAuthority;

@ApiModel(description = "设置权限对象")
public class RoleAuthorityVo {

	@ApiModelProperty(value = "系统ID")
	private Long projectId;	
	@ApiModelProperty(value = "角色编码")
	private String roleCode;
	@ApiModelProperty(value = "角色权限列表")
	private List<RoleAuthority> roleAuths;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<RoleAuthority> getRoleAuths() {
		return roleAuths;
	}

	public void setRoleAuths(List<RoleAuthority> roleAuths) {
		this.roleAuths = roleAuths;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
