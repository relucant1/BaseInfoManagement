package cn.com.teacher.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.Page;
import cn.com.teacher.entity.User;
import cn.com.teacher.entity.UserDto;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.UserService;
import cn.com.teacher.util.JsonUtil;
import cn.com.teacher.util.ResultUtil;

@ApiIgnore
@Controller
@RequestMapping("/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	/**
	 * 用户管理页面
	 */
	@RequestMapping(value = "/showMain")
	public String showMain(ModelAndView mode, HttpServletRequest request) {
		
		return "/user/index";
	}

	/**
	 * 获取所有用户信息 分页
	 */
	@RequestMapping(value="/showUserMain")
	@ResponseBody
	public Result findByPage(@RequestBody Page page)throws Exception{
		try{
			PageInfo<UserDto> pageInfo = userService.getUserAllPage(page);
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("page",pageInfo);
			return ResultUtil.buildSuccess(m);
		}catch(Exception e){
			e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		}
	}
	
	
	/**
	 * 根据主键ID获取数据项
	 */
	@RequestMapping(value = "/getUserById")
	@ResponseBody
	public Result  getUserById(Integer userId) {
	    if(userId!=null){
	    	try{
	    		User user = userService.selectByUserId(userId);
		    	Map<String,Object> map = new HashMap<String,Object>();
		    	map.put("user",user);
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
	 * 根据查询条件获取用户列表 分页
	 */
	@RequestMapping(value = "/getUserByCondition", method = RequestMethod.POST)
	@ResponseBody
	public Result  getUserByCondition(HttpServletRequest request,
			HttpServletResponse response,@RequestBody JSONObject jsonObject) {
		
		 try{
			  //判断参数
			 PageInfo<UserDto> pageInfo  = null;
			  Map<String,Object> params = judgeCondition(jsonObject);
			  
			  if(!params.isEmpty()){
				  pageInfo = userService.selectByConditionPage(params);
			  }else{
				  Page page = new Page();
				  page.setPageNum(jsonObject.getInteger("pageNum"));
				  page.setPageSize(jsonObject.getInteger("pageSize"));
				  pageInfo= userService.getUserAllPage(page);
			  }
			  Map<String,Object> map = new HashMap<String,Object>();
		      map.put("list",pageInfo);
			  return ResultUtil.buildSuccess(map);
	        }catch(Exception e){
				e.printStackTrace();
				return ResultUtil.buildFail("系统错误,请联系管理员");
			}
	    
	}
	
	/**
	 * 编辑用户信息
	 */
	@RequestMapping(value = "/editeUser", method = RequestMethod.POST)
	@ResponseBody
	public Result  editeUser(HttpServletRequest request,
			HttpServletResponse response,@RequestBody JSONObject jsonObject) {
		
		try{
			 //判断参数
			 if(judgeParams(jsonObject).getIsSuccess()!=1){
				 return ResultUtil.buildFail(judgeParams(jsonObject).getMsg());
			 }
			 
			 //数据转换
			 User user = convertToUser(jsonObject);
			 
			 //更新user
			 int flag =  userService.updateUser(user);
			 if(flag==0){
				return ResultUtil.buildFail("更新数据失败");
			 }
			 return ResultUtil.buildSuccess("编辑成功");
			 
		  }catch(Exception e){
			   e.printStackTrace();
			   return ResultUtil.buildFail("系统错误,请联系管理员");
	      } 
		
	}
	
	/**
	 * 批量停用
	 */
	@RequestMapping(value = "/batchDisable", method = RequestMethod.POST)
	@ResponseBody
	public Result  batchDisable(HttpServletRequest request,
			HttpServletResponse response,@RequestBody JSONObject jsonObject) {
		
		try{
			
			//数据转换
			List<User> list = convertToUsers(jsonObject);
			if(null == list || list.size()==0){
				return ResultUtil.buildFail("请求参数错误，处理失败");
			}
			
			//更新user
			for(User user:list){
				user.setStatus(false);
				int flag =  userService.updateUser(user);
				if(flag==0){
					return ResultUtil.buildFail("更新数据失败");
				}
			}
			
			return ResultUtil.buildSuccess("编辑成功");
			 
		  }catch(Exception e){
			   e.printStackTrace();
			   return ResultUtil.buildFail("系统错误,请联系管理员");
	      } 
	}

	/**
	 * 批量恢复
	 */
	@RequestMapping(value = "/batchAvailable", method = RequestMethod.POST)
	@ResponseBody
	public Result  batchAvailable(HttpServletRequest request,
			HttpServletResponse response,@RequestBody JSONObject jsonObject) {
		
		try{
			
			//数据转换
			List<User> list = convertToUsers(jsonObject);
			if(null == list || list.size()==0){
				return ResultUtil.buildFail("请求参数错误，处理失败");
			}
		     
			//更新user
			for(User user:list){
				user.setStatus(true);
				int flag =  userService.updateUser(user);
				if(flag==0){
					return ResultUtil.buildFail("更新数据失败");
				}
			}
			 
			return ResultUtil.buildSuccess("编辑成功");
			 
		  }catch(Exception e){
			   e.printStackTrace();
			   return ResultUtil.buildFail("系统错误,请联系管理员");
	      } 
		
	}
	
	@RequestMapping(value = "/export")
	@ResponseBody
    public Result export(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		  try {
			  List<User>  list = new ArrayList<User>();
			  //Map<String,Object> params = judgeCondition(jsonObject);
			  Map<String,Object> params = new HashMap<String,Object>();
			  
			  if(!params.isEmpty()){
				 
					list = userService.selectByCondition(params);
				  
			  }else{
				  list= userService.getAll();
				 
			  }
			  Map<String,Object> map = new HashMap<String,Object>();
		      map.put("list",list);
		      
		      /*QrmallCashExcelView view = new QrmallCashExcelView();
		      HSSFWorkbook workbook=new HSSFWorkbook();
		      view.buildExcelDocument(map, workbook, request, response);*/
		      return ResultUtil.buildSuccess("导出成功");
		  } catch (Exception e) {
				e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		  }
    }
	
	
	/**
	 * 导出结果到Excel
	 */
	@RequestMapping(value = "/exportToExcel")
	@ResponseBody
	public Result exportToExcel(HttpServletRequest request,
			HttpServletResponse response,@RequestBody JSONObject jsonObject){
		
		try{
			
			List<User> list = new ArrayList<User>();
			//判断参数
			Map<String,Object> params = judgeCondition(jsonObject);
			  
			if(!params.isEmpty()){
			   list = userService.selectByCondition(params);
			 }else{
			   list= userService.getAll();
			}
			
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list",list );
			return ResultUtil.buildSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		}
		
	}
	
	/**
	 * 获取所有用户信息
	 */
	@RequestMapping(value = "/getAll")
	@ResponseBody
	public Result getAll() {
		try{
			List<User> list= userService.getAll();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list",list );
			return ResultUtil.buildSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResultUtil.buildFail("系统错误,请联系管理员");
		}
	}
	
	public Result judgeParams(JSONObject jsonObject) {
		
		 if(JsonUtil.isEmpty(jsonObject, "userId")){
			 return ResultUtil.buildFail("userId不能为空！");
		 }
	
		 if(JsonUtil.isEmpty(jsonObject, "name")){
			 return ResultUtil.buildFail("姓名不能为空！");
		 }
		 if(JsonUtil.isEmpty(jsonObject, "password")){
			 return ResultUtil.buildFail("密码不能为空！");
		 }
		 if(JsonUtil.isEmpty(jsonObject, "type")){
			 return ResultUtil.buildFail("类型不能为空！");
		 }
		 if(JsonUtil.isEmpty(jsonObject, "studyStageSubject")){
			 return ResultUtil.buildFail("学段学科不能为空！");
		 }
		 if(JsonUtil.isEmpty(jsonObject, "region")){
			 return ResultUtil.buildFail("区域不能为空！");
		 }
		 return ResultUtil.buildSuccess("");
	}
	
	public Map<String,Object> judgeCondition(JSONObject jsonObject) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		 
		 if(!JsonUtil.isEmpty(jsonObject, "name")){
			map.put("name", jsonObject.getString("name"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "mobile")){
			 map.put("mobile", jsonObject.getString("mobile"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "idNumber")){
			 map.put("idNumber", jsonObject.getString("idNumber"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "studyStageSubject")){
			 map.put("studyStageSubject", jsonObject.getString("studyStageSubject"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "type")){
			 map.put("type", jsonObject.getInteger("type"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "region")){
			 map.put("region", jsonObject.getString("region"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "status")){
			 map.put("status", jsonObject.getBooleanValue("status"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "pageNum")){
			 map.put("pageNum", jsonObject.getInteger("pageNum"));
		 }
		 if(!JsonUtil.isEmpty(jsonObject, "pageSize")){
			 map.put("pageSize", jsonObject.getInteger("pageSize"));
		 }
		 return map;
	}
	
	/*
	 * Json 转换为User
	 */
	public User convertToUser(JSONObject jsonObject) throws IOException {
		
		 User user = new User();
		 //photo转换为字节数组
		 Object obj = jsonObject.get("photo");
		 ByteArrayOutputStream bo = new ByteArrayOutputStream(); 
		 ObjectOutputStream oo = new ObjectOutputStream(bo); 
		 oo.writeObject(obj); 
		 byte[] bytes = bo.toByteArray();
		 //json数据转换为User对象
		 jsonObject.remove("photo");
		 user = JSONObject.toJavaObject(jsonObject,User.class);
		 user.setPhoto(bytes);
		 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String updateTime = fmt.format(new Date());
		 user.setUpdateTime(updateTime);
		 
		 return user;
	}
	
	/**
	 * json数组转换为List<User>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public List<User> convertToUsers(JSONObject jsonObject) throws Exception {
		
		List<User> list = null;
		
		String s = jsonObject.getString("ids");
		System.out.println(s);
		if(s.length()!=0){
			list = new ArrayList<User>();
			String[] ids = s.split(",");
			System.out.println(ids);
			for(int i=0;i<ids.length;i++){
				System.out.println(ids[i]);
				User user = userService.selectByUserId(Integer.valueOf(ids[i]));
				if(null==user){
					return null;
				}
				list.add(user);
			}
		}
		return list;
	}
	
}
