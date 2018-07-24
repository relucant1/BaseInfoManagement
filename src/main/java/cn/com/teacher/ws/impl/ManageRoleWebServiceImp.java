package cn.com.teacher.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.teacher.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.service.ManageRoleService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.ws.ManageRoleWebService;
import org.springframework.transaction.annotation.Transactional;

@Service("manageRoleWebService")
public class ManageRoleWebServiceImp extends BaseServiceImpl implements ManageRoleWebService {

	@Autowired
	private ManageRoleService manageRoleService;

	@Override
	public String getManageRoles() throws Exception {
		ManageRole manageRole = getObject(ManageRole.class);
		if(null==manageRole){
			return JSON.toJSONString(ResultUtil.buildFail("请求参数转换出错！"));
		}
		Map<String,Object> m = new HashMap<String,Object>();
		try{
			List<ManageRole> list = manageRoleService.getManangeRoleAll();
			m.put("list",list);
			return JSON.toJSONString(ResultUtil.buildSuccess(m));
		}catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}

	@Override
	public String testT() throws Exception {
		ManageRole manageRole = new ManageRole();
		manageRole.setName("haha");
		manageRole.setRemark("hahahaha");
		manageRoleService.insertManageRole(manageRole);
		ManageRole manageRole2 = new ManageRole();
		manageRole.setName("haha2");
		manageRole.setRemark("hahahaha2");
		manageRoleService.insertManageRole(manageRole);
		return ResponseUtil.buildSuccess(getParamMap());
	}

}
