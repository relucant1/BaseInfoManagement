package cn.com.teacher.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/6/12
 * email：liuxiaowen@teacher.com.cn
 */
public class HttpSecurityConfigEvent extends ApplicationEvent
{
    private HttpSecurity httpSecurity;

    public HttpSecurityConfigEvent(Object source, HttpSecurity httpSecurity)
    {
        super(source);
        this.httpSecurity = httpSecurity;
    }

    public HttpSecurity getHttpSecurity()
    {
        return httpSecurity;
    }

    public void setHttpSecurity(HttpSecurity httpSecurity)
    {
        this.httpSecurity = httpSecurity;
    }
}
