package cn.com.teacher.ws.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.teacher.service.BaseSectionService;
import cn.com.teacher.service.BaseSectionSubjectService;
import cn.com.teacher.service.BaseSubjectService;
import cn.com.teacher.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSectionSubject;
import cn.com.teacher.entity.BaseSubject;
import cn.com.teacher.mapper.BaseSectionMapper;
import cn.com.teacher.mapper.BaseSectionSubjectMapper;
import cn.com.teacher.mapper.BaseSubjectMapper;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.BaseSectionVo;
import cn.com.teacher.ws.SectionSubjectWebService;

@Service("sectionSubjectWebService")
public class SectionSubjectWebServiceImp extends BaseServiceImpl implements SectionSubjectWebService {

	@Autowired
	private BaseSectionService sectionService;
	@Autowired
	private BaseSubjectService subjectService;
	@Autowired
	private BaseSectionSubjectService sectionSubjectService;
	
	@Override
	public String getSubjectListBySection() throws Exception {

		List<BaseSection> sections = sectionService.getSectionsBySequence();
		List<BaseSectionVo> list=new ArrayList<BaseSectionVo>();
		for (int i = 0; i < sections.size(); i++) {
			String sectionCode = sections.get(i).getSectionCode();
			List<BaseSectionSubject> sectionSubjects = sectionSubjectService.getSubjectList(sectionCode);
			List<BaseSubject> listSubject=new ArrayList<BaseSubject>();
			for (int j = 0; j < sectionSubjects.size(); j++) {

				String subjectCode = sectionSubjects.get(j).getSubjectCode();
				BaseSubject subjectOrg = subjectService.getBySubjectCode(subjectCode);
				BaseSubject subject = new BaseSubject();
				subject.setId(subjectOrg.getId());
				subject.setSubjectName(subjectOrg.getSubjectName());
				subject.setSubjectCode(sectionCode+subjectCode);
				subject.setSequence(subjectOrg.getSequence());
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
			if(baseSectionVo.getSubjects().size()>0){
				list.add(baseSectionVo);
			}
			
		}
		return ResponseUtil.buildSuccess(list);
	}

	@Override
	public String getSections() {
		List<BaseSection> sections = sectionService.getSectionsBySequence();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sections", sections);
		return ResponseUtil.buildSuccess(map);
	}

	@Override
	public String getSubjects() {
		String sectionCode = this.getParam("sectionCode");
		List<BaseSectionSubject> sectionSubjects = sectionSubjectService.getSubjectList(sectionCode);
		List<BaseSubject> listSubject=new ArrayList<BaseSubject>();
		for (int i = 0; i < sectionSubjects.size(); i++) {

			String subjectCode = sectionSubjects.get(i).getSubjectCode();
			BaseSubject subjectOrg = subjectService.getBySubjectCode(subjectCode);
			if(subjectOrg!=null){
				BaseSubject subject = new BaseSubject();
				subject.setId(subjectOrg.getId());
				subject.setSubjectName(subjectOrg.getSubjectName());
				subject.setSubjectCode(sectionCode+subjectCode);
				subject.setSequence(subjectOrg.getSequence());
				listSubject.add(subject);
			}
			subjectOrg = null;
			
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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("subjects", listSubject);
		return ResponseUtil.buildSuccess(map);
	}
}
