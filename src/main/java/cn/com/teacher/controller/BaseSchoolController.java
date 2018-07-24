package cn.com.teacher.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;





import com.alibaba.fastjson.serializer.SerializerFeature;

//import sun.util.LocaleServiceProviderPool.LocalizedObjectGetter;
import cn.com.teacher.constant.EnumConstant;
import cn.com.teacher.constant.EnumType;
import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.BaseSchool;
import cn.com.teacher.entity.BaselimitSchool;
import cn.com.teacher.entity.Result;
import cn.com.teacher.service.BaseRegionService;
import cn.com.teacher.service.BaseSchoolService;
import cn.com.teacher.util.ExcelUtil;
import cn.com.teacher.util.ExlJxlUtils;
import cn.com.teacher.util.GeneratorExcel;
import cn.com.teacher.util.Pager;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.SchoolExcelVo;

@ApiIgnore
@Controller
@RequestMapping("/school")
public class BaseSchoolController {
    
	private static final Logger LOG = LoggerFactory.getLogger(BaseSchoolController.class);
	@Autowired
	private BaseSchoolService baseSchoolService;
	
	@Autowired
	private BaseRegionService baseRegionService;
	
	/**
	 * 进入学校主界面
	 * 
	 * @param model
	 * @param baselimitSchool
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/goBaseSchool")
	public ModelAndView goBaseSchool(ModelAndView model, HttpServletRequest request) {
		Map map = new HashMap<String,Object>();
		//区域层级
		List<BaseRegion> regions = baseRegionService.getChildrenByParentId(0);
		//学校规模
		Map<String, String> scaleMap = EnumConstant.toMap();
		model.addObject("scaleMap",scaleMap);
		//学校等级
		Map<String, String> typeMap = EnumType.toMap();
		model.addObject("typeMap",typeMap);
		model.addObject("provinceList",regions);
		model.setViewName("schcool/basicData");
		return model;
	}
	
	/**
	 * 进入学校主界面
	 * @param model
	 * @param baselimitSchool
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getbaseSchool")
	@ResponseBody
	public String getbaseSchool(Model model, BaselimitSchool baselimitSchool, Pager page,
			HttpServletRequest request) {
		if (page == null) {
			page = new Pager();
		}
		Map paramMap = new HashMap<String, Object>();
		page.setTotal(baseSchoolService.baseSchoolCount(baselimitSchool));

		List<BaseSchool> findAll = baseSchoolService.getbaseSchoolByPage(page.getStartRecord(), page.getPageSize(),
				baselimitSchool);
		if(findAll.size()>0){
			Map<String, String> scaleMap = EnumConstant.toMap();
			//学校等级
			Map<String, String> typeMap = EnumType.toMap();
			for(BaseSchool bs : findAll){
				bs.setScaleName(scaleMap.get(bs.getScale()+""));
				bs.setTypeName(typeMap.get(bs.getType()+""));
				bs.setRegionName(baseRegionService.findFullNameById(bs.getRegionId()));
			}
		}
		paramMap.put("baseSchoolList", findAll);
		paramMap.put("page", page);
		SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,
	    		SerializerFeature.WriteNullStringAsEmpty};
		return JSONObject.toJSONString(paramMap,features);
	}
	/**
	 * 跳转添加学校
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAddSchool")
	public ModelAndView getAddSchool(ModelAndView model, BaselimitSchool baselimitSchool, Pager page,
			HttpServletRequest request) {
		List<BaseRegion> regions = baseRegionService.getChildrenByParentId(0);
		//学校规模
		Map<String, String> scaleMap = EnumConstant.toMap();
		model.addObject("scaleMap",scaleMap);
		//学校等级
		Map<String, String> typeMap = EnumType.toMap();
		model.addObject("typeMap",typeMap);
		model.addObject("provinceList", regions);
		model.addObject("baseSchool", new BaseSchool());
		model.setViewName("schcool/addSchool");
		return model;
	}
	
	/**
	 * 添加学校
	 * @param request
	 * @param response
	 * @param record
	 * @throws Exception
	 */
	@RequestMapping(value = "insertSchool",method=RequestMethod.POST)
	@ResponseBody
	public String insertSchool(HttpServletRequest request, HttpServletResponse response, BaseSchool record)
			throws Exception {
		return JSON.toJSONString(baseSchoolService.saveSchool(record));
	}

	/**
	 * 根据id查询单个学校
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getByIdSchool")
	public ModelAndView getByIdSchool(int id,ModelAndView model) {
		BaseSchool baseSchool = baseSchoolService.selectByPrimaryKey(id);
		model.addObject("baseSchool",baseSchool);
		BaseRegion  baseRegion = baseRegionService.getBaseRegionById(baseSchool.getRegionId());
		String[] strings = baseRegion.getPath().substring(1,baseRegion.getPath().length()-1).split(",");
		Integer provinceId = null;
		Integer cityId = null;
		Integer areaId = null;
		Integer roadId = null;
		if(strings.length>2){
			
			provinceId = Integer.parseInt(strings[0]);
			cityId = Integer.parseInt(strings[1]);
			areaId = Integer.parseInt(strings[2]);
			roadId = baseSchool.getRegionId();
			
		}else{
			
			provinceId = Integer.parseInt(strings[0]);
			cityId = Integer.parseInt(strings[1]);
			areaId = baseSchool.getRegionId();
			roadId = null;
			
		}
		model.addObject("provinceId", provinceId);
		model.addObject("cityId", cityId);
		model.addObject("areaId", areaId);
		model.addObject("roadId", roadId);
		
		List<BaseRegion> provinceList = baseRegionService.getChildrenByParentId(0);
		List<BaseRegion> cityList = baseRegionService.getChildrenByParentId(provinceId);
		List<BaseRegion> areaList = baseRegionService.getChildrenByParentId(cityId);
		List<BaseRegion> roadList = baseRegionService.getChildrenByParentId(areaId);
	
//		Integer provinceId = Integer.parseInt(strings[1]);
//		Integer cityId     = Integer.parseInt(strings[2]);
//		BaseRegion cityRegion = baseRegionService.getBaseRegionById(cityId);
//		Integer quId     = baseRegion.getId();
//		model.addObject("provinceId", provinceId);
//		model.addObject("cityId", cityId);
//		model.addObject("quId", quId);
//		model.addObject("quName", baseRegion.getName());
//		model.addObject("cityName", cityRegion.getName());
//		
		//学校规模
		Map<String, String> scaleMap = EnumConstant.toMap();
		model.addObject("scaleMap",scaleMap);
		//学校等级
		Map<String, String> typeMap = EnumType.toMap();
		model.addObject("typeMap",typeMap);
		model.addObject("provinceList", provinceList);
		model.addObject("cityList", cityList);
		model.addObject("areaList", areaList);
		model.addObject("roadList", roadList);
		
		model.setViewName("schcool/editSchool");
		return model;
	}

	/**
	 * 编辑学校
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateSchool")
	public String updateSchool(BaseSchool baseSchool, HttpServletRequest request) {
		if (baseSchoolService.updateByPrimaryKeySelective(baseSchool)) {
			baseSchool = baseSchoolService.findById(baseSchool.getId());
			request.setAttribute("baseSchool", baseSchool);
			return "redirect:schcool/basicData";
		} else {
			return JSON.toJSONString(ResultUtil.buildFail("编辑失败"));
		}
	}

	/**
	 * 导入学校页面
	 * @return
	 */
	@RequestMapping(value = "/importSchool")
	public ModelAndView importSchool(ModelAndView model, HttpServletRequest request) {
		model.setViewName("schcool/importSchool");
		return model;
	}

	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteMore")
	@ResponseBody
	public Boolean deleteMore(HttpServletRequest request, @RequestParam("ids") String ids,
			HttpServletResponse response) {
			if (ids!=null && ids!="") {
				boolean deleteMore = baseSchoolService.deleteMore(ids);
				return deleteMore;
			}
			return false;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new String("".getBytes("ISO-8859-1"),"UTF-8"));
	}
	/**
	 * 搜索页面
	 * @param pager
	 * @param school
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/searchSchool")
    @ResponseBody
    public String searchSchool(Pager pager,BaseSchool school,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		LOG.info("接受前端参数："+JSONObject.toJSONString(pager==null?"":pager));
		Map map = new HashMap<String,Object>();
		if(pager==null){
			pager = new Pager();
		}
//		try {
//			String a=new String(school.getName().getBytes("ISO-8859-1"),"UTF-8");
//			System.out.println(a);
//			if(a!=null&&!"".equals(a)){
//				map.put("name", a);
//				System.err.println(map.toString());
//			}else{
//				map.put("name", null);
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		if(StringUtils.isEmpty(school.getName())){
			map.put("name", null);
		}else{
			map.put("name", school.getName());
		}
		
		map.put("area", school.getRegionId());
		map.put("type", school.getType());
		map.put("scale", school.getScale());
		map.put("beginline", pager.getStartRecord());
		map.put("pagesize", pager.getPageSize());
		System.err.println("a"+map.toString());
		List<BaseSchool> baseSchools = baseSchoolService.getBaseSchoolByConditoions(map);
		pager.setTotal(baseSchoolService.countBaseSchoolByConditoions(map));
		if(baseSchools.size()>0){
			Map<String, String> scaleMap = EnumConstant.toMap();
			//学校等级
			Map<String, String> typeMap = EnumType.toMap();
			for(BaseSchool bs : baseSchools){
				bs.setScaleName(scaleMap.get(bs.getScale()+""));
				bs.setTypeName(typeMap.get(bs.getType()+""));
				bs.setRegionName(baseRegionService.findFullNameById(bs.getRegionId()));
			}
		}
		map.clear();
		pager.setPageCount(pager.getPageCount());
		map.put("baseSchoolList", baseSchools);
		map.put("page", pager);
		SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,
	    		SerializerFeature.WriteNullStringAsEmpty};
		return JSONObject.toJSONString(map,features);
	}
	
	/**
	 * 导入学校excel
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/improtSchoolExcel", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload_file,HttpServletRequest request) {

//		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);
		MultipartFile file = upload_file;
		if (null == file) {
			return JSON.toJSONString(ResultUtil.buildFail("上传文件为空！"));
		}
		try {
			Result excelResult = ExcelUtil.uploadExcel(upload_file,SchoolExcelVo.class);
			if (!ResultUtil.isSuccess(excelResult)){
				return JSON.toJSONString(excelResult);
			}
			Result result = baseSchoolService.uploadSchool((List<SchoolExcelVo>)excelResult.getData());
			return JSON.toJSONString(result);
		} catch (Exception e) {
			return JSON.toJSONString(ResultUtil.buildFail("上传学校信息失败"));
		}
//		String originalFileName = file.getOriginalFilename();
//		String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);// 文件后缀
//		if (!"xls".equals(ext) && !"xlsx".equals(ext)) {
//			return JSON.toJSONString(ResultUtil.buildFail("文件为非Excel!"));
//		}
//		
//		LinkedHashMap<String, String> fieldMap1 = new LinkedHashMap<String, String>();
//		fieldMap1.put("学校名称", "name");
//		fieldMap1.put("级别", "typeName");
//		fieldMap1.put("规模", "scaleName");
//		fieldMap1.put("所属区域第一级", "areaName1");
//		fieldMap1.put("所属区域第二级", "areaName2");
//		fieldMap1.put("所属区域第三级", "areaName3");
//		fieldMap1.put("所属区域第四级", "areaName4");
//		String sheetName1 = "Sheet1";
//		InputStream in1 = null;
//		List<BaseSchool> baseSchools = new ArrayList<BaseSchool>();
//		
//		try {
//			in1 = file.getInputStream();
//
//			baseSchools = ExlJxlUtils.excelToList(in1, sheetName1, BaseSchool.class, fieldMap1, false, null);
//			if (baseSchools.size() <= 0) {
//				return JSON.toJSONString(ResultUtil.buildFail("学校信息为空!"));
//			}
//			in1.close();
//		} catch (Exception e) {
//			return JSON.toJSONString(ResultUtil.buildFail("解析excel异常！"+e.getMessage()));
//		}
//		boolean flag = baseSchoolService.saveSchool(baseSchools);
//		if (flag) {
//			return JSON.toJSONString(ResultUtil.buildSuccess("上传成功"));
//		} else {
//			return JSON.toJSONString(ResultUtil.buildFail("上传失败！"));
//		}  
	}
	
	/**
	 * 导出学校
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/baseSchoolInfo")
	public String baseSchoolInfo(Model model,BaseSchool school, BaselimitSchool baselimitSchool, HttpServletRequest request, HttpServletResponse response,
			String type,String name,String scale,String regionId) {
		ExecutorService pool = null;
		try{
			String title = "School-info"+new Random().nextInt(123);// 导出EXCEL的预置文件名称
			Map<String, String> headerMap = new LinkedHashMap<String, String>();
			// 导出EXCEL表表头设置
			headerMap.put("学校名称", "name");
			headerMap.put("级别", "typeName");
			headerMap.put("规模", "scaleName");
			headerMap.put("所属区域第一级", "areaName1");
			headerMap.put("所属区域第二级", "areaName2");
			headerMap.put("所属区域第三级", "areaName3");
			headerMap.put("所属区域第四级", "areaName4");
			//参数 
			response.setContentType("text/html;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			Map map = new HashMap<String,Object>();
			if(name!=null&&!"".equals(name)){
				map.put("name", name);
			}else{
				map.put("name", null);
			}
			if(regionId!=null&&!"".equals(regionId)){
				map.put("area", regionId);
			}else{
				map.put("area", null);
			}
			if(type!=null&&!"".equals(type)){
				map.put("type", type);
			}else{
				map.put("type", null);
			}
			if(scale!=null&&!"".equals(scale)){
				map.put("scale", scale);
			}else{
				map.put("scale", null);
			}
			List<BaseSchool> baseSchools = baseSchoolService.exportBaseSchoolByConditoions(map);
			if(baseSchools.size()>0){
				Map<String, String> scaleMap = EnumConstant.toMap();
				//学校等级
				Map<String, String> typeMap = EnumType.toMap();
				for(BaseSchool bs : baseSchools){
					bs.setScaleName(scaleMap.get(bs.getScale()+""));
					bs.setTypeName(typeMap.get(bs.getType()+""));
					String regionName = baseRegionService.findFullNameById2(bs.getRegionId());
					bs.setRegionName(regionName);
					String[] pathName = regionName.split(" ");
					for(int i=0;i<pathName.length;i++){
						if(i==0){
							bs.setAreaName1(pathName[0]);
						}
						if(i==1){
							bs.setAreaName2(pathName[1]);
						}
						if(i==2){
							bs.setAreaName3(pathName[2]);
						}
						if(i==3){
							bs.setAreaName4(pathName[3]);
						}
						if(i==4){
							bs.setAreaName5(pathName[4]);
						}
					}
				}
			}
		    generateExcel(headerMap, title, baseSchools, request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pool != null) {
				pool.shutdown();
			}
		}
		return null;
	}
	
	/**
	 * 生成excel并导出下载方法
	 * @param headerMap
	 * @param title
	 * @param dataset
	 * @param request
	 * @param response
	 */
	@RequestMapping("generateExcel")
	public <T> void generateExcel(Map<String, String> headerMap, String title, List dataset,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			GeneratorExcel<T> ex = new GeneratorExcel<T>();
			HSSFWorkbook workbook = ex.exportExcel(title, headerMap, dataset);
			writeDownload(title + ".xls", workbook, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeDownload(String fileName, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		workbook.write(response.getOutputStream());
	}
}
