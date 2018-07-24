package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.entity.Result;
/**
 * Created by zhouzhiyuan on 16/12/30.
 */
public interface ManageUserRelationRoleService {
	/**
	 * 用户分页查询Example
	 * @param example
	 * @return
	 * @throws Exception
	 */
	List<ManageUserRelationRole> selectByExample(ManageUserRelationRoleExample example)throws Exception;
	/**
	 * 插入
	 * @param roleName
	 * @param remark
	 * @return
	 */
	Result insertManageUserRelationRole(ManageUserRelationRole manageUserRelationRole)throws Exception;
	/**
	 * 根据userID删除
	 * @param example
	 * @return
	 * @throws Exception
	 */
	Result deleteByExample(ManageUserRelationRoleExample example)throws Exception;
}
