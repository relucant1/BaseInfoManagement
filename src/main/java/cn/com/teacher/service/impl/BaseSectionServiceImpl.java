package cn.com.teacher.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.com.teacher.entity.BaseSection;
import cn.com.teacher.mapper.BaseSectionMapper;
import cn.com.teacher.service.BaseSectionService;

/**
 * Created by Qukun on 16/11/15.
 */
@Service("sectionService")
public class BaseSectionServiceImpl implements BaseSectionService {

	@Autowired
	private BaseSectionMapper baseSectionMapper;

	
	public List<BaseSection> getSections() {
		
		return baseSectionMapper.getSections();
		
	}

	
	public BaseSection getSection(Integer id) {
		
		if (id != null && id>0) {
			return baseSectionMapper.selectByPrimaryKey(id);
		}
		return null;
		
	}

	
	public BaseSection getSectionBySectionCode(String sectionCode){
		// TODO Auto-generated method stub
		return baseSectionMapper.getBySectionCode(sectionCode);
		
	}

	@Override
	public BaseSection updateSequence(Integer id, String type) {
		BaseSection curr = baseSectionMapper.selectByPrimaryKey(id);
		BaseSection target = null;
		if("up".equals(type)){
			target = baseSectionMapper.selectByLtSequence(curr);
		}else{
			target = baseSectionMapper.selectByGtSequence(curr);
		}
		if(target != null){
			Integer temp = curr.getSequence();
			curr.setSequence(target.getSequence());
			target.setSequence(temp);
			
			baseSectionMapper.updateByPrimaryKeySelective(curr);
			baseSectionMapper.updateByPrimaryKeySelective(target);
		}
		
		return curr;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return baseSectionMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public int insertBaseSection(BaseSection section) {
		// TODO Auto-generated method stub
		return baseSectionMapper.insert(section);
	}

	@Override
	public int updateBaseSection(BaseSection section) {
		// TODO Auto-generated method stub
		return baseSectionMapper.updateByPrimaryKeySelective(section);
	}


	@Override
	public List<BaseSection> getSectionsBySequence() {
		// TODO Auto-generated method stub
		return baseSectionMapper.getSectionsBySequence();
	}


	@Override
	public Integer selectMaxSequence() {
		// TODO Auto-generated method stub
		return baseSectionMapper.selectMaxSequence();
	}


	@Override
	public void deleteAllBaseSection() {
		baseSectionMapper.deleteAllBaseSection();
		
	}

	
	
	
}
