package cn.com.teacher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/***
 * 权限资源实体类
 * @author hugang
 *
 */
@ApiModel(description = "权限资源信息对象")
public class AuthorityResource implements Serializable {

	/**
	 * 权限ID
	 */
	@ApiModelProperty(value = "权限ID")
	private Long authId;
	
	/**
	 * 权限名称
	 */
	@ApiModelProperty(value = "权限名称")
	private String authName;
	
	/**
	 * 权限访问路径
	 */
	@ApiModelProperty(value = "权限访问路径")
	private String authUrl;
	
	/**
	 * 上级权限ID
	 */
	@ApiModelProperty(value = "上级权限ID")
	private Long parentId;
	
	/**
	 * 是否是菜单 0 否 1 是
	 */
	@ApiModelProperty(value = "是否是菜单 0 否 1 是")
	private String isMenu;
	
	/**
	 * 权限所属系统ID
	 */
	@ApiModelProperty(value = "权限所属系统ID")
	private Long projectId;
	
	/**
	 * 是否是半选状态 0 否  1是
	 */
	@ApiModelProperty(value = "是否是半选状态 0 否  1是")
	private String isHalfChecked;

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getIsHalfChecked() {
		return isHalfChecked;
	}

	public void setIsHalfChecked(String isHalfChecked) {
		this.isHalfChecked = isHalfChecked;
	}
	
}
