package cn.com.teacher.filter;

import cn.com.teacher.config.CustomJwtAccessTokenConverter;
import cn.com.teacher.util.BearerTokenExtractor;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/10
 * email：liuxiaowen@teacher.com.cn
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//        HttpServletResponse response, FilterChain chain)
//        throws ServletException, IOException
//    {
//        Authentication authentication1 = this.tokenExtractor.extract(request);
//        String authHeader = request.getHeader(this.tokenHeader);
//        if (authHeader != null && authHeader.startsWith(tokenHead)) {
//            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
//            if(!CustomJwtAccessTokenConverter.getInstance().isExpired(authToken))
//            {
//                UsernamePasswordAuthenticationToken authentication = CustomJwtAccessTokenConverter.getInstance().getAuthenticationToken(authToken);
//                if (authentication != null) {
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    //存放authentication到SecurityContextHolder
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    HttpSession session = request.getSession(true);
//                    //在session中存放security context,方便同一个session中控制用户的其他操作
//                    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//                }
//            }else
//            {
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("text/html;charset=utf-8");
//                PrintWriter writer = response.getWriter();
//                writer.write(JSON.toJSONString("token过期！"));
//                writer.flush();
//                writer.close();
//            }
//        }
//        chain.doFilter(request, response);
//    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException
    {
        BearerTokenExtractor extractor = new BearerTokenExtractor();
        String authToken =  extractor.extractToken(request);
        if (StringUtils.isNotEmpty(authToken)) {
            UsernamePasswordAuthenticationToken authentication = CustomJwtAccessTokenConverter.getInstance().getAuthenticationToken(authToken);
            if (authentication != null) {
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //存放authentication到SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);
                HttpSession session = request.getSession(true);
                //在session中存放security context,方便同一个session中控制用户的其他操作
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            }
        }
        chain.doFilter(request, response);
    }
}
