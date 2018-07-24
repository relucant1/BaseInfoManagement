package cn.com.teacher.vo;

import java.util.List;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSectionSubject;
import cn.com.teacher.entity.BaseSubject;

public class UpdateSubjectVo {
	private BaseSubject subject;
	
	private List<BaseSectionSubject> sectionSubjects;
	private List<String> sectionCodes;
	private String sectionCode;
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public BaseSubject getSubject() {
		return subject;
	}
	public void setSubject(BaseSubject subject) {
		this.subject = subject;
	}
	
	public List<BaseSectionSubject> getSectionSubjects() {
		return sectionSubjects;
	}
	public void setSectionSubjects(List<BaseSectionSubject> sectionSubjects) {
		this.sectionSubjects = sectionSubjects;
	}
	public List<String> getSectionCodes() {
		return sectionCodes;
	}
	public void setSectionCodes(List<String> setionCodes) {
		this.sectionCodes = setionCodes;
	}
	
}
