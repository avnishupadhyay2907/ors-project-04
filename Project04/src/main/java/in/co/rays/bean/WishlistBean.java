package in.co.rays.bean;

import java.util.Date;

public class WishlistBean extends BaseBean {

	private String product;
	private Date date;
	private String userName;
	private String remark;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return product;
	}
}
