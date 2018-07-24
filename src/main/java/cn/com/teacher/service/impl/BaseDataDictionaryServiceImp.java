package cn.com.teacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.teacher.entity.BaseDataDictionary;
import cn.com.teacher.mapper.BaseDataDictionaryMapper;
import cn.com.teacher.service.BaseDataDictionaryService;

@Service("dataDictionary")
public class BaseDataDictionaryServiceImp implements BaseDataDictionaryService {

	@Autowired
	private BaseDataDictionaryMapper baseDataDictionaryMapper;
	
	@Override
	public List<BaseDataDictionary>  getDataDictionaryAll() {
		
		return baseDataDictionaryMapper.selectAll();
	}
	
	@Override
	public BaseDataDictionary getDataDictionaryById(Integer id){
		return baseDataDictionaryMapper.selectDataDictionaryById(id);
	}

}
