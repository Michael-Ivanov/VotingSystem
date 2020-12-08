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
    User user = (User) session.getAttribute("user");
    if (user.isVoted()) {
        response.sendRedirect("/voting_result");
    }

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

<p>Candidates list size = <%=candidates.size()%>
</p>
<div>
    <%
        request.getRequestDispatcher("/jsp/candidates.jsp").include(request, response);
    %>
</div>

</body>
</html>
