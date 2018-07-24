package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSectionSubject;
import cn.com.teacher.entity.BaseSubject;

public interface BaseSectionSubjectService {
	//查询所有
	List<BaseSectionSubject> selectSectionSubjectall();
	
	//按Id查询
	BaseSectionSubject getSectionSubjectById(Integer id);
	
	//按SectionSubject的list查询section所有
	List<BaseSection> getSectionByCode();
	
	//按SectionSubject的list查询Subject所有
	List<BaseSubject> getSubjectByCode();
	
    //查询section去重
    List<BaseSectionSubject> selectAllWithoutSame();
    
    //根据sectionCode查询所有的subject
	List<BaseSectionSubject> getSubjectList(String sectionCode);
	
	//根据subjectCode查询所有的sectionCode
	List<String> getSectionCodes(String subjectCode);

	/**添加BaseSectionSubject
	 * @param sectionSubject
	 * @return
	 */
	int insertBaseSectionSubject(BaseSectionSubject sectionSubject);

	/**删除BaseSectionSubject根据sectionCode和subjectCode
	 * @param sectionCode
	 * @param subjectCode
	 * @return
	 */
	int deleteBaseSectionSubjectByCode(BaseSectionSubject sectionSubject);

	/**
	 * 删除所有的BaseSectionSection
	 * 
	 */
	void deleteAllBaseSectionSection();
}
