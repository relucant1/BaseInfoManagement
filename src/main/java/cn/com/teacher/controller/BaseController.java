package cn.com.teacher.controller;

import cn.com.teacher.common.ResponseData;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import java.util.*;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/9
 * email：liuxiaowen@teacher.com.cn
 */
@ApiIgnore
@RestController
@Scope("session")
public class BaseController
{
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @Autowired
    @Qualifier("clientTemplate")
    private OAuth2RestOperations clientTemplate;

    @Value("${config.oauth2.resourceURI}")
    public String RESOURCE_URL;

    public Map<String, String> params = new HashMap<String, String>();

    enum MethodType
    {
        GET("GET"),POST("POST"), PUT("PUT"), DELETE("DELETE");
        private String name;
        MethodType(String name)
        {
            this.name = name;
        }
        public String getName()
        {
            return name;
        }
    }

    enum ReturnType
    {
        LIST,MAP,STRING
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,
        HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        params.clear();
    }


    public Map<String, String> getParams()
    {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String prop = names.nextElement();
            params.put(prop, request.getParameter(prop));
        }
        return params;
    }

    private WebClient getWebClient(String path)
    {
        List<Object> providerList = new ArrayList<Object>();
        providerList.add(new JacksonJsonProvider());
        WebClient client = WebClient.create(RESOURCE_URL, providerList)
            .path(path)
            .accept(MediaType.APPLICATION_JSON)
            .type(MediaType.APPLICATION_JSON);
        client.header("Authorization", "Bearer "+ clientTemplate.getAccessToken().getValue());
        Map<String, String> params = getParams();
        for(Map.Entry<String, String> entry : params.entrySet())
        {
            client.query(entry.getKey(), entry.getValue());
        }
        return client;
    }

    public String getWsRest(String path)
    {
        return getWsRest(path, MethodType.GET, String.class);
    }

    public String getWsRest(String path, MethodType methodType)
    {
        return getWsRest(path, methodType, String.class);
    }

    public String getWsRest(String path, Class clazz)
    {
        return getWsRest(path, MethodType.GET, clazz);
    }

    public String getWsRest(String path, MethodType methodType, Class clazz)
    {
        Object object = null;
        try
        {
            WebClient client = getWebClient(path);
            object =client.invoke(methodType.getName(), getParams(), clazz);
        }
        catch (Exception e)
        {
            return "路径"+RESOURCE_URL + path + " Rest接口调用失败！";
        }
        return object +"";
    }

    private <T> T getObject(String str, Class<T> clazz)
    {
        return JSON.parseObject(str, clazz);
    }

    private <T> T getObject(Object object, Class<T> clazz)
    {
        return JSON.parseObject(JSON.toJSONString(object), clazz);
    }

    public Long expire(String key)
    {
        getParams().put("key", key);
        ResponseData data = getObject(getWsRest("/redis/expire", MethodType.GET), ResponseData.class);
        if(data.getIsSuccess() == 1)
        {
            return getObject(data.getData(), Long.class);
        }
        return 0l;
    }

    Boolean exists(String key)
    {
        params.put("key", key);
        ResponseData data = getObject(getWsRest("/redis/exists", MethodType.GET), ResponseData.class);
        if(data.getIsSuccess() == 1)
        {
            return getObject(data.getData(), Boolean.class);
        }
        return false;
    }

    /**
     *
     * @param key
     * @param value
     * @param time 过期时间，单位为s
     * @return
     */
    Boolean setRedis(String key, String value, String time)
    {
        params.put("key", key);
        params.put("value", value);
        params.put("time", time);
        ResponseData data = getObject(getWsRest("/redis/string", MethodType.POST), ResponseData.class);
        return data.getIsSuccess() == 1;
    }

    String getRedis(String key)
    {
        getParams().put("key", key);
        ResponseData data = getObject(getWsRest("/redis/string", MethodType.GET), ResponseData.class);
        if(data.getIsSuccess() == 1)
        {
            return getObject(data.getData(), String.class);
        }
        return "";
    }
}
