package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.util.JDBCDataSource;

public class CourseModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}
		return pk + 1;

	}

	public void add(CourseBean bean) throws Exception {

		CourseBean existBean = findByPk(bean.getId());
		
		if (existBean != null) {
			throw new Exception(" ===========>>>>>name already exist");
		}
		
		
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_course values (?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1,pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getDuration());
		pstmt.setString(4, bean.getDescription());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDateTime());
		pstmt.setTimestamp(8, bean.getModifiedDateTime());
		
		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(CourseBean bean) throws Exception {

		
		
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_course set name = ?,duration = ?,description = ?,created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getDuration());
		pstmt.setString(3, bean.getDescription());
		pstmt.setString(4, bean.getCreatedBy());
		pstmt.setString(5, bean.getModifiedBy());
		pstmt.setTimestamp(6, bean.getCreatedDateTime());
		pstmt.setTimestamp(7, bean.getModifiedDateTime());
		pstmt.setLong(8, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(CourseBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(CourseBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "'");

				
			}
		}
		System.out.println(sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new CourseBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDuration(rs.getString(3));
			bean.setDescription(rs.getString(4));
			bean.setCreatedBy(rs.getString(5));
			bean.setModifiedBy(rs.getString(6));
			bean.setCreatedDateTime(rs.getTimestamp(7));
			bean.setModifiedDateTime(rs.getTimestamp(8));
			
			list.add(bean);
		}

		return list;

	}

	public CourseBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_course where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		CourseBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new CourseBean(); // Create a new instance of UserBean when data is found
			
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDuration(rs.getString(3));
			bean.setDescription(rs.getString(4));
			bean.setCreatedBy(rs.getString(5));
			bean.setModifiedBy(rs.getString(6));
			bean.setCreatedDateTime(rs.getTimestamp(7));
			bean.setModifiedDateTime(rs.getTimestamp(8));
			
			
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public CourseBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_course where roll_no = ?");

		pstmt.setString(1,name);

		ResultSet rs = pstmt.executeQuery();

		CourseBean bean = null;

		while (rs.next()) {
			bean = new CourseBean(); // Create a new instance of UserBean when data is found
			
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDuration(rs.getString(3));
			bean.setDescription(rs.getString(4));
			bean.setCreatedBy(rs.getString(5));
			bean.setModifiedBy(rs.getString(6));
			bean.setCreatedDateTime(rs.getTimestamp(7));
			bean.setModifiedDateTime(rs.getTimestamp(8));
			
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}

}
