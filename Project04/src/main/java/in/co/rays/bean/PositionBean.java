package in.co.rays.bean;

import java.util.Date;

public class PositionBean extends BaseBean {

	private String designation;
	private Date openingDate;
	private String requiredExperience;
	private String condition;
	
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public String getRequiredExperience() {
		return requiredExperience;
	}
	public void setRequiredExperience(String requiredExperience) {
		this.requiredExperience = requiredExperience;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}
	public String getValue() {
		// TODO Auto-generated method stub
		return condition;
	}
	
}
