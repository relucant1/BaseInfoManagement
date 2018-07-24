package cn.com.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;
import cn.com.teacher.entity.ManageRoleRelationAuthority;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.ManageRoleRelationAuthorityService;
import cn.com.teacher.service.ManageRoleService;
import cn.com.teacher.service.ManageUserRelationRoleService;
import cn.com.teacher.util.ResultUtil;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
@ApiIgnore
@Controller
@RequestMapping("/manageRoleRelationAuthorityCtl")
public class ManageRoleRelationAuthorityController {
	@Autowired 
	private ManageRoleRelationAuthorityService manageRoleRelationAuthorityService;
	@Autowired
	private ManageRoleService manageRoleService;
	@Autowired
	private ManageUserRelationRoleService manageUserRelationRoleService;
	
	/** 
	 * 删除
	 * @param response
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delManageRoleMain", method = RequestMethod.POST)
	@ResponseBody
	public Result delManageRole(Integer id) throws Exception {
		if (id == null) {
			return ResultUtil.buildFail("删除失败");
		}else{
			manageRoleService.delManageRoleById(id);
			ManageRoleRelationAuthority manageRoleRelationAuthority=new ManageRoleRelationAuthority();
			manageRoleRelationAuthority.setRoleId(id);
			manageRoleRelationAuthorityService.deleteRoleRelationAuthorityByRoleId(manageRoleRelationAuthority);
			ManageUserRelationRoleExample example=new ManageUserRelationRoleExample();
			example.createCriteria().andRoleIdEqualTo(id);
			manageUserRelationRoleService.deleteByExample(example);
		}
		return ResultUtil.buildSuccess("删除成功");
	}
	/**
	 * 插入权限角色
	 * @param manageRoleRelationAuthority
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="insertRoleAuthority",method = RequestMethod.POST )
	@ResponseBody
	public Result insertRoleAuthoritys(Integer setAutority_roleId ,String arr) throws Exception{
		if (setAutority_roleId == null && arr.length()==0) {
			return ResultUtil.buildFail("添加失败");
		}else{ 
			ManageRoleRelationAuthority manageRoleRelationAuthority=new ManageRoleRelationAuthority();
			manageRoleRelationAuthority.setRoleId(setAutority_roleId);
			manageRoleRelationAuthorityService.deleteRoleRelationAuthorityByRoleId(manageRoleRelationAuthority);
			String aString[]=arr.split(",");
			for (int i = 0; i < aString.length; i++) {
				manageRoleRelationAuthority.setRoleId(setAutority_roleId);
				manageRoleRelationAuthority.setAuthorityId(Integer.valueOf(aString[i]));	
				manageRoleRelationAuthorityService.insertRoleAuthority(manageRoleRelationAuthority);
			}
			return ResultUtil.buildSuccess("添加成功");
		}
	}

}
