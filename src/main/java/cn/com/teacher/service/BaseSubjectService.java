package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.BaseSubject;

/**
 * 学科服务类
 * 
 * @author yuleilei
 * 2016-11-30 上午09:53:54
 */
/**
 * @author Administrator
 *
 */
public interface BaseSubjectService {
    
	/**
	 * 根据ID获取学科
	 * @param id
	 * @return
	 */
	public BaseSubject getSubject(Integer id);
	
	/**
	 * 获取所有学科
	 * @return
	 */
	public List<BaseSubject> getSubjects();
	
	/**
	 * 根据subjectCode获取学科
	 * @param subjectCode
	 * @return
	 * @throws Exception
	 */
	BaseSubject getBySubjectCode(String subjectCode);
	
	
	/**
	 * 修改学科顺序
	 * @param id
	 * @param type
	 * @return
	 */
	public BaseSubject updateSequence(Integer id, String type);

	
	/**添加学科
	 * @param subject
	 * @return
	 */
	public int insertBaseSubject(BaseSubject subject);

	
	/**
	 * 修改学科
	 * @param subject
	 * @return
	 */
	public int updateBaseSection(BaseSubject subject);

	/**删除学科
	 * @param id
	 * @return
	 */
	public int deleteSubject(Integer id);

	/**
	 * 根据subjectCodeList获取学科集合
	 * @param list
	 * @return
	 */
	public List<BaseSubject> getBySubjectCodeList(List<String> list);
	
	/**
	 * 获取所有学科根据sequence排序
	 * @return
	 */
	public List<BaseSubject> getSubjectsBySequence();

	/**
	 * 获得Sequence的最大值
	 * @return
	 */
	public Integer selectMaxSequence();

	/**
	 * 删除所有数据
	 * 
	 */
	public void deleteAllBaseSubject();

	/**
	 * 根据Sequence获取subject
	 * @param i
	 * @return
	 */
	public BaseSubject selectSubjectBySequence(Integer i);

}
