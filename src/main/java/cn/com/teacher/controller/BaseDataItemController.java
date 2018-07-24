package cn.com.teacher.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.teacher.entity.BaseDataDictionary;
import cn.com.teacher.entity.BaseDataItem;
import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.BaseDataDictionaryService;
import cn.com.teacher.service.BaseDataItemService;
import cn.com.teacher.util.ResultUtil;

@ApiIgnore
@Controller
@RequestMapping("/dictionary")
public class BaseDataItemController {
    
	@Autowired
	private BaseDataDictionaryService baseDataDictionaryService;
	
	@Autowired
	private BaseDataItemService baseDataItemService;
	
	/**
	 * 展示某一数据类别下的所有数据项
	 */
	@RequestMapping(value = "/showItems")
	@ResponseBody
	public Result showMain(Integer categoryId) {
		
			if(null!=categoryId){
				try{
					BaseDataDictionary dictionary = baseDataDictionaryService.getDataDictionaryById(categoryId);
					List<BaseDataItem> list= baseDataItemService.getDataItemByCategoryId(categoryId);
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("list",list );
					map.put("dictionary",dictionary);
					return ResultUtil.buildSuccess(map);
				}catch(Exception e){
					e.printStackTrace();
					return ResultUtil.buildFail("系统错误,请联系管理员");
				}
			}else{
				return ResultUtil.buildSuccess("数据类别编号为空");
			}
	}

	/**
	 * 新增数据项
	 */
	@RequestMapping(value = "/addItem",method=RequestMethod.POST)
	@ResponseBody                                                 
	public Result  insertDataItem(String name,String code,Integer categoryId) {
		
		if(StringUtils.isEmpty(name)){
			return ResultUtil.buildFail("数据项名称不能为空");
		}
		if(StringUtils.isEmpty(code)){
			return ResultUtil.buildFail("数据项编码不能为空");
		}
		if(categoryId==null){
			return ResultUtil.buildFail("数据项类别不能为空");
		}
		
		try{
	    	boolean flag = baseDataItemService.existsItemCode(categoryId, code,null);
	    	if(flag){
	    		return ResultUtil.buildFail("数据项编码已经存在");
	    	}
	    	BaseDataItem item = new BaseDataItem();
		    item.setDataItemName(name);
		    item.setDataItemCode(code);
		    item.setCategoryId(categoryId);
		    //查询当前最大的sequence
		    Integer sequence = baseDataItemService.getMaxItemSequence();
		    item.setSequence(sequence+1);
			baseDataItemService.insertItem(item);
			
			return ResultUtil.buildSuccess("添加成功");
	    }catch(Exception e){
			e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
	    }
	}
	
	/**
	 * 根据主键ID获取数据项
	 */
	@RequestMapping(value = "/getItemById")
	@ResponseBody
	public Result  getDataItemById(Integer id) {
	    if(id!=null){
	    	try{
	    		BaseDataItem item = baseDataItemService.getDataItemsById(id);
		    	Map<String,Object> map = new HashMap<String,Object>();
		    	map.put("item",item );
				return ResultUtil.buildSuccess(map);
	    	}catch(Exception e){
				e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		    }
	    	
	    }else{
	    	return ResultUtil.buildFail("请求参数错误，查询失败");
	    }
	}
	
	/**
	 * 编辑数据项
	 */
	@RequestMapping(value = "/editeItem")
	@ResponseBody
	public Result  editeDataItem(String name,String code,Integer id) {
		System.out.println("name："+name+"code："+"id"+id);
		if (name == ""||id==null || name.length()==0) {
			return ResultUtil.buildFail("请求参数不正确,编辑失败");
		}else{
			try{
				BaseDataItem item = baseDataItemService.getDataItemsById(id);
				boolean flag = baseDataItemService.existsItemCode(item.getCategoryId(), code,item.getId());
		    	if(flag){
		    		return ResultUtil.buildFail("数据项编码已经存在");
		    	}
			    item.setDataItemName(name);
			    item.setDataItemCode(code);
				baseDataItemService.updateItem(item);
				return ResultUtil.buildSuccess("编辑成功");
			}catch(Exception e){
				e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		    }
		}
	}
	
	/**
	 * 数据项排序
	 */
	@RequestMapping(value = "/moveItem")
	@ResponseBody
	public Result  moveDataItem(Integer currId,Integer nextId) {
		
		if(null==currId || null==nextId){
			return ResultUtil.buildFail("请求参数不正确");
		}else{
			try{
				Map map = new HashMap<String,Object>();
				List<BaseDataItem> list = new ArrayList<BaseDataItem>();
				list.add(baseDataItemService.getDataItemsById(currId));
				list.add(baseDataItemService.getDataItemsById(nextId));
				
				return baseDataItemService.moveItem(list);
			}catch(Exception e){
				e.printStackTrace();
				return ResultUtil.buildFail("排序失败");
		    }
		}
	}

	/**
	 * 删除数据项
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteItem")
	@ResponseBody
	public String  deleDataItem(Integer id) {
		String res = JSON.toJSONString(baseDataItemService.deleteItem(id));
		return res;
	}
	
}
