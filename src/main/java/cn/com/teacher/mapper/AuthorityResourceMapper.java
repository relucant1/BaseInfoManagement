package cn.com.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.teacher.entity.AuthorityResource;
import cn.com.teacher.entity.RoleAuthority;

public interface AuthorityResourceMapper {

	/**
	 * 根据条件查询权限信息
	 * @param roleCode
	 * @param projectId
	 * @param parentId
	 * @return
	 */
	List<AuthorityResource> findAuths(@Param("roleCode")String roleCode,
			@Param("projectId")Long projectId,@Param("parentId")Long parentId);
	
	/**
	 * 根据条件查询角色对应的权限信息
	 * @param roleCode
	 * @param projectId
	 * @param parentId
	 * @return
	 */
	List<AuthorityResource> findAuthByRole(@Param("roleCode")String roleCode,
			@Param("projectId")Long projectId,@Param("parentId")Long parentId,@Param("isMenu")String isMenu);
	
	/**
	 * 根据角色编码查询对应的权限信息
	 * @param roleCode
	 * @return
	 */
	List<RoleAuthority> findRoleAuthByRoleCode(@Param("roleCode")String roleCode);
	
	/**
	 * 批量新增角色权限信息
	 * @param roleCode
	 * @param roleAuths
	 * @return
	 */
	int insertRoleAuths(@Param("roleCode")String roleCode,@Param("roleAuths")List<RoleAuthority> roleAuths);
	
	/**
	 * 新增角色权限信息
	 * @param roleAuthority
	 * @return
	 */
	int insertRoleAuth(RoleAuthority roleAuthority);
	
	/**
	 * 根据角色编码删除对应的角色权限信息
	 * @param roleCode
	 * @return
	 */
	int deleteRoleAuths(@Param("roleCode")String roleCode); 
	
	/**
	 * 根据系统ID查询对应的角色权限对应信息
	 * @param projectId
	 * @return
	 */
	List<RoleAuthority> findRoleAuthByProjectId(@Param("projectId")Long projectId);
	
	/**
	 * 根据角色编码查询系统ID
	 * @param roleCode
	 * @return
	 */
	List<Long> findProjectIdByRoleCode(@Param("roleCode")String roleCode);
	
	/**
	 * 根据系统ID查询对应的角色权限对应信息(包含admin)
	 * @param projectId
	 * @return
	 */
	List<RoleAuthority> findAuthByProjectId(@Param("projectId")Long projectId);
	
	/**
	 * 根据权限所属系统和权限url获取权限信息
	 * @param projectId
	 * @param authUrl
	 * @return
	 */
	AuthorityResource getAuthByUrl(@Param("authUrl")String authUrl,@Param("projectId")Long projectId);
	
	/**
	 * 根据系统ID和角色删除对应角色权限关联关系
	 * @param projectId
	 * @param roleCode
	 * @return
	 */
	int deleteRoleAuthByProject(@Param("projectId")Long projectId,@Param("roleCode")String roleCode);
}
