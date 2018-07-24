package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.BaseDataItem;
import cn.com.teacher.entity.Result;

/**
 * 数据项服务类
 * @author lili
 * 2017-05-15 上午10:54:50
 */
public interface BaseDataItemService {
	
	/**
	 * 根据数据字典类别查询数据项
	 */
	List<BaseDataItem> getDataItemByCategoryId(Integer categoryId);
	
	/**
	 * 添加数据项
	 */
	void insertItem(BaseDataItem item);
	
	/**
	 * 添加数据项
	 */
	void updateItem(BaseDataItem item);

	/**
	 * 修改数据项顺序
	 * @param id
	 * @param type
	 * @return
	 */
	public BaseDataItem updateSequence(Integer id, String type);
	
	/**
	 * 根据主键ID获取数据项
	 */
	public BaseDataItem getDataItemsById(Integer id);
	
	/**
	 * 获取数据项中最大的序列号
	 */
	public int getMaxItemSequence();
	
	/**
	 * 移动数据项
	 */
	public Result moveItem(List<BaseDataItem> list);
	
	/**
	 * 删除数据项
	 * @param id
	 * @return
	 */
	public Result deleteItem(Integer id);
	
	/**
	 * 判断同一类别下的编码是否已经存在
	 * @param categoryId
	 * @param dataItemCode
	 * @return
	 */
	public boolean existsItemCode(Integer categoryId,String dataItemCode,Integer eidtId);
}
