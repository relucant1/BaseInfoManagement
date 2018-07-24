package cn.com.teacher.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.com.teacher.entity.AuthorityResource;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.AuthorityResourceService;
import cn.com.teacher.vo.FindAuthsConditionVo;
import cn.com.teacher.vo.RoleAuthorityVo;

/***
 * 角色权限控制器
 * @author hugang
 *
 */
@Controller
@RequestMapping("/authority")
@Api(value = "角色权限 API")
public class AuthorityController extends BaseController {

	@Autowired
	private AuthorityResourceService authorityResourceService;
	
	/**
	 * 查询角色对应的权限信息
	 * @return
	 */
	@ApiOperation(value="查询角色对应的权限信息", notes="",response=AuthorityResource.class)
	@ApiImplicitParams({
       @ApiImplicitParam(name = "findAuthsCondition",value = "查询权限信息对象 ", required = true, dataType = "FindAuthsConditionVo")
    })
	@RequestMapping(value = "findAuthsByRole", method = RequestMethod.POST)
	public @ResponseBody String findAuths(@RequestBody FindAuthsConditionVo findAuthsCondition){
		SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,
	    		SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty};
		String res = JSON.toJSONString(authorityResourceService.findAuths(findAuthsCondition),features);
		return res;
	}
	
	/**
	 * 设置角色权限
	 * @param roleAuthority
	 * @return
	 */
	@ApiOperation(value="设置角色权限", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "roleAuthority",value = "权限设置对象", required = true, dataType = "RoleAuthorityVo")
    })
	@RequestMapping(value = "saveAuths", method = RequestMethod.POST)
	public @ResponseBody String saveAuths(@RequestBody RoleAuthorityVo roleAuthority){
		String res = JSON.toJSONString(authorityResourceService.saveRoleAuth(roleAuthority));
		return res;
	}
	
	@ApiOperation(value="获取当前用户系统菜单", notes="")
	@ApiImplicitParams({
       @ApiImplicitParam(name = "accessToken",value = "访问token", required = true, dataType = "String",paramType="query")
    })
	@RequestMapping(value = "getMenus", method = RequestMethod.GET)
	public @ResponseBody String getMenus(String accessToken){
		String res = JSON.toJSONString(authorityResourceService.getMenus(accessToken));
		return res;
	}
}
