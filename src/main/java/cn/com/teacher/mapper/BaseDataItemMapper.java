package cn.com.teacher.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.teacher.entity.BaseDataItem;

public interface BaseDataItemMapper {

	List<BaseDataItem> getDataItemsByCategoryId(Integer categoryId);

	List<BaseDataItem> getEntireDataItems();
	
	void insert(BaseDataItem item);
	
	void update(BaseDataItem item);
	
	public BaseDataItem updateSequence(Integer id, String type);
	
	public BaseDataItem getDataItemsById(Integer id);
	
	public Integer getMaxItemSequence();
	
	int delete(@Param("id")Integer id);
	
	List<BaseDataItem> getDataItem(@Param("categoryId")Integer categoryId,@Param("dataItemCode")String dataItemCode);
	
}
