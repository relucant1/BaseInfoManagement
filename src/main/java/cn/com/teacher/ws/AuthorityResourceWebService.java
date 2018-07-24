package cn.com.teacher.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/roleAuth")
public interface AuthorityResourceWebService {

	/**
	 * 获取系统当前所有权限和角色对应关系
	 * @return
	 */
	@GET
    @Path("/getRoleAuth")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoleAuth();
	
	/**
	 * 根据角色编码获取对应的系统ID
	 * @return
	 */
	@GET
    @Path("/getProjectId")
    @Produces(MediaType.APPLICATION_JSON)
	public String getProjectId();
	
	/**
	 * 获取用户角色对应的系统菜单
	 * @return
	 */
	@GET
    @Path("/getRoleMenu")
    @Produces(MediaType.APPLICATION_JSON)
	public String getRoleMenu();
	
	/**
	 * 根据系统ID和权限URL获取对应的权限信息
	 * @return
	 */
	@GET
    @Path("/getAuthByUrl")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAuthByUrl();
	
}
