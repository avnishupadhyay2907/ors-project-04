<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.StockPurchaseCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- jQuery Library -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- jQuery UI Library -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Our custom JavaScript file -->
<script src="/Project04/js/datepicker.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.STOCK_PURCHASE_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.StockPurchaseBean"
			scope="request"></jsp:useBean>



		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Stock Purchase
				</font>
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
					<th>Quantity :</th>
					<td><input type="text" name="quantity"
						placeholder="Enter Quantity "
						value="<%=DataUtility.getStringData(bean.getQuantity())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("quantity", request)%></font></td>
				</tr>

				<tr>
					<th>Purchase Price :</th>
					<td><input type="text" name="purchasePrice"
						placeholder="Enter Purchase Price"
						value="<%=DataUtility.getStringData(bean.getPurchasePrice())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("purchasePrice", request)%></font></td>
				</tr>

				<tr>
					<th>Purchase Date :</th>
					<td><input type="text" id="udate" name="purchaseDate"
						placeholder="Select Purchase Date"
						value="<%=DataUtility.getDateString(bean.getPurchaseDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("purchaseDate", request)%></font></td>
				</tr>



				<tr>
					<th>Order Type :</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("market", "market");
							map.put("limit", "limit");
						%> <%=HTMLUtility.getList("OrderType", bean.getOrderType(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("OrderType", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StockPurchaseCtl.OP_UPDATE%>">
						<input type="submit" name="operation"
						value="<%=StockPurchaseCtl.OP_CANCEL%>"> <%
 	} else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StockPurchaseCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=StockPurchaseCtl.OP_RESET%>"> <%
 	}
 %>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>