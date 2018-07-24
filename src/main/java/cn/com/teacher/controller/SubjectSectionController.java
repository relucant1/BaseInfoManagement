package cn.com.teacher.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSON;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSectionSubject;
import cn.com.teacher.entity.BaseSubject;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.BaseSectionService;
import cn.com.teacher.service.BaseSectionSubjectService;
import cn.com.teacher.service.BaseSubjectService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.BaseSectionMapVo;
import cn.com.teacher.vo.BaseSectionVo;
import cn.com.teacher.vo.UpdateSubjectMapVo;
import cn.com.teacher.vo.UpdateSubjectVo;


/**
 * @author chenchunhui
 *
 * 2017-2-23
 */
@ApiIgnore
@Controller
@RequestMapping("/sectionSubject")
public class SubjectSectionController {
	@Autowired
	private BaseSectionService sectionService;
	@Autowired
	private BaseSubjectService subjectService;
	@Autowired
	private BaseSectionSubjectService sectionSubjectService;
	
	/**
	 * 学科学段页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/")
	public String section(HttpServletRequest request, HttpServletResponse response){
		
		return "/sectionSubject/list";
	}
	
	/**
	 * 学段页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sectionList")
	public String sectionList(HttpServletRequest request, HttpServletResponse response){
		
		return "/sectionSubject/section";
	}
	/**
	 * 学科页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/subjectList")
	public String subjectList(HttpServletRequest request, HttpServletResponse response){
		
		return "/sectionSubject/subject";
	}
	/**
	 * 学段页面数据
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sectionList", method = RequestMethod.POST)
	@ResponseBody
	public Result sectionList(HttpServletRequest request, HttpServletResponse response,Model model){
		List<BaseSection> sections = sectionService.getSectionsBySequence();
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("sections", sections);
		return ResultUtil.buildSuccess(map);
	}
	
	@RequestMapping(value = "/subjectListBySection",method = RequestMethod.POST)
	@ResponseBody
	public Result sectionSubjectList(HttpServletRequest request, HttpServletResponse response){
		List<BaseSection> sections = sectionService.getSectionsBySequence();
		List<BaseSectionVo> list=new ArrayList<BaseSectionVo>();
		for (int i = 0; i < sections.size(); i++) {
			String sectionCode = sections.get(i).getSectionCode();
			List<BaseSectionSubject> sectionSubjects = sectionSubjectService.getSubjectList(sectionCode);
			List<BaseSubject> listSubject=new ArrayList<BaseSubject>();
			for (int j = 0; j < sectionSubjects.size(); j++) {
				
				String subjectCode = sectionSubjects.get(j).getSubjectCode();
				BaseSubject subject = subjectService.getBySubjectCode(subjectCode);
				if(subject!=null){
					listSubject.add(subject);
				}
			}
			Collections.sort(listSubject, new Comparator<BaseSubject>(){  
				  
	             
	            public int compare(BaseSubject o1, BaseSubject o2) {  
	              
	                //按照BaseSubject.sequence进行升序排列  
	                if(o1.getSequence() > o2.getSequence()){  
	                    return 1;  
	                }  
	                if(o1.getSequence() == o2.getSequence()){  
	                    return 0;  
	                }  
	                return -1;  
	            }  
	        });   
			BaseSectionVo baseSectionVo=new BaseSectionVo();
			baseSectionVo.setSection(sections.get(i));
			baseSectionVo.setSubjects(listSubject);
			list.add(baseSectionVo);
		}
		
		
		
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("list", list);
		return ResultUtil.buildSuccess(map);
		
	}
	/**
	 * 学段移动
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/sectionMove")
	@ResponseBody
	public BaseSection sectionMove(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer id,String type){
		BaseSection section =sectionService.updateSequence(id, type);
		
		return section;
	}
	
	/**
	 * 学段删除
	 * @param request
	 * @param response
	 * @param id
	 * @param sectionCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sectionDelete",method = RequestMethod.POST)
	@ResponseBody
	public Result sectionDelete(HttpServletRequest request, HttpServletResponse response,Integer id){
		BaseSection section=sectionService.getSection(id);
		String sectionCode = section.getSectionCode();
		try {
			if(id != null){
				int key = sectionService.deleteByPrimaryKey(id);
				BaseSectionSubject sectionSubject=new BaseSectionSubject();
				sectionSubject.setSectionCode(sectionCode);
				if(sectionCode!=null){
					sectionSubjectService.deleteBaseSectionSubjectByCode(sectionSubject);
				}
				return ResultUtil.buildSuccess("删除成功");
			}
			return ResultUtil.buildSuccess("id为空 ");
		} catch (Exception e) {
			return ResultUtil.buildFail("删除失败");
		}
		
		
	}
	
	/**
	 * 学科移动
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/subjectMove")
	@ResponseBody
	public BaseSubject SubjectMove(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer id,String type){
			BaseSubject subject =subjectService.updateSequence(id, type);
			return subject;
		
	}
	
	
	/**
	 * 学科删除
	 * @param request
	 * @param response
	 * @param subjectCode
	 * @param sectionCode
	 * @return
	 */
	@RequestMapping(value = "/subjectSectionDelete")
	public String subjectSectionDelete(HttpServletRequest request, HttpServletResponse response,@RequestParam String subjectCode,String sectionCode){
		BaseSectionSubject sectionSubject=new BaseSectionSubject();
		sectionSubject.setSectionCode(sectionCode);
		sectionSubject.setSubjectCode(subjectCode);
		if(subjectCode!=null && sectionCode!=null){
			return JSON.toJSONString(sectionSubjectService.deleteBaseSectionSubjectByCode(sectionSubject));
		}else{
			return JSON.toJSONString(ResultUtil.buildFail("删除失败"));
		}
		
	}
	
	@RequestMapping(value = "/subjectDelete",method = RequestMethod.POST)
	@ResponseBody
	public String subjectDelete(HttpServletRequest request, HttpServletResponse response,Integer id){
		if(id!=null){
			BaseSubject subject=subjectService.getSubject(id);
			BaseSectionSubject sectionSubject=new BaseSectionSubject();
			sectionSubject.setSubjectCode(subject.getSubjectCode());
			if(subject.getSubjectCode()!=null){
				sectionSubjectService.deleteBaseSectionSubjectByCode(sectionSubject);
			}
			return JSON.toJSONString(subjectService.deleteSubject(id));
		}else{
			return JSON.toJSONString(ResultUtil.buildFail("删除失败"));
		}
	}
	
	/**
	 * 学段修改
	 * @param request
	 * @param response
	 * @param sectionMap
	 * @return
	 */
	@RequestMapping(value = "/sectionUpdate")
	@ResponseBody
	public String updateAndAddSections(HttpServletRequest request, HttpServletResponse response,BaseSectionMapVo sectionMap){
		Set set = sectionMap.getSectionMap().keySet();  
	    Iterator iterator = set.iterator();
	    int i=1;
	    try {
	    	sectionService.deleteAllBaseSection();
	    	while(iterator.hasNext()){  
		        Integer id = (Integer)iterator.next();
		        BaseSection section=sectionMap.getSectionMap().get(id);
		        	section.setSequence(i);
		        	i+=1;
		        	sectionService.insertBaseSection(section);
		    } 
	    	return JSON.toJSONString(ResultUtil.buildSuccess("修改成功"));
		} catch (Exception e) {
			System.out.println(e);
			return JSON.toJSONString(ResultUtil.buildFail("修改失败"));
		}
	}
	
	/**
	 * 学科页面数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/subjectList",method = RequestMethod.POST)
	@ResponseBody
	public Result subjectListJson(HttpServletRequest request, HttpServletResponse response){
		List<BaseSubject> subjects =subjectService.getSubjectsBySequence();
		List<BaseSection> sections =sectionService.getSectionsBySequence();
		List<UpdateSubjectVo> updateSubjectVoList=new ArrayList();
		for (int i = 0; i < subjects.size(); i++) {
			BaseSubject subject=subjects.get(i);
			String subjectCode = subject.getSubjectCode();
			List<String> sectionCodes=sectionSubjectService.getSectionCodes(subjectCode);
			UpdateSubjectVo updateSubjectVo=new UpdateSubjectVo();
			updateSubjectVo.setSubject(subject);
			updateSubjectVo.setSectionCodes(sectionCodes);
			updateSubjectVoList.add(updateSubjectVo);
		}
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("updateSubjectVoList", updateSubjectVoList);
		map.put("sections", sections);
		return ResultUtil.buildSuccess(map);
	}
	
	/**
	 * 学科修改
	 * @param request
	 * @param response
	 * @param updatesubjectMap
	 * @return
	 */
	@RequestMapping(value = "/subjectUpdate")
	@ResponseBody
	public String updateAndAddSubjeccts(HttpServletRequest request, HttpServletResponse response,UpdateSubjectMapVo updatesubjectMap){
		Set<Integer> set = updatesubjectMap.getUpdateSubjectMap().keySet();
		Object[] keyArray = set.toArray();
		Arrays.sort(keyArray);
	    //Iterator<Integer> iterator = set.iterator();
	    subjectService.deleteAllBaseSubject();
	    sectionSubjectService.deleteAllBaseSectionSection();
	    int sequence=1;
	    try {
	    	 for (Object obj : keyArray) {
				
	    		
	 	        Integer id = (Integer)obj;
	 	        UpdateSubjectVo updateSubjectVo = updatesubjectMap.getUpdateSubjectMap().get(id);
	 	        BaseSubject subject = updateSubjectVo.getSubject();
	 	        subject.setSequence(sequence);
	        	subjectService.insertBaseSubject(subject);
	        	BaseSubject subjectnew=subjectService.selectSubjectBySequence(sequence);
	        	sequence+=1;
	 	        Integer subjectId=subjectnew.getId();
	 	        String subjectCode = subjectnew.getSubjectCode();
	 	        
	 	        
	 	        
	 	        String sectionCode2 = updateSubjectVo.getSectionCode();
	 	       	String[] arr = sectionCode2.split(",");
	 	       	List<String> se = Arrays.asList(arr);
	 	       	HashSet<String> hs = new HashSet<String>(se);
	 	       	List<String> sectionCodes=new ArrayList(hs);
	 	       	
	 	       if(sectionCodes.size()>0){
	        		for (int i = 0; i < sectionCodes.size(); i++) {
	        			String code=sectionCodes.get(i);
	        			if(!"".equals(code)){
	        				BaseSection section = sectionService.getSectionBySectionCode(code);
		        			BaseSectionSubject sectionSubject=new BaseSectionSubject();
		        			sectionSubject.setSectionCode(code);
		        			sectionSubject.setSubjectCode(subjectCode);
		        			sectionSubject.setSectionId(section.getId());
		        			sectionSubject.setSubjectId(subjectId);
		        			sectionSubjectService.insertBaseSectionSubject(sectionSubject);
	        			}
	        			
	        		}
	        	}
	 	       	
	 	    }
	    	return JSON.toJSONString(ResultUtil.buildSuccess("修改成功"));
		} catch (Exception e) {
			System.out.println(e);
			return JSON.toJSONString(ResultUtil.buildFail("修改失败"));
		}
	}
	    
}
