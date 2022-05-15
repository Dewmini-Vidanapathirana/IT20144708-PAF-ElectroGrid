<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/payment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Payment Management</h1>
				<form action="Payment.jsp" id="formPay" name="formPay" method="post">
					Customer Name:
					<input id="cusName" name="cusName" type="text" class="form-control form-control-sm">
					<br>
					Customer Address:
					<input id="cusAddress" name="cusAddress" type="text" class="form-control form-control-sm">
					<br>
					Customer Account:
					<input id="cusAccount" name="cusAccount" type="text" class="form-control form-control-sm">
					<br>
					Time:
					<input id="time" name="time" type="text" class="form-control form-control-sm">
					<br>
					Date:
					<input id="date" name="date" type="text" class="form-control form-control-sm">
					<br>
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					 <input type="hidden" id="hididSave" name="hididSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divPayGrid">
					 <%
					 	Payment PayObj = new Payment();
					 	out.print(PayObj.readPay()); 
					 %>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>