package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.StockPurchaseBean;
import in.co.rays.util.JDBCDataSource;

public class StockPurchaseModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from stock_purchase");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(StockPurchaseBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into stock_purchase values (?,?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1, pk);
		System.out.println("jghhjjhhginnnn model" +bean.getQuantity());
		pstmt.setString(2, bean.getQuantity());
		pstmt.setString(3, bean.getPurchasePrice());
		pstmt.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
		
		pstmt.setString(5, bean.getOrderType());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDateTime());
		pstmt.setTimestamp(9, bean.getModifiedDateTime());
		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(StockPurchaseBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update stock_purchase set quantity = ?, purchase_price = ?, purchase_date = ?, order_type = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getQuantity());
		pstmt.setString(2, bean.getPurchasePrice());
		pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(4, bean.getOrderType());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDateTime());
		pstmt.setTimestamp(8, bean.getModifiedDateTime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(StockPurchaseBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from stock_purchase where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(StockPurchaseBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from stock_purchase where 1=1");

		if (bean != null) {
			System.out.println("bean not = nullllllllllllll");
			
			if (bean.getQuantity() != null && bean.getQuantity().length() > 0) {
				sql.append(" and quantity like '" + bean.getQuantity() + "%'");
			}
			if (bean.getPurchasePrice() != null && bean.getPurchasePrice().length() > 0) {
				sql.append(" and purchase_price like '" + bean.getPurchasePrice() + "%'");
			}
			
			if (bean.getPurchaseDate() != null && ((CharSequence) bean.getPurchaseDate()).length() > 0) {
				sql.append(" and purchase_date like '" + bean.getPurchaseDate() + "%'");
			}
			if (bean.getOrderType() != null && bean.getOrderType().length() > 0) {
				sql.append(" and order_type like '" + bean.getOrderType() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		System.out.println(sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new StockPurchaseBean();

			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getString(2));
			bean.setPurchasePrice(rs.getString(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
			
			list.add(bean);
		}
		return list;
	}

	public StockPurchaseBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from stock_purchase where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		StockPurchaseBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new StockPurchaseBean(); // Create a new instance of stock_purchaseBean when data is found
		
			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getString(2));
			bean.setPurchasePrice(rs.getString(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public StockPurchaseBean findByPurchasePrice(String purchasePrice) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from stock_purchase where purchase_price = ?");

		pstmt.setString(1, purchasePrice);

		ResultSet rs = pstmt.executeQuery();

		StockPurchaseBean bean = null;

		while (rs.next()) {
			bean = new StockPurchaseBean();
		
			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getString(2));
			bean.setPurchasePrice(rs.getString(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public void delete(int int1) {
		// TODO Auto-generated method stub
		
	}
	
}
