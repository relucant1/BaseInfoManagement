package cn.com.teacher.config;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import cn.com.teacher.constant.Constant;
import cn.com.teacher.entity.RoleAuthority;
import cn.com.teacher.mapper.AuthorityResourceMapper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/6/12
 * email：liuxiaowen@teacher.com.cn
 */
@Component
public class HttpSecurityConfigListener implements ApplicationListener<HttpSecurityConfigEvent>
{
    private HttpSecurity httpSecurity;

    @Autowired
	private AuthorityResourceMapper authorityResourceMapper;

    public void onApplicationEvent(HttpSecurityConfigEvent event)
    {
        httpSecurity = event.getHttpSecurity();
        try
        {
        	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = httpSecurity.authorizeRequests();
        	List<RoleAuthority> roleAuthList = 
        			authorityResourceMapper.findAuthByProjectId(Long.valueOf(Constant.SYSTEM_ID));
    	   	Map<String,String> roleAuthMap = new HashMap<String, String>();
    	   	if(roleAuthList!=null&&roleAuthList.size()>0){
    			for (RoleAuthority roleAuthority : roleAuthList) {
    				roleAuthMap.put(roleAuthority.getAuthUrl(), roleAuthority.getRoleCode());
    			}
    		}
    	   	String [] roles = null;
    	   	for(Map.Entry<String,String> entry : roleAuthMap.entrySet()){
                //System.out.println(entry.getKey()+":"+entry.getValue());
                roles = entry.getValue().split(",");
                expressionInterceptUrlRegistry.antMatchers(entry.getKey()).hasAnyRole(roles);
            }
    	   	expressionInterceptUrlRegistry.anyRequest().permitAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public HttpSecurity getHttpSecurity()
    {
        return httpSecurity;
    }
}
