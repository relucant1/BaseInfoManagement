package cn.com.teacher.ws.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.com.teacher.config.CustomJwtAccessTokenConverter;
import cn.com.teacher.constant.Constant;
import cn.com.teacher.entity.AuthorityResource;
import cn.com.teacher.entity.RoleAuthority;
import cn.com.teacher.mapper.AuthorityResourceMapper;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.ws.AuthorityResourceWebService;

@Service
public class AuthorityResourceWebServiceImpl  extends BaseServiceImpl implements
		AuthorityResourceWebService {

	@Autowired
	private AuthorityResourceMapper authorityResourceMapper;
	
	@Override
	public String getRoleAuth() {
		String projectId  = getParam("projectId");
		List<RoleAuthority> roleAuthList = 
				authorityResourceMapper.findAuthByProjectId(Long.valueOf(projectId));
		Map<String,String> roleAuthMap = new HashMap<String, String>();
		if(roleAuthList!=null&&roleAuthList.size()>0){
			for (RoleAuthority roleAuthority : roleAuthList) {
				
				roleAuthMap.put(roleAuthority.getAuthUrl(), roleAuthority.getRoleCode());
				
			}
		}
		SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,
	    		SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty};
		String res = JSON.toJSONString(roleAuthMap,features);
		return res;
	}

	@Override
	public String getProjectId() {
		
		String roleCode  = getParam("roleCode");
		List<Long> projectIds = authorityResourceMapper.findProjectIdByRoleCode(roleCode);
		SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,
	    		SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty};
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("projectIds", projectIds);
		String res = JSON.toJSONString(resMap,features);
		return res;
	}

	@Override
	public String getRoleMenu() {
		String projectId  = getParam("projectId");
		String parentId = getParam("parentId");
		String accessToken = getParam("accessToken");
		Collection<GrantedAuthority> grantedAuthorities = 
 	   			CustomJwtAccessTokenConverter.getInstance().getAuthenticationToken(accessToken).getAuthorities();
 	   	StringBuffer roles = new StringBuffer();
 	   	if(grantedAuthorities==null||grantedAuthorities.size()==0){
 	   		throw new RuntimeException("accessToken is error!");
 	   	}
 	   	Iterator<GrantedAuthority> iterator = grantedAuthorities.iterator();
 	   	while(iterator.hasNext()){
 	   		GrantedAuthority authority = iterator.next();
 	   		roles.append(authority.getAuthority()).append(",");
 	   	}
 	   	String roleCode = roles.substring(0,roles.length()-1);
		String [] roleArray = roleCode.split(",");
		Map<String,String> authMap = new LinkedHashMap<String, String>();
		for (String role : roleArray) {
			role = role.replaceAll(Constant.AUTH_PREFIX, "");
			List<AuthorityResource> authList = 
					authorityResourceMapper.findAuthByRole(role,Long.valueOf(projectId),Long.valueOf(parentId),Constant.IS_MENU_TRUE);
			if(authList!=null){
				for (AuthorityResource authorityResource : authList) {
					authMap.put(authorityResource.getAuthUrl(), authorityResource.getAuthName());
				}
			}
		}
		List<Map<String,String>> resList = new ArrayList<Map<String,String>>();
		for(Map.Entry<String,String> entry : authMap.entrySet()){
			Map<String,String> authorityResource = new HashMap<String, String>();
			authorityResource.put("authName", entry.getValue());
			authorityResource.put("authUrl", entry.getKey());
			resList.add(authorityResource);
        }
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("menus", resList);
		String res = JSON.toJSONString(resMap);
		return res;
	}

	@Override
	public String getAuthByUrl() {
		String projectId  = getParam("projectId");
		String authUrl = getParam("authUrl");
		AuthorityResource authorityResource = authorityResourceMapper.getAuthByUrl(authUrl, Long.valueOf(projectId));
		String res = JSON.toJSONString(authorityResource);
		return res;
	}

}
