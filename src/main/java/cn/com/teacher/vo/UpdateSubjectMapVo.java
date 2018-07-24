package cn.com.teacher.vo;

import java.util.LinkedHashMap;
import java.util.Map;



public class UpdateSubjectMapVo {
	private LinkedHashMap<Integer , UpdateSubjectVo> updateSubjectMap;

	public LinkedHashMap<Integer, UpdateSubjectVo> getUpdateSubjectMap() {
		return updateSubjectMap;
	}

	public void setUpdateSubjectMap(
			LinkedHashMap<Integer, UpdateSubjectVo> updateSubjectMap) {
		this.updateSubjectMap = updateSubjectMap;
	}
	
}
