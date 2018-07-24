package cn.com.teacher.entity;

import java.util.List;

import cn.com.teacher.vo.ManageAuthorityVo;

public class ManageAuthority {
    private Integer id;

    private String name;

    private String moduleName;

    private String className;

    private String methodName;

    private String remark;

    private Integer parentId;
    
    /**
     * 子节点
     * */
    private List<ManageAuthorityVo> childList;

    public List<ManageAuthorityVo> getChildList() {
		return childList;
	}

	public void setChildList(List<ManageAuthorityVo> childList) {
		this.childList = childList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}