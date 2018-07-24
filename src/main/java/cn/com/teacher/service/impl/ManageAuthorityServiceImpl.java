package cn.com.teacher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.teacher.entity.ManageAuthority;
import cn.com.teacher.entity.ManageRoleRelationAuthority;
import cn.com.teacher.mapper.ManageAuthorityMapper;
import cn.com.teacher.service.ManageAuthorityService;
import cn.com.teacher.service.ManageRoleRelationAuthorityService;
import cn.com.teacher.vo.ManageAuthorityVo;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
@Service("manageAuthorityService")
public class ManageAuthorityServiceImpl implements ManageAuthorityService{
	@Autowired
	private ManageAuthorityMapper manageAuthorityMapper;
	@Autowired
	private ManageRoleRelationAuthorityService manageRoleRelationAuthorityService;

	@Override
	public List<ManageAuthority> getManageAuthorityAll() throws Exception {
		return manageAuthorityMapper.getManageAuthorityAll();
	}

	@Override
	public List<ManageAuthorityVo> setAuthoritySplit(Integer roleId) throws Exception{
		
		//查询所有权限
		List<ManageAuthority> listMa=this.getManageAuthorityAll();
		//根绝角色ID查询角色与权限关联关系
		List<ManageRoleRelationAuthority> listMrra=manageRoleRelationAuthorityService.getManageRoleRelationAuthorityAll(roleId);
		//list存储当前角色已选和未选的所有权限信息
		List<ManageAuthorityVo> list=new ArrayList<ManageAuthorityVo>();
		//如果当前角色没有关联任何权限，则将所有权限信息保存在list，未选权限的Checked:false
		if(listMrra.size()==0){
			for(ManageAuthority vo:listMa){
				ManageAuthorityVo manageAuthorityVo=new ManageAuthorityVo();
				manageAuthorityVo.setId(vo.getId());
				manageAuthorityVo.setChildList(vo.getChildList());
				manageAuthorityVo.setMethodName(vo.getMethodName());
				manageAuthorityVo.setModuleName(vo.getModuleName());
				manageAuthorityVo.setParentId(vo.getParentId());
				manageAuthorityVo.setName(vo.getName());
				manageAuthorityVo.setClassName(vo.getClassName());
				manageAuthorityVo.setRemark(vo.getRemark());
				manageAuthorityVo.setChecked(false);
				list.add(manageAuthorityVo);
			}
		}else{
		//如果当前角色已关联权限，则将已关联的权限和未关联的权限存入list，已选权限的Checked：true
		for(ManageAuthority vo:listMa){
			for(ManageRoleRelationAuthority obj:listMrra){
				ManageAuthorityVo manageAuthorityVo=new ManageAuthorityVo();
				if(vo.getId().equals(obj.getAuthorityId())){
					manageAuthorityVo.setId(vo.getId());
					manageAuthorityVo.setChildList(vo.getChildList());
					manageAuthorityVo.setMethodName(vo.getMethodName());
					manageAuthorityVo.setModuleName(vo.getModuleName());
					manageAuthorityVo.setParentId(vo.getParentId());
					manageAuthorityVo.setName(vo.getName());
					manageAuthorityVo.setClassName(vo.getClassName());
					manageAuthorityVo.setRemark(vo.getRemark());
					manageAuthorityVo.setChecked(true);
					list.add(manageAuthorityVo);
					break;
				}else{
					manageAuthorityVo.setId(vo.getId());
					manageAuthorityVo.setChildList(vo.getChildList());
					manageAuthorityVo.setMethodName(vo.getMethodName());
					manageAuthorityVo.setModuleName(vo.getModuleName());
					manageAuthorityVo.setParentId(vo.getParentId());
					manageAuthorityVo.setName(vo.getName());
					manageAuthorityVo.setClassName(vo.getClassName());
					manageAuthorityVo.setRemark(vo.getRemark());
					manageAuthorityVo.setChecked(false);
					list.add(manageAuthorityVo);
				}
			}
		}
	}
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		List<ManageAuthorityVo> listTo=new ArrayList<ManageAuthorityVo>();
		//map中存储权限，key=权限ID，value=ManageAuthorityVo
		for (int i = 0; i < list.size(); i++) {
			//把所有数据放到map中
			map.put(list.get(i).getId(), list.get(i));
		}
		//权限按照父节点->子节点的层次关系保存在listTo
		for (Integer id : map.keySet()) {
			ManageAuthorityVo manageAuthority=(ManageAuthorityVo)map.get(id);
			//判断是否为父节点
			if (manageAuthority.getParentId().compareTo(-1)==0) {
				//为父节点添加到list中
				listTo.add(manageAuthority);
				continue;
			}
			ManageAuthorityVo parent=(ManageAuthorityVo)map.get(manageAuthority.getParentId());
			//判断子节点是否为空
			if (parent.getChildList()==null) {
				List<ManageAuthorityVo> childerList=new ArrayList<ManageAuthorityVo>();
				childerList.add(manageAuthority);
				parent.setChildList(childerList);
			}else{
				List<ManageAuthorityVo> childerList=parent.getChildList();
				childerList.add(manageAuthority);
				parent.setChildList(childerList);
			}
		}		
		return listTo;
	}
	
}
