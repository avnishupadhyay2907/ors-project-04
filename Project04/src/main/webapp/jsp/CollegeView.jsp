<%@page import="in.co.rays.ctl.CollegeCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
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
	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
			scope="request"></jsp:useBean>
		<div align="center">

			<h1 style="color: navy;" align="center">Add College</h1>

			<h3 style="color: green;" align="center"><%=ServletUtility.getSuccessMessage(request)%></h3>

			<h3 style="color: red;" align="center"><%=ServletUtility.getErrorMessage(request)%></h3>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">

			<table>
				<tr>
					<th>College Name:</th>
					<td><input type="text" name="name"
						placeholder="Enter College Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th>Address:</th>
					<td><input type="text" name="address"
						placeholder="Enter Address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>
				<tr>
					<th>State:</th>
					<td><input type="text" name="state" placeholder="Enter State"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th>City:</th>
					<td><input type="text" name="city" placeholder="Enter City"
						value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr>
					<th>Phone No:</th>
					<td><input type="text" name="phoneNo"
						placeholder="Enter Phone No"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>">&nbsp; <input
						type="submit" name="operation" value="<%=CollegeCtl.OP_RESET%>"></td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>