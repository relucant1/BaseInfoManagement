package cn.com.teacher.service;

import java.util.List;
import cn.com.teacher.entity.BaseDataDictionary;

/**
 * 数据字典服务类
 * @author lili
 * 2017-05-15 上午10:49:50
 */
public interface BaseDataDictionaryService {
	
	/**
	 * 获取所有公共数据字典
	 */
	List<BaseDataDictionary>  getDataDictionaryAll();
	
	/**
	 * 根据主键id获取数据字典
	 */
	BaseDataDictionary getDataDictionaryById(Integer id);

}
