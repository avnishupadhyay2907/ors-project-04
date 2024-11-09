<%@page import="in.co.rays.ctl.FacultyCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.MarksheetCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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
<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
			scope="request"></jsp:useBean>

		<%
			
			List studentList = (List) request.getAttribute("studentList");
		%>

		<div align="center">

			<h1>
				<font color="navy">Add Marksheet</font>
			</h1>

			<h3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>

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
					<th>Roll No:</th>
					<td><input type="text" name="rollNo"
						placeholder="Enter Roll No"
						value="<%=DataUtility.getStringData(bean.getRoll_no())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
				</tr>
				<th>Student:</th>
					<td><%=HTMLUtility.getList("studentId", DataUtility.getStringData(bean.getStudent_id()), studentList)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%></font></td>
				</tr>
				<tr>
					<th>Physics:</th>
					<td><input type="text" name="physics"
						placeholder="Enter Physics No"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>
				
				<tr>
					
				<tr>
					<th>Chemistry:</th>
					<td><input type="text" name="chemistry"
						placeholder="Enter Chemistry No"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>
				<tr>
					<th>Maths:</th>
					<td><input type="text" name="maths"
						placeholder="Enter Maths No"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>
				</tr>
				
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>">&nbsp; <input
						type="submit" name="operation" value="<%=MarksheetCtl.OP_RESET%>"></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>