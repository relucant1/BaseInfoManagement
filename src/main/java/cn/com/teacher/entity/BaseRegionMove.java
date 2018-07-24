package cn.com.teacher.entity;

import java.util.List;

public class BaseRegionMove {

	private BaseRegion baseRegion;
	
	private List<BaseRegion> baseRegions;

	public BaseRegion getBaseRegion() {
		return baseRegion;
	}

	public void setBaseRegion(BaseRegion baseRegion) {
		this.baseRegion = baseRegion;
	}

	public List<BaseRegion> getBaseRegions() {
		return baseRegions;
	}

	public void setBaseRegions(List<BaseRegion> baseRegions) {
		this.baseRegions = baseRegions;
	}
}
