package cn.com.teacher.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.BaseRegionMove;
import cn.com.teacher.entity.BaseSchool;
import cn.com.teacher.entity.Result;
import cn.com.teacher.entity.TreeVo;
import cn.com.teacher.mapper.BaseRegionMapper;
import cn.com.teacher.mapper.BaseSchoolMapper;
import cn.com.teacher.service.BaseRegionService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.RegionExcelVo;


@Service("regionService")
public class BaseRegionServiceImpl implements BaseRegionService {

	@Autowired
	private BaseRegionMapper baseRegionMapper;

	@Autowired
	private BaseSchoolMapper baseSchoolMapper;
	
	public List<BaseRegion> getRegions() {
		return baseRegionMapper.getRegions();
	}

	
	public void update(BaseRegion region) {
		baseRegionMapper.updateByPrimaryKeySelective(region);
	}

	
	public int del(Integer id) {
		return (int) baseRegionMapper.del(id);
	}

	
	public void batchInsert(List<BaseRegion> BaseRegion) {
		baseRegionMapper.batchInsert(BaseRegion);
	}

	
	public List<BaseRegion> findBaseRegion() {
		return baseRegionMapper.findBaseRegion();
	}

	public int insertSelective(BaseRegion record) {
		return baseRegionMapper.insertSelective(record);
	}

	public List<TreeVo> getRegionsTree() {
		
		return baseRegionMapper.getRegionsTree();
	}
	
	public void insert(BaseRegion baseRegion) {
		baseRegionMapper.insert(baseRegion);
	}

	public boolean updateByPrimaryKeySelective(BaseRegion baseRegion) {
		return baseRegionMapper.updateBaseRegion(baseRegion)==1?true:false;
	}

	public Object moveBaseRegion(BaseRegionMove baseRegionMove) {
		List<BaseRegion> baseRegions = baseRegionMove.getBaseRegions();
		if (baseRegions.size() != 2) {
			return ResultUtil.buildFail("排序失败");
		}
		BaseRegion BaseRegion = new BaseRegion();
		BaseRegion BaseRegionSecond = new BaseRegion();
		Integer orderByte = baseRegions.get(0).getCataOrder();
		Integer courseCataId = baseRegions.get(0).getId();
		Integer orderByteSecond = baseRegions.get(1).getCataOrder();
		Integer courseCataIdSecond = baseRegions.get(1).getId();
		if (orderByte != null && courseCataId != null
				&& orderByteSecond != null && courseCataIdSecond != null) {
			BaseRegion.setId(courseCataId);
			BaseRegion.setCataOrder(orderByteSecond);
			BaseRegionSecond.setId(courseCataIdSecond);
			BaseRegionSecond.setCataOrder(orderByte);
		} else {
			return ResultUtil.buildFail("参数非法");
		}
		int result = baseRegionMapper.updateByPrimaryKeySelective(BaseRegion);
		int resultSecond = baseRegionMapper.updateByPrimaryKeySelective(BaseRegionSecond);
		if (result != 0 && resultSecond != 0) {
			return ResultUtil.buildSuccess("排序成功");
		}
		return ResultUtil.buildFail("排序失败");
	}


	public List<BaseRegion> getParentRegion(Map map) {
		return baseRegionMapper.getParentRegion(map);
	}


	public List<BaseRegion> getChildrenByParentId(Integer parentId) {
			return baseRegionMapper.getChildrenByParentId(parentId);
	}


	@SuppressWarnings("unchecked")
	public String findFullNameById(int id) {
		Map map = new HashMap<String,String>();
		map.put("name","");
		getFullParentNameById(id,map);
		if(map.get("name").equals(""))return "";
		String unsortName = (String)map.get("name");
		String[] str = unsortName.substring(0,unsortName.length()-1).split("_");
		StringBuilder sb = new StringBuilder();
		for(int k = str.length-1 ; k>=0;k-- ){
			sb.append(str[k]);
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String findFullNameById2(int id) {
		Map map = new HashMap<String,String>();
		map.put("name","");
		getFullParentNameById(id,map);
		if(map.get("name").equals(""))return "";
		String unsortName = (String)map.get("name");
		String[] str = unsortName.substring(0,unsortName.length()-1).split("_");
		StringBuilder sb = new StringBuilder();
		for(int k = str.length-1 ; k>=0;k-- ){
			if(k==0){
				sb.append(str[k]);
			}else{
				sb.append(str[k]).append(" ");
			}
		}
		return sb.toString();
	}


	/*递归实现全区域名称的汉化   深度达到三层以上比较耗费性能*/
	private void getFullParentNameById(int id,Map map) {
		BaseRegion br = findById(id);
		if(br == null)return ;
		map.put("name", map.get("name")+br.getName()+"_");
		if(br.getParentId()==0) return;
		getFullParentNameById(br.getParentId(),map);
	}


	public List<BaseRegion> getlimiteRegion(String name) {
		 List<BaseRegion> regionList = baseRegionMapper.getlimiteRegion(name);
		 List<BaseRegion> returnRegionList=new ArrayList<>();
		 if ( regionList !=null && regionList.size()>0) {
			 for (BaseRegion baseRegion : regionList) {
				String path = baseRegion.getPath();
				if(path==null){
					continue;
				}
				String[] str = path.split(",");
				for (String i : str) {
					if (i==null || i.equals("")) {
						continue;
					}
					BaseRegion region = baseRegionMapper.getBaseRegionById(Integer.parseInt(i));
					returnRegionList.add(region);
				}
			}
			
		}
		return returnRegionList;
	}

	@Transactional
	public Result deleteRegion(Integer regionId){
		Map map = Maps.newHashMap();
		map.put("path", "," + regionId + ",");
		List<BaseRegion> baseRegionList = getParentRegion(map);
		List<Integer> idList = new ArrayList<>();
		if(baseRegionList!=null&&baseRegionList.size()>0){
			for (BaseRegion baseRegion : baseRegionList) {
				idList.add(baseRegion.getId());
			}
		}
		idList.add(regionId);
		List<BaseSchool> baseSchoolList = baseSchoolMapper.getSchoolByRegionId(idList);
		if(baseSchoolList!=null&&baseSchoolList.size()>0){
			return ResultUtil.buildFail("该区域节点有学校，不能删除！");
		}
		baseRegionMapper.deleteByPrimaryKey(regionId);
		baseRegionMapper.deleteChild("," + regionId + ",");
		return ResultUtil.buildSuccess(null);
	}
	
	public int deleteByPrimaryKeys(List<BaseRegion> list) {
		
		if (list !=null && !list.isEmpty()) {
			List <Integer> idList=new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				idList.add(list.get(i).getId());
			}
			
			List<BaseSchool>	baseSchoolList=baseSchoolMapper.getSchoolByRegionId(idList);
			if (baseSchoolList !=null && !baseSchoolList.isEmpty()) {
				return 0;
			}else{
				int count = baseRegionMapper.deleteByPrimaryKeys(list);
				return count;
			}
		}else{
			return 0;
		}
	}

	public int selectMaxSeq() {
		return baseRegionMapper.selectMaxSeq();
	}


	public BaseRegion findById(Integer id) {
		return baseRegionMapper.selectByPrimaryKey(id);
	}


	public List<BaseRegion> getRegionInfo() {
		return baseRegionMapper.getRegions();
	}

	public BaseRegion  getBaseRegionById(Integer regionId){
		return baseRegionMapper.getBaseRegionById(regionId);
	};
	
	public BaseRegion getBaseRegion(String name,Integer parentId,Byte level){
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("parentId", parentId);
		map.put("level", level);
		BaseRegion baseRegion = baseRegionMapper.getBaseRegionByMap(map);
		return baseRegion;
	}
	
	public boolean saveRegion(List<BaseRegion> baseRegions){
		for(int i=0;i<baseRegions.size();i++){
			String areaName1 = baseRegions.get(i).getAreaName1();
			int pid = 0;
			StringBuffer path = new StringBuffer();
			if(areaName1!=null && areaName1.length()>0){
				pid = getBaseRegionId(areaName1,pid,(byte)1,path.toString());
				String areaName2 = baseRegions.get(i).getAreaName2();
				if(areaName2!=null && areaName2.length()>0){
					path.append(",").append(String.valueOf(pid)).append(",");
					pid = getBaseRegionId(areaName2,pid,(byte)2,path.toString());
					String areaName3 = baseRegions.get(i).getAreaName3();
					if(areaName3!=null && areaName3.length()>0){
						path.append(String.valueOf(pid)).append(",");
						pid = getBaseRegionId(areaName3,pid,(byte)3,path.toString());
						String areaName4 = baseRegions.get(i).getAreaName4();
						if(areaName4!=null && areaName4.length()>0){
							path.append(String.valueOf(pid)).append(",");
							pid = getBaseRegionId(areaName4,pid,(byte)4,path.toString());
							String areaName5 = baseRegions.get(i).getAreaName5();
							if(areaName5!=null && areaName5.length()>0){
								path.append(String.valueOf(pid)).append(",");
								pid = getBaseRegionId(areaName5,pid,(byte)5,path.toString());
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	
	private int getBaseRegionId(String areaName,Integer pid,Byte byt,String path){
		BaseRegion baseRegion1 = getBaseRegion(areaName,pid,byt);
		
		if(baseRegion1!=null){
			pid = baseRegion1.getId();
		}else{
			BaseRegion baseRegion = new BaseRegion();
			baseRegion.setName(areaName);
			baseRegion.setParentId(pid);
			baseRegion.setLevel(byt);
			baseRegion.setPath(path);
			baseRegionMapper.insert(baseRegion);
			BaseRegion baseRegion2 = getBaseRegion(areaName,pid,byt);
			pid = baseRegion2.getId();
		}
		return pid;
	}


	@Override
	@Transactional
	public Result uploadRegion(List<RegionExcelVo> regionExcelVoList) {
		Result validateResult = validateAndTransfor(regionExcelVoList);
		List<List<BaseRegion>> rowRegionList = (List<List<BaseRegion>>)validateResult.getData();
		if (CollectionUtils.isEmpty(rowRegionList)) {
			return ResultUtil.buildFail(validateResult.getMsg());
		}
		Map<String,BaseRegion> regionMap = new HashMap<String, BaseRegion>();
		BaseRegion baseRegion = null;
		int parentId;
		StringBuffer pathStr = new StringBuffer();
		for (List<BaseRegion> list : rowRegionList) {
			parentId = 0;
			pathStr.setLength(0);
			pathStr.append(",");
			for (BaseRegion region : list) {
				baseRegion = regionMap.get(region.getName()+parentId);
				if(baseRegion==null){
					
					baseRegion = getBaseRegionByNameAndParent(region.getName(),parentId);
					if(baseRegion==null){
						
						region.setParentId(parentId);
						region.setPath(parentId==0?null:pathStr.toString());
						region.setCataOrder(selectMaxSeq());
						insert(region);
						regionMap.put(region.getName()+parentId, region);
						parentId = region.getId();
						pathStr.append(parentId).append(",");
						
					}else{
						
						regionMap.put(baseRegion.getName()+parentId, baseRegion);
						parentId = baseRegion.getId();
						pathStr.append(parentId).append(",");
						
					}
					
				}else{
					parentId = baseRegion.getId();
					pathStr.append(parentId).append(",");
				}
				
			}
		}
		if (ResultUtil.isSuccess(validateResult)) {
			return ResultUtil.buildSuccess("全部导入成功~");
		} else {
			return ResultUtil
					.buildFail(validateResult.getMsg() + ",其余导入成功~");
		}
		
	}
	
	private Result validateAndTransfor(List<RegionExcelVo> regionExcelVoList){
		
		if (CollectionUtils.isEmpty(regionExcelVoList)) {
			return ResultUtil.buildFail("区域列表为空");
		}
		StringBuilder errorString = new StringBuilder("");
		List<List<BaseRegion>> rowRegionList = new ArrayList<List<BaseRegion>>();
		int lineNum = 1;
		boolean isError = false;
		for (RegionExcelVo regionExcelVo : regionExcelVoList) {
			isError = false;
			lineNum++;
			if(StringUtils.isEmpty(regionExcelVo.getRegionFirstLevel())){
				errorString.append("第" + lineNum + "行,区域第一级不能为空;<br/>");
				isError = true;
			}
			if(StringUtils.isEmpty(regionExcelVo.getRegionSecondLevel())&&(
					!StringUtils.isEmpty(regionExcelVo.getRegionThirdLevel())||
					!StringUtils.isEmpty(regionExcelVo.getRegionFourthLevel())||
					!StringUtils.isEmpty(regionExcelVo.getRegionFifthLevel()))){
				errorString.append("第" + lineNum + "行,区域第二级不能为空;<br/>");
				isError = true;
			}
			if(StringUtils.isEmpty(regionExcelVo.getRegionThirdLevel())&&(
					!StringUtils.isEmpty(regionExcelVo.getRegionFourthLevel())||
					!StringUtils.isEmpty(regionExcelVo.getRegionFifthLevel()))){
				errorString.append("第" + lineNum + "行,区域第三级不能为空;<br/>");
				isError = true;
			}
			if(StringUtils.isEmpty(regionExcelVo.getRegionFourthLevel())&&
					!StringUtils.isEmpty(regionExcelVo.getRegionFifthLevel())){
				errorString.append("第" + lineNum + "行,区域第四级不能为空;<br/>");
				isError = true;
			}			
			if (!isError) {
				
				rowRegionList.add(buildBaseRegion(regionExcelVo));
			}
			
		}
		if (StringUtils.isEmpty(errorString.toString())) {
			return ResultUtil.buildSuccess(rowRegionList);
		}
		Result errorResult = ResultUtil.buildFail(errorString.toString());
		errorResult.setData(rowRegionList);
		return errorResult;
	}
	
	private List<BaseRegion> buildBaseRegion(RegionExcelVo regionExcelVo){
		
		List<BaseRegion> rowRegions = new ArrayList<BaseRegion>();
		BaseRegion baseRegion = new BaseRegion();
		baseRegion.setName(regionExcelVo.getRegionFirstLevel());
		baseRegion.setLevel((byte)1);
		rowRegions.add(baseRegion);
		if(!StringUtils.isEmpty(regionExcelVo.getRegionSecondLevel())){
			baseRegion = new BaseRegion();
			baseRegion.setName(regionExcelVo.getRegionSecondLevel());
			baseRegion.setLevel((byte)2);
			rowRegions.add(baseRegion);
		}
		if(!StringUtils.isEmpty(regionExcelVo.getRegionThirdLevel())){
			baseRegion = new BaseRegion();
			baseRegion.setName(regionExcelVo.getRegionThirdLevel());
			baseRegion.setLevel((byte)3);
			rowRegions.add(baseRegion);
		}
		if(!StringUtils.isEmpty(regionExcelVo.getRegionFourthLevel())){
			baseRegion = new BaseRegion();
			baseRegion.setName(regionExcelVo.getRegionFourthLevel());
			baseRegion.setLevel((byte)4);
			rowRegions.add(baseRegion);
		}
		if(!StringUtils.isEmpty(regionExcelVo.getRegionFifthLevel())){
			baseRegion = new BaseRegion();
			baseRegion.setName(regionExcelVo.getRegionFifthLevel());
			baseRegion.setLevel((byte)5);
			rowRegions.add(baseRegion);
		}
		return rowRegions;
		
	}
	
	@Override
	public BaseRegion getBaseRegionByNameAndParent(String name,Integer parentId){
		
		List<BaseRegion> regionList = baseRegionMapper.getRegionByNameAndParent(parentId, name);
		if(regionList!=null&&regionList.size()>0){
			return regionList.get(0);
		}
		return null;
	}
}
