<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Group 7: Create a Phone Page</title>
</head>

<body>

<h2>Create a phone</h2>
<br/>

<br/>
<form:form method="post" modelAttribute="phone">
    <table>
        <tr>
            <td>ID:</td>
            <td>${nextid}</td>
        </tr>
        <tr>
            <td>Phone Number</td>
            <td><form:input path="numb"/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td>Phone Address Street</td>
            <td><form:input path="address.street"/></td>
        </tr>
        <tr>
            <td>Phone Address City</td>
            <td><form:input path="address.city"/></td>
        </tr>
        <tr>
            <td>Phone Address State</td>
            <td><form:input path="address.state"/></td>
        </tr>
        <tr>
            <td>Phone Address Zip</td>
            <td><form:input path="address.zip"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Create"/></td>
        </tr>
    </table>
    <br>
</form:form>
</body>
</html>