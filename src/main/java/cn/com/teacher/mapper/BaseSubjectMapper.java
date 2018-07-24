package cn.com.teacher.mapper;

import cn.com.teacher.entity.BaseSubject;
import cn.com.teacher.entity.BaseSubjectExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseSubjectMapper {
    int countByExample(BaseSubjectExample example);

    int deleteByExample(BaseSubjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseSubject record);

    int insertSelective(BaseSubject record);

    List<BaseSubject> selectByExample(BaseSubjectExample example);

    BaseSubject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseSubject record, @Param("example") BaseSubjectExample example);

    int updateByExample(@Param("record") BaseSubject record, @Param("example") BaseSubjectExample example);

    int updateByPrimaryKeySelective(BaseSubject record);

    int updateByPrimaryKey(BaseSubject record);

	List<BaseSubject> getSubjects();

	BaseSubject getBySubjectCode(String subjectCode);

	BaseSubject selectByLtSequence(BaseSubject curr);

	BaseSubject selectByGtSequence(BaseSubject curr);

	List<BaseSubject> getSubjectsBySequence();

	List<BaseSubject> selectInSubjectCode(List<String> list);

	Integer selectMaxSequence();

	void deleteAllBaseSubject();

	BaseSubject selectSubjectBySequence(int i);
}