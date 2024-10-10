package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.util.JDBCDataSource;

public class UserModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}
		return pk + 1;

	}

	public void add(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_user values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setString(4, bean.getLoginId());
		pstmt.setString(5, bean.getPassword());
		pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(7, bean.getMobileNo());
		pstmt.setLong(8, bean.getRoleId());
		pstmt.setString(9, bean.getGender());
		pstmt.setString(10, bean.getCreatedBy());
		pstmt.setString(11, bean.getModifiedBy());
		pstmt.setTimestamp(12, bean.getCreatedDateTime());
		pstmt.setTimestamp(13, bean.getModifiedDateTime());
		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_user set first_name = ? ,last_name = ? ,login_id = ? ,password = ? ,dob = ? ,mobile_no = ? ,role_id = ? ,gender = ? ,created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setString(3, bean.getLoginId());
		pstmt.setString(4, bean.getPassword());
		pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(6, bean.getMobileNo());
		pstmt.setLong(7, bean.getRoleId());
		pstmt.setString(8, bean.getGender());
		pstmt.setString(9, bean.getCreatedBy());
		pstmt.setString(10, bean.getModifiedBy());
		pstmt.setTimestamp(11, bean.getCreatedDateTime());
		pstmt.setTimestamp(12, bean.getModifiedDateTime());
		pstmt.setLong(13, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(UserBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "'");

				if (bean.getLastName() != null && bean.getLastName().length() > 0) {
					sql.append(" and last_name like '" + bean.getLastName() + "'");
				}
			}
		}
		System.out.println(sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
			list.add(bean);
		}

		return list;

	}
	public UserBean findByPk(long id) throws Exception {
	    Connection conn = JDBCDataSource.getConnection();

	    PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");
	    pstmt.setLong(1, id);

	    ResultSet rs = pstmt.executeQuery();

	    UserBean bean = null; // Initialize the bean to null

	    if (rs.next()) {
	        bean = new UserBean(); // Create a new instance of UserBean when data is found
	        bean.setId(rs.getLong(1));
	        bean.setFirstName(rs.getString(2));
	        bean.setLastName(rs.getString(3));
	        bean.setLoginId(rs.getString(4));
	        bean.setPassword(rs.getString(5));
	        bean.setDob(rs.getDate(6));
	        bean.setMobileNo(rs.getString(7));
	        bean.setRoleId(rs.getLong(8));
	        bean.setGender(rs.getString(9));
	        bean.setCreatedBy(rs.getString(10));
	        bean.setModifiedBy(rs.getString(11));
	        bean.setCreatedDateTime(rs.getTimestamp(12));
	        bean.setModifiedDateTime(rs.getTimestamp(13));
	    }

	    JDBCDataSource.closeConnection(conn);

	    return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public UserBean findByLoginId(String loginId) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login_id = ?");

		pstmt.setString(1, loginId);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}
}