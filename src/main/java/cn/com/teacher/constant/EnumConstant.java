package cn.com.teacher.constant;

import java.util.HashMap;
import java.util.Map;
/**
 * 规模枚举
 * @author msj
 *
 */
public enum EnumConstant {
	
	SCHOOL_SCALE_ONE("1","29人以下"),
	SCHOOL_SCALE_TWO("2","30-49人"),
	SCHOOL_SCALE_THREE("3","50-79人"), 
	SCHOOL_SCALE_FOUR("4","80-109人"),
	SCHOOL_SCALE_FIVE("5","110-139人"), 
	SCHOOL_SCALE_SIX("6","140-169人"), 
	SCHOOL_SCALE_SEVEN("7","170-199人"), 
	SCHOOL_SCALE_EIGHT("8","200人以上"); 
	
	private String level;
	private String content;

	
	public String getKey() {
		return level;
	}

	public String getValue() {
		return content;
	}

	private EnumConstant(String level, String content) {
			this.level = level;
			this.content = content;
	
	}
	
	public static Map<String, String> toMap() {
		Map<String, String> map=new HashMap<String, String>();
		for(EnumConstant con:values() ){
			map.put(con.getKey(), con.getValue());
		}
		return map;
		
	}
	
	public static boolean isExists(String content){
		for(EnumConstant con:values() ){
			//map.put(con.getKey(), con.getValue());
			if(content.equals(con.getValue())){
				return true;
			}
		}
		return false;
	}
	
	public static String getContents(){
		StringBuffer contents = new StringBuffer();
		for(EnumConstant con:values() ){
			contents.append(con.getValue()).append(",");
		}
		return contents.substring(0,contents.length()-1);
	}
	
	public static String getLevel(String content){
		for(EnumConstant con:values() ){
			//map.put(con.getKey(), con.getValue());
			if(content.equals(con.getValue())){
				return con.getKey();
			}
		}
		return null;
	}

}
