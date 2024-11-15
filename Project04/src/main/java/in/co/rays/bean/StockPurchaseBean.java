package in.co.rays.bean;

import java.util.Date;

public class StockPurchaseBean extends BaseBean {

	private String quantity ;
	private String purchasePrice;
	private Date purchaseDate;
	private String OrderType;

	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getOrderType() {
		return OrderType;
	}
	public void setOrderType(String orderType) {
		OrderType = orderType;
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
