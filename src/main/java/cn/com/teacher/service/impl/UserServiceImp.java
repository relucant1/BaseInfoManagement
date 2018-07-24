package cn.com.teacher.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.Page;
import cn.com.teacher.entity.User;
import cn.com.teacher.entity.UserDto;
import cn.com.teacher.mapper.UserMapper;
import cn.com.teacher.service.UserService;

@Service("user")
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User>  getAll(){
		
		return userMapper.selectAll();
	}
	
	/**
	 * 查询所有数据分页
	 */
	@Override
	public PageInfo<UserDto> getUserAllPage(Page page) throws Exception {
		if(page==null){
			page = new Page();
		}
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<UserDto> list = userMapper.getUserAllPage(page);
		PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(list);
		return pageInfo;
	}
	
	@Override
	public List<User> selectByCondition(Map<String,Object> map){
		
		List<User> list = userMapper.selectByCondition(map);
		return list;
	}
	
	@Override
	public PageInfo<UserDto> selectByConditionPage(Map<String,Object> map){
		
		PageHelper.startPage((Integer)map.get("pageNum"),(Integer)map.get("pageSize"));
		List<UserDto> list = userMapper.selectByConditionPage(map);
		PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(list);
		return pageInfo;
	}
	
	@Override
	public	int updateUser(User user){
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User selectByUserId(Integer userId) {
		return	userMapper.selectByUserId(userId);
	}

}
