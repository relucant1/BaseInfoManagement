package cn.com.teacher.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.teacher.entity.BaseDataItem;
import cn.com.teacher.mapper.BaseDataItemMapper;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.ws.DataDictionaryWebService;

@Service("dataDictionaryWebService")
public class DataDictionaryWebServiceImp extends BaseServiceImpl implements DataDictionaryWebService {

	@Autowired
	private BaseDataItemMapper baseDataItemMapper;

	@Override
	public String getDataItems() throws Exception {
		
		String id = getParam("categoryId");
		if(null==id){
			return JSON.toJSONString(ResultUtil.buildFail("请求参数转换出错！"));
		}
		
		Map<String,Object> m = new HashMap<String,Object>();
		try{
			List<BaseDataItem> items = baseDataItemMapper.getDataItemsByCategoryId(Integer.valueOf(id) );
			m.put("list",items);
			return JSON.toJSONString(ResultUtil.buildSuccess(m));
		}catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}

	@Override
	public String getEntireDataItems() throws Exception {

		Map<String,Object> m = new HashMap<String,Object>();
		try{
			List<BaseDataItem> items = baseDataItemMapper.getEntireDataItems();
			m.put("list",items);
			return JSON.toJSONString(ResultUtil.buildSuccess(m));
		}catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}


}
