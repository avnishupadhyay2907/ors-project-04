<%@page import="in.co.rays.ctl.WishlistCtl"%>
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
<!-- jQuery Library -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- jQuery UI Library -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Our custom JavaScript file -->
<script src="/Project04/js/datepicker.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.WISHLIST_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.WishlistBean"
			scope="request"></jsp:useBean>



		<div align="center">
		 <h1><font color="navy">
                <%
					if (bean != null && bean.getId() > 0) {
				%>
				Update
				<%
					} else {
				%>
				Add
				<%
					}
				%>
				Wishlist
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
					<th>Product:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Fortuner", "Fortuner");
							map.put("RangeRover", "RangeRover");
						%> <%=HTMLUtility.getList("product", bean.getProduct(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("product", request)%></font></td>
				</tr>

				<tr>


					<th>Date :</th>
					<td><input type="text" id="udate" name="date"
						placeholder="Select Date"
						value="<%=DataUtility.getDateString(bean.getDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("date", request)%></font></td>
				</tr>


				<tr>
					<th>User Name:</th>
					<td><input type="text" name="userName"
						placeholder="Enter User Name"
						value="<%=DataUtility.getStringData(bean.getUserName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("userName", request)%></font></td>
				</tr>
				<tr>
					<th>Remark :</th>
					<td><input type="text" name="remark"
						placeholder="Enter Remark"
						value="<%=DataUtility.getStringData(bean.getRemark())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("remark", request)%></font></td>
				</tr>


				 <tr>
                    <th></th>
                    <%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2">
					<input type="submit" name="operation" value="<%=WishlistCtl.OP_UPDATE%>">
					<input type="submit" name="operation" value="<%=WishlistCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2">
					<input type="submit" name="operation" value="<%=WishlistCtl.OP_SAVE%>">
					<input type="submit" name="operation" value="<%=WishlistCtl.OP_RESET%>">
						<%
							}
						%>
                </tr>
			</table>
		</div>
	</form>

</body>
</html>