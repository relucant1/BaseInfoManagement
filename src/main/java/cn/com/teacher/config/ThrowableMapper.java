package cn.com.teacher.config;

import cn.com.teacher.util.ResponseUtil;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/24
 * email：liuxiaowen@teacher.com.cn
 */
public class ThrowableMapper implements ExceptionMapper<Throwable>
{
    @Override
    public Response toResponse(Throwable e) {
        String result = ResponseUtil.buildFail("失败原因："+e.getMessage() +" 请重试!!!");
        return Response.status(Response.Status.OK).header("Content-Type", "application/json").entity(result).build();
    }
}
