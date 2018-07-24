package cn.com.teacher.service;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.teacher.entity.BaseSchool;
import cn.com.teacher.entity.BaseSchoolExample;
import cn.com.teacher.entity.BaselimitSchool;
import cn.com.teacher.entity.Result;
import cn.com.teacher.vo.SchoolExcelVo;

public interface BaseSchoolService {

    /**
     * 搜索
     * @param baselimitSchool
     * @return
     */
	int baseSchoolCount(BaselimitSchool baselimitSchool);
	/**
	 * 查询、分页
	 * @param startRecord
	 * @param pageSize
	 * @param baselimitSchool
	 * @return
	 */
	List<BaseSchool> getbaseSchoolByPage(int startRecord, int pageSize, BaselimitSchool baselimitSchool);
	/**
	 * 通过ID进行修改
	 * @param record
	 * @return
	 */
	BaseSchool findById(Integer id);
	/**
	 * 通过对象进行修改
	 * @param record
	 * @return
	 */
	boolean updateByPrimaryKeySelective(BaseSchool record);
	/**
	 * 删除学校
	 * @param ids
	 * @return
	 */
	boolean deleteMore(String ids);
	/**
	 * 根据条件获取学校信息
	 * @param map
	 * @return
	 */
	List<BaseSchool> getBaseSchoolByConditoions(Map map);
	/**
	 * 计算条数
	 * @param map
	 * @return
	 */
	int countBaseSchoolByConditoions(Map map);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	BaseSchool selectByPrimaryKey(Integer id);
	/**
	 * 添加学校
	 * @param record
	 * @return
	 */
	int insert(BaseSchool record);
	/**
	 * 导出
	 * @param map
	 * @return
	 */
	List<BaseSchool> getBaseSchoolInfo(Map map);
	/**
	 * 保存list
	 */
	boolean saveSchool(List<BaseSchool> baseSchools);
	
	List<BaseSchool> exportBaseSchoolByConditoions(Map map);
	
	Result uploadSchool(List<SchoolExcelVo> schoolExcelList);
	
	Result saveSchool(BaseSchool school);
}
