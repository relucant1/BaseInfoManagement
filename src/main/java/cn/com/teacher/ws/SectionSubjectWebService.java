package cn.com.teacher.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * 学段学科
 * @author lili 2017-05-31
 *
 */
@Path("")
public interface SectionSubjectWebService {

	
	/**
	 * 学段学科列表
	 * 
	 */
	
    @GET
    @Path("/subjectsBySection")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getSubjectListBySection() throws Exception;
    
    /**
     * 获取学段信息
     * @return
     */
    @GET
    @Path("/sections")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getSections();
    
    /**
     * 根据学段获取对应学科信息
     * @return
     */
    @GET
    @Path("/subjects")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getSubjects();
}
