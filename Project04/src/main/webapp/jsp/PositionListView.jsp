<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.PositionBean"%>
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
	<h1 style="color: navy;" align="center	">Position List</h1>
	<form action="<%=ORSView.POSITION_LIST_CTL%>" method="post">



		<%
 			List list = (List) request.getAttribute("list");
		
		%>

<tr>
<th>Condition :</th>
<td><input type = "text" name = "condition" placeholder="serach condition"></td>
<td> <input type ="submit" name = "operation" value = "search"></td>

</tr>


		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>Description</th>
				<th>OpeningDate</th>
				<th>RequiredExperience</th>
				<th>Condition</th>
				<th>Edit</th>

				<%
					 Iterator it = list.iterator(); 
					
 %>

				<%
					while (it.hasNext()) {
 			PositionBean bean =(PositionBean) it.next();
				%>
			
			<tr align="center">

				<td><input type="checkbox"  id = "selectall"name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getDesignation()%></td>
				
				<td><%=bean.getOpeningDate()%></td>
				<td><%=bean.getRequiredExperience()%></td>
				<td><%=bean.getCondition()%></td>
				<td><a href="<%=ORSView.POSITION_CTL%>?id=<%=bean.getId()%>"
						<%if (bean.getId() == RoleBean.ADMIN) {%>
						onclick="return false;" <%}%>>edit</a></td>

				<%
					}
				%>
			
		</table>
<tr>
<th></th>
<td><input type = "submit" name = "operation" value = "Previous"></td>
</tr>

<tr>
<th></th>
<td><input type = "submit" name = "operation" value = "Delete"></td>
</tr>

<tr>
<th></th>
<td><input type = "submit" name = "operation" value = "Next"></td>
</tr>

<tr>
<th></th>
<td><input type = "submit" name = "operation" value = "New"></td>
</tr>
	</form>
</body>
</html>