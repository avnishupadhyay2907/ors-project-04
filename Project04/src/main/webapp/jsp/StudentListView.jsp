<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.model.CollegeModel"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
<%@page import="in.co.rays.bean.StudentBean"%>
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
	<h1 style="color: navy;" align="center	">Student List</h1>
	<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.StudentBean"
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
				<th>College Name</th>

				<th>Edit</th>

				<%
					Iterator it = list.iterator();
					/* Iterator it = list.iterator(); */
				%>

				<%
					while (it.hasNext()) {
						bean = (StudentBean) it.next();
						CollegeBean collegeBean = new CollegeBean();
						CollegeModel collegeModel = new CollegeModel();

						collegeBean = collegeModel.findByPk(bean.getCollegeId());
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

				<%
					}
				%>
			
		</table>

	</form>
</body>
</html>