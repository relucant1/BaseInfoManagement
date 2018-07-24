package cn.com.teacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.entity.ManageUser;
import cn.com.teacher.entity.ManageUserExample;
import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.mapper.ManageRoleMapper;
import cn.com.teacher.mapper.ManageUserMapper;
import cn.com.teacher.mapper.ManageUserRelationRoleMapper;
import cn.com.teacher.service.ManageUserService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.ManageUserManageRoleVo;

/**
 * Created by zhouzhiyuan on 16/12/30.
 */
@Service("/manageUserService")
public class ManageUserServiceImpl implements ManageUserService{
	@Autowired
	private ManageUserMapper manageUserMapper;
	@Autowired
	private ManageRoleMapper manageRoleMapper;
	@Autowired
	private ManageUserRelationRoleMapper manageUserRelationRoleMapper;
	
	@Override
	public List<ManageUser> getManageUserAll() throws Exception {
		return manageUserMapper.getManageUserAll();
	}

	@Override
	public PageInfo<ManageUserManageRoleVo> getManageUserManageRoleVoAllPage(ManageUserManageRoleVo manageUserManageRoleVo) throws Exception {
	PageHelper.startPage(manageUserManageRoleVo.getPageNum(),manageUserManageRoleVo.getPageSize());
	List<ManageUserManageRoleVo> list= manageUserMapper.getManageUserManageRoleVoAllPage(manageUserManageRoleVo);
	PageInfo<ManageUserManageRoleVo> page = new PageInfo<ManageUserManageRoleVo>(list);
	for(ManageUserManageRoleVo manageRoleVo:list){
		ManageUserRelationRoleExample example= new ManageUserRelationRoleExample();
		example.createCriteria().andUserIdEqualTo(manageRoleVo.getId());
		List<ManageUserRelationRole> murr =manageUserRelationRoleMapper.selectByExample(example);
		StringBuilder sb=new StringBuilder();
		for(ManageUserRelationRole aa:murr) {
			ManageRole manageRole=manageRoleMapper.selectByPrimaryKey(aa.getRoleId());
			sb.append(" "+manageRole.getName());
		}
		manageRoleVo.setRoleName(sb.toString().replaceFirst("  ",""));
	}
	return page;
	}
	
	@Override
	public Result updateIsStartByPrimaryKey(ManageUser record) throws Exception {
		if (record != null) {
			manageUserMapper.updateIsStartByPrimaryKey(record);
			return ResultUtil.buildSuccess("停用成功");
		}else{
			return ResultUtil.buildFail("停用失败");
		}
	}

	@Override
	public Result insertManageUser(ManageUser manageUser) throws Exception {
		if(manageUser!=null) {
			manageUserMapper.insert(manageUser);
			return ResultUtil.buildSuccess("插入成功");
		}else{
			return	ResultUtil.buildFail("插入失败");
		}
	}

	@Override
	public List<ManageUser> selectByExample(ManageUserExample example) throws Exception {
		return manageUserMapper.selectByExample(example);
	}

	@Override
	public Result updateManageUserByPrimaryKey(ManageUser record) throws Exception {
		if(record!=null) {
			manageUserMapper.updateByPrimaryKeySelective(record);
			return ResultUtil.buildSuccess("编辑成功");
		}else{
			return	ResultUtil.buildFail("编辑失败");
		}
	}


}
