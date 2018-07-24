package cn.com.teacher.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.teacher.constant.EnumConstant;
import cn.com.teacher.constant.EnumType;
import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.BaseSchool;
import cn.com.teacher.entity.BaseSchoolExample;
import cn.com.teacher.entity.BaselimitSchool;
import cn.com.teacher.entity.Result;
import cn.com.teacher.mapper.BaseRegionMapper;
import cn.com.teacher.mapper.BaseSchoolMapper;
import cn.com.teacher.service.BaseRegionService;
import cn.com.teacher.service.BaseSchoolService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.SchoolExcelVo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("schoolService")
public class BaseSchoolServiceImpl implements BaseSchoolService {

	@Autowired
	private BaseSchoolMapper baseSchoolMapper;
	@Autowired
	private BaseRegionService baseRegionService;
	@Autowired
	private BaseRegionMapper baseRegionMapper;

	public int countByExample(BaseSchoolExample example) {
		return baseSchoolMapper.countByExample(example);
	}

	public int deleteByExample(BaseSchoolExample example) {
		return baseSchoolMapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return baseSchoolMapper.deleteByPrimaryKey(id);
	}

	public int insert(BaseSchool record) {
		
		return baseSchoolMapper.insert(record);
	}

	public int insertSelective(BaseSchool record) {
		
		return baseSchoolMapper.insertSelective(record);
	}

	public List<BaseSchool> selectByExample(BaseSchoolExample example) {
		
		return baseSchoolMapper.selectByExample(example);
	}

	public BaseSchool selectByPrimaryKey(Integer id) {
		
		return baseSchoolMapper.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(BaseSchool record, BaseSchoolExample example) {
		
		return baseSchoolMapper.updateByExampleSelective(record,example);
	}

	public int updateByExample(BaseSchool record, BaseSchoolExample example) {
		
		return baseSchoolMapper.updateByExample(record, example);
	}

	public boolean updateByPrimaryKeySelective(BaseSchool record) {
		
		return baseSchoolMapper.updateByPrimaryKey(record) < 0 ? true : false;
	}

	public int updateByPrimaryKey(BaseSchool record) {
		
		return baseSchoolMapper.updateByPrimaryKey(record);
	}

	public int baseSchoolCount(BaselimitSchool baselimitSchool) {
		return baseSchoolMapper.baseSchoolCount(baselimitSchool);
	}

	public List<BaseSchool> getbaseSchoolByPage(int startRecord, int pageSize, BaselimitSchool baselimitSchool) {
		Map<String, Object> map = new HashMap();
		map.put("startRecord", startRecord);
		map.put("pageSize", pageSize);
		map.put("name", baselimitSchool.getName());
		map.put("regionId", baselimitSchool.getRegionId());
		map.put("type", baselimitSchool.getType());
		map.put("scale", baselimitSchool.getScale());
		return baseSchoolMapper.getbaseSchoolByPage(map);
	}

	public BaseSchool findById(Integer id) {
	
		return baseSchoolMapper.updateByKey(id);
	}

	public PageInfo<BaseSchool> getAllbaseSchool(BaseSchool baseSchool) {
		if(baseSchool==null){
			new BaseSchool();
		}
		PageHelper.startPage(baseSchool.getPageNum(),baseSchool.getPageSize());
		List<BaseSchool> list = baseSchoolMapper.getAllbaseSchool(baseSchool);
		PageInfo<BaseSchool> page = new PageInfo<BaseSchool>(list);
		
		return page;
	}
	
	public boolean deleteMore(List<Integer> ids) {
		for (Integer id : ids) {
			baseSchoolMapper.deleteByPrimaryKey(id);
		}
		return true;
	}
	
	public List<BaseSchool> getBaseSchoolByConditoions(Map map) {
		return baseSchoolMapper.getBaseSchoolByConditoions(map);
	}
	
	public List<BaseSchool> exportBaseSchoolByConditoions(Map map) {
		return baseSchoolMapper.exportBaseSchoolByConditoions(map);
	}
	
	public int countBaseSchoolByConditoions(Map map) {
		return baseSchoolMapper.countBaseSchoolByConditoions(map);
	}
	
	public boolean deleteMore(String ids) {
		try {
			Map<String, String> map=new HashMap<String, String>();
			map.put("ids", ids);
			int count = baseSchoolMapper.deleteMore(map);
			if (ids.split(",").length==count) {
				return true;
			}
		} catch (Exception e) {
			new RuntimeException();
			return false;
		}
		return false;
	}

	public List<BaseSchool> getBaseSchoolInfo(Map map) {
		// TODO Auto-generated method stub
		return baseSchoolMapper.getBaseSchoolInfo(map);
	}
	
	public boolean saveSchool(List<BaseSchool> baseSchools){
		for(int i=0;i<baseSchools.size();i++){
			BaseSchool baseSchool = baseSchools.get(i);
			Integer regionId = -1;
			//地区id
			if(baseSchool.getAreaName1()!=null && baseSchool.getAreaName1().length()>0){
				BaseRegion baseRegion1 = baseRegionService.getBaseRegion(baseSchool.getAreaName1(),0,(byte)1);
				if(baseRegion1 != null){
					if(baseSchool.getAreaName2()!=null && baseSchool.getAreaName2().length()>0){
						BaseRegion baseRegion2 = baseRegionService.getBaseRegion(baseSchool.getAreaName2(),baseRegion1.getParentId(),(byte)2);
						if(baseRegion2 != null){
							if(baseSchool.getAreaName3()!=null && baseSchool.getAreaName3().length()>0){
								BaseRegion baseRegion3 = baseRegionService.getBaseRegion(baseSchool.getAreaName3(),baseRegion1.getParentId(),(byte)3);
								if(baseRegion3 != null){
									if(baseSchool.getAreaName4()!=null && baseSchool.getAreaName4().length()>0){
										BaseRegion baseRegion4 = baseRegionService.getBaseRegion(baseSchool.getAreaName4(),baseRegion1.getParentId(),(byte)4);
										if(baseRegion4 != null){
											regionId = baseRegion4.getId();
										}else{
											regionId = baseRegion3.getId();
										}
									}
								}else{
									regionId = baseRegion2.getId();
								}
							}
						}else{
							regionId = baseRegion1.getId();
						}
					}
				}
			}
			if(regionId > -1){
				baseSchool.setRegionId(regionId);
			}else{
				continue;
			}
			Integer typeId = -1;
			typeId = Integer.valueOf(getTypeId(baseSchool.getTypeName()));
			if(typeId > -1){
				baseSchool.setType(typeId);
			}else{
				continue;
			}
			baseSchool.setScale(Integer.valueOf(getScaleId(baseSchool.getScaleName())));
			baseSchool.setCreateDate(new Date());
			baseSchool.setIsStart(false);
			baseSchool.setCreatorId(0);//暂时默认为0
			System.out.println("----------1-------");
			baseSchoolMapper.insert(baseSchool);
			System.out.println("---------2--------");
		}
		return true;
	}
	
//	private BaseRegion getBaseRegion(String name,Integer parentId,Byte level){
//		Map<String, Object> map = new HashMap<>();
//		map.put("name", name);
//		map.put("parentId", parentId);
//		map.put("level", level);
//		BaseRegion baseRegion = baseRegionMapper.getBaseRegionByMap(map);
//		return baseRegion;
//	}
	
	private String getScaleId(String content){
		if(content.equals(EnumConstant.SCHOOL_SCALE_ONE.getValue())){
			return EnumConstant.SCHOOL_SCALE_ONE.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_TWO.getValue())){
			return EnumConstant.SCHOOL_SCALE_TWO.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_THREE.getValue())){
			return EnumConstant.SCHOOL_SCALE_THREE.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_FOUR.getValue())){
			return EnumConstant.SCHOOL_SCALE_FOUR.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_FIVE.getValue())){
			return EnumConstant.SCHOOL_SCALE_FIVE.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_SIX.getValue())){
			return EnumConstant.SCHOOL_SCALE_SIX.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_SEVEN.getValue())){
			return EnumConstant.SCHOOL_SCALE_SEVEN.getKey();
		}else if(content.equals(EnumConstant.SCHOOL_SCALE_EIGHT.getValue())){
			return EnumConstant.SCHOOL_SCALE_EIGHT.getKey();
		}
		return null;
	}
	
	private String getTypeId(String content){
		if(content.equals(EnumType.SCHOOL_TUPE_PROVINCE.getValue())){
			return EnumType.SCHOOL_TUPE_PROVINCE.getKey();
		}else if(content.equals(EnumType.SCHOOL_TUPE_CITY.getValue())){
			return EnumType.SCHOOL_TUPE_CITY.getKey();
		}else if(content.equals(EnumType.SCHOOL_TUPE_COUNTY.getValue())){
			return EnumType.SCHOOL_TUPE_COUNTY.getKey();
		}else if(content.equals(EnumType.SCHOOL_TUPE_ORDINARY.getValue())){
			return EnumType.SCHOOL_TUPE_ORDINARY.getKey();
		}
		return null;
	}

	@Override
	@Transactional
	public Result uploadSchool(List<SchoolExcelVo> schoolExcelList) {
		Result validateResult = validateAndTransfor(schoolExcelList);
		List<BaseSchool> schoolList = (List<BaseSchool>)validateResult.getData();
		if (CollectionUtils.isEmpty(schoolList)) {
			return ResultUtil.buildFail(validateResult.getMsg());
		}
		for (BaseSchool baseSchool : schoolList) {
			baseSchoolMapper.insert(baseSchool);
		}
		if (ResultUtil.isSuccess(validateResult)) {
			return ResultUtil.buildSuccess("全部导入成功~");
		} else {
			return ResultUtil
					.buildFail(validateResult.getMsg() + ",其余导入成功~");
		}
	}
	
	private Result validateAndTransfor(List<SchoolExcelVo> schoolExcelList){
		
		if (CollectionUtils.isEmpty(schoolExcelList)) {
			return ResultUtil.buildFail("学校列表为空");
		}
		StringBuilder errorString = new StringBuilder("");
		List<BaseSchool> schoolList = new ArrayList<BaseSchool>();
		int lineNum = 1;
		boolean isError = false;
		for (SchoolExcelVo baseSchoolVo : schoolExcelList) {
			isError = false;
			lineNum++;
			if (StringUtils.isEmpty(baseSchoolVo.getSchoolName())) {
				errorString.append("第" + lineNum + "行,学校名称不能为空;<br/>");
				isError = true;
			}
			if (!StringUtils.isEmpty(baseSchoolVo.getSchoolLevel())) {
				if(!EnumType.isExists(baseSchoolVo.getSchoolLevel())){
					errorString.append("第" + lineNum + "行,学校级别["+EnumType.getContents()+"]输入不合法;<br/>");
					isError = true;
				}
				
			}
			if(!StringUtils.isEmpty(baseSchoolVo.getSchoolScale())){
				if(!EnumConstant.isExists(baseSchoolVo.getSchoolScale())){
					errorString.append("第" + lineNum + "行,学校规模["+EnumConstant.getContents()+"]输入不合法;<br/>");
					isError = true;
				}
			}
			if (StringUtils.isEmpty(baseSchoolVo.getRegionFirstLevel())) {
				errorString.append("第" + lineNum + "行,所属区域一级不能为空;<br/>");
				isError = true;
			}else{
				BaseRegion region = getRegionByName(baseSchoolVo.getRegionFirstLevel(),0);
				if(region==null){
					errorString.append("第" + lineNum + "行,所属区域一级"+baseSchoolVo.getRegionFirstLevel()+"不是本系统区域数据;<br/>");
					isError = true;
				}else{
					if(region.getLevel()!=1){
						errorString.append("第" + lineNum + "行,所属区域一级"+baseSchoolVo.getRegionFirstLevel()+"不是一级区域数据;<br/>");
						isError = true;
					}else{
						baseSchoolVo.setRegionId(region.getId());
					}
					
				}
			}
			if(baseSchoolVo.getRegionId()!=null){
				if (StringUtils.isEmpty(baseSchoolVo.getRegionSecondLevel())) {
					errorString.append("第" + lineNum + "行,所属区域二级不能为空;<br/>");
					isError = true;
					baseSchoolVo.setRegionId(null);
				}else{
					BaseRegion region = getRegionByName(baseSchoolVo.getRegionSecondLevel(),baseSchoolVo.getRegionId());
					if(region==null){
						errorString.append("第" + lineNum + "行,所属区域二级"+baseSchoolVo.getRegionSecondLevel()+"不是本系统区域数据;<br/>");
						isError = true;
						baseSchoolVo.setRegionId(null);
					}else{
						if(region.getLevel()!=2){
							errorString.append("第" + lineNum + "行,所属区域二级"+baseSchoolVo.getRegionSecondLevel()+"不是二级区域数据;<br/>");
							isError = true;
							baseSchoolVo.setRegionId(null);
						}else{
							baseSchoolVo.setRegionId(region.getId());
						}
						
					}
				}
			}
			if(baseSchoolVo.getRegionId()!=null){
				if (StringUtils.isEmpty(baseSchoolVo.getRegionThirdLevel())) {
					errorString.append("第" + lineNum + "行,所属区域三级不能为空;<br/>");
					isError = true;
					baseSchoolVo.setRegionId(null);
				}else{
					BaseRegion region = getRegionByName(baseSchoolVo.getRegionThirdLevel(),baseSchoolVo.getRegionId());
					if(region==null){
						errorString.append("第" + lineNum + "行,所属区域三级"+baseSchoolVo.getRegionThirdLevel()+"不是本系统区域数据;<br/>");
						isError = true;
						baseSchoolVo.setRegionId(null);
					}else{
						if(region.getLevel()!=3){
							errorString.append("第" + lineNum + "行,所属区域三级"+baseSchoolVo.getRegionThirdLevel()+"不是三级区域数据;<br/>");
							isError = true;
							baseSchoolVo.setRegionId(null);
						}else{
							baseSchoolVo.setRegionId(region.getId());
						}
						
					}
				}
				
			}
			if(baseSchoolVo.getRegionId()!=null){
				if(!StringUtils.isEmpty(baseSchoolVo.getRegionFourthLevel())){
					BaseRegion region = getRegionByName(baseSchoolVo.getRegionFourthLevel(),baseSchoolVo.getRegionId());
					if(region==null){
						errorString.append("第" + lineNum + "行,所属区域四级"+baseSchoolVo.getRegionFourthLevel()+"不是本系统区域数据;<br/>");
						isError = true;
						baseSchoolVo.setRegionId(null);
					}else{
						if(region.getLevel()!=4){
							errorString.append("第" + lineNum + "行,所属区域四级"+baseSchoolVo.getRegionFourthLevel()+"不是四级区域数据;<br/>");
							isError = true;
							baseSchoolVo.setRegionId(null);
						}else{
							baseSchoolVo.setRegionId(region.getId());
						}
						
					}
				}
			}
			//TODO 判断学校名称是否唯一
			if(!isError){
				BaseSchool baseSchool = 
						baseSchoolMapper.getByRegionAndName(baseSchoolVo.getRegionId(), baseSchoolVo.getSchoolName());
				if(baseSchool!=null){
					errorString.append("第" + lineNum + "行,所属区域学校"+baseSchoolVo.getSchoolName()+"已经存在;<br/>");
					isError = true;
				}
			}
			
			if (!isError) {
				
				schoolList.add(buildBaseSchool(baseSchoolVo));
			}
		}
		if (StringUtils.isEmpty(errorString.toString())) {
			return ResultUtil.buildSuccess(schoolList);
		}
		Result errorResult = ResultUtil.buildFail(errorString.toString());
		errorResult.setData(schoolList);
		return errorResult;
	}
	
	private BaseSchool buildBaseSchool(SchoolExcelVo baseSchoolVo){
		BaseSchool school = new BaseSchool();
		school.setCreateDate(new Date());
		school.setCreatorId(0);
		school.setIsStart(false);
		school.setName(baseSchoolVo.getSchoolName());
		school.setScale(Integer.valueOf(EnumConstant.getLevel(baseSchoolVo.getSchoolScale())));
		school.setType(Integer.valueOf(EnumType.getType(baseSchoolVo.getSchoolLevel())));
		school.setRegionId(baseSchoolVo.getRegionId());
		return school;
	}
	
	private BaseRegion getRegionByName(String name,Integer parentId){
		List<BaseRegion> regionList = baseRegionMapper.getRegionByName(name,parentId);
		if(regionList!=null&&regionList.size()>0){
			return regionList.get(0);
		}
		return null;
	}

	@Override
	public Result saveSchool(BaseSchool school) {
		if (school == null) {
			return ResultUtil.buildFail("新增学校为空");
		}
		BaseSchool baseSchool = 
				baseSchoolMapper.getByRegionAndName(school.getRegionId(), school.getName());
		if(school.getId()!=null){

			if(baseSchool!=null){
				if(baseSchool.getId().intValue()!=school.getId().intValue()){
					return ResultUtil.buildFail("所属区域下已经存在名称为["+school.getName()+"]的学校信息");
				}
			}
			BaseSchool oldBs = baseSchoolMapper.selectByPrimaryKey(school.getId());
			oldBs.setName(school.getName());
			oldBs.setRegionId(school.getRegionId());
			oldBs.setType(school.getType());
			oldBs.setScale(school.getScale());
			baseSchoolMapper.updateByPrimaryKey(oldBs);
			return ResultUtil.buildSuccess("更新成功！");
		}else{
			if(baseSchool!=null){
				return ResultUtil.buildFail("所属区域下已经存在名称为["+school.getName()+"]的学校信息");
			}
			school.setCreateDate(new Date());
			school.setIsStart(false);
			school.setCreatorId(0);//暂时默认为0
			school.setName(school.getName());
			baseSchoolMapper.insert(school);
			return ResultUtil.buildSuccess("新增成功！");
		}
		
	}
}
