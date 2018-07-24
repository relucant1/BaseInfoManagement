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
 * 系统信息控制器
 * @author hugang
 *
 */
@Controller
@RequestMapping("/project")
@Api(value = "系统信息 API")
public class ProjectController extends BaseController {

	/**
	 * 获取所有系统信息
	 * @return
	 */
	 @ApiOperation(value="获取所有系统信息", notes="")
	 @RequestMapping(value = "getAll", method = RequestMethod.GET) 
	 public @ResponseBody String getAll() {
		 return getWsRest("project/project", MethodType.GET);
	 }
	 
}
