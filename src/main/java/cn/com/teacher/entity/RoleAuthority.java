package cn.com.teacher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "角色权限对象")
public class RoleAuthority implements Serializable {

	/**
	 * 自增主键
	 */
	@ApiModelProperty(value = "自增主键",hidden=true)
	private Long id;
	
	/**
	 * 权限ID
	 */
	@ApiModelProperty(value = "权限ID")
	private Long authId;
	
	/**
	 * 角色编码
	 */
	@ApiModelProperty(value = "角色编码")
	private String roleCode;
	
	/**
	 * 是否是半选状态 0 否  1是
	 */
	@ApiModelProperty(value = "是否是半选状态 0 否  1是")
	private String isHalfChecked;
	
	/**
	 * 权限资源访问路径
	 */
	@ApiModelProperty(value = "权限资源访问路径")
	private String authUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getIsHalfChecked() {
		return isHalfChecked;
	}

	public void setIsHalfChecked(String isHalfChecked) {
		this.isHalfChecked = isHalfChecked;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	
}
