<%@page import="in.co.rays.ctl.PatientCtl"%>
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
	<form action="<%=ORSView.PATIENT_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.PatientBean"
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
				Patient
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
					<th>Name Of Patient:</th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th>Date Of Visit :</th>
					<td><input type="text" id="udate" name="dateOfVisit"
						placeholder="Select Date Of Visit"
						value="<%=DataUtility.getDateString(bean.getDateOFVisit())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dateOfVisit", request)%></font></td>
				</tr>


				<tr>
					<th>Mobile No :</th>
					<td><input type="text" name="mobile"
						placeholder="Enter Mobile No"
						value="<%=DataUtility.getStringData(bean.getMobile())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font></td>
				</tr>
				<tr>
					<th>Disease :</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Malaria", "Malaria");
							map.put("TyphoidFever", "TyphoidFever");
							map.put("Chikungunya", "Chikungunya");
							map.put("Influenza", "Influenza");
							map.put("Pneumonia", "Pneumonia");
						%> <%=HTMLUtility.getList("disease", bean.getDisease(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("disease", request)%></font></td>
				</tr>


				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PatientCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=PatientCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PatientCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=PatientCtl.OP_RESET%>">
						<%
							}
						%>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>