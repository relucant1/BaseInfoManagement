package cn.com.teacher.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.entity.Result;
import cn.com.teacher.vo.ManageRoleVo;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
public interface ManageRoleService {
	/**
	 * 查询所有,分页
	 * @param manageRole
	 * @return
	 * @throws Exception
	 */
	PageInfo<ManageRole> getManangeRoleAllPage(ManageRole manageRole)throws Exception;
	/**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<ManageRole> getManangeRoleAll()throws Exception;
    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    Result delManageRoleById(Integer id)throws Exception;
	/**
	 * 插入
	 * @param roleName
	 * @param remark
	 * @return
	 */
	Result insertManageRole(ManageRole manageRole)throws Exception;
	/**
	 * 编辑
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	Result updateManageRole(ManageRole manageRole)throws Exception;
	/**
	 * 用户后台管理分页
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ManageRole selectByPrimaryKey(Integer id)throws Exception;
	/**
	 * 回显拼接Vo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<ManageRoleVo> editBackRoleName(Integer userId) throws Exception;
	
}
