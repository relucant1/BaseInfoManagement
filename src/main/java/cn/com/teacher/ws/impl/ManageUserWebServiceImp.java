package cn.com.teacher.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.mapper.ManageRoleMapper;
import cn.com.teacher.mapper.ManageUserMapper;
import cn.com.teacher.mapper.ManageUserRelationRoleMapper;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.ManageUserManageRoleVo;
import cn.com.teacher.ws.ManageUserWebService;

@Service("manageUserWebService")
public class ManageUserWebServiceImp extends BaseServiceImpl implements ManageUserWebService {

	@Autowired
	private ManageUserMapper manageUserMapper;
	@Autowired
	private ManageRoleMapper manageRoleMapper;
	@Autowired
	private ManageUserRelationRoleMapper manageUserRelationRoleMapper;
	
	@Override
	public String getManageUsersRole() throws Exception {
		
		ManageUserManageRoleVo manageUserManageRoleVo = getObject(ManageUserManageRoleVo.class);
		if(null==manageUserManageRoleVo){
			return JSON.toJSONString(ResultUtil.buildFail("请求参数转换出错！"));
		}
		if(manageUserManageRoleVo.getRoleId()==null){
			manageUserManageRoleVo.setRoleId(-1);
		}
		
		PageHelper.startPage(manageUserManageRoleVo.getPageNum(),manageUserManageRoleVo.getPageSize());
		//根据分页数据和角色及姓名查询管理员账户
		if(manageUserManageRoleVo.getName()!=null&&manageUserManageRoleVo.getName().length()>0){
			//解决中文乱码
			manageUserManageRoleVo.setName(new String(manageUserManageRoleVo.getName().getBytes("ISO-8859-1")));
		}  
		
		List<ManageUserManageRoleVo> list= manageUserMapper.getManageUserManageRoleVoAllPage(manageUserManageRoleVo);
		PageInfo<ManageUserManageRoleVo> page = new PageInfo<ManageUserManageRoleVo>(list);
		//查询管理员对应的所有角色
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
		
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("page",page);
		return JSON.toJSONString(ResultUtil.buildSuccess(m));
	}

}
