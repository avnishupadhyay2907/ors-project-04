d<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.model.CourseModel"%>
<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="in.co.rays.bean.SubjectBean"%>
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
	<h1 style="color: navy;" align="center	">User List</h1>
	<form action="<%=ORSView.USER_LIST_VIEW%>" method="post">

<jsp:useBean id="bean" class="in.co.rays.bean.SubjectBean"
			scope="request"></jsp:useBean>


		<%
			List list = (List) request.getAttribute("list");
		%>



		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>S.No</th>
				<th>Name</th>
				<th>Course Name</th>
				<th>Description</th>
				
				<th>Edit</th>

				<%
			
					/* Iterator it = list.iterator(); */
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						
						bean = (SubjectBean)it.next();
						CourseBean courseBean = new CourseBean();
						CourseModel courseModel = new CourseModel();
						courseBean = courseModel.findByPk(bean.getCourseId());
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getCourseName()%></td>
				<td><%=bean.getDescription()%></td>
				

				<%
					}
				%>
			
		</table>

	</form>
</body>
</html>