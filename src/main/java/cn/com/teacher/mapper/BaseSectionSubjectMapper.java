package cn.com.teacher.mapper;

import cn.com.teacher.entity.BaseSectionSubject;
import cn.com.teacher.entity.BaseSectionSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseSectionSubjectMapper {
    int countByExample(BaseSectionSubjectExample example);

    int deleteByExample(BaseSectionSubjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseSectionSubject record);

    int insertSelective(BaseSectionSubject record);

    List<BaseSectionSubject> selectByExample(BaseSectionSubjectExample example);

    BaseSectionSubject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseSectionSubject record, @Param("example") BaseSectionSubjectExample example);

    int updateByExample(@Param("record") BaseSectionSubject record, @Param("example") BaseSectionSubjectExample example);

    int updateByPrimaryKeySelective(BaseSectionSubject record);

    int updateByPrimaryKey(BaseSectionSubject record);

	List<BaseSectionSubject> selectSectionSubject();

	List<BaseSectionSubject> selectAllWithoutSame();

	List<BaseSectionSubject> getSubjectIdList(String sectionCode);

	List<String> getSectionCodes(String subjectCode);

	int deleteByCode(BaseSectionSubject record);

	void deleteAllBaseSectionSection();
}