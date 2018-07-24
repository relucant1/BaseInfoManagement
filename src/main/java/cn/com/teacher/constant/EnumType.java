package cn.com.teacher.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 学校级别
 * @author msj
 *
 */
public enum EnumType {

	SCHOOL_TUPE_PROVINCE("0","省级示范"),
	SCHOOL_TUPE_CITY("1","市级示范"), 
	SCHOOL_TUPE_COUNTY("2","区县级示范"), 
	SCHOOL_TUPE_ORDINARY("3","普通学校");
	
	private String types;
	private String contents;

	
	public String getKey() {
		return types;
	}

	public String getValue() {
		return contents;
	}

	private EnumType(String types, String contents) {
			this.types = types;
			this.contents = contents;
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map=new HashMap<String, String>();
		for(EnumType con:values() ){
			map.put(con.getKey(), con.getValue());
		}
		return map;
		
	}
	
	public static boolean isExists(String content){
		for(EnumType con:values() ){
			//map.put(con.getKey(), con.getValue());
			if(content.equals(con.getValue())){
				return true;
			}
		}
		return false;
	}
	
	public static String getContents(){
		StringBuffer contents = new StringBuffer();
		for(EnumType con:values() ){
			contents.append(con.getValue()).append(",");
		}
		return contents.substring(0,contents.length()-1);
	}
	
	public static String getType(String content){
		for(EnumType con:values() ){
			//map.put(con.getKey(), con.getValue());
			if(content.equals(con.getValue())){
				return con.getKey();
			}
		}
		return null;
	}
	
}
