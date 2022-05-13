<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="model.User"%>

<%
//Save---------------------------------
if (request.getParameter("userCode") != null)
{
User userObj = new User();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidIDSave") == "")
{
stsMsg = userObj.insertUser(request.getParameter("userCode"),
request.getParameter("userName"),
request.getParameter("userPnumber"),
request.getParameter("userGmail"));
}
else//Update----------------------
{
stsMsg = userObj.updateUser(request.getParameter("hidIDSave"),
request.getParameter("userCode"),
request.getParameter("userName"),
request.getParameter("userPnumber"),
request.getParameter("userGmail"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidIDDelete") != null)
{
User userObj = new User();
String stsMsg =
userObj.deleteUser(request.getParameter("hidIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>

<title>User Management</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-3">
        <div class="nauk-info-connections">
        </div>
     </div>
</div>
<h1>User Management</h1>

<form id="formUser" name="formUser" method="post" action="User.jsp">
User code:
<input id="userCode" name="userCode" type="text"
 class="form-control form-control-sm">
<br> User Name:
<input id="userName" name="userName" type="text"
 class="form-control form-control-sm">
<br> User phone number:
<input id="userPnumber" name="userPnumber" type="text"
 class="form-control form-control-sm">
<br> User Gmail:
<input id="userGmail" name="userGmail" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divUserGrid">
<%

User userObj = new User();
out.print(userObj.readUsers());
%>
</div>
</body>
</html>