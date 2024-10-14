package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.util.JDBCDataSource;

public class FacultyModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}
		return pk + 1;

	}

	public void add(FacultyBean bean) throws Exception {

		FacultyBean existBean = findByPk(bean.getId());

		if (existBean != null) {
			throw new Exception("roll_no already exist");
		}
		CollegeModel collegeModel = new CollegeModel();

		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());

		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());

		bean.setSubjectName(subjectBean.getName());

		System.out.println("CollegeBean: " + collegeBean);
		System.out.println("College Name: " + collegeBean.getName());

		System.out.println("CourseBean: " + courseBean);
		System.out.println("Course Name: " + courseBean.getName());

		System.out.println("SubjectBean: " + subjectBean);
		System.out.println("Subject Name: " + subjectBean.getName());

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn
				.prepareStatement("insert into st_faculty values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
		pstmt.setLong(10, bean.getCourseId());
		pstmt.setString(11, bean.getCourseName());
		pstmt.setLong(12, bean.getSubjectId());
		pstmt.setString(13, bean.getSubjectName());
		pstmt.setString(14, bean.getCreatedBy());
		pstmt.setString(15, bean.getModifiedBy());
		pstmt.setTimestamp(16, bean.getCreatedDateTime());
		pstmt.setTimestamp(17, bean.getModifiedDateTime());

		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully !!!" + i);

	}

	public void update(FacultyBean bean) throws Exception {

		FacultyBean existBean = findByPk(bean.getId());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new Exception("Data updated successfully...");
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE st_faculty SET first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? WHERE id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getMobileNo());
		pstmt.setString(6, bean.getEmail());
		pstmt.setLong(7, bean.getCollegeId());
		pstmt.setString(8, bean.getCollegeName());
		pstmt.setLong(9, bean.getCourseId());
		pstmt.setString(10, bean.getCourseName());
		pstmt.setLong(11, bean.getSubjectId());
		pstmt.setString(12, bean.getSubjectName());
		pstmt.setString(13, bean.getCreatedBy());
		pstmt.setString(14, bean.getModifiedBy());
		pstmt.setTimestamp(15, bean.getCreatedDateTime());
		pstmt.setTimestamp(16, bean.getModifiedDateTime());
		pstmt.setLong(17, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(FacultyBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List<FacultyBean> search(FacultyBean bean) throws Exception {

	    Connection conn = JDBCDataSource.getConnection();
	    StringBuilder sql = new StringBuilder("SELECT * FROM st_faculty WHERE 1=1");

	    List<Object> queryParameters = new ArrayList<Object>();

	    if (bean != null) {

	        if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
	            sql.append(" AND first_name LIKE ?");
	            queryParameters.add(bean.getFirstName() + "%");
	        }

	        if (bean.getLastName() != null && bean.getLastName().length() > 0) {
	            sql.append(" AND last_name LIKE ?");  // Updated to correct column name
	            queryParameters.add(bean.getLastName() + "%");
	        }
	    }

	    System.out.println(sql.toString());

	    PreparedStatement pstmt = conn.prepareStatement(sql.toString());

	    for (int i = 0; i < queryParameters.size(); i++) {
	        pstmt.setObject(i + 1, queryParameters.get(i));
	    }

	    ResultSet rs = pstmt.executeQuery();

	    List<FacultyBean> list = new ArrayList<FacultyBean>();

	    while (rs.next()) {
	        FacultyBean resultBean = new FacultyBean();

	        resultBean.setId(rs.getLong(1));
	        resultBean.setFirstName(rs.getString(2));
	        resultBean.setLastName(rs.getString(3));
	        resultBean.setDob(rs.getDate(4));
	        resultBean.setGender(rs.getString(5));
	        resultBean.setMobileNo(rs.getString(6));
	        resultBean.setEmail(rs.getString(7));
	        resultBean.setCollegeId(rs.getLong(8));
	        resultBean.setCollegeName(rs.getString(9));
	        resultBean.setCourseId(rs.getLong(10));
	        resultBean.setCourseName(rs.getString(11));
	        resultBean.setSubjectId(rs.getLong(12));
	        resultBean.setSubjectName(rs.getString(13));
	        resultBean.setCreatedBy(rs.getString(14));
	        resultBean.setModifiedBy(rs.getString(15));
	        resultBean.setCreatedDateTime(rs.getTimestamp(16));
	        resultBean.setModifiedDateTime(rs.getTimestamp(17));

	        list.add(resultBean);
	    }

	    rs.close();
	    pstmt.close();
	    conn.close();

	    return list;
	}


	public FacultyBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		FacultyBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new FacultyBean(); // Create a new instance of UserBean when data is found

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDateTime(rs.getTimestamp(16));
			bean.setModifiedDateTime(rs.getTimestamp(17));
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public FacultyBean findByEmail(String email) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where email = ?");

		pstmt.setString(1, email);

		ResultSet rs = pstmt.executeQuery();

		FacultyBean bean = null;

		while (rs.next()) {
			bean = new FacultyBean(); // Create a new instance of UserBean when data is found

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDateTime(rs.getTimestamp(16));
			bean.setModifiedDateTime(rs.getTimestamp(17));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}

}
