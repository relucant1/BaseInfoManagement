package cn.com.teacher.vo;

import java.io.Serializable;
import java.util.List;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSubject;

public class BaseSectionVo implements Serializable
{
	private BaseSection section;
	private List<BaseSubject> subjects;
	public BaseSection getSection() {
		return section;
	}
	public void setSection(BaseSection section) {
		this.section = section;
	}
	public List<BaseSubject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<BaseSubject> subjects) {
		this.subjects = subjects;
	}
}
