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

<h4>Please log in</h4>

<form method="post" action="login_servlet">
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <br>
    <input type="submit" value="Login">
    <a href="user_registration_servlet"><h4>Register new user</h4></a>
</form>
</body>
</html>