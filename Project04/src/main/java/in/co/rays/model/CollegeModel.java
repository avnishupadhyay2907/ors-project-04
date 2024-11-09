package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class CollegeModel {
	public int nextPk() throws Exception {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_college");

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

	public void add(CollegeBean bean) throws Exception {

		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("insert into st_college values (?,?,?,?,?,?,?,?,?,?)");
			pk = nextPk();

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getphoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDateTime());
			pstmt.setTimestamp(10, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();

			conn.setAutoCommit(false);

			conn.commit();
			System.out.println("data inserted" + i);

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

	public void update(CollegeBean bean) throws Exception {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_college set name = ? ,address = ? ,state = ? ,city = ? ,phone_no = ?,created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getphoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDateTime());
			pstmt.setTimestamp(9, bean.getModifiedDateTime());
			pstmt.setLong(10, bean.getId());

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

	public void delete(CollegeBean bean) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("delete from st_college where id = ?");

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
	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(CollegeBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_college where 1=1");

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

				bean = (CollegeBean) new CollegeBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setphoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDateTime(rs.getTimestamp(9));
				bean.setModifiedDateTime(rs.getTimestamp(10));

				list.add(bean);
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search user " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	public CollegeBean findByPk(long id) throws Exception {
		Connection conn = null;
		CollegeBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_college where id = ?");
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setphoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDateTime(rs.getTimestamp(9));
				bean.setModifiedDateTime(rs.getTimestamp(10));
			}

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public CollegeBean findByName(String name) throws Exception {

		Connection conn = null;
		CollegeBean bean = null;
		PreparedStatement pstmt = conn.prepareStatement("select * from st_college where name = ?");
		try {
			conn = JDBCDataSource.getConnection();
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setphoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDateTime(rs.getTimestamp(9));
				bean.setModifiedDateTime(rs.getTimestamp(10));
			}

		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting User by login " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}
}
