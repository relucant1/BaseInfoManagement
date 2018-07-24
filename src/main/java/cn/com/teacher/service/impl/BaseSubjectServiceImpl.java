package cn.com.teacher.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.entity.BaseSubject;
import cn.com.teacher.entity.BaseSubjectExample;
import cn.com.teacher.entity.BaseSubjectExample.Criteria;
import cn.com.teacher.mapper.BaseSubjectMapper;
import cn.com.teacher.service.BaseSubjectService;

/**
 * Created by Qukun on 16/11/15.
 */
@Service("subjectService")
public class BaseSubjectServiceImpl implements BaseSubjectService {

	@Autowired
	private BaseSubjectMapper baseSubjectMapper;

	
	public BaseSubject getSubject(Integer id) {
		
		if (id != null && id>0) {
			return baseSubjectMapper.selectByPrimaryKey(id);
		}
		return null;
		
	}

	
	public List<BaseSubject> getSubjects() {
		
		return baseSubjectMapper.getSubjects();
		
		
	}

	
	public BaseSubject getBySubjectCode(String subjectCode){
		
		return baseSubjectMapper.getBySubjectCode(subjectCode);
		
		
	}

	@Override
	public BaseSubject updateSequence(Integer id, String type) {
		BaseSubject curr = baseSubjectMapper.selectByPrimaryKey(id);
		BaseSubject target = null;
		if("up".equals(type)){
			target = baseSubjectMapper.selectByLtSequence(curr);
		}else{
			target = baseSubjectMapper.selectByGtSequence(curr);
		}
		if(target != null){
			Integer temp = curr.getSequence();
			curr.setSequence(target.getSequence());
			target.setSequence(temp);
			
			baseSubjectMapper.updateByPrimaryKeySelective(curr);
			baseSubjectMapper.updateByPrimaryKeySelective(target);
		}
		
		return curr;
	}

	@Override
	public int insertBaseSubject(BaseSubject subject) {
		// TODO Auto-generated method stub
		return baseSubjectMapper.insert(subject);
	}

	@Override
	public int updateBaseSection(BaseSubject subject) {
		// TODO Auto-generated method stub
		return baseSubjectMapper.updateByPrimaryKeySelective(subject);
	}

	@Override
	public int deleteSubject(Integer id) {
		// TODO Auto-generated method stub
		return baseSubjectMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseSubject> getBySubjectCodeList(List<String> list) {
		
		return baseSubjectMapper.selectInSubjectCode(list);
	}


	@Override
	public List<BaseSubject> getSubjectsBySequence() {
		// TODO Auto-generated method stub
		return baseSubjectMapper.getSubjectsBySequence();
	}


	@Override
	public Integer selectMaxSequence() {
		// TODO Auto-generated method stub
		return baseSubjectMapper.selectMaxSequence();
	}


	@Override
	public void deleteAllBaseSubject() {
		// TODO Auto-generated method stub
		baseSubjectMapper.deleteAllBaseSubject();
	}


	@Override
	public BaseSubject selectSubjectBySequence(Integer i) {
		// TODO Auto-generated method stub
		return baseSubjectMapper.selectSubjectBySequence(i);
	}
	
	
}
