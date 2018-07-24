package cn.com.teacher.mapper;

import cn.com.teacher.entity.ManageRole;
import cn.com.teacher.entity.ManageRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
public interface ManageRoleMapper {
    int countByExample(ManageRoleExample example);

    int deleteByExample(ManageRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ManageRole record);

    int insertSelective(ManageRole record);

    List<ManageRole> selectByExample(ManageRoleExample example);

    ManageRole selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") ManageRole record, @Param("example") ManageRoleExample example);

    int updateByExample(@Param("record") ManageRole record, @Param("example") ManageRoleExample example);

    int updateByPrimaryKeySelective(ManageRole record);

    int updateByPrimaryKey(ManageRole record);
    /**
     * 查询所有,分页
     * @return
     * @throws Exception
     */
    List<ManageRole> getManangeRoleAllPage(ManageRole manageRole)throws Exception;
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<ManageRole> getManangeRoleAll()throws Exception;
    /**
     * 更新
     * @param roleName
     * @param id
     * @return
     */
    int updateNameByPrimaryKey(ManageRole manageRole);


}