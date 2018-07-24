package cn.com.teacher;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/7
 * email：liuxiaowen@teacher.com.cn
 */
@SpringBootApplication
@EnableOAuth2Client
public class Application extends SpringBootServletInitializer
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Value("${config.oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${config.oauth2.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${config.oauth2.clientID}")
    private String clientID;

    @Value("${config.oauth2.clientSecret}")
    private String clientSecret;

    @Bean(name = "clientTemplate")
    public OAuth2RestOperations clientTemplate(OAuth2ClientContext oauth2ClientContext) {
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), oauth2ClientContext);
        template.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        template.getMessageConverters().add(new FormHttpMessageConverter());
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template;
    }

    private OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientId(clientID);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setScope(Arrays.asList("read write"));
        resourceDetails.setGrantType("client_credentials");
        return resourceDetails;
    }

    @Bean
    public ResourceOwnerPasswordAccessTokenProvider passwordAccessTokenProvider()
    {
        return new ResourceOwnerPasswordAccessTokenProvider();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
    
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter()
    {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(
            MediaType.valueOf("application/json;charset=UTF-8"),
            MediaType.valueOf("text/plain;charset=UTF-8"),
            MediaType.valueOf("text/html;charset=UTF-8")));
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        return converter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
