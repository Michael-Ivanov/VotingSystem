<%@ page import="users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User registration</title>
</head>
<body>

<%
    User user = (User) request.getAttribute("regUser");
    Boolean unique = (Boolean) request.getAttribute("unique");
    String responseLine = "";
    String pointerEvents = "all";
    String visibility = "hidden";
    if (user != null) {
        visibility = "visible";
        if (unique) {
            responseLine = "New user " + user.getName() + " registered successfully.";
        } else {
            responseLine = "Login " + user.getLogin() + " is unavailable. Please choose another login.";
            pointerEvents = "none"; // блокируем ссылку на голосование
        }
    }
%>

<div style="padding-top: 50px; padding-left: 30px">
    <h1>New user registration</h1>

    <form method="post" action="user_registration_servlet">
        <label for="username">New user name:</label><br>
        <input type="text" id="username" name="username"><br>

        <label for="login">New user login:</label><br>
        <input type="text" id="login" name="login"><br>

        <label for="password">New user password:</label><br>
        <input type="password" id="password" name="password"><br>
        <br>
        <input type="submit" value="Register">
    </form>
</div>


<div style="visibility: <%=visibility%>; padding-top: 30px; padding-left: 30px">
    <h2><%=responseLine%></h2>
    <a href="/jsp/user_voting.jsp" style="pointer-events: <%=pointerEvents%>">Proceed to voting</a>
</div>
</body>
</html>