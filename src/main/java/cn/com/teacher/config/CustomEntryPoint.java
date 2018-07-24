package cn.com.teacher.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/13
 * email：liuxiaowen@teacher.com.cn
 */
public class CustomEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException
    {
        request.getRequestDispatcher("/403.html").forward(request,response);
    }
}
