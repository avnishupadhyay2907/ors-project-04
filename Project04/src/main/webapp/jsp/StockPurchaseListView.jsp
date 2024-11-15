<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.StockPurchaseBean"%>
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
	<h1 style="color: navy;" align="center	">Stock Purchase List</h1>
	<form action="<%=ORSView.STOCK_PURCHASE_LIST_CTL%>" method="post">



		<%
 			List list = (List) request.getAttribute("list");
		
		%>

<tr>
<th>Order Type :</th>
<td><input type = "text" name = "OrderType" placeholder="serach order_type"></td>
<td> <input type ="submit" name = "operation" value = "search"></td>

</tr>


		<table border="1px" width="100%" align="center">
			<tr>

				<th>Click</th>
				<th>Id</th>
				<th>Quantity</th>
				<th>Purchase Price</th>
				<th>Purchase Date</th>
				
				<th>Order Type</th>
				<th>Edit</th>

				<%
					 Iterator it = list.iterator(); 
					
 %>

				<%
					while (it.hasNext()) {
 			StockPurchaseBean bean =(StockPurchaseBean) it.next();
				%>
			
			<tr align="center">

				<td><input type="checkbox"  id = "selectall"name="ids" value="<%=bean.getId()%>"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getQuantity()%></td>
				<td><%=bean.getPurchasePrice()%></td>
				<td><%=bean.getPurchaseDate()%></td>
				
				<td><%=bean.getOrderType()%></td>
				<td><a href="<%=ORSView.STOCK_PURCHASE_CTL%>?id=<%=bean.getId()%>"
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