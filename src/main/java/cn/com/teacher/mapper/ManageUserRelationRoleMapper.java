package cn.com.teacher.mapper;

import cn.com.teacher.entity.ManageUserRelationRole;
import cn.com.teacher.entity.ManageUserRelationRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageUserRelationRoleMapper {
    int countByExample(ManageUserRelationRoleExample example);

    int deleteByExample(ManageUserRelationRoleExample example)throws Exception;

    int deleteByPrimaryKey(Integer id);

    int insert(ManageUserRelationRole record) throws Exception;

    int insertSelective(ManageUserRelationRole record);

    List<ManageUserRelationRole> selectByExample(ManageUserRelationRoleExample example)throws Exception;

    ManageUserRelationRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManageUserRelationRole record, @Param("example") ManageUserRelationRoleExample example);

    int updateByExample(@Param("record") ManageUserRelationRole record, @Param("example") ManageUserRelationRoleExample example);

    int updateByPrimaryKeySelective(ManageUserRelationRole record);

    int updateByPrimaryKey(ManageUserRelationRole record);

	
}