package cn.com.teacher.entity;

/**
 * 数据项
 * @author lili
 * 2017-05-15 上午09:44:24
 */
public class BaseDataItem {
	
	private Integer id;
	private String dataItemName;
	private String dataItemCode;
	private Integer categoryId;
	private Integer sequence;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataItemName() {
		return dataItemName;
	}
	public void setDataItemName(String dataItemName) {
		this.dataItemName = dataItemName;
	}
	public String getDataItemCode() {
		return dataItemCode;
	}
	public void setDataItemCode(String dataItemCode) {
		this.dataItemCode = dataItemCode;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
