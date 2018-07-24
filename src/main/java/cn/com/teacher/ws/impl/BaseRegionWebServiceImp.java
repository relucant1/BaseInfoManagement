package cn.com.teacher.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.mapper.BaseRegionMapper;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.ws.BaseRegionWebService;

@Service("baseRegionWebService")
public class BaseRegionWebServiceImp extends BaseServiceImpl implements BaseRegionWebService {

	@Autowired
	private BaseRegionMapper baseRegionMapper;

	@Override
	public String getBaseRegion() throws Exception {
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			//区域层级
			List<BaseRegion> regions = baseRegionMapper.getChildrenByParentId(0);
			map.put("list",regions);
			return JSON.toJSONString(ResultUtil.buildSuccess(map));
		}catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(ResultUtil.buildFail("系统错误,请联系管理员"));
		}
	}

	@Override
	public String getRegionByParentId() {
		
		String parentId = this.getParam("parentId");
		List<BaseRegion> regions = baseRegionMapper.getChildrenByParentId(Integer.valueOf(parentId));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list",regions);
		return JSON.toJSONString(ResultUtil.buildSuccess(map));
		
	}
	
	

}
