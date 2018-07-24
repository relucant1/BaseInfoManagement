package cn.com.teacher.vo;

/**
 * 学校导入导出ecxel
 * @author hugang
 *
 */
public class SchoolExcelVo {
	
	/**
	 * 学校名称
	 */
	private String schoolName;
	
	/**
	 * 学校级别
	 */
	private String schoolLevel;
	
	/**
	 * 学校规模
	 */
	private String schoolScale;
	
	/**
	 * 所属区域第一级
	 */
	private String regionFirstLevel;
	
	/**
	 * 所属区域第二级
	 */
	private String regionSecondLevel;
	
	/**
	 * 所属区域第三级
	 */
	private String regionThirdLevel;
	
	/**
	 * 所属区域第四级
	 */
	private String regionFourthLevel;
	
	private Integer regionId;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolLevel() {
		return schoolLevel;
	}

	public void setSchoolLevel(String schoolLevel) {
		this.schoolLevel = schoolLevel;
	}

	public String getSchoolScale() {
		return schoolScale;
	}

	public void setSchoolScale(String schoolScale) {
		this.schoolScale = schoolScale;
	}

	public String getRegionFirstLevel() {
		return regionFirstLevel;
	}

	public void setRegionFirstLevel(String regionFirstLevel) {
		this.regionFirstLevel = regionFirstLevel;
	}

	public String getRegionSecondLevel() {
		return regionSecondLevel;
	}

	public void setRegionSecondLevel(String regionSecondLevel) {
		this.regionSecondLevel = regionSecondLevel;
	}

	public String getRegionThirdLevel() {
		return regionThirdLevel;
	}

	public void setRegionThirdLevel(String regionThirdLevel) {
		this.regionThirdLevel = regionThirdLevel;
	}

	public String getRegionFourthLevel() {
		return regionFourthLevel;
	}

	public void setRegionFourthLevel(String regionFourthLevel) {
		this.regionFourthLevel = regionFourthLevel;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

}
