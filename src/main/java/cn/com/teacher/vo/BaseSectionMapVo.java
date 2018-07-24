package cn.com.teacher.vo;

import java.util.Map;

import cn.com.teacher.entity.BaseSection;

public class BaseSectionMapVo {
	private Map<Integer,BaseSection> sectionMap;

	public Map<Integer, BaseSection> getSectionMap() {
		return sectionMap;
	}

	public void setSectionMap(Map<Integer, BaseSection> sectionMap) {
		this.sectionMap = sectionMap;
	}
}
