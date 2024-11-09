<%@page import="in.co.rays.model.SubjectModel"%>
<%@page import="in.co.rays.bean.SubjectBean"%>
<%@page import="in.co.rays.model.CourseModel"%>
<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="in.co.rays.model.CollegeModel"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
<%@page import="in.co.rays.bean.FacultyBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h1 style="color: navy;" align="center	">Faculty List</h1>
	<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

<jsp:useBean id="bean" class="in.co.rays.bean.FacultyBean"
			scope="request"></jsp:useBean>

		<%
			List list = (List) request.getAttribute("list");
		%>



		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>DOB</th>
				<th>Gender</th>
				<th>Mobile No</th>
				<th>Email Id</th>
				<th>College</th>
				<th>Course</th>
				<th>Subject</th>

				<th>Edit</th>

				<%
					/* Iterator it = list.iterator(); */
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						
						 bean = (FacultyBean) it.next();
						CollegeBean collegeBean = new CollegeBean();
						CollegeModel collegeModel = new CollegeModel(); 
						CourseBean courseBean = new CourseBean();
						CourseModel courseModel = new CourseModel();
						SubjectBean subjectBean = new SubjectBean();
						SubjectModel subjectModel = new SubjectModel();
	
						collegeBean = collegeModel.findByPk(bean.getId());
						courseBean = courseModel.findByPk(bean.getId());
						subjectBean= subjectModel.findByPk(bean.getId());
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getDob()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=bean.getEmail()%></td>
				<td><%=collegeBean.getName()%></td>
				<td><%=courseBean.getName()%></td>
				<td><%=subjectBean.getName()%></td>

				<%
					}
				%>
			
		</table>

	</form>
</body>
</html>