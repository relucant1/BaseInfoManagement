package cn.com.teacher.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/24
 * email：liuxiaowen@teacher.com.cn
 */
@Configuration
public class CXFConfig
{

    @Autowired
    private ApplicationContext ctx;


    @Bean
    public ServletRegistrationBean cxfServletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean(name = "cxf")
    public Bus bus() {
        final SpringBus springBus = new SpringBus();
        springBus.getInInterceptors().add(new LoggingInInterceptor());
        springBus.getOutInterceptors().add(new LoggingOutInterceptor());
        return springBus;
    }

    @Bean
    public Server restService() {
        List<Object> serviceBeans = new ArrayList<Object>(ctx.getBeansWithAnnotation(Path.class).values());
        final JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setAddress("/");
        endpoint.setBus(bus());
        endpoint.setProviders(Arrays.asList(new ThrowableMapper(), new JacksonJaxbJsonProvider()));
        endpoint.setServiceBean(serviceBeans);
        return endpoint.create();
    }

}
