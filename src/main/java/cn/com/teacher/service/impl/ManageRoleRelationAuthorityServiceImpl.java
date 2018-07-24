package cn.com.teacher.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.teacher.entity.ManageRoleRelationAuthority;
import cn.com.teacher.entity.ManageRoleRelationAuthorityExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.mapper.ManageRoleRelationAuthorityMapper;
import cn.com.teacher.service.ManageRoleRelationAuthorityService;
import cn.com.teacher.util.ResultUtil;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
@Service("manageRoleRelationAuthorityService")
public class ManageRoleRelationAuthorityServiceImpl implements ManageRoleRelationAuthorityService{
	@Autowired
	private ManageRoleRelationAuthorityMapper manageRoleRelationAuthorityMapper;
	
	
	@Override
	public Result insertRoleAuthority(ManageRoleRelationAuthority manageRoleRelationAuthority) throws Exception {
		if(manageRoleRelationAuthority==null){
			return ResultUtil.buildFail("保存失败");
		}else{
			manageRoleRelationAuthorityMapper.insert(manageRoleRelationAuthority);
			return ResultUtil.buildSuccess("保存成功");
		}
	}

	@Override
	public List<ManageRoleRelationAuthority> selectByExample(ManageRoleRelationAuthorityExample example) {
		return manageRoleRelationAuthorityMapper.selectByExample(example);
	}

	@Override
	public List<ManageRoleRelationAuthority> getManageRoleRelationAuthorityAll(Integer roleId) throws Exception {
		return manageRoleRelationAuthorityMapper.getManageRoleRelationAuthorityAll(roleId);
	}

	@Override
	public int deleteRoleRelationAuthorityByRoleId(ManageRoleRelationAuthority manageRoleRelationAuthority) throws Exception {
		return manageRoleRelationAuthorityMapper.deleteRoleRelationAuthorityByRoleId(manageRoleRelationAuthority);
	}
	
}
