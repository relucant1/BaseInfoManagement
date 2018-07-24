package cn.com.teacher.util;

import cn.com.teacher.constant.Constant;
import cn.com.teacher.entity.Result;

/**
 * Created by Qukun on 16/11/15.
 */
public class ResultUtil {

	public static Result buildSuccess(Object data){
		Result result = new Result();
		result.setIsSuccess(1);
		result.setData(data);
		return result;
	}

	public static Result buildFail(String msg){
		Result result = new Result();
		result.setIsSuccess(0);
		result.setMsg(msg);
		return result;
	}
	
	public static Boolean isSuccess(Result result){
		if (Constant.RESULT_SUCCESS.equals(result.getIsSuccess())){
			return true;
		}	else {
			return false;
		}
	}
}
