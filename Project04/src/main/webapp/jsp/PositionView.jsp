<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.PositionCtl"%>
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
	<form action="<%=ORSView.POSITION_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.PositionBean"
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
				Position
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
					<th>Designation :</th>
					<td><input type="text" name="designation"
						placeholder="Enter Designation Name"
						value="<%=DataUtility.getStringData(bean.getDesignation())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("designation", request)%></font></td>
				</tr>
				<tr>

					<th>Opening Date :</th>
					<td><input type="text" id="udate" name="openingDate"
						placeholder="Select Opening Date"
						value="<%=DataUtility.getDateString(bean.getOpeningDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("openingDate", request)%></font></td>
				</tr>


				<tr>
					<th>Required Experience:</th>
					<td><input type="text" name="requiredExperience"
						placeholder="Enter Experience"
						value="<%=DataUtility.getStringData(bean.getRequiredExperience())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("requiredExperience", request)%></font></td>
				</tr>
				<tr>
					<th>Condition:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Open", "Open");
							map.put("Closed", "Closed");
							map.put("OnHold", "OnHold");
		%> <%=HTMLUtility.getList("condition", bean.getCondition(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("condition", request)%></font></td>
				</tr>

 <tr>
                    <th></th>
                    <%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2">
					<input type="submit" name="operation" value="<%=PositionCtl.OP_UPDATE%>">
					<input type="submit" name="operation" value="<%=PositionCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2">
					<input type="submit" name="operation" value="<%=PositionCtl.OP_SAVE%>">
					<input type="submit" name="operation" value="<%=PositionCtl.OP_RESET%>">
						<%
							}
						%>
                </tr>
				 
			</table>
		</div>
	</form>

</body>
</html>