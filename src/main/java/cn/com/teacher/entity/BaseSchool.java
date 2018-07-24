package cn.com.teacher.entity;

import java.util.Date;

public class BaseSchool extends Page{
    private Integer id;

    private Integer regionId;

    private Integer scale;

    private Integer type;

    private Boolean isStart;

    private Date createDate;

    private Integer creatorId;

    private String name;
    
    //添加冗余字段
    private String  scaleName;
    
    private String typeName;
    
    private String regionName;
    
    private String areaName1;
    private String areaName2;
    private String areaName3;
    private String areaName4;
    private String areaName5;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(Boolean isStart) {
        this.isStart = isStart;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

	public String getScaleName() {
		return scaleName;
	}

	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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