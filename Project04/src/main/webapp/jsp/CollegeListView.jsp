<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
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
	<h1 style="color: navy;" align="center	">College List</h1>
	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">



		<%
	List list = (List) request.getAttribute("list");
		%>



		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>Name</th>
				<th>Address</th>
				<th>State</th>
				<th>City</th>
				<th>Phone</th>
				
				<th>Edit</th>

				<%
					/* Iterator it = list.iterator(); */
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
					
						CollegeBean bean = (CollegeBean)it.next();
						
						
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getAddress()%></td>
				<td><%=bean.getState()%></td>
				<td><%=bean.getCity()%></td>
				<td><%=bean.getphoneNo()%></td>
				
				<%
					}
				%>
			
		</table>

	</form>
	
	  <%@ include file="Footer.jsp" %>
</body>
</html>