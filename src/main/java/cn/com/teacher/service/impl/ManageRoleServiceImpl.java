package cn.com.teacher.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.mapper.ManageRoleMapper;
import cn.com.teacher.mapper.ManageUserRelationRoleMapper;
import cn.com.teacher.service.ManageRoleService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.ManageRoleVo;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
@Service("manangeRoleService")
public class ManageRoleServiceImpl implements ManageRoleService {
	@Autowired
	private  ManageRoleMapper manangeRoleMapper;
	@Autowired
	private ManageUserRelationRoleMapper manageUserRelationRoleMapper;
		
	/**
	 * 查询所有数据分页
	 */
	public PageInfo<ManageRole> getManangeRoleAllPage(ManageRole manageRole) throws Exception {
		if(manageRole==null){
			manageRole = new ManageRole();
		}
		PageHelper.startPage(manageRole.getPageNum(),manageRole.getPageSize());
		List<ManageRole> list = manangeRoleMapper.getManangeRoleAllPage(manageRole);
		PageInfo<ManageRole> page = new PageInfo<ManageRole>(list);
		return page;
	}


	public List<ManageRole> getManangeRoleAll() throws Exception {
		return manangeRoleMapper.getManangeRoleAll();
	}


	public Result delManageRoleById(Integer id) throws Exception {
		if (id != null) {
			manangeRoleMapper.deleteByPrimaryKey(id);
			return ResultUtil.buildSuccess("删除成功");
		}else{
		return ResultUtil.buildFail("删除失败");
		}
	}


	public Result insertManageRole(ManageRole manageRole)throws Exception {
		if(manageRole!=null) {
			manangeRoleMapper.insert(manageRole);
			return ResultUtil.buildSuccess("插入成功");
		}else{
			return	ResultUtil.buildFail("插入失败");
		}
	}


	public Result updateManageRole(ManageRole manageRole) throws Exception {
		if(manageRole != null) {
			manangeRoleMapper.updateByPrimaryKey(manageRole);
			return ResultUtil.buildSuccess("编辑成功");
		}else{
			return ResultUtil.buildFail("编辑失败");
		}
	}


	@Override
	public ManageRole selectByPrimaryKey(Integer id) throws Exception {
		return manangeRoleMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<ManageRoleVo> editBackRoleName(Integer userId) throws Exception {
		ManageUserRelationRoleExample example=new ManageUserRelationRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<ManageUserRelationRole> listExample=manageUserRelationRoleMapper.selectByExample(example);
		List<ManageRole> list=manangeRoleMapper.getManangeRoleAll();
		List<ManageRoleVo> listVo=new ArrayList<ManageRoleVo>();
		for(ManageRole manageRole:list){
			ManageRoleVo manageRoleVo=new ManageRoleVo();
			manageRoleVo.setId(manageRole.getId());
			manageRoleVo.setName(manageRole.getName());
			manageRoleVo.setRemark(manageRole.getRemark());
			for(ManageUserRelationRole manageUserRelationRole:listExample){
				if(manageUserRelationRole.getRoleId().equals(manageRole.getId())){
					manageRoleVo.setCheck(true);
					break;
				}else{
					manageRoleVo.setCheck(false);
				}
			}
			listVo.add(manageRoleVo);
		}
		return listVo;
	}

	

	
}
