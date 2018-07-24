package cn.com.teacher.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	/*
	 * 判断Json中key对应的数据是否为空字符串
	 */
	public static boolean isEmpty( JSONObject jsonObject,String key){
		
		if(null==jsonObject.get(key)||jsonObject.getString(key)==""){
			return true;
		}
		return false;
	}
	
	/*
	 * 判断Json中key对应的数据是否为Null
	 */
	public static boolean isNull( JSONObject jsonObject,String key){
		
		if(null==jsonObject.get(key)){
			return true;
		}
		return true;
	}

}
