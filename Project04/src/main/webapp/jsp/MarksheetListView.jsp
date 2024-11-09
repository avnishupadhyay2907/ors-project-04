<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.model.StudentModel"%>
<%@page import="in.co.rays.bean.StudentBean"%>
<%@page import="in.co.rays.bean.MarksheetBean"%>
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
	<h1 style="color: navy;" align="center	">Marksheet List</h1>
	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">
<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
			scope="request"></jsp:useBean>


		<%
			List list = (List) request.getAttribute("list");
		%>



		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>S.No</th>
				<th>Roll NO</th>
				<th>Student Name</th>
				<th>Marks in Physics</th>
				<th>Marks in Chemistry</th>
				<th>Marks in Maths</th>
				
				<th>Edit</th>

				<%
				Iterator it = list.iterator();
					/* Iterator it = list.iterator(); */
				%>

				<%
					while (it.hasNext()) {
					
						 bean = (MarksheetBean)it.next();
						
						 StudentBean studentBean = new StudentBean();
						 StudentModel studentModel = new StudentModel();
						 studentBean = studentModel.findByPk(bean.getId());
						 
				%>
			
			<tr align="center">

				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getRoll_no()%></td>
				<td><%=studentBean.getFirstName()+ studentBean.getLastName()%></td>
				<td><%=bean.getPhysics()%></td>
				<td><%=bean.getChemistry()%></td>
				<td><%=bean.getMaths()%></td>
				

				<%
					}
				%>
			
		</table>

	</form>
</body>
</html>