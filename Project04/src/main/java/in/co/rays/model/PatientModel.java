package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.PatientBean;
import in.co.rays.util.JDBCDataSource;

public class PatientModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from patient");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}
		return pk + 1;

	}

	public void add(PatientBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into patient values (?,?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setDate(3, new java.sql.Date(bean.getDateOFVisit().getTime()));
		pstmt.setString(4, bean.getMobile());
		pstmt.setString(5, bean.getDisease());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDateTime());
		pstmt.setTimestamp(9, bean.getModifiedDateTime());
		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(PatientBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update patient set name = ? ,date_of_visit = ? ,mobile = ? ,disease = ? ,created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setDate(2, new java.sql.Date(bean.getDateOFVisit().getTime()));
		pstmt.setString(3, bean.getMobile());
		pstmt.setString(4, bean.getDisease());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDateTime());
		pstmt.setTimestamp(8, bean.getModifiedDateTime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(PatientBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from patient where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(PatientBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from patient where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDateOFVisit() != null && bean.getDateOFVisit().getTime() > 0) {
				sql.append(" and date_of_visit like '" + bean.getDateOFVisit() + "%'");

			}
			if (bean.getMobile() != null && bean.getMobile().length() > 0) {
				sql.append(" and mobile like '" + bean.getMobile() + "%'");

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

			bean = new PatientBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDateOFVisit(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setDisease(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
			list.add(bean);
		}

		return list;

	}

	public PatientBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from patient where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		PatientBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new PatientBean(); // Create a new instance of PatientBean when data is found
		
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDateOFVisit(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setDisease(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));;
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public PatientBean findByMobile(String mobile) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from patient where mobile = ?");

		pstmt.setString(1, mobile);

		ResultSet rs = pstmt.executeQuery();

		PatientBean bean = null;

		while (rs.next()) {
			bean = new PatientBean();
		
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDateOFVisit(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setDisease(rs.getString(5));
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

}
