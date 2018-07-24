package cn.com.teacher.service;

import cn.com.teacher.entity.Result;
import cn.com.teacher.vo.FindAuthsConditionVo;
import cn.com.teacher.vo.RoleAuthorityVo;

public interface AuthorityResourceService {

	/**
	 * 根据条件查询权限信息
	 * @param findAuthsCondition
	 * @return
	 */
	public Result findAuths(FindAuthsConditionVo findAuthsCondition);
	
	/**
	 * 设置权限
	 * @param roleAuthority
	 * @return
	 */
	public Result saveRoleAuth(RoleAuthorityVo roleAuthority);
	
	/**
	 * 获取当前用户系统菜单
	 * @param accessToken
	 * @return
	 */
	public Result getMenus(String accessToken);
	
	
}
