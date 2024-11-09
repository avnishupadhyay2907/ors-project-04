<%@page import="in.co.rays.ctl.CourseCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.HashMap"%>
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
	<form action="<%=ORSView.COURSE_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.CourseBean"
			scope="request"></jsp:useBean>
		<div align="center">

			<h1 style="color: navy;" align="center">Add Course</h1>

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
					<th>Course Name:</th>
					<td><input type="text" name="name"
						placeholder="Enter Course Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th>Duration:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("4 years", "4 years");
							/* map.put("3 years", "3 years");
							map.put("2 years", "2 years"); */
						%> <%=HTMLUtility.getList("gender", bean.getDuration(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%></font></td>
				</tr>
				<tr>
					<th>Description:</th>
					<td><input type="text" name="description" placeholder="Enter Description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
				
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=CourseCtl.OP_SAVE%>">&nbsp; <input
						type="submit" name="operation" value="<%=CourseCtl.OP_RESET%>"></td>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>