package cn.com.teacher.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.com.teacher.common.ResponseData;
import cn.com.teacher.config.CustomJwtAccessTokenConverter;
import cn.com.teacher.constant.Constant;
import cn.com.teacher.entity.AuthorityResource;
import cn.com.teacher.entity.Result;
import cn.com.teacher.entity.RoleAuthority;
import cn.com.teacher.mapper.AuthorityResourceMapper;
import cn.com.teacher.service.AuthorityResourceService;
import cn.com.teacher.util.ResultUtil;
import cn.com.teacher.util.WebServiceUtil;
import cn.com.teacher.vo.FindAuthsConditionVo;
import cn.com.teacher.vo.RoleAuthorityVo;

@Service
public class AuthorityResourceServiceImpl implements AuthorityResourceService {

	@Autowired
	private AuthorityResourceMapper authorityResourceMapper;
	
	@Value("${config.oauth2.resourceURI}")
	private String resourceURI;
	
	@Autowired
    @Qualifier("clientTemplate")
    private OAuth2RestOperations clientTemplate;
	
	@Override
	public Result findAuths(FindAuthsConditionVo findAuthsCondition) {
		List<AuthorityResource> authList = authorityResourceMapper.findAuths(findAuthsCondition.getRoleCode(),
				findAuthsCondition.getProjectId(), findAuthsCondition.getParentId());
		return ResultUtil.buildSuccess(authList);
	}

	@Transactional
	@Override
	public Result saveRoleAuth(RoleAuthorityVo roleAuthority) {
		//authorityResourceMapper.deleteRoleAuths(roleAuthority.getRoleCode());
		authorityResourceMapper.deleteRoleAuthByProject(roleAuthority.getProjectId(), roleAuthority.getRoleCode());
		authorityResourceMapper.insertRoleAuths(roleAuthority.getRoleCode(), roleAuthority.getRoleAuths());
		Map<String,Object> params = new HashMap<String, Object>();
	   	params.put("projectId", roleAuthority.getProjectId());
	   	params.put("role", Constant.AUTH_PREFIX+roleAuthority.getRoleCode());
	   	WebClient client = WebServiceUtil.getWebClient(resourceURI, "/role/roleProject", params);
	   	client.header("Authorization", "Bearer "+ clientTemplate.getAccessToken().getValue());
	   	String res = client.invoke("POST", params, String.class);
	   	JSONObject jsonObj = JSONObject.parseObject(res);
	   	ResponseData resObject = JSONObject.toJavaObject(jsonObj, ResponseData.class);
	   	if(resObject.getIsSuccess()>1){
	   		throw new RuntimeException(resObject.getMsg());
	   	}
		return ResultUtil.buildSuccess(null);
	}

	@Override
	public Result getMenus(String accessToken) {
		
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
 	   	Long projectId = Long.valueOf(Constant.SYSTEM_ID);
		String [] roleArray = roleCode.split(",");
		Map<String,String> authMap = new LinkedHashMap<String, String>();
		for (String role : roleArray) {
			role = role.replaceAll(Constant.AUTH_PREFIX, "");
			List<AuthorityResource> authList = 
					authorityResourceMapper.findAuthByRole(role,projectId,0L,Constant.IS_MENU_TRUE);
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
		return ResultUtil.buildSuccess(resMap);
	}

}
