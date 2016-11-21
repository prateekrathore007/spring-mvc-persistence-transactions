<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group 7: Index Page</title>
</head>
<body>
Group 7 Lab 2 Index Page<br>
<br>
Following endpoints are available:<br>
<br>
<b>GET:</b><br>
/user<br>
/user/{userid}<br>
/user/{userid}?json=true<br>
/phone<br>
/phone/{phoneid}<br>
/phone/{phoneid}?json=true<br>
<br>
<b>POST:</b><br>
/user<br>
/user/{userid}<br>
Example: /user/1?firstname=XX&lastname=XX&title=XX&street=XX&city=XX&state=XX&zip=XX<br>
<br>
/user/userId<br>
Example: /user/userId?firstname=XX&lastname=XX&title=XX&street=XX&city=XX&state=XX&zip=XX<br>
<br>
/phone<br>
/phone/phoneId<br>
Example: /phone/phoneId?number=11&description=XX&street=XX&city=XX&state=XX&zip=XX&users[]=1&users[]=2<br>
<br>
/phone/{phoneid}<br>
<br>
<b>DELETE:</b><br>
/user/{userid}<br>
/phone/{phoneid}<br>
</body>
</html>
