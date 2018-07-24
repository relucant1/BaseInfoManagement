package cn.com.teacher.entity;

import java.io.Serializable;

//@SuppressWarnings("serial")
public class BaseRegion implements Serializable {

	private Integer id;

    private String name;

    private Integer parentId;

    private Byte level;

    private String path;

    private Byte isSchool; //学校数据还是省市区    1代表学校

    private Integer cataOrder;
    
    private String areaName1;
    private String areaName2;
    private String areaName3;
    private String areaName4;
    private String areaName5;
    
    public Integer getCataOrder() {
		return cataOrder;
	}

	public void setCataOrder(Integer cataOrder) {
		this.cataOrder = cataOrder;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Byte getIsSchool() {
        return isSchool;
    }

    public void setIsSchool(Byte isSchool) {
        this.isSchool = isSchool;
    }

	public String getAreaName1() {
		return areaName1;
	}

	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}

	public String getAreaName2() {
		return areaName2;
	}

	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}

	public String getAreaName3() {
		return areaName3;
	}

	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}

	public String getAreaName4() {
		return areaName4;
	}

	public void setAreaName4(String areaName4) {
		this.areaName4 = areaName4;
	}

	public String getAreaName5() {
		return areaName5;
	}

	public void setAreaName5(String areaName5) {
		this.areaName5 = areaName5;
	}
    
}