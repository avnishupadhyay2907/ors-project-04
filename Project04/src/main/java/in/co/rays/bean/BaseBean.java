package in.co.rays.bean;

import java.security.Timestamp;

public abstract class BaseBean implements DropdownListBean {

	protected long id;
	protected String created_by;
	protected String modified_by;
	protected java.sql.Timestamp created_datetime;
	protected  java.sql.Timestamp  modified_datetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return created_by;
	}
	public void setCreatedBy(String createdBy) {
		this.created_by = createdBy;
	}
	public String getModifiedBy() {
		return modified_by;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modified_by = modifiedBy;
	}
	public java.sql.Timestamp getCreatedDateTime() {
		return created_datetime;
	}
	public void setCreatedDateTime(java.sql.Timestamp createdDateTime) {
		this.created_datetime = createdDateTime;
	}
	public java.sql.Timestamp getModifiedDateTime() {
		return modified_datetime;
	}
	public void setModifiedDateTime(java.sql.Timestamp modifiedDateTime) {
		this.modified_datetime = modifiedDateTime;
	}
	
	
}
