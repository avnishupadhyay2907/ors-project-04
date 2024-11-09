<%@page import="in.co.rays.model.RoleModel"%>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@page import="in.co.rays.ctl.FacultyCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.UserRegistrationCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>

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
	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">



		<%
			List list = (List) request.getAttribute("list");
		%>

<tr>
<th>firstname</th>
<td><input type = "text" name = "firstName" placeholder="serach firstName"></td>
<td> <input type ="submit" name = "operation" value = "search"></td>

</tr>


		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>LoginId</th>
				<th>Password</th>
				<th>DOB</th>
				<th>Phone</th>
				<th>Role Id</th>
				<th>Gender</th>
				<th>Edit</th>

				<%
					/* Iterator it = list.iterator(); */
					Iterator it = list.iterator();
				%>

				<%
					while (it.hasNext()) {
						UserBean bean = (UserBean) it.next();
						RoleBean roleBean = new RoleBean();
						RoleModel roleModel = new RoleModel();
						roleBean = roleModel.findByPk(bean.getId());
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getLoginId()%></td>
				<td><%=bean.getPassword()%></td>
				<td><%=bean.getDob()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=roleBean.getName()%></td>
				<td><%=bean.getGender()%></td>
<td><a href="<%=ORSView.USER_CTL%>?id=<%=bean.getId()%>"
						<%if (userBean.getId() == bean.getId() || bean.getRoleId() == RoleBean.ADMIN) {%>
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
	</form>

</body>
</html>