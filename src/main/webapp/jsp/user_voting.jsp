<%@ page import="users.User" %>
<%@ page import="votingsystem.VotingSystem" %>
<%@ page import="votingsystem.Voting" %>
<%@ page import="votingsystem.Candidate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    List<Candidate> candidates = Collections.emptyList();
    if (voting != null) {
        votingTitleLine = "It's voting for " + voting.getTitle() + ".";
        candidates = voting.getCandidates();
    }
%>
<h2>Hello, <%=user.getName()%>
</h2>
<h4><%=votingTitleLine%>
</h4>
<p>Candidates list size = <%=candidates.size()%></p>
<form action="user_voting_result" method="post">
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
