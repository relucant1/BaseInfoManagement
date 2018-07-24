package cn.com.teacher.mapper;

import java.util.List;
import java.util.Map;



import cn.com.teacher.entity.Page;
import cn.com.teacher.entity.User;
import cn.com.teacher.entity.UserDto;

public interface UserMapper {
	
    /**
     * 查询所有,分页
     * @return
     * @throws Exception
     */
    List<UserDto> getUserAllPage(Page page)throws Exception;
	
	List<User>  selectAll();
	
	List<UserDto>  selectByConditionPage(Map<String,Object> map);
	
	List<User>  selectByCondition(Map<String,Object> map);
	
	User selectByUserId(Integer userId);
	
	int updateByPrimaryKeySelective(User user);

}
