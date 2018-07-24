package cn.com.teacher.vo;

import java.util.List;

/**
 * Created by zhouzhiyuan on 16/12/23
 */
public class ManageAuthorityVo {
		private Integer id;

	    private String name;

	    private String moduleName;

	    private String className;

	    private String methodName;

	    private String remark;

	    private Integer parentId;
	    
	    private boolean isChecked;
	    
	    /**
	     * 子节点
	     * */
	    private List<ManageAuthorityVo> childList;

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
			this.name = name;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

		public boolean isChecked() {
			return isChecked;
		}

		public void setChecked(boolean isChecked) {
			this.isChecked = isChecked;
		}

	    public List<ManageAuthorityVo> getChildList() {
			return childList;
		}

		public void setChildList(List<ManageAuthorityVo> childList) {
			this.childList = childList;
		}
	    
}
