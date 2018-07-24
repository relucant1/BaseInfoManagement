package cn.com.teacher.mapper;

import cn.com.teacher.entity.ManageUser;
import cn.com.teacher.entity.ManageUserExample;
import cn.com.teacher.vo.ManageUserManageRoleVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;

public interface ManageUserMapper {
    int countByExample(ManageUserExample example);

    int deleteByExample(ManageUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManageUser record)throws Exception;

    int insertSelective(ManageUser record);

    List<ManageUser> selectByExample(ManageUserExample example)throws Exception;

    ManageUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManageUser record, @Param("example") ManageUserExample example);

    int updateByExample(@Param("record") ManageUser record, @Param("example") ManageUserExample example);

    int updateByPrimaryKeySelective(ManageUser record);

    int updateByPrimaryKey(ManageUser record) throws Exception;
    /**
     * 根据id停用User
     * @param record
     * @return
     * @throws Exception
     */
    int updateIsStartByPrimaryKey(ManageUser record) throws Exception;
    /**
	 * 查询所有ManageUser信息 
	 * @return
	 * @throws Exception
	 */
	List<ManageUser> getManageUserAll()throws Exception;
	/**
	 * 后台拼接分页
	 * @param manageRole
	 * @return
	 * @throws Exception
	 */
	List<ManageUserManageRoleVo> getManageUserManageRoleVoAllPage(ManageUserManageRoleVo manageUserManageRoleVo)throws Exception;
}