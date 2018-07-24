package cn.com.teacher.entity;

public class User {
    private Integer id;

    private Integer userId;
    
    private String mobile;
    
    private String password;

    private String name;

    private Integer type;

    private String studyStageSubject;

    private String region;

    private String workUnit;

    private Boolean sex;

    private Boolean status;

    private String jjwNumber;

    private String idNumber;

    private Integer traineeCategory;

    private Integer schoolArea;

    private Integer schoolCategory;
    
    private Integer degree;

    private String birthday;

    private Integer nation;

    private Integer comAttribute;

    private String teachCreatetime;

    private String teacherQualificationid;

    private String mailbox;

    private byte[] photo;

    private Integer professionalTitle;

    private String duties;

    private String graduationCollege;

    private String major;

    private String mainReward;

    private String trainingExperience;

    private String officePhone;

    private String address;

    private String postCode;

    private String createTime;

    private String updateTime;
    

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.studyStageSubject = studyStageSubject == null ? null : studyStageSubject.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getJjwNumber() {
        return jjwNumber;
    }

    public void setJjwNumber(String jjwNumber) {
        this.jjwNumber = jjwNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public Integer getTraineeCategory() {
        return traineeCategory;
    }

    public void setTraineeCategory(Integer traineeCategory) {
        this.traineeCategory = traineeCategory;
    }

    public Integer getSchoolArea() {
        return schoolArea;
    }

    public void setSchoolArea(Integer schoolArea) {
        this.schoolArea = schoolArea;
    }

    public Integer getSchoolCategory() {
        return schoolCategory;
    }

    public void setSchoolCategory(Integer schoolCategory) {
        this.schoolCategory = schoolCategory;
    }
    
    public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public Integer getComAttribute() {
        return comAttribute;
    }

    public void setComAttribute(Integer comAttribute) {
        this.comAttribute = comAttribute;
    }

    public String getTeachCreatetime() {
        return teachCreatetime;
    }

    public void setTeachCreatetime(String teachCreatetime) {
        this.teachCreatetime = teachCreatetime == null ? null : teachCreatetime.trim();
    }

    public String getTeacherQualificationid() {
        return teacherQualificationid;
    }

    public void setTeacherQualificationid(String teacherQualificationid) {
        this.teacherQualificationid = teacherQualificationid;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }

    public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(Integer professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getGraduationCollege() {
        return graduationCollege;
    }

    public void setGraduationCollege(String graduationCollege) {
        this.graduationCollege = graduationCollege == null ? null : graduationCollege.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getMainReward() {
        return mainReward;
    }

    public void setMainReward(String mainReward) {
        this.mainReward = mainReward == null ? null : mainReward.trim();
    }

    public String getTrainingExperience() {
        return trainingExperience;
    }

    public void setTrainingExperience(String trainingExperience) {
        this.trainingExperience = trainingExperience == null ? null : trainingExperience.trim();
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}