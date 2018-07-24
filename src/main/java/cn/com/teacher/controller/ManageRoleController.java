package cn.com.teacher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.entity.ManageUser;
import cn.com.teacher.entity.ManageUserExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.ManageRoleService;
import cn.com.teacher.service.ManageUserService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.ManageRoleVo;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
@ApiIgnore
@Controller
@RequestMapping("/manangeRoleCtl")
public class ManageRoleController {
	@Autowired
	private ManageRoleService manageRoleService;
	@Autowired
	private ManageUserService manageUserService;
	/**
	 * 角色管理数据展示
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="showManageRole")
	public String showMain(HttpServletRequest request) throws Exception{
		return "/role/index";
	}
	
	@RequestMapping(value="manageRoleLimit")
	public String manageRoleLimit(HttpServletRequest request) throws Exception{
		return "role/roleLimit";
	}
	
	/**
	 * 角色管理数据展示,分页
	 * @param manageRole
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="showRoleMain")
	@ResponseBody
	public Result findByPage(@RequestBody ManageRole manageRole)throws Exception{
		try{
			PageInfo<ManageRole> page = manageRoleService.getManangeRoleAllPage(manageRole);
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("page",page);
			return ResultUtil.buildSuccess(m);
		}catch(Exception e){
			e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		}
	}
	/**
	 * 插入角色
	 * @param request
	 * @param response
	 * @param roleName
	 * @param remark
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "insertManageRole", method = RequestMethod.POST)
	@ResponseBody
	public String insertManageRoles(String roleRemarks,String roleNames) throws Exception {
		if (roleNames == "" || roleRemarks=="") {
			return JSON.toJSONString(ResultUtil.buildFail("添加失败"));
		}else{
			ManageRole manageRole=new ManageRole();
			manageRole.setName(roleNames);
			manageRole.setRemark(roleRemarks);
		return JSON.toJSONString(manageRoleService.insertManageRole(manageRole));
		}
	}
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateManageRole", method = RequestMethod.POST)
	@ResponseBody
	public String updateManageRoles(String roleName,Integer id,String roleRemark) throws Exception {
		if (roleName == "" || roleRemark=="" ) {
			return JSON.toJSONString(ResultUtil.buildFail("编辑失败"));
		}else{
		ManageRole manageRole=new ManageRole();
		manageRole.setId(id);
		manageRole.setName(roleName);
		manageRole.setRemark(roleRemark);
		return JSON.toJSONString(manageRoleService.updateManageRole(manageRole));
		}
	}
	/**
	 * 添加User回显roleName
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="backRoleName")
	@ResponseBody
	public String backRoleNames() throws Exception{
		try{
			Map<String,Object> m = new HashMap<String,Object>();
			List<ManageRole> list=manageRoleService.getManangeRoleAll();
			m.put("list",list);
			return JSON.toJSONString(ResultUtil.buildSuccess(m));
		}catch(Exception e){
			e.printStackTrace();
			return  JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}
	/**
	 * 编辑User回显checkTure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="editBackRoleName")
	@ResponseBody
	public String editBackRoleNames(Integer userId) throws Exception{
		try{
			Map<String,Object> m = new HashMap<String,Object>();
			List<ManageRoleVo> list=manageRoleService.editBackRoleName(userId);
			ManageUserExample example=new ManageUserExample();
			example.createCriteria().andIdEqualTo(userId);
			List<ManageUser> userList=manageUserService.selectByExample(example);
			m.put("list",list);
			m.put("user", userList);
			return JSON.toJSONString(ResultUtil.buildSuccess(m));
		}catch(Exception e){
			e.printStackTrace();
			return  JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}
}
	
	

					

