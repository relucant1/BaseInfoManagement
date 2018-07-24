package cn.com.teacher.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 内部账号管理
 * @author lili 2017-05-26
 *
 */
@Path("")
public interface ManageUserWebService {

	
	/**
	 * 根据角色，姓名，手机号查询管理员
	 * @param 
	 * @return
	 */
	  
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getManageUsersRole() throws Exception;
}
