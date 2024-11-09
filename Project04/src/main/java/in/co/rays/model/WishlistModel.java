package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.WishlistBean;
import in.co.rays.util.JDBCDataSource;

public class WishlistModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from wishlist");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(WishlistBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into wishlist values (?,?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1, pk);
		System.out.println("jghhjjhhginnnn model" + bean.getProduct());
		pstmt.setString(2, bean.getProduct());
		pstmt.setDate(3, new java.sql.Date(bean.getDate().getTime()));
		pstmt.setString(4, bean.getUserName());
		pstmt.setString(5, bean.getRemark());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDateTime());
		pstmt.setTimestamp(9, bean.getModifiedDateTime());
		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(WishlistBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update wishlist set product = ?, date = ?, user_name = ?, remark = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getProduct());
		pstmt.setDate(2, new java.sql.Date(bean.getDate().getTime()));
		pstmt.setString(3, bean.getUserName());
		pstmt.setString(4, bean.getRemark());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDateTime());
		pstmt.setTimestamp(8, bean.getModifiedDateTime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(WishlistBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from wishlist where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(WishlistBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from wishlist where 1=1");

		if (bean != null) {
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" and user_name like '" + bean.getUserName() + "%'");
			}
			if (bean.getRemark() != null && bean.getRemark().length() > 0) {
				sql.append(" and last_name like '" + bean.getRemark() + "%'");
			}
			if (bean.getProduct() != null && bean.getProduct().length() > 0) {
				sql.append(" and last_name like '" + bean.getProduct() + "%'");
			}
			if (bean.getDate() != null && ((CharSequence) bean.getDate()).length() > 0) {
				sql.append(" and last_name like '" + bean.getDate() + "%'");
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

			bean = new WishlistBean();

			bean.setId(rs.getLong(1));
			bean.setProduct(rs.getString(2));
			bean.setDate(rs.getDate(3));
			bean.setUserName(rs.getString(4));
			bean.setRemark(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
			list.add(bean);
		}
		return list;
	}

	public WishlistBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from wishlist where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		WishlistBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new WishlistBean(); // Create a new instance of WishlistBean when data is found
			bean.setId(rs.getLong(1));
			bean.setProduct(rs.getString(2));
			bean.setDate(rs.getDate(3));
			bean.setUserName(rs.getString(4));
			bean.setRemark(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public WishlistBean findByUserName(String userName) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from wishlist where user_name = ?");

		pstmt.setString(1, userName);

		ResultSet rs = pstmt.executeQuery();

		WishlistBean bean = null;

		while (rs.next()) {
			bean = new WishlistBean();
			bean.setId(rs.getLong(1));
			bean.setProduct(rs.getString(2));
			bean.setDate(rs.getDate(3));
			bean.setUserName(rs.getString(4));
			bean.setRemark(rs.getString(5));
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
