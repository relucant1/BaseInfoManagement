package cn.com.teacher.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/13
 * email：liuxiaowen@teacher.com.cn
 */
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl
{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        AccessDeniedException e)
        throws IOException, ServletException
    {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString("认证失败,无权限访问！"));
        writer.flush();
        writer.close();
    }
}
