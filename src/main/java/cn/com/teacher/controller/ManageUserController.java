package cn.com.teacher.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.ManageUser;
import cn.com.teacher.entity.ManageUserExample;
import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.ManageUserRelationRoleService;
import cn.com.teacher.service.ManageUserService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.ManageUserManageRoleVo;
/**
 * Created by zhouzhiyuan on 16/12/30.
 */
@ApiIgnore
@Controller
@RequestMapping("/manageUserCtl")
public class ManageUserController {
	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private ManageUserRelationRoleService manageUserRelationRoleService;
	
	/**
	 * 用户后台管理页面
	 * @return
	 */
	@RequestMapping(value="userMain")
	public String showUserMain(){
		return "/userManagement/index";
	}
	/**
	 * 拼接后台用户展示分页
	 * @param manageUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="userMainPage")
	@ResponseBody
	public String findByPage(@RequestBody ManageUserManageRoleVo manageUserManageRoleVo)throws Exception{
		try{
			if(manageUserManageRoleVo.getRoleId()==null){
				manageUserManageRoleVo.setRoleId(-1);
			}
			PageInfo<ManageUserManageRoleVo> page = manageUserService.getManageUserManageRoleVoAllPage(manageUserManageRoleVo);
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("page",page);
			return JSON.toJSONString(ResultUtil.buildSuccess(m));
		}catch(Exception e){
			e.printStackTrace();
			return  JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}
	/**
	 * 停用User改变状态User_id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateStopUser", method = RequestMethod.POST)
	@ResponseBody
	public Result updateStopUsers(Integer id) throws Exception {
		if (id == null ) {
			return ResultUtil.buildFail("停用失败");
		}else{
		ManageUser manageUser=new ManageUser();
		manageUser.setId(id);
		manageUser.setIsStart(false);
		manageUser.setCreateDate(new Date());
		manageUserService.updateIsStartByPrimaryKey(manageUser);
		return ResultUtil.buildSuccess("停用成功");
		}
	}
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping(value="addUserMain")
	public String addUserMains(){
		return "/userManagement/addUser";
	}
	
	@RequestMapping(value="addUser")
	public String addUser(){
		return "/userManagement/addUser";
	}
	
	/**
	 * 插入User
	 * @param userName
	 * @param mobile
	 * @param password
	 * @param arr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="insertUser", method = RequestMethod.POST)
	@ResponseBody
	public Result insertUsers(String userName,String mobile,String password,String arr) throws Exception{
		ManageUserExample examples=new ManageUserExample();
		examples.createCriteria().andMobileEqualTo(mobile);
		List<ManageUser> lists=manageUserService.selectByExample(examples);
		for (ManageUser ms:lists) {
			if(mobile.equals(ms.getMobile())){
				return ResultUtil.buildFail("该账号已被注册");
			}
		}
		if (userName == "" ||mobile==""||password=="" || arr.length()==0) {
			return ResultUtil.buildFail("请求参数错误，添加失败");
		}else{
			ManageUser manageUser=new ManageUser();
			manageUser.setCreateDate(new Date());
			manageUser.setCreatorId(11);
			manageUser.setIsStart(true);
			manageUser.setMobile(mobile);
			manageUser.setName(userName);
			manageUser.setPassword(password);
			System.err.println(manageUser.getId());
			manageUserService.insertManageUser(manageUser);
			ManageUserExample example=new ManageUserExample();
			example.createCriteria().andMobileEqualTo(mobile);
			List<ManageUser> list=manageUserService.selectByExample(example);
			String aString[]=arr.split(",");
			for (int i = 0; i < aString.length; i++) {
				ManageUserRelationRole manageUserRelationRole=new ManageUserRelationRole();
				manageUserRelationRole.setRoleId(Integer.valueOf(aString[i]));
				for(ManageUser user:list){
					manageUserRelationRole.setUserId(user.getId());
				}
				manageUserRelationRoleService.insertManageUserRelationRole(manageUserRelationRole);
			}
			return ResultUtil.buildSuccess("添加成功");
		}
	}
	/**
	 * 跳转编辑页面
	 * @return
	 */
	@RequestMapping(value="editUserMain")
	public ModelAndView editUserMains(Integer id){
		ModelAndView mv=new ModelAndView("/userManagement/editUserManage");
		mv.addObject("userId",id);
		return mv;
	}
	/**
	 * 编辑回显复选框
	 * @param id
	 * @return
	 */
	@RequestMapping(value="editUserManage")
	@ResponseBody
	public String editUserManages(Integer id){
		return null;
	}
	/**
	 * 编辑User
	 * @param userName
	 * @param mobile
	 * @param password
	 * @param arr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateUser")
	@ResponseBody
	public Result updateUsers(String userName,String mobile,String password,String arr,Integer userId) throws Exception{
		if (userName == "" ||mobile==""||password=="" || arr.length()==0 || userId==null) {
			return ResultUtil.buildFail("编辑失败");
		}else{
			ManageUser manageUser=new ManageUser();
			manageUser.setCreateDate(new Date());
			manageUser.setCreatorId(11);
			manageUser.setIsStart(true);
			manageUser.setMobile(mobile);
			manageUser.setName(userName);
			manageUser.setPassword(password);
			manageUser.setId(userId);
			manageUserService.updateManageUserByPrimaryKey(manageUser);
			ManageUserRelationRoleExample example=new ManageUserRelationRoleExample();
			example.createCriteria().andUserIdEqualTo(userId);
			manageUserRelationRoleService.deleteByExample(example);
			String aString[]=arr.split(",");
			for (int i = 0; i < aString.length; i++) {
				ManageUserRelationRole manageUserRelationRole=new ManageUserRelationRole();
				manageUserRelationRole.setUserId(userId);
				manageUserRelationRole.setRoleId(Integer.valueOf(aString[i]));
				manageUserRelationRoleService.insertManageUserRelationRole(manageUserRelationRole);
			}
			return ResultUtil.buildSuccess("添加成功");
		}
	}
	
}
