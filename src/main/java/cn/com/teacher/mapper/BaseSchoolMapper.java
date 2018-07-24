package cn.com.teacher.mapper;

import cn.com.teacher.entity.BaseSchool;
import cn.com.teacher.entity.BaseSchoolExample;
import cn.com.teacher.entity.BaselimitSchool;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface BaseSchoolMapper {
   
	List<BaselimitSchool> goMySql(Map<String, Object> map);
	
	int countByExample(BaseSchoolExample example);

    int deleteByExample(BaseSchoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseSchool record);

    int insertSelective(BaseSchool record);

    List<BaseSchool> selectByExample(BaseSchoolExample example);

    BaseSchool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseSchool record, @Param("example") BaseSchoolExample example);

    int updateByExample(@Param("record") BaseSchool record, @Param("example") BaseSchoolExample example);

    int updateByPrimaryKeySelective(BaseSchool record);

    int updateByPrimaryKey(BaseSchool record);

	int baseSchoolCount(BaselimitSchool baselimitSchool);

	List<BaseSchool> getbaseSchoolByPage(Map<String, Object> map);

	List<BaseSchool> getAllbaseSchool(BaseSchool baseSchool);

	BaseSchool updateByKey(Integer id);

	List<BaseSchool> selectByRegion(BaseSchool baseSchool);
	/*根据条件获取学校信息
	 * @param map
	 * */
	List<BaseSchool> getBaseSchoolByConditoions(Map map);
	List<BaseSchool> exportBaseSchoolByConditoions(Map map);
	/*根据条件获取学校信息总条数
	 * @param map
	 * */
	int countBaseSchoolByConditoions(Map map);

	int deleteMore(Map<String, String> map);

	List<BaseSchool> getBaseSchoolInfo(Map map);

	List<BaseSchool> getSchoolByRegionId(List<Integer> idList);
	
	BaseSchool getByRegionAndName(@Param("regionId")Integer regionId,@Param("name")String name);
	
}