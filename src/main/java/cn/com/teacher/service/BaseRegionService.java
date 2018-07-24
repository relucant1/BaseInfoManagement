package cn.com.teacher.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.BaseRegionMove;
import cn.com.teacher.entity.BaseSchool;
import cn.com.teacher.entity.Result;
import cn.com.teacher.entity.TreeVo;
import cn.com.teacher.vo.RegionExcelVo;

/**
 * 省市区学校服务类
 * 
 * @author yuleilei
 * 2016-11-30 上午09:54:50
 */
public interface BaseRegionService {
	
	/**
	 * 获取所有表数据
	 * @return
	 */
    void update(BaseRegion region);  
						
	int del(Integer id);

	void batchInsert(List<BaseRegion> BaseRegion);

	List<BaseRegion> findBaseRegion();
	
	int insertSelective(BaseRegion record);
	 
	List<TreeVo> getRegionsTree();
	/**
	 * 添加省级
	 * @param baseRegion
	 */
	void insert(BaseRegion baseRegion);
	/**
	 * 编辑区域
	 * @param baseRegion
	 * @return
	 */
	boolean updateByPrimaryKeySelective(BaseRegion baseRegion);
	/**
	 * 根据id去查找对象
	 * @param id
	 * @return
	 */
	BaseRegion findById(Integer id);

	Object moveBaseRegion(BaseRegionMove baseRegionMove);
	/**
	 * 查询区域层级
	 * @param valueOf
	 * @return
	 */
	List<BaseRegion> getChildrenByParentId(Integer valueOf);
	
	String findFullNameById(int id);
	
	String findFullNameById2(int id);

	List<BaseRegion> getParentRegion(Map<String, Object> map);

	List<BaseRegion> getlimiteRegion(String name);

	int selectMaxSeq();

	int deleteByPrimaryKeys(List<BaseRegion> baseRegionList);
	/**
	 * 导出区域
	 * @return
	 */
	List<BaseRegion> getRegionInfo();


	BaseRegion getBaseRegionById(Integer regionId);
	
	BaseRegion getBaseRegion(String name,Integer parentId,Byte level);
	
	boolean saveRegion(List<BaseRegion> baseRegions);
	
	Result deleteRegion(Integer regionId);
	
	Result uploadRegion(List<RegionExcelVo> regionExcelVoList);
	
	BaseRegion getBaseRegionByNameAndParent(String name,Integer parentId);
	
}
