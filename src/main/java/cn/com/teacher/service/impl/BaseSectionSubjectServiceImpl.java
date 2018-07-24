package cn.com.teacher.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSectionSubject;
import cn.com.teacher.entity.BaseSubject;
import cn.com.teacher.mapper.BaseSectionSubjectMapper;
import cn.com.teacher.service.BaseSectionService;
import cn.com.teacher.service.BaseSectionSubjectService;
import cn.com.teacher.service.BaseSubjectService;

@Service("sectionSubjectService")
public class BaseSectionSubjectServiceImpl implements BaseSectionSubjectService {
	@Autowired
	private BaseSectionSubjectMapper baseSectionSubjectMapper;
	@Autowired
	private BaseSectionService sections;
	@Autowired
	private BaseSubjectService subjects;
	
	
	public List<BaseSectionSubject> selectSectionSubjectall() {
		
		return baseSectionSubjectMapper.selectSectionSubject();
		 
	}


	
	public BaseSectionSubject getSectionSubjectById(Integer id) {
		if (id != null && id>0) {
			return baseSectionSubjectMapper.selectByPrimaryKey(id);
		}
		return null;
		 
	}


	
	public List<BaseSection> getSectionByCode(){
		List<BaseSectionSubject> list=this.selectSectionSubjectall();
		List<BaseSection> listSection=new ArrayList<BaseSection>();
		for (int i = 0; i < list.size(); i++) {
			listSection.add(sections.getSectionBySectionCode(list.get(i).getSectionCode()));
		}
		return listSection;
	}


	
	public List<BaseSubject> getSubjectByCode() {
		List<BaseSectionSubject> list=this.selectSectionSubjectall();
		List<BaseSubject> listSubject=new ArrayList<BaseSubject>();
		for (int i = 0; i < list.size(); i++) {
			listSubject.add(subjects.getSubject(list.get(i).getSubjectId()));
		}
		return listSubject;
	}


	
	public List<BaseSectionSubject> selectAllWithoutSame() {
		// TODO Auto-generated method stub
		return baseSectionSubjectMapper.selectAllWithoutSame();
		
	}


	@Override
	public List<BaseSectionSubject> getSubjectList(String sectionCode) {
		
		return baseSectionSubjectMapper.getSubjectIdList(sectionCode);
	}


	@Override
	public List<String> getSectionCodes(String subjectCode) {
		// TODO Auto-generated method stub
		return baseSectionSubjectMapper.getSectionCodes( subjectCode);
	}


	@Override
	public int insertBaseSectionSubject(BaseSectionSubject sectionSubject) {
		// TODO Auto-generated method stub
		return baseSectionSubjectMapper.insert(sectionSubject);
	}


	@Override
	public int deleteBaseSectionSubjectByCode(BaseSectionSubject sectionSubject) {
		// TODO Auto-generated method stub
		return baseSectionSubjectMapper.deleteByCode(sectionSubject);
	}



	@Override
	public void deleteAllBaseSectionSection() {
		// TODO Auto-generated method stub
		baseSectionSubjectMapper.deleteAllBaseSectionSection();
	}
	
}
