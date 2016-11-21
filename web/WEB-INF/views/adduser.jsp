<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Group 7: Create User</title>
</head>

<body>

<h2>Create a User</h2>
<br/>

<br/>
<form:form method="post" modelAttribute="user">
    <table>
        <tr>
            <td>ID:</td>
            <td>${nextid}</td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type="text" placeholder="First Name" id="firstname" name="firstname"></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" placeholder="Last Name" id="lastname" name="lastname"></td>
        </tr>
        <tr>
            <td>Title</td>
            <td><input type="text" placeholder="Title" id="title" name="title"></td>
        </tr>
        <tr>
            <td>Street</td>
            <td><input type="text" placeholder="Street" id="address.street" name="address.street"></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" placeholder="City" id="address.city" name="address.city"></td>
        </tr>
        <tr>
            <td>State</td>
            <td><input type="text" placeholder="State" id="address.state" name="address.state"></td>
        </tr>
        <tr>
            <td>Zip</td>
            <td><input type="text" placeholder="ZipCode" id="address.zip" name="address.zip"></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Create"/></td>
        </tr>
    </table>
    <br>
</form:form>
</body>
</html>