package cn.com.teacher.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.teacher.entity.Result;

import com.google.common.collect.Lists;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;

public class ExcelUtil {

	private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	private XSSFWorkbook wb = null;

	private XSSFSheet sheet = null;

	/**
	 * @param wb
	 * @param sheet
	 */
	public ExcelUtil(XSSFWorkbook wb, XSSFSheet sheet) {
		this.wb = wb;
		this.sheet = sheet;
	}

	public ExcelUtil() {
	}

	/**
	 * 合并单元格后给合并后的单元格加边框
	 *
	 * @param region
	 * @param cs
	 */
	private void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {

		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				XSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
				// (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	/**
	 * 设置表头的单元格样式
	 *
	 * @return
	 */
	private XSSFCellStyle getHeadStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	/**
	 * 设置表体的单元格样式
	 *
	 * @return
	 */
	private XSSFCellStyle getBodyStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	private final static String excel2003L = ".xls";    //2003- 版本的excel
	private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

	/**
	 * 导入EXECL
	 *
	 * @param upfile
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Result uploadExcel(MultipartFile upfile, Class<?> o) throws Exception {
		if (upfile.isEmpty()) {
			throw new Exception("文件不存在！");
		}
		InputStream in = upfile.getInputStream();
		//创建Excel工作薄
		Workbook work = getWorkbook(in, upfile.getOriginalFilename());
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		List<Object> list = Lists.newArrayList();
		boolean isBug = false;
		StringBuilder errorMsg = new StringBuilder("");
		//遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			Sheet sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			//遍历当前sheet中的所有行
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				int lineNum = j + 1;
				Row row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				try {
					list.add(getObject(row, o));
				} catch (Exception e) {
					logger.error("单元格解析错误", e);
					isBug = true;
					errorMsg.append("第").append(lineNum).append("行出错.<br/>");
				}
			}
		}
		in.close();
		if (isBug) {
			return ResultUtil.buildFail(errorMsg.toString());
		} else {
			return ResultUtil.buildSuccess(list);
		}
	}

	private static Object getObject(Row row, Class<?> o) throws Exception {
		Class classType = Class.forName(o.getName());
		Object obj = classType.newInstance();
		for (int i = 0; i < o.getDeclaredFields().length; i++) {
			Field field = o.getDeclaredFields()[i];
			//如果这个单元格为空,跳过,下一个(不报错是因为EXCEL允许某些列不填)
			if (row.getCell(i) == null) {
				continue;
			}
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			Object cellValue = row.getCell(i).getRichStringCellValue().getString();
			Method method = classType.getMethod(buildSetMethodName(field.getName()), cellValue.getClass());
			method.invoke(obj, cellValue);
		}
		return obj;
	}

	/**
	 * 根据属性名 得到set方法
	 *
	 * @param name
	 * @return
	 */
	private static String buildSetMethodName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return String.valueOf("set" + name);
	}


	/**
	 * 根据属性名 得到get方法
	 *
	 * @param name
	 * @return
	 */
	private static String buildGetMethodName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return String.valueOf("get" + name);
	}

	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 *
	 * @param inStr,fileName
	 * @return
	 * @throws Exception
	 */
	private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr);  //2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr);  //2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 *
	 * @return
	 *//*
	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  //日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("yyyy/MM/dd".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}*/

	/**
	 * 导出EXCEL
	 * @param titleList
	 * @param dataList
	 * @param outputStream
	 * @return
	 */
	public static Result exportExcel(List<String> titleList, List<?> dataList, ServletOutputStream outputStream) {
		try {
			// 创建一个workbook 对应一个excel应用文件
			XSSFWorkbook workBook = new XSSFWorkbook();
			// 在workbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = workBook.createSheet("sheet1");
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
			// 构建表头
			XSSFRow headRow = sheet.createRow(0);
			for (int i = 0; i < titleList.size(); i++) {
				XSSFCell cell = headRow.createCell(i);
				cell.setCellStyle(headStyle);
				cell.setCellValue(titleList.get(i));
			}
			// 构建表体数据
			if (dataList != null && dataList.size() > 0) {
				for (int j = 0; j < dataList.size(); j++) {
					XSSFRow bodyRow = sheet.createRow(j + 1);
					Class classType = dataList.get(j).getClass();
					Object obj = dataList.get(j);
					for (int i = 0; i < classType.getDeclaredFields().length; i++) {
						Field field = classType.getDeclaredFields()[i];

						Method method = classType.getMethod(buildGetMethodName(field.getName()));
						XSSFCell cell = bodyRow.createCell(i);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(method.invoke(obj).toString());
					}
				}
			}
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error("导出EXCEL失败", e);
			return ResultUtil.buildFail("导出EXCEL失败");
		}
		return ResultUtil.buildSuccess("EXCEL导出成功");
	}

	public static void exportExcel(Map<String,String> colsMap,List<?> dataList,
			ServletOutputStream outputStream){
		try {
			// 创建一个workbook 对应一个excel应用文件
			XSSFWorkbook workBook = new XSSFWorkbook();
			// 在workbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = workBook.createSheet("sheet1");
			ExcelUtil exportUtil = new ExcelUtil(workBook, sheet);
			XSSFCellStyle headStyle = exportUtil.getHeadStyle();
			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
			// 构建表头
			XSSFRow headRow = sheet.createRow(0);
			Set<String> keyset = colsMap.keySet();
			Iterator<String> iterator = keyset.iterator();
			for (int i = 0; iterator.hasNext(); i++) {
				String key = iterator.next();
				XSSFCell cell = headRow.createCell(i);
				cell.setCellStyle(headStyle);
				cell.setCellValue(colsMap.get(key));
			}
			// 构建表体数据
			if (dataList != null && dataList.size() > 0) {
				for (int j = 0; j < dataList.size(); j++) {
					XSSFRow bodyRow = sheet.createRow(j + 1);
					Class classType = dataList.get(j).getClass();
					Object obj = dataList.get(j);
					Object refectObj = null;
					iterator = keyset.iterator();
					for (int i = 0; iterator.hasNext(); i++) {
						String key = iterator.next();
						Method method = classType.getMethod(buildGetMethodName(key));
						XSSFCell cell = bodyRow.createCell(i);
						cell.setCellStyle(bodyStyle);
						refectObj = method.invoke(obj);
						if(refectObj==null){
							cell.setCellValue("");
						}else{
							cell.setCellValue(method.invoke(obj).toString());
						}
					}
				
				}
			}
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();			
			
		} catch (Exception e) {
			logger.error("导出EXCEL失败", e);
			throw new RuntimeException(e);
		}
		
	}
	
	public static String nameToUTF8(String s){
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if (c >= 0 && c <= 255){sb.append(c);}
			else{
				byte[] b;
				try { b = Character.toString(c).getBytes("utf-8");}
				catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
