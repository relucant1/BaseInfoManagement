package cn.com.teacher.service;

import java.util.List;

import cn.com.teacher.entity.ManageAuthority;
import cn.com.teacher.vo.ManageAuthorityVo;
/**
 * Created by zhouzhiyuan on 16/12/23.
 */
public interface ManageAuthorityService {
	/**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<ManageAuthority> getManageAuthorityAll()throws Exception;
    /**
     * 后台拼接角色管理
     * @return
     * @throws Exception
     */
    List<ManageAuthorityVo> setAuthoritySplit(Integer roleId)throws Exception;
}
