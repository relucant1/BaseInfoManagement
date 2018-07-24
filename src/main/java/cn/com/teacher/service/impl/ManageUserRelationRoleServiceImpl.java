package cn.com.teacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.mapper.ManageUserRelationRoleMapper;
import cn.com.teacher.service.ManageUserRelationRoleService;
import cn.com.teacher.util.ResultUtil;
/**
 * Created by zhouzhiyuan on 16/12/30.
 */
@Service("manageUserRelationRoleService")
public class ManageUserRelationRoleServiceImpl implements ManageUserRelationRoleService{
	@Autowired
	private ManageUserRelationRoleMapper manageUserRelationRoleMapper;
	@Override
	public List<ManageUserRelationRole> selectByExample(ManageUserRelationRoleExample example) throws Exception {
		return manageUserRelationRoleMapper.selectByExample(example);
	}
	@Override
	public Result insertManageUserRelationRole(ManageUserRelationRole manageUserRelationRole) throws Exception {
		if(manageUserRelationRole!=null) {
			manageUserRelationRoleMapper.insert(manageUserRelationRole);
			return ResultUtil.buildSuccess("插入成功");
		}else{
			return	ResultUtil.buildFail("插入失败");
		}
	}
	@Override
	public Result deleteByExample(ManageUserRelationRoleExample example) throws Exception {
		if(example!=null) {
			manageUserRelationRoleMapper.deleteByExample(example);
			return ResultUtil.buildSuccess("删除成功");
		}else{
			return	ResultUtil.buildFail("删除失败");
		}
	}
}
