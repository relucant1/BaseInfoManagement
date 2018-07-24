package cn.com.teacher.mapper;

import cn.com.teacher.entity.ManageAuthority;
import cn.com.teacher.entity.ManageAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageAuthorityMapper {
    int countByExample(ManageAuthorityExample example);

    int deleteByExample(ManageAuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManageAuthority record);

    int insertSelective(ManageAuthority record);

    List<ManageAuthority> selectByExample(ManageAuthorityExample example);

    ManageAuthority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ManageAuthority record, @Param("example") ManageAuthorityExample example);

    int updateByExample(@Param("record") ManageAuthority record, @Param("example") ManageAuthorityExample example);

    int updateByPrimaryKeySelective(ManageAuthority record);

    int updateByPrimaryKey(ManageAuthority record);
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<ManageAuthority> getManageAuthorityAll()throws Exception;
    
}