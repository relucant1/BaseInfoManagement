package cn.com.teacher.util;

import cn.com.teacher.common.ResponseData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 作者：liuxiaowen
 * 创建时间： 2017/5/24
 * email：liuxiaowen@teacher.com.cn
 */
public class ResponseUtil
{
    public static String buildSuccess(Object data){
        ResponseData result = new ResponseData();
        result.setIsSuccess(1);
        result.setData(data);
        return JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String buildFail(String msg){
        ResponseData result = new ResponseData();
        result.setMsg(msg);
        return JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
    }
}
