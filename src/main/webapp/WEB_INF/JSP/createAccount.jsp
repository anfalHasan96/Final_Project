<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@ page  language="java"  contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <title>register</title>
</head>
<body>

<a href="/hotel/home"><button>back</button></a>
<form action="/hotel/customer/createCustomer">
<table>
    <tr>First Name <input type="text" id="firstName"></tr>
    <tr>last Name <input type="text" id="lastName"></tr>
    <tr>email <input type="text" id="email"></tr>
    <tr>Phone Number <input type="text" id="phoneNumber"></tr>
    <tr>UserName <input type="text" id="userName"></tr>
    <tr>Password <input type="text" id="password"></tr>
</table>
<input type="submit" title="create" >
</form>

</body>
</html>