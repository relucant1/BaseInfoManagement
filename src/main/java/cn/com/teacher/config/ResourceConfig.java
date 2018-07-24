package cn.com.teacher.config;

import cn.com.teacher.filter.JwtAuthenticationTokenFilter;
import cn.com.teacher.filter.OAuth2AuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.header.HeaderWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/18
 * email：liuxiaowen@teacher.com.cn
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter
{
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private OAuth2AuthenticationFilter oAuth2AuthenticationFilter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenServices()).resourceId("1").stateless(true);
    }

    @Autowired
    private HttpSecurityEventPublisher httpSecurityEventPublisher;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
//             允许对于网站静态资源的无授权访问
            .antMatchers(
                "/",
                "/index",
                "/**/favicon.ico",
                "/**/*.html",
                "/**/*.ftl",
                "/**/*.css",
                "/**/*.js",
                "/**/*.png",
                "/**/*.jpg",
                "/druid/**"
            )
            .permitAll()
            .antMatchers("/ws/**").hasAnyRole("TRUSTED_CLIENT", "SUPER_ADMIN","TEARCHER")
//            .antMatchers("/**/**").permitAll()
//            .antMatchers("/**/**").hasRole("SUPER_ADMIN")
//             除上面外的所有请求全部需要鉴权认证
//            .anyRequest().authenticated()
            .and()
            //解决跨域问题
            .headers()
            .frameOptions()
            .sameOrigin()
            .and()
            .cors()
            .and()
            .headers().addHeaderWriter(new HeaderWriter()
        {
            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response)
            {
                response.addHeader("Access-Control-Allow-Origin", "*");
                if (request.getMethod().equals("OPTIONS"))
                {
                    response.setHeader("Access-Control-Allow-Methods",
                        request.getHeader("Access-Control-Request-Method"));
                    response.setHeader("Access-Control-Allow-Headers",
                        request.getHeader("Access-Control-Request-Headers"));
                }
            }
        }).and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
            .authenticationEntryPoint(new CustomEntryPoint());
//      禁用缓存
        http.headers().cacheControl();
//        添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//      添加验证token是否过期 filter
        http.addFilterBefore(oAuth2AuthenticationFilter, LogoutFilter.class);
        HttpSecurityConfigEvent event = new HttpSecurityConfigEvent(this, http);
        httpSecurityEventPublisher.publish(event);
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        return CustomJwtAccessTokenConverter.getInstance();
    }

    @Bean
    public JwtTokenStore tokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(tokenEnhancer());
        return jwtTokenStore;
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler()
    {
        AccessDeniedHandler handler = new CustomAccessDeniedHandler();
        return handler;
    }
}

