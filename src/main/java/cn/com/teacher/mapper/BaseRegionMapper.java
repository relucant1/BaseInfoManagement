package cn.com.teacher.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.BaseRegionExample;
import cn.com.teacher.entity.TreeVo;

public interface BaseRegionMapper {
    int countByExample(BaseRegionExample example);

    int deleteByExample(BaseRegionExample example);

    int deleteByPrimaryKey(Integer id);

    List<BaseRegion> insertbaseRegion(BaseRegion baseRegion);

    int insertSelective(BaseRegion record);

    List<BaseRegion> selectByExample(BaseRegionExample example);

    BaseRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseRegion record, @Param("example") BaseRegionExample example);

    int updateByExample(@Param("record") BaseRegion record, @Param("example") BaseRegionExample example);

    int updateByPrimaryKeySelective(BaseRegion record);

    int updateByPrimaryKey(BaseRegion record);

	List<BaseRegion> getRegions();

	void batchInsert(List<BaseRegion> baseRegion);

	int del(Integer id);

	int getProductsCount();

	List<BaseRegion> selectProductsByPage(int startPos, int pageSize);

	List<BaseRegion> getlimiteRegion(String name);
	
	List<BaseRegion> getRegionByName(@Param("name")String name,@Param("parentId")Integer parentId);

	List<TreeVo> getRegionsTree();

	List<BaseRegion> findBaseRegion();

	int updateBaseRegion(BaseRegion baseRegion);

	List<BaseRegion> getChildrenByParentId(Integer parentId);
	
	void insert(BaseRegion baseRegion);

	List<BaseRegion> getParentRegion(Map map);

	int deleteByPrimaryKeys(List list);

	int selectMaxSeq();

	List<BaseRegion> getRegionInfo();
	
	BaseRegion  getBaseRegionById(Integer id);
	
	BaseRegion  getBaseRegionByMap(Map<String, Object> map);
	
	int deleteChild(@Param("path")String path);
	
	List<BaseRegion> getRegionByNameAndParent(@Param("parentId")Integer parentId,@Param("name")String name);
}