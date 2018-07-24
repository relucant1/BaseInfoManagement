package cn.com.teacher.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.teacher.controller.BaseController.MethodType;

/***
 * 内部账号信息Controller
 * @author hugang
 *
 */
@Controller
@RequestMapping("/innerUser")
@Api(value = "内部账号信息 API")
public class InnerUserController extends BaseController {

	@ApiOperation(value="查询内部账号列表信息", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "isRegister", value = "内部账号 isRegister=0", required =true , dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "role", value = "用户角色", required = false, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "name", value = "用户姓名", required = false, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "mobile", value = "用户手机号", required = false, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "status", value = "状态 0：停用 1 正常", required = false, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "page", value = "当前页", required = false, dataType = "Integer",paramType="query"),
       @ApiImplicitParam(name = "pagesize", value = "每页显示记录数", required = false, dataType = "Integer",paramType="query")
       
    })
	@RequestMapping(value = "findUsers", method = RequestMethod.GET) 
	public @ResponseBody String findUsers() {
		return getWsRest("user/userInfoPage", MethodType.GET);
	}
	
	@ApiOperation(value="添加内部账号", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "authorityIds", value = "用户对应的权限ID，多个用半角逗号隔开(\"1,2,3\")。", required = true, dataType = "String",paramType="query")
       
    })
	@RequestMapping(value = "addUser", method = RequestMethod.POST) 
	public @ResponseBody String addUser() {
		this.params.put("status", "1");
		return getWsRest("user/adminUser", MethodType.POST);
	}
	
	@ApiOperation(value="更新内部账号", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "userInfoId", value = "内部账号ID", required =true , dataType = "Long",paramType="query"),
       @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "authorityIds", value = "用户对应的权限ID，多个用半角逗号隔开(\"1,2,3\")。", required = true, dataType = "String",paramType="query")
       
    })
	@RequestMapping(value = "updateUser", method = RequestMethod.PUT) 
	public @ResponseBody String updateUser() {
		
		return getWsRest("user/updateUserInfo", MethodType.PUT);
	}
	
}
