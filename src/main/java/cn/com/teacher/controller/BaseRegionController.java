package cn.com.teacher.controller;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.google.common.collect.Maps;

import cn.com.teacher.entity.BaseRegion;
import cn.com.teacher.entity.BaseRegionMove;
import cn.com.teacher.entity.Result;
import cn.com.teacher.entity.TreeVo;
import cn.com.teacher.service.BaseRegionService;
import cn.com.teacher.util.ExcelUtil;
import cn.com.teacher.util.ExlJxlUtils;
import cn.com.teacher.util.GeneratorExcel;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.vo.RegionExcelVo;
import cn.com.teacher.vo.SchoolExcelVo;
import cn.com.teacher.vo.ZtreeVo;

@ApiIgnore
@Controller
@RequestMapping("/region")
public class BaseRegionController {

	@Autowired
	private BaseRegionService baseRegionService;

	/**
	 * 进入区域层级
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */   
	@RequestMapping(value = "/getRegion")
	public String getRegion(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		model.addAttribute("treeDatas", baseRegionService.findBaseRegion());
		
		return "administrativeLevel/regionalLevel";
	}

	/**
	 * 获取区域层级
	 * @param model
	 * @param baseRegion
	 * @param request
	 * @return
	 * @return
	 */
	@RequestMapping(value = "/getRegiontree")
	@ResponseBody
	public ZtreeVo getRegiontree(ModelAndView model, BaseRegion baseRegion, HttpServletRequest request)
			throws Exception {

		List<TreeVo> RegionList = baseRegionService.getRegionsTree();
		ZtreeVo ztreeVo = new ZtreeVo();
		ztreeVo.setEare1(RegionList);
		return ztreeVo;
	}

	/**
	 * 模糊查询
	 * @param model
	 * @param baseRegion
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getlimiteRegion")
	@ResponseBody
	@SuppressWarnings("all")
	public Map getlimiteRegion(ModelAndView model, BaseRegion baseRegion, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
//		request.setAttribute("areaName",baseRegion.getName());
		request.setAttribute("areaName","紫荆路");
		Map<String, Object> map = new HashMap();
		if (StringUtils.isEmpty(baseRegion.getName())) {
			map.put("regionList", null);
			return map;
		}
		List<BaseRegion> baseRegionList = new ArrayList<BaseRegion>();
		List<BaseRegion> findAll = baseRegionService.getlimiteRegion(baseRegion.getName());

		 List<Integer> regionList=new ArrayList<>();
			for (BaseRegion baseRegion2 : findAll) {
				regionList.add(baseRegion2.getId());
			}
		 map.put("regionList", regionList);


		return map;
	}
//	public Map getlimiteRegion(ModelAndView model, BaseRegion baseRegion, HttpServletResponse response,
//			HttpServletRequest request) throws Exception {
////		request.setAttribute("areaName",baseRegion.getName());
//		request.setAttribute("areaName","紫荆路");
//		Map<String, Object> map = Maps.newHashMap();
//		if (StringUtils.isEmpty(baseRegion.getName())) {
//			map.put("regionList", baseRegionService.findBaseRegion());
//			return map;
//		}
//		List<BaseRegion> baseRegionList = new ArrayList<BaseRegion>();
//		List<BaseRegion> findAll = baseRegionService.getlimiteRegion("紫荆路");
//		List<String[]> arrList=new ArrayList<>();
////		List<BaseRegion> findAll = baseRegionService.getlimiteRegion(baseRegion.getName());
//		if (findAll == null || findAll.size() == 0) {
//			request.setAttribute("regionList", null);
//		} else {
//			for (BaseRegion baseRegion1 : findAll) {
//				baseRegionList.add(baseRegion1);
//				// 查询上级和下级
//				String path = baseRegion1.getPath();
//				if (StringUtils.isNotBlank(path)) {// 有上级
//					String[] str = path.substring(1, path.length() - 1).split(",");
//					int array[] = new int[str.length];
//					for (int i = 0; i < str.length; i++) {
//						array[i] = Integer.parseInt(str[i]);
//					}
//					map.put("ids", array);
//					arrList.add(str);
//					baseRegionList.addAll(baseRegionService.getParentRegion(map));
//					
//				}
//				// 查询子集（path中有,id,）
//				map.clear();
//				map.put("path", "," + baseRegion1.getId() + ",");
//				baseRegionList.addAll(baseRegionService.getParentRegion(map));
//			}
//		}
//		map.clear();
//		/*
//		 * 去重
//		 */
//		List<BaseRegion> tempList = new ArrayList<BaseRegion>();
//		boolean flag = false;
//		for (BaseRegion i : baseRegionList) {
//			flag = false;
//			if (tempList.size() == 0) {
//				tempList.add(i);
//			} else {
//				for (BaseRegion j : tempList) {
//					if (j.getId().equals(i.getId())) {
//						flag = true;
//					}
//				}
//				if (!flag) {
//					tempList.add(i);
//					flag = false;
//				}
//			}
//		}
//		map.put("regionList", tempList);
//		map.put("pidArr", arrList);
//		return map;
//	}

	/**
	 * 进入批量导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getbatchImport")
	public String getbatchImport(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "administrativeLevel/batchImport";
	}

	/**
	 * 添加省级
	 * @param request
	 * @param response
	 * @param baseRegion
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "insertBaseRegion")
	@ResponseBody
	public String insertBaseRegion(HttpServletRequest request, HttpServletResponse response, BaseRegion baseRegion)
			throws Exception {
		BaseRegion before = null;
		if (StringUtils.isEmpty(baseRegion.getName())) {
			return JSON.toJSONString(ResultUtil.buildFail("添加失败"));
		}
		List<BaseRegion> regions = new ArrayList<BaseRegion>();
		if (baseRegion.getId() != null) {
			before = baseRegionService.findById(baseRegion.getId());
		}
		String[] name = baseRegion.getName().split("\n");
		int parentId = baseRegion.getId() == null?0:before.getId();
		for (String str : name) {
			BaseRegion region = baseRegionService.getBaseRegionByNameAndParent(str,parentId);
			if(region!=null){
				return JSON.toJSONString(ResultUtil.buildFail(str+"已经存在！"));
			}
		}
		int maxSeq = baseRegionService.selectMaxSeq();
		for (String n : name) {
			maxSeq++;
			BaseRegion region = new BaseRegion();
			if (baseRegion.getId() == null) {
				region.setName(n);
				region.setLevel((byte) 1);
				region.setParentId(0);
				region.setCataOrder(maxSeq);
			} else {
				region.setName(n);
				region.setLevel((byte) (before.getLevel().intValue() + 1));
				region.setParentId(before.getId());
				region.setPath((before.getPath() == null ? "," : before.getPath()) + before.getId() + ",");
				region.setCataOrder(maxSeq);
			}
			baseRegionService.insert(region);
			regions.add(region);
		}
		return JSON.toJSONString(ResultUtil.buildSuccess(regions));
	}

	/**
	 * 删除本级及子级
	 * 
	 * @param request
	 * @param response
	 * @param baseRegion
	 * @return
	 */
	@RequestMapping(value = "deleteBaseRegion", method = RequestMethod.POST)
	@ResponseBody
	public String deleteBaseRegion(HttpServletRequest request, HttpServletResponse response, BaseRegion baseRegion) {
		
		String res = JSON.toJSONString(baseRegionService.deleteRegion(baseRegion.getId()));
		return res;
	}


	/**
	 * 区域层级市级上下移动
	 * 
	 * @param request
	 * @param response
	 * 从前台传入需要移动的2个id,讲排序字段交换
	 * @return
	 */
	@RequestMapping(value = "moveBaseRegion", method = RequestMethod.POST)
	@ResponseBody
	public String moveBaseRegion(HttpServletRequest request, HttpServletResponse response, Integer bUserId,
			Integer aUserId) {

		Map map = Maps.newHashMap();
		map.put("flag", true);
		// 可先判断user是否为空
		if (bUserId == null || aUserId == null) {
			map.put("flag", false);
			map.put("msg", "参数错误");
			return JSON.toJSONString(map);
		}
		BaseRegionMove baseRegionMove = new BaseRegionMove();
		List<BaseRegion> list = new ArrayList<BaseRegion>();
		list.add(baseRegionService.findById(bUserId));
		list.add(baseRegionService.findById(aUserId));
		baseRegionMove.setBaseRegions(list);
		map.put("msg", baseRegionService.moveBaseRegion(baseRegionMove));
		return JSON.toJSONString(map);
	}

	/**
	 * 编辑区域
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateBaseRegion")
	@ResponseBody
	public String updateSchool(BaseRegion baseRegion, HttpServletRequest request, HttpServletResponse response) {
		if (baseRegionService.updateByPrimaryKeySelective(baseRegion)) {
			baseRegion = baseRegionService.findById(baseRegion.getId());
			request.setAttribute("baseRegion", baseRegion);
			return JSON.toJSONString(baseRegion);
		} else {
			return JSON.toJSONString(ResultUtil.buildFail("编辑失败"));
		}
	}

	/**
	 * 导入学校页面
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String importIndex() {
		return "school/index";
	}

	/**
	 * 获取下一级select的选项的内容
	 * @author L.T
	 * @return AjaxJson
	 * @param product
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHtml")
	@ResponseBody
	public String getHtml(HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		// 选中的option ID
		String selectedVal = StringUtils.isBlank(request.getParameter("selectedVal")) ? ""
				: request.getParameter("selectedVal");
		String msg = null;
		if (parentId != null) {
			List<BaseRegion> childrenList = new ArrayList<BaseRegion>();
			childrenList = baseRegionService.getChildrenByParentId(Integer.valueOf(parentId));
			// List<ProductTypeEntity> childrenList =
			// productTypeService.findByProperty(ProductTypeEntity.class,
			// "productTypeEntity.id", Integer.valueOf(pranetId));
			StringBuilder sb = new StringBuilder();
			sb.append(" <option value=''>请选择</option>");
			for (BaseRegion ptre : childrenList) {
				sb.append(" <option " + (ptre.getId().toString().equals(selectedVal) ? "selected='selected'" : "")
						+ " value='" + ptre.getId() + "'>" + ptre.getName() + "</option>");
			}
			msg = sb.toString();
			return JSON.toJSONString(ResultUtil.buildSuccess(msg));
		} else {
			msg = "参数传输错误：pranetId  为  null";
			return JSON.toJSONString(ResultUtil.buildFail(msg));
		}
	}
	
	
	/**
	 * 导入学校页面
	 * @return
	 */
	@RequestMapping(value = "/importRegion")
	public ModelAndView importSchool(ModelAndView model, HttpServletRequest request) {
		//System.out.println("1111111");
		model.setViewName("administrativeLevel/batchImport");
		return model;
	}
	
	/**
	 * 批量导入
	 * @param request
	 * @param response
	 * @param file
	 * @param model
	 */
	@RequestMapping(value = "/improtRegionExcel",method=RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload_file,HttpServletRequest request) {

		MultipartFile file = upload_file;
		if (null == file) {
			return JSON.toJSONString(ResultUtil.buildFail("上传文件为空！"));
		}
		
		try {
			Result excelResult = ExcelUtil.uploadExcel(upload_file,RegionExcelVo.class);
			if (!ResultUtil.isSuccess(excelResult)){
				return JSON.toJSONString(excelResult);
			}
			Result result = baseRegionService.uploadRegion((List<RegionExcelVo>)excelResult.getData());
			return JSON.toJSONString(result);
		} catch (Exception e) {
			return JSON.toJSONString(ResultUtil.buildFail("上传区域信息失败"));
		}
		
//		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);
//		
//		MultipartFile file = mRequest.getFile("file");
//		if (null == file) {
//			json.put("success", false);
//			json.put("msg", "文件为空!");
//			return json;
//		}
//		String originalFileName = file.getOriginalFilename();
//		String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);// 文件后缀
//		if (!"xls".equals(ext) && !"xlsx".equals(ext)) {
//			json.put("success", false);
//			json.put("msg", "文件为非Excel!");
//			return json;
//		}
//		LinkedHashMap<String, String> fieldMap1 = new LinkedHashMap<String, String>();
//		fieldMap1.put("区域第一级", "areaName1");
//		fieldMap1.put("区域第二级", "areaName2");
//		fieldMap1.put("区域第三级", "areaName3");
//		fieldMap1.put("区域第四级", "areaName4");
//		fieldMap1.put("区域第五级", "areaName5");
//		String sheetName1 = "Sheet1";
//		InputStream in1 = null;
//		List<BaseRegion> baseRegions = new ArrayList<BaseRegion>();
//		
//		try {
//			in1 = file.getInputStream();
//
//			baseRegions = ExlJxlUtils.excelToList(in1, sheetName1, BaseRegion.class, fieldMap1, false, null);
//			if (baseRegions.size() <= 0) {
//				json.put("success", false);
//				json.put("msg", "学校信息为空!");
//				return json;
//			}
//			in1.close();
//		} catch (Exception e) {
//			json.put("success", false);
//			json.put("msg", "学校信息为空!");
//			return json;
//		}
//		boolean flag = baseRegionService.saveRegion(baseRegions);
//		if (flag) {
//			json.put("success", true);
//			json.put("msg", "操作完成!");
//			return json;
//		} else {
//			json.put("success", true);
//			json.put("msg", "数据保存失败，请联系技术解决!");
//			return json;
//		}  
	}
	
	/**
	 * 导出区域
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/baseRegionInfo")
	public String baseRegionInfo(Model model, HttpServletRequest request, HttpServletResponse response
			 ) {
		ExecutorService pool = null;
		try{
			String title = "Region-info"+new Random().nextInt(7654329);// 导出EXCEL的预置文件名称
			Map<String, String> headerMap = new LinkedHashMap<String, String>();
			// 导出EXCEL表表头设置
			headerMap.put("区域第一级", "areaName1");
			headerMap.put("区域第二级", "areaName2");
			headerMap.put("区域第三级", "areaName3");
			headerMap.put("区域第四级", "areaName4");
			headerMap.put("区域第五级", "areaName5");
			
			    List<BaseRegion> regionInfos= baseRegionService.getRegionInfo();
			    if(regionInfos!=null && regionInfos.size()>0){
			    	for(int i=0;i<regionInfos.size();i++){
			    		BaseRegion regionInfo = regionInfos.get(i);
			    		String regionName = baseRegionService.findFullNameById2(regionInfo.getId());
			    		String[] regionNames = regionName.split(" ");
			    		if(regionNames.length==1){
			    			regionInfo.setAreaName1(regionNames[0]);
			    		}
			    		if(regionNames.length==2){
			    			regionInfo.setAreaName1(regionNames[0]);
			    			regionInfo.setAreaName2(regionNames[1]);
			    		}
			    		if(regionNames.length==3){
			    			regionInfo.setAreaName1(regionNames[0]);
			    			regionInfo.setAreaName2(regionNames[1]);
			    			regionInfo.setAreaName3(regionNames[2]);
			    		}
			    		if(regionNames.length==4){
			    			regionInfo.setAreaName1(regionNames[0]);
			    			regionInfo.setAreaName2(regionNames[1]);
			    			regionInfo.setAreaName3(regionNames[2]);
			    			regionInfo.setAreaName4(regionNames[3]);
			    		}
			    		if(regionNames.length==5){
			    			regionInfo.setAreaName1(regionNames[0]);
			    			regionInfo.setAreaName2(regionNames[1]);
			    			regionInfo.setAreaName3(regionNames[2]);
			    			regionInfo.setAreaName4(regionNames[3]);
			    			regionInfo.setAreaName5(regionNames[4]);
			    		}
			    	}
			    }
				//getPage(userInfo, record);
				generateExcel(headerMap, title, regionInfos, request, response);
			   // GeneratorExcel<T>;
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
	
	@RequestMapping("/exportById")
	public String exportById(Model model, HttpServletRequest request, HttpServletResponse response,
			 Integer id) {
		ExecutorService pool = null;
		try{
			String title = "Region-info"+new Random().nextInt(7654329);// 导出EXCEL的预置文件名称
			Map<String, String> headerMap = new LinkedHashMap<String, String>();
			// 导出EXCEL表表头设置
			headerMap.put("区域第一级", "areaName1");
			headerMap.put("区域第二级", "areaName2");
			headerMap.put("区域第三级", "areaName3");
			headerMap.put("区域第四级", "areaName4");
			headerMap.put("区域第五级", "areaName5");
			BaseRegion region = baseRegionService.findById(id);
			Map<String, Object> map = Maps.newHashMap();
			map.put("path", "," + region.getId() + ",");
			List<BaseRegion> regionInfos = new ArrayList<BaseRegion>();
			regionInfos.add(region);
			regionInfos.addAll(baseRegionService.getParentRegion(map));
			
		    if(regionInfos!=null && regionInfos.size()>0){
		    	for(int i=0;i<regionInfos.size();i++){
		    		BaseRegion regionInfo = regionInfos.get(i);
		    		String regionName = baseRegionService.findFullNameById2(regionInfo.getId());
		    		String[] regionNames = regionName.split(" ");
		    		if(regionNames.length==1){
		    			regionInfo.setAreaName1(regionNames[0]);
		    		}
		    		if(regionNames.length==2){
		    			regionInfo.setAreaName1(regionNames[0]);
		    			regionInfo.setAreaName2(regionNames[1]);
		    		}
		    		if(regionNames.length==3){
		    			regionInfo.setAreaName1(regionNames[0]);
		    			regionInfo.setAreaName2(regionNames[1]);
		    			regionInfo.setAreaName3(regionNames[2]);
		    		}
		    		if(regionNames.length==4){
		    			regionInfo.setAreaName1(regionNames[0]);
		    			regionInfo.setAreaName2(regionNames[1]);
		    			regionInfo.setAreaName3(regionNames[2]);
		    			regionInfo.setAreaName4(regionNames[3]);
		    		}
		    		if(regionNames.length==5){
		    			regionInfo.setAreaName1(regionNames[0]);
		    			regionInfo.setAreaName2(regionNames[1]);
		    			regionInfo.setAreaName3(regionNames[2]);
		    			regionInfo.setAreaName4(regionNames[3]);
		    			regionInfo.setAreaName5(regionNames[4]);
		    		}
		    	}
		    }
			//getPage(userInfo, record);
			generateExcel(headerMap, title, regionInfos, request, response);
		   // GeneratorExcel<T>;
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
	 * 
	 * @param headerMap
	 * @param title
	 * @param dataset
	 * @param request
	 * @param response
	 */
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
		// response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		workbook.write(response.getOutputStream());
	}
}
