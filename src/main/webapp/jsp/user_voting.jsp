<%@ page import="users.User" %>
<%@ page import="votingsystem.VotingSystem" %>
<%@ page import="votingsystem.Voting" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Voting</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("currentUser");
    VotingSystem votingSystem = VotingSystem.getInstance();
    Voting voting = votingSystem.getCurrentVoting();
    String votingTitleLine = "No voting available currently.";
    if (voting != null) {
        votingTitleLine = "It's voting for "  + voting.getTitle() + ".";
    }
%>
<h2>Hello, <%=user.getName()%></h2>
<h4><%=votingTitleLine%></h4>
<form>
    <label for="can1">candidate 1 </label>
    <input type="radio" id="can1">
</form>
</body>
</html>
