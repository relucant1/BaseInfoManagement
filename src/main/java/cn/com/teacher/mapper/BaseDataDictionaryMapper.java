package cn.com.teacher.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.BaseDataDictionary;

public interface BaseDataDictionaryMapper {

	List<BaseDataDictionary>  selectAll();
	
	BaseDataDictionary selectDataDictionaryById(Integer id);
}
