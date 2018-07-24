package cn.com.teacher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;
import cn.com.teacher.entity.BaseDataDictionary;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.BaseDataDictionaryService;
import cn.com.teacher.util.ResultUtil;

@ApiIgnore
@Controller
@RequestMapping("/dictionary")
public class BaseDataDictionaryController {
    
	@Autowired
	private BaseDataDictionaryService baseDataDictionaryService;
	
	
	@RequestMapping(value = "/editnum")
	public String editnum(HttpServletRequest request, HttpServletResponse response){
		
		return "/dictionary/editnum";
	}
	
	@RequestMapping(value = "/")
	public String section(HttpServletRequest request, HttpServletResponse response){
		
		return "/dictionary/index";
	}
	
	/**
	 * 数据字典后台管理页面
	 */
	@RequestMapping(value = "/showDictionaryMain")
	public String showMain(ModelAndView mode, HttpServletRequest request) {
		
		return "/dictionary/index";
	}

	/**
	 * 获取所有数据字典
	 */
	@RequestMapping(value = "/getAll")
	@ResponseBody
	public Result getDataDictionary() {
		try{
			List<BaseDataDictionary> list= baseDataDictionaryService.getDataDictionaryAll();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list",list );
			return ResultUtil.buildSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		}
	}

}
