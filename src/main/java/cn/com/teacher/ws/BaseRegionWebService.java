package cn.com.teacher.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * 区域层级
 * @author lili 2017-05-27
 *
 */
@Path("")
public interface BaseRegionWebService {

	
	/**
	 * 获得省级列表
	 * 
	 */
	
    @GET
    @Path("/getProvince")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getBaseRegion() throws Exception;
    
    /**
     * 根据父ID获取对应的区域信息
     * @return
     */
    @GET
    @Path("/getRegion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getRegionByParentId();
}
