<%@page import="in.co.rays.ctl.PatientListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@page import="in.co.rays.bean.PatientBean"%>
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
	<h1 style="color: navy;" align="center	">Patient List</h1>
	<form action="<%=ORSView.PATIENT_LIST_CTL%>" method="post">



		<%
			List list = (List) request.getAttribute("list");
		%>

		<tr align="center">
			<th>Disease :</th>
			<td><input type="text" name="disease"
				placeholder="serach disease"></td>
			<td><input type="submit" name="operation" value="<%=PatientListCtl.OP_SEARCH%>"> &nbsp;
			<input type="submit" name="operation" value="<%=PatientListCtl.OP_RESET%>">
			</td>

		</tr>


		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>Name</th>
				<th>DateOfVisit</th>
				<th>Mobile</th>
				<th>Disease</th>
				<th>Edit</th>

				<%
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						PatientBean bean = (PatientBean) it.next();
				%>
			
			<tr align="center">

				<td><input type="checkbox" id="selectall" name="ids"
					value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>

				<td><%=bean.getDateOFVisit()%></td>
				<td><%=bean.getMobile()%></td>
				<td><%=bean.getDisease()%></td>
				<td><a href="<%=ORSView.PATIENT_CTL%>?id=<%=bean.getId()%>"
					<%if (bean.getId() == RoleBean.ADMIN) {%> onclick="return false;"
					<%}%>>edit</a></td>

				<%
					}
				%>
			
		</table>
		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="Previous"></td>
		</tr>

		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="Delete"></td>
		</tr>

		<tr>
			<th></th>
			<td><input type="submit" name="operation" value="Next"></td>
		</tr>
	</form>
</body>
</html>