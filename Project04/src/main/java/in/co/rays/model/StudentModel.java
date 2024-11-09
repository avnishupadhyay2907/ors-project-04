
package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.util.JDBCDataSource;

public class StudentModel {
	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}
		return pk + 1;

	}

	public void add(StudentBean bean) throws Exception {
		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new Exception("email already exist..!!");
		}

		CollegeModel collegeModel = new CollegeModel();

		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_student values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(5, bean.getGender());
		pstmt.setString(6, bean.getMobileNo());
		pstmt.setString(7, bean.getEmail());
		pstmt.setLong(8, bean.getCollegeId());
		pstmt.setString(9, bean.getCollegeName());
		pstmt.setString(10, bean.getCreatedBy());
		pstmt.setString(11, bean.getModifiedBy());
		pstmt.setTimestamp(12, bean.getCreatedDateTime());
		pstmt.setTimestamp(13, bean.getModifiedDateTime());
		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(StudentBean bean) throws Exception {

		
		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new Exception("email already exist..!!");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_student set first_name = ? ,last_name = ? ,dob = ? ,gender = ? ,mobile_no = ? ,email = ? ,college_id = ? ,gender = ? ,college_name = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getMobileNo());
		pstmt.setString(6, bean.getEmail());
		pstmt.setLong(7, bean.getCollegeId());
		pstmt.setString(8, bean.getCollegeName());
		pstmt.setString(9, bean.getCreatedBy());
		pstmt.setString(10, bean.getModifiedBy());
		pstmt.setTimestamp(11, bean.getCreatedDateTime());
		pstmt.setTimestamp(12, bean.getModifiedDateTime());
		pstmt.setLong(13,bean.getId());

		
		
		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(StudentBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_student where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(StudentBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_student where 1=1");

		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "'");

				if (bean.getLastName() != null && bean.getLastName().length() > 0) {
					sql.append(" and last_name like '" + bean.getLastName() + "'");
				}
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

			bean = new StudentBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
			list.add(bean);
		}

		return list;

	}
	public StudentBean findByPk(long id) throws Exception {
	    Connection conn = JDBCDataSource.getConnection();

	    PreparedStatement pstmt = conn.prepareStatement("select * from st_student where id = ?");
	    pstmt.setLong(1, id);

	    ResultSet rs = pstmt.executeQuery();

	    StudentBean bean = null; // Initialize the bean to null

	    if (rs.next()) {
	        bean = new StudentBean(); // Create a new instance of UserBean when data is found
	        bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
	    }

	    JDBCDataSource.closeConnection(conn);

	    return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public StudentBean findByEmail(String email) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_student where email = ?");

		pstmt.setString(1, email);

		ResultSet rs = pstmt.executeQuery();

		StudentBean bean = null;

		while (rs.next()) {
			bean = new StudentBean();
			  bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}
	public List list() throws Exception {
		return search(null, 0, 0);
	}
}
