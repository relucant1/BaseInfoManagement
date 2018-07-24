package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.ManageRoleRelationAuthority;
import cn.com.teacher.entity.ManageRoleRelationAuthorityExample;
import cn.com.teacher.entity.Result;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
public interface ManageRoleRelationAuthorityService {
	/**
	 * 添加权限角色
	 * @param manageRoleRelationAuthority
	 * @return
	 * @throws Exception
	 */
	Result insertRoleAuthority(ManageRoleRelationAuthority manageRoleRelationAuthority)throws Exception;
	/**
	 * Exampl查询List
	 * @param example
	 * @return
	 */
	List<ManageRoleRelationAuthority> selectByExample(ManageRoleRelationAuthorityExample example)throws Exception;
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	List<ManageRoleRelationAuthority> getManageRoleRelationAuthorityAll(Integer roleId)throws Exception;
	/**
	 * 删除所有roleId,保存权限
	 * @param manageRoleRelationAuthority
	 * @return
	 */
	int deleteRoleRelationAuthorityByRoleId(ManageRoleRelationAuthority manageRoleRelationAuthority)throws Exception;

}
