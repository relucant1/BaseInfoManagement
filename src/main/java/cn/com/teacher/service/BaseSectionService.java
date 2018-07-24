package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.BaseSection;

/**
 * 学段服务类
 * 
 * @author yuleilei
 * 2016-11-30 上午09:54:50
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface BaseSectionService {
	
    /**
     * 根据id获取所有学段
     * @param id
     * @return
     */
	public BaseSection getSection(Integer id);
	
	/**
	 * 获取所有学段
	 * @return
	 */
	public List<BaseSection> getSections();
	
	 /**
     * 根据sectionCode获取所有学段
     * @param sectionCode
     * @return
     */
	BaseSection getSectionBySectionCode(String sectionCode);
	
	

	/**
	 * 修改学段顺序
	 * @param id
	 * @param type
	 * @return
	 */
	public BaseSection updateSequence(Integer id, String type);
	

	/**
	 * 根据id删除学段
	 * @param id
	 */
	public int deleteByPrimaryKey(Integer id);

	/**
	 * 添加学段
	 * @param section
	 * @return
	 */
	public int insertBaseSection(BaseSection section);

	/**修改学段
	 * @param section
	 */
	public int updateBaseSection(BaseSection section);
	
	/**
	 * 获取所有学段根据sequence排序
	 * @return
	 */
	public List<BaseSection> getSectionsBySequence();

	/**查询最大的sequence值
	 * @return
	 */
	public Integer selectMaxSequence();

	/**
	 * 删除所有的数据
	 * 
	 */
	public void deleteAllBaseSection();

}
