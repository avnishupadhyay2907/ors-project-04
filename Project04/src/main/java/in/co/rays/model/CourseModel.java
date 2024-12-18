package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class CourseModel {

	public int nextPk() throws Exception {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getInt(1);

				System.out.println("max id = " + pk);

			}
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}

	public void add(CourseBean bean) throws Exception {

		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("insert into st_course values (?,?,?,?,?,?,?,?)");
			pk = nextPk();

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDateTime());
			pstmt.setTimestamp(8, bean.getModifiedDateTime());
			int i1 = pstmt.executeUpdate();

			conn.setAutoCommit(false);

			conn.commit();
			System.out.println("data inserted" + i1);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void update(CourseBean bean) throws Exception {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
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
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(CourseBean bean) throws Exception {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("Data Deleted Successfully..." + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(CourseBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "'");

			}
		}
		
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		Connection conn = null;
		List list = new ArrayList();
		System.out.println(sql.toString());
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

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
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search user " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	public CourseBean findByPk(long id) throws Exception {
		Connection conn = null;
		CourseBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_course where id = ?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();
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

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public CourseBean findByName(String name) throws Exception {

		Connection conn = null;
		CourseBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_course where name = ?");

			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

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
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by login " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}
}
