<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Group 7: Get Phone as JSON page</title>
</head>

<body>

<h2>Phone Details Json</h2>
<br/>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Number</td>
        <td>Description</td>
        <td>Address: Street</td>
        <td>Address: City</td>
        <td>Address: State</td>
        <td>Address: Zip</td>
    </tr>
    <c:forEach items="${phonedetails}" var="ph">
        <tr>
            <td>${ph.id}</td>
            <td>${ph.numb}</td>
            <td>${ph.description}</td>
            <td>${ph.address.street}</td>
            <td>${ph.address.city}</td>
            <td>${ph.address.state}</td>
            <td>${ph.address.zip}</td>
        </tr>
    </c:forEach>
</table>

<script src="https://code.jquery.com/jquery-2.2.2.js"></script>
<script type="text/javascript">
    if(${$isJson} = "true") {

    } else {

    }
    </script>

</body>
</html>