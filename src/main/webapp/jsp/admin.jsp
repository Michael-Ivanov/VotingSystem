<%@ page import="java.util.List" %>
<%@ page import="users.User" %>
<%@ page import="votingsystem.Candidate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voting management</title>

    <style type="text/css">
        TABLE {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }

        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
            text-align: left;
        }

        TH {
            background: #b0e0e6; /* Цвет фона */
        }
    </style>
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
    String visibility = (String) request.getAttribute("visibility");
    if (visibility == null) {
        visibility = "hidden";
    }
%>

<h4>Hello, admin</h4>

<div>
    <table>
        <caption>Registered users</caption>
        <tr>
            <th>User name</th>
            <th>Login</th>
        </tr>
        <%
            for (User user : users) { %>
        <tr>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getLogin()%>
            </td>
        </tr>
        <% }%>
    </table>
</div>
<br>
<div>
    <table>
        <caption>Registered candidates</caption>
        <tr>
            <th>Candidate name</th>
            <th>Voices</th>
        </tr>
        <%
            for (Candidate candidate : candidates) { %>
        <tr>
            <td><%=candidate.getName()%>
            </td>
            <td><%=candidate.getVoices()%>
            </td>
        </tr>
        <% }%>
    </table>
</div>
<br>
<form method="get" action="/adminServlet">
    <button type="submit" name="button" value="newCandidate">New candidate</button>
</form>

<form method="post" action="/adminServlet" style="visibility: <%=visibility%>">
    <input type="text" name="name" value="name">
    <button type="submit" name="button" value="addCandidate">Add candidate</button>
</form>


</body>
</html>
