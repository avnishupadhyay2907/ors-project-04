<%@page import="in.co.rays.bean.TimetableBean"%>
<%@page import="in.co.rays.model.SubjectModel"%>
<%@page import="in.co.rays.model.CourseModel"%>
<%@page import="in.co.rays.bean.SubjectBean"%>
<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.model.RoleModel"%>
<%@page import="in.co.rays.model.UserModel"%>

<%@page import="in.co.rays.bean.RoleBean"%>
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
	<h1 style="color: navy;" align="center	">Timetable List</h1>
	<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">
<jsp:useBean id="bean" class="in.co.rays.bean.TimetableBean"
			scope="request"></jsp:useBean>


		<%
			List list = (List) request.getAttribute("list");
		%>



		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>Semester</th>
				<th>Description</th>
				<th>ExamDate</th>
				<th>ExamTime</th>
				<th>CourseName</th>
				<th>SubjectName</th>

				<th>Edit</th>

				<%
					/* Iterator it = list.iterator(); */
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						bean = (TimetableBean) it.next();
						
						CourseBean courseBean = new CourseBean();
						SubjectBean subjectBean = new SubjectBean();
						CourseModel courseModel = new CourseModel();
						SubjectModel subjectModel = new SubjectModel();
						courseBean = courseModel.findByPk(bean.getId());
						subjectBean= subjectModel.findByPk(bean.getId());
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids"
					value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getSemester()%></td>
				<td><%=bean.getDescription()%></td>
				<td><%=bean.getExamDate()%></td>
				<td><%=bean.getExamTime()%></td>
				<td><%=courseBean.getName()%></td>
				<td><%=subjectBean.getName()%></td>
				

				<%
					}
				%>
			
		</table>

	</form>
</body>
</html>
s
