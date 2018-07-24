package cn.com.teacher.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;
import cn.com.teacher.service.BaseSubjectService;
import cn.com.teacher.util.ResultUtil;

import com.google.gson.Gson;

/**
 * Created by Qukun on 16/11/15.
 */
@ApiIgnore
@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private BaseSubjectService subjectService; 

	@RequestMapping(value = "getSubject/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable
			Integer id) {
		if (id == null || id == 0) {
			return new Gson().toJson(ResultUtil.buildFail("id不合法"));
		}
		return new Gson().toJson(subjectService.getSubject(id));
	}

	@RequestMapping(value = "getSubjects")
	@ResponseBody
	public String getSubjects(HttpServletRequest request, HttpServletResponse response) {
		
		return new Gson().toJson(subjectService.getSubjects());
		
	}
	/*@RequestMapping(value = "insertCourse", method = RequestMethod.GET)
	@ResponseBody
	public String insertCourse(HttpServletRequest request, HttpServletResponse response, Course course) {
		if (course == null) {
			return new Gson().toJson(ResultUtil.buildFail("课程为空"));
		}
		return JSON.toJSONString(courseService.insertCourse(course));
	}*/
}
