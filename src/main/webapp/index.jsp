<%@ page import="users.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Voting System</title>
</head>
<body>
<h1>Voting System</h1>
<%  User user = (User) request.getAttribute("existingUser");%>
<%!
    String getLine(User user) {
        if (user != null) {
            if (user.getName().equals("nobody")) {
                return "No such user. Please try again.";
            } else {
                return "Hello, " + user.getName();
            }
        } else {
            return "Please log in";
        }
    }
%>
<h4><%=getLine(user)%></h4>

<form method="post" action="/loginServlet">
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <br>
    <input type="submit" value="Login">
    <a href="user_registration.html"><h4>Register new user</h4></a>
</form>
</body>
</html>