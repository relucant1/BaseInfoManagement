package cn.com.teacher.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.authentication.*;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/15
 * email：liuxiaowen@teacher.com.cn
 */
@Component
public class OAuth2AuthenticationFilter implements Filter, InitializingBean
{
    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
    private OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
    private AuthenticationDetailsSource<HttpServletRequest, ?>
        authenticationDetailsSource = new OAuth2AuthenticationDetailsSource();
    private TokenExtractor tokenExtractor = new BearerTokenExtractor();
    private boolean stateless = true;

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        try {
            Authentication failed = this.tokenExtractor.extract(request);
            if(failed == null) {
                if(this.stateless && this.isAuthenticated()) {
                    SecurityContextHolder.clearContext();
                }
            } else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, failed.getPrincipal());
                if(failed instanceof AbstractAuthenticationToken) {
                    AbstractAuthenticationToken authResult = (AbstractAuthenticationToken)failed;
                    authResult.setDetails(this.authenticationDetailsSource.buildDetails(request));
                }
                authenticationManager.setTokenServices(tokenServices);
                Authentication authResult1 = this.authenticationManager.authenticate(failed);
                SecurityContextHolder.getContext().setAuthentication(authResult1);
            }
        } catch (OAuth2Exception var9) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString("token过期,请重新登录！"));
            writer.flush();
            writer.close();
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void afterPropertiesSet()
        throws Exception
    {
        Assert.state(this.authenticationManager != null, "AuthenticationManager is required");
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public void setStateless(boolean stateless) {
        this.stateless = stateless;
    }

    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    public void setAuthenticationManager(OAuth2AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }
}
