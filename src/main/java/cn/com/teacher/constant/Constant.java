package cn.com.teacher.constant;

import java.util.Map;

/**
 * 常量定义列表
 */
public class Constant {
	
	/**
	 * 学校级别
	 */
	public static Integer SCHOOL_TUPE_PROVINCE = 0; //0省级示范
	public static Integer SCHOOL_TUPE_CITY = 1; //1市级示范
	public static Integer SCHOOL_TUPE_COUNTY = 2; //2区县级示范
	public static Integer SCHOOL_TUPE_ORDINARY = 3; //3普通学校
	
	/**
	 * 是否停用
	 */
	public static Integer SCHOOL_IS_START_DISABLE = 0; // 0停用
	public static Integer SCHOOL_IS_START_ENABLE = 1; // 1启用
	
	/**
	 * 学校规模 
	 */
	public static Integer SCHOOL_SCALE_ONE = 1; //1  29人以下
	public static Integer SCHOOL_SCALE_TWO = 2; //2  30-49人
	public static Integer SCHOOL_SCALE_THREE = 3; //3  50-79人
	public static Integer SCHOOL_SCALE_FOUR = 4; //4  80-109人
	public static Integer SCHOOL_SCALE_FIVE = 5; //5  110-139人
	public static Integer SCHOOL_SCALE_SIX = 6; //6  140-169人
	public static Integer SCHOOL_SCALE_SEVEN = 7; //7  170-199人
	public static Integer SCHOOL_SCALE_EIGHT = 8; //8  200人以上
	

	public static final String SYSTEM_ID = "3";
	
	public static final String AUTH_PREFIX = "ROLE_";
	
	public static final String IS_MENU_TRUE = "1";  //权限是否菜单 是
	
	public static final String IS_MENU_FALSE = "0"; //权限是否菜单 否
	
	/**
	 * result结果常量
	 */
	public static Integer RESULT_SUCCESS = 1; //1 成功
	public static Integer RESULT_FAIL = 0; //2 失败
	
}
