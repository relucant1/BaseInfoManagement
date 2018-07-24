package cn.com.teacher.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "查询权限信息对象")
public class FindAuthsConditionVo {

	@ApiModelProperty(value = "角色编码")
	private String roleCode;
	
	@ApiModelProperty(value = "系统ID")
	private Long projectId;
	
	@ApiModelProperty(value = "权限父级ID")
	private Long parentId;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
