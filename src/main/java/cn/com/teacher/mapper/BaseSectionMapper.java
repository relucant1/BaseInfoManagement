package cn.com.teacher.mapper;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseSectionMapper {
    int countByExample(BaseSectionExample example);

    int deleteByExample(BaseSectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseSection record);

    int insertSelective(BaseSection record);

    List<BaseSection> selectByExample(BaseSectionExample example);

    BaseSection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseSection record, @Param("example") BaseSectionExample example);

    int updateByExample(@Param("record") BaseSection record, @Param("example") BaseSectionExample example);

    int updateByPrimaryKeySelective(BaseSection record);

    int updateByPrimaryKey(BaseSection record);

	List<BaseSection> getSections();

	BaseSection getBySectionCode(String sectionCode);

	BaseSection selectByLtSequence(BaseSection curr);

	BaseSection selectByGtSequence(BaseSection curr);

	List<BaseSection> getSectionsBySequence();

	Integer selectMaxSequence();

	void deleteAllBaseSection();
}