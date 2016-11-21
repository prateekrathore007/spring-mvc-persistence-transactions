<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Group 7: View User</title>
<script src="https://code.jquery.com/jquery-2.2.2.js"></script>
<style>
    #updateuserform{
        background: -webkit-linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
        background: -moz-linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
        background: linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
        margin: auto;
        position: relative;
        width: 450px;
        height: 300px;
        font-family: Tahoma, Geneva, sans-serif;
        font-size: 14px;
        font-style: italic;
        line-height: 24px;
        font-weight: bold;
        color: #09C;
        text-decoration: none;
        border-radius: 10px;
        padding: 10px;
        border: 1px solid #999;
        border: inset 1px solid #333;
        -webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
        -moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
    }
    #deleteUser {
        width: 150px;
        position: absolute;
        height: 250px;
        background: #ff0000;
        color: #fff;
        text-align: center;
        margin-top: 27%;
        bottom: 200%;
        left: 37%;
        font-family: Tahoma, Geneva, sans-serif;
        top: 55px;
        height: 30px;
        border-radius: 15px;
        border: 1 p solid #999;
    }
    #updateUser {
        width: 150px;
        position: absolute;
        height: 250px;
        background: #0000FF;
        color: #fff;
        text-align: center;
        margin-top: 45%;
        left: 58%;
        font-family: Tahoma, Geneva, sans-serif;
        top: 138px;
        height: 30px;
        border-radius: 15px;
        border: 1 p solid #999;
    }
    input.button:hover {
        background: #fff;
        color: #0000FF;
    }
</style>
<body>
<h1>User details for ID ${userdetails.id}</h1>
<form:form id="updateuserform" method="post" modelAttribute="user">
    <label>
        <span>ID</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${userdetails.id}<br>
    </label>
    <label>
        <span>First Name</span>&nbsp;&nbsp;&nbsp;<input type="text" placeholder="First Name" id ="firstname" name="firstname" value='${userdetails.firstname}'><br>
    </label>
    <label>
        <span>Last Name</span>&nbsp;&nbsp;&nbsp;<input type="text" placeholder="Last Name" id ="lastname" name="lastname" value='${userdetails.lastname}'><br>
    </label>
    <label>
        <span>Title</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="Title" id ="title" name="title" value='${userdetails.title}'><br>
    </label>
    <label>
        <span>City</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="City" id ="address.city" name="city" value='${userdetails.address.city}'><br>
    </label>
    <label>
        <span>State</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="State" id ="address.state" name="state" value='${userdetails.address.state}'><br>
    </label>
    <label>
        <span>Street</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="Street" id ="address.street" name="street" value='${userdetails.address.street}'><br>
    </label>
    <label>
        <span>Zip</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="ZipCode" id ="zip" name="zip" value='${userdetails.address.zip}'>
    </label>
    <br>
    <c:forEach items="${userdetails.phones}" var="ph" varStatus="i">
        <label>
            <span>Phone Number ${ph.id}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ph.numb}<br>
        </label>
    </c:forEach>
    <br>
    <button  type="submit" id="updateUser" title="Update" name ="Update" value="Update">Update</button>
</form:form>
<form:form id="deleteuserform" method="DELETE" action="${pageContext.request.contextPath}/user/${userdetails.id}">
    <button type="submit" id ="deleteUser" title="Delete" name ="Delete" value="Delete">Delete</button>
</form:form>
</body>
</html>