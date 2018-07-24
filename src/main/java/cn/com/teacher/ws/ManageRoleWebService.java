package cn.com.teacher.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * 内部角色管理
 * @author lili 2017-05-27
 *
 */
@Path("")
public interface ManageRoleWebService {

	
	/**
	 * 查询所有角色
	 * 
	 */

    @GET
    @Path("/manageRoles")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getManageRoles() throws Exception;

    /**
     * 查询所有角色
     *
     */

    @GET
    @Path("/testT")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String testT() throws Exception;
}
