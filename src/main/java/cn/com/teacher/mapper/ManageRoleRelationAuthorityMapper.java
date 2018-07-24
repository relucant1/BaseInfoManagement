package cn.com.teacher.mapper;

import cn.com.teacher.entity.ManageRoleRelationAuthority;
import cn.com.teacher.entity.ManageRoleRelationAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageRoleRelationAuthorityMapper {
    int countByExample(ManageRoleRelationAuthorityExample example);

    int deleteByExample(ManageRoleRelationAuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManageRoleRelationAuthority record);

    int insertSelective(ManageRoleRelationAuthority record);

    List<ManageRoleRelationAuthority> selectByExample(ManageRoleRelationAuthorityExample example);

    ManageRoleRelationAuthority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManageRoleRelationAuthority record, @Param("example") ManageRoleRelationAuthorityExample example);

    int updateByExample(@Param("record") ManageRoleRelationAuthority record, @Param("example") ManageRoleRelationAuthorityExample example);

    int updateByPrimaryKeySelective(ManageRoleRelationAuthority record);

    int updateByPrimaryKey(ManageRoleRelationAuthority record);
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<ManageRoleRelationAuthority> getManageRoleRelationAuthorityAll(Integer roleId)throws Exception;
	/**
	 * 删除所有roleId,保存权限
	 * @param manageRoleRelationAuthority
	 * @return
	 */
    int deleteRoleRelationAuthorityByRoleId(ManageRoleRelationAuthority manageRoleRelationAuthority)throws Exception;
    
}