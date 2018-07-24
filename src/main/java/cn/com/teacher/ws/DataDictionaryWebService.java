package cn.com.teacher.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * 数据字典
 * @author lili 2017-05-27
 *
 */
@Path("")
public interface DataDictionaryWebService {

	
	/**
	 * 查询数据字典对应的数据项
	 * @param id
	 * @return
	 */
	
    @GET
    @Path("/dataItems")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getDataItems() throws Exception;

    /**
     * 查询所有数据字典数据项
     * @return
     */

    @GET
    @Path("/entireDataItems")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getEntireDataItems() throws Exception;

}
