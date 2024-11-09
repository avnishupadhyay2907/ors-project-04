<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.bean.CourseBean"%>
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
	<h1 style="color: navy;" align="center	">Course List</h1>
	<form action="<%=ORSView.USER_LIST_VIEW%>" method="post">



		<%
			List list = (List) request.getAttribute("list");
		%>



		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>S.No</th>
				<th>Name</th>
				<th>Duration</th>
				<th>Description</th>
				
				<th>Edit</th>

				<%
					/* Iterator it = list.iterator(); */
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						CourseBean bean = (CourseBean)it.next();
						
					
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDuration()%></td>
				<td><%=bean.getDescription()%></td>
				

				<%
					}
				%>
			
		</table>

	</form>
</body>
</html>