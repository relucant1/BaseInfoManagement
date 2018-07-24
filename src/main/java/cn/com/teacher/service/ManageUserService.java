package cn.com.teacher.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageUser;
import cn.com.teacher.entity.ManageUserExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.vo.ManageUserManageRoleVo;
/**
 * Created by zhouzhiyuan on 16/12/30.
 */
public interface ManageUserService {

	/**
	 * 查询所有ManageUser信息 
	 * @return
	 * @throws Exception
	 */
	List<ManageUser> getManageUserAll()throws Exception;
	
	/**
	 * 后台拼接分页
	 * @param manageRole
	 * @return
	 * @throws Exception
	 */
	PageInfo<ManageUserManageRoleVo> getManageUserManageRoleVoAllPage(ManageUserManageRoleVo manageUserManageRoleVo)
			throws Exception;
	/**
	 * 根据id停用用户改变状态
	 * @param record
	 * @return
	 * @throws Exception
	 */
	Result updateIsStartByPrimaryKey(ManageUser record) throws Exception;
	/**
	 * 插入
	 * @param roleName
	 * @param remark
	 * @return
	 */
	Result insertManageUser(ManageUser manageUser)throws Exception;
	/**
	 * Example查询
	 * @param example
	 * @return
	 * @throws Exception
	 */
	List<ManageUser> selectByExample(ManageUserExample example)throws Exception;
	/**
	 * 编辑User
	 * @param record
	 * @return
	 * @throws Exception
	 */
	Result updateManageUserByPrimaryKey(ManageUser record) throws Exception;
}
