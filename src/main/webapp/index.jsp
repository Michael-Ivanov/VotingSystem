<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="users.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Voting System</title>
</head>
<body>
<h1>Voting System</h1>
<%  User user = (User) request.getAttribute("existingUser");
    String promptLine = getLine(user);%>
<%!
    String getLine(User user) {
        if (user != null) {
            if (user.getName().equals("nobody")) {
                return "<h4 style=\"color: red\">Incorrect user login or password. Please try again.</h4>";
            }
        } else {
            return "<h4>Please log in</h4>";
        }
        return "";
    }
%>
<div><%=promptLine%></div>

<form method="post" action="/loginServlet">
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <br>
    <input type="submit" value="Login">
    <a href="jsp/user_registration.jsp"><h4>Register new user</h4></a>
</form>
</body>
</html>