package cn.com.teacher.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * web service工具类
 * @author hugang
 *
 */
public class WebServiceUtil {
	
	public static WebClient getWebClient(String host,String path,Map<String, Object> params){
		
		List<Object> providerList = new ArrayList<Object>();
	    providerList.add(new JacksonJsonProvider());
	    WebClient client = WebClient.create(host, providerList)
	            .path(path)
	            .accept(MediaType.APPLICATION_JSON)
	            .type(MediaType.APPLICATION_JSON);
	    for(Map.Entry<String, Object> entry : params.entrySet()){
	    	client.query(entry.getKey(), entry.getValue());
	    }
	    return client;
	}
	
}
