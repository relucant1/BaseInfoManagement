package cn.com.teacher.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;
import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSubject;
import cn.com.teacher.service.BaseSectionSubjectService;

/**
 * Created by Qukun on 16/11/15.
 */
@ApiIgnore
@Controller
public class IndexController {
	
	@Autowired
	private BaseSectionSubjectService sectionSubjectService;

	@RequestMapping(value = "/")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//List<BaseSectionSubject> list=sectionSubjectService.selectSectionSubjectall();
//		List<BaseSection> list1=sectionSubjectService.getSectionByCode();
//		List<BaseSubject> list2=sectionSubjectService.getSubjectByCode();
//	        for (BaseSection baseSection : list1) {
//				System.err.println(baseSection.getSectionName());
//			}
			/*for (BaseSubject baseSubject : list2) {
				System.err.println(baseSubject.getSubjectName());
			}*/	
		
//		Cookie [] cookie = request.getCookies();
//		String url = null;
//		if(cookie!=null){
//			for (Cookie ck : cookie) {
//				//System.out.println(ck.getName()+"="+ck.getValue());
//				if("url".equals(ck.getName())){
//					url = ck.getValue();
//				}
//				
//			}
//		}
//		System.out.println(url);
		return new ModelAndView("/index");
	}
	
	  public static Map<String, Object> objectToMap(Object obj) throws Exception {    
	        if(obj == null){    
	            return null;    
	        }   
	        Map<String, Object> map = new HashMap<String, Object>();    
	        Field[] declaredFields = obj.getClass().getDeclaredFields();    
	        for (Field field : declaredFields) {    
	            field.setAccessible(true);  
	            map.put(field.getName(), field.get(obj));  
	        }    
	        return map;  
	  }   
}
