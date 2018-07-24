package cn.com.teacher.entity;

public class UserDto {

    private Integer userId;
    
    private String mobile;
    
    private String name;

    private Integer type;

    private String studyStageSubject;

    private String region;

    private String status;

    private String createTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStudyStageSubject() {
		return studyStageSubject;
	}

	public void setStudyStageSubject(String studyStageSubject) {
		this.studyStageSubject = studyStageSubject;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}