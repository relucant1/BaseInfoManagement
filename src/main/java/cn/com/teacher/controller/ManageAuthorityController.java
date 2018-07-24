package cn.com.teacher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.ManageAuthorityService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.ManageAuthorityVo;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
@ApiIgnore
@Controller
@RequestMapping("/manageAuthorityCtl")
public class ManageAuthorityController {
	@Autowired
	private ManageAuthorityService manageAuthorityService;
	
	/**
	 * 权限管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="setAuthority" )
	@ResponseBody
	public Result setAuthoritys(Integer roleId) throws Exception{
			try{
				Map<String,Object> map= new HashMap<String,Object>();
				List<ManageAuthorityVo> aList = manageAuthorityService.setAuthoritySplit(roleId);
				map.put("aList",aList);
				return ResultUtil.buildSuccess(map);
			}catch(Exception e){
				e.printStackTrace();
				return ResultUtil.buildFail("系统错误,请联系管理员");
			}
		}
}
