package cn.com.teacher.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.Page;
import cn.com.teacher.entity.User;
import cn.com.teacher.entity.UserDto;

/**
 * 用户信息服务类
 * @author lili
 * 2017-05-19 上午10:49:50
 */
public interface UserService {
	
	/**
	 * 获取所有用户 分页
	 */
	PageInfo<UserDto> getUserAllPage(Page page) throws Exception;
	
	/**
	 * 获取所有用户信息
	 */
	List<User>  getAll() throws Exception;
	
	/**
	 * 根据查询条件获取用户
	 */
	List<User> selectByCondition(Map<String,Object> map)throws Exception;
	
	/**
	 * 根据查询条件获取用户列表 分页
	 */
	PageInfo<UserDto> selectByConditionPage(Map<String,Object> map) throws Exception;
	
	/**
	 * 根据主键id获取用户信息
	 */
	User selectByUserId(Integer userId) throws Exception;
	
	/**
	 * 修改用户信息
	 */
	int updateUser(User user) throws Exception;


}
