<%@ page import="votingsystem.VotingSystem" %>
<%@ page import="votingsystem.Voting" %>
<%@ page import="votingsystem.Candidate" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 08.12.2020
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidates</title>
</head>
<body>
<%
    VotingSystem votingSystem = VotingSystem.getInstance();
    Voting voting = votingSystem.getCurrentVoting();
    List<Candidate> candidates = voting.getCandidates();
%>

<form action="voting_result" method="post">
    <p>Please select one of the following candidates: </p>
    <%
        int count = 0;
        for (Candidate candidate : candidates) {
            count++;
    %>
    <label for="can<%=count%>"><%=candidate.getName()%></label>
    <input type="radio" id="can<%=count%>" name="users_choice" value="<%=candidate.getName()%>">
    <br>
    <% }
    %>
    <button type="submit">Submit</button>
</form>

</body>
</html>
