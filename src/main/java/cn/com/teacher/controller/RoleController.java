package cn.com.teacher.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.teacher.controller.BaseController.MethodType;
import cn.com.teacher.entity.Result;

/**
 * 角色管理控制器
 * @author hugang
 *
 */
@Controller
@RequestMapping("/role")
@Api(value = "角色信息 API")
public class RoleController extends BaseController {

	/**
	 * 查询角色列表信息
	 * @return
	 */
	 @ApiOperation(value="查询后台角色列表信息", notes="groupId=1后台角色,groupId=2常态用户,groupId=3项目内角色")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "groupId", value = "系统ID，此处查询后台角色所以为1", required = true, dataType = "Long",paramType="query"),
        @ApiImplicitParam(name = "page", value = "当前页", required = false, dataType = "Integer",paramType="query"),
        @ApiImplicitParam(name = "pagesize", value = "每页显示记录数", required = false, dataType = "Integer",paramType="query")
        
     })
	 @RequestMapping(value = "findRoles", method = RequestMethod.GET) 
	 public @ResponseBody String findRoles() {
		 return getWsRest("role/roleList", MethodType.GET);
	 }
	 
	 /**
	  * 新增角色信息
	  * @return
	  */
	 @ApiOperation(value="新增角色信息", notes="")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "groupId", value = "系统ID，此处查询后台角色所以为1", required = true, dataType = "Long",paramType="query"),
        @ApiImplicitParam(name = "authority", value = "角色编码", required = true, dataType = "String",paramType="query"),
        @ApiImplicitParam(name = "description", value = "角色名称", required = true, dataType = "String",paramType="query")
     })
	 @RequestMapping(value = "addRole", method = RequestMethod.POST) 
	 public @ResponseBody String addRole() {
		 return getWsRest("role/role", MethodType.POST);
	 }
	 
	 /**
	  * 修改角色信息
	  * @return
	  */
	 @ApiOperation(value="修改角色信息", notes="")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType="query"),
        @ApiImplicitParam(name = "authority", value = "角色编码", required = true, dataType = "String",paramType="query"),
        @ApiImplicitParam(name = "description", value = "角色名称", required = true, dataType = "String",paramType="query")
     })
	 @RequestMapping(value = "updateRole", method = RequestMethod.PUT) 
	 public @ResponseBody String updateRole() {
		 return getWsRest("role/role", MethodType.PUT);
	 }
	 
	 /**
	  * 删除角色信息
	  * @return
	  */
	 @ApiOperation(value="删除角色信息", notes="")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long",paramType="query")
     })
	 @RequestMapping(value = "deleteRole", method = RequestMethod.POST) 
	 public @ResponseBody String deleteRole() {
		 return getWsRest("role/deleteRole", MethodType.POST);
	 }
	 
	/**
	 * 查询角色信息
	 * @return
	 */
	 @ApiOperation(value="查询角色信息", notes="",response=Result.class)
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long",paramType="query")
     })
	 @RequestMapping(value = "getRole", method = RequestMethod.GET) 
	 public @ResponseBody String getRole() {
		 return getWsRest("role/role", MethodType.GET);
	 }
}
