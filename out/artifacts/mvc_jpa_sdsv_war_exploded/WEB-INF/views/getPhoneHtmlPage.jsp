<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Group 7: View Phone</title>
<script src="https://code.jquery.com/jquery-2.2.2.js"></script>
<style>
    #updateuserviewform {
        background: -webkit-linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
        background: -moz-linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
        background: linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
        margin: auto;
        position: relative;
        width: 450px;
        height: 350px;
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
    #deletePhone {
        width: 150px;
        position: absolute;
        height: 250px;
        background: #ff0000;
        color: #fff;
        text-align: center;
        margin-top: 30%;
        bottom: 200%;
        left: 37%;
        font-family: Tahoma, Geneva, sans-serif;
        top: 60px;
        height: 30px;
        border-radius: 15px;
        border: 1 p solid #999;
    }
    #updatePhone {
        width: 150px;
        position: absolute;
        height: 250px;
        background: #0000FF;
        color: #fff;
        text-align: center;
        margin-top: 55%;
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
<h1>Phone details for phone ID ${phone.id}</h1>
<form:form id="updateuserviewform" method="post" modelAttribute="phone">
    <label>
        <span>ID</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${phone.id}<br>
    </label>
    <label>
        <span>Number</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
                                                                                  placeholder="Unique Phone Number"
                                                                                  id="number" name="number"
                                                                                  value='${phone.numb}'><br>
    </label>
    <label>
        <span>Description</span>&nbsp;&nbsp;<input type="text" placeholder="Description" id="description"
                                                   name="description" value='${phone.description}'><br>
    </label>
    <label>
        <span>City</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
            type="text" placeholder="City" id="address.city" name="city" value='${phone.address.city}'><br>
    </label>
    <label>
        <span>State</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
            type="text" placeholder="State" id="address.state" name="state" value='${phone.address.state}'><br>
    </label>
    <label>
        <span>Street</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
                                                                                                    placeholder="Street"
                                                                                                    id="address.street"
                                                                                                    name="street"
                                                                                                    value='${phone.address.street}'><br>
    </label>
    <label>
        <span>Zip</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
            type="text" placeholder="ZipCode" id="address.zip" name="zip" value='${phone.address.zip}'><br>
    </label>
    <c:choose>
        <c:when test="${phone.userList.size()>0}">
            <tr>User List:</tr>
            <c:forEach items="${phone.userList}" var="phoneuserobj">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="viewonlyuseridname" readonly
                                                       value='${phoneuserobj.id}  ${phoneuserobj.firstname}'/></td>
                </tr>
                <br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
            &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="adduser" value="Add new User's ID:" onclick="addTextBox()"/>
            &nbsp;&nbsp;<input type="button" id="removeuser" value="Remove Existing User's ID:" onclick="removeTextBox()"/>
        </c:when>
        <c:otherwise>
            <br>
            No users currently mapped<br>
            &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="adduser" value="Add new User's ID:" onclick="addTextBox()"/>
            &nbsp;&nbsp;<input type="button" id="removeuser" value="Remove Existing User's ID:" onclick="removeTextBox()"/>
        </c:otherwise>
    </c:choose>
    <script type="text/javascript">
        function addTextBox() {
            var form = document.getElementById('updateuserviewform');
            var temp = "Available User IDs: ";
            <c:forEach items="${userIdsList}" var="userid">
            temp += ${userid}+', '
            </c:forEach>
            form.appendChild(document.createElement('div')).innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' style='width: 200px' placeholder= temp id='useridname' name='useridname'/> Add 1 ID at a time.";
            document.getElementById("useridname").placeholder = temp;
            document.getElementById('adduser').disabled = true;
        }
        function removeTextBox() {
            var form = document.getElementById('updateuserviewform');
            var temp = "Remove User IDs: ";
            <c:forEach items="${phone.userList}" var="phoneuserobj1">
            temp += ${phoneuserobj1.id}+', '
            </c:forEach>
            form.appendChild(document.createElement('div')).innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' style='width: 200px' placeholder= temp id='useridnameremoval' name='useridnameremoval'/> Remove 1 ID at a time.";
            document.getElementById("useridnameremoval").placeholder = temp;
            document.getElementById('removeuser').disabled = true;
        }
    </script>
    <button type="submit" id="updatePhone" title="Update" name="Update" value="Update">Update</button>
</form:form>
<form:form id="deletephoneform" method="DELETE" action="${pageContext.request.contextPath}/phone/${phone.id}">
    <button type="submit" id="deletePhone" value="delete">Delete</button>
</form:form>
</body>
</html>