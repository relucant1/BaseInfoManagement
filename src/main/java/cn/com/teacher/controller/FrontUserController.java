package cn.com.teacher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.teacher.controller.BaseController.MethodType;

@Controller
@RequestMapping("/frontUser")
@Api(value = "用户信息 API")
public class FrontUserController extends BaseController {

	@ApiOperation(value="查询用户列表信息", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "isRegister", value = "用户 isRegister=1", required =true , dataType = "String",paramType="query"),
       @ApiImplicitParam(name = "idnumber", value = "身份证号", required = false, dataType = "String",paramType="query"),
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
	
	@ApiOperation(value="查询用户信息详情", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "userId", value = "内部账号ID", required =true , dataType = "Long",paramType="query")
       
    })
	@RequestMapping(value = "findUser", method = RequestMethod.GET) 
	public @ResponseBody String findUser() {
		return getWsRest("user/userInfo", MethodType.GET);
	}
	
	@RequestMapping(value = "index")
	public ModelAndView showMain(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		ModelAndView modelAndView = new ModelAndView("userAdmin/index");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "details")
	public ModelAndView details(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		ModelAndView modelAndView = new ModelAndView("userAdmin/details");
		return modelAndView;
	}
	
}
