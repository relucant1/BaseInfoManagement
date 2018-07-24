package cn.com.teacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 系统本地文件管理
 * @author hugang
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {
	
	private static Map<String,String> fileMap = new HashMap<String, String>();
	static{
		
		fileMap.put("regionImportTemplate.xlsx", "区域层级导入模板.xlsx");
		fileMap.put("shcoolImportTemplate.xlsx", "学校导入模板.xlsx");
	
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String download(HttpServletRequest request,
			HttpServletResponse response,String fileName){
		
		
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			String fileNameIsoString = new String(fileMap.get(fileName).getBytes(),"iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;fileName="+ fileNameIsoString);
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"download";
			inputStream = new FileInputStream(new File(path + File.separator + fileMap.get(fileName)));
			os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

}
