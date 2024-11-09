	package in.co.rays.bean;

import java.util.Date;

public class PatientBean extends BaseBean {

	private String name;
	private Date dateOFVisit;
	private String mobile;
	private String disease;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOFVisit() {
		return dateOFVisit;
	}

	public void setDateOFVisit(Date dateOFVisit) {
		this.dateOFVisit = dateOFVisit;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
