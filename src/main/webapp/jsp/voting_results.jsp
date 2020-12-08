<%@ page import="votingsystem.VotingSystem" %>
<%@ page import="votingsystem.Voting" %>
<%@ page import="votingsystem.Candidate" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        caption {
            border: 1px solid #dddddd;
            padding: 8px;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<%!
    VotingSystem votingSystem;
%>
<%
    votingSystem = VotingSystem.getInstance();
    Voting voting = votingSystem.getCurrentVoting();
    List<Candidate> candidates = voting.getCandidates();
%>
<table>
    <caption>Voting results</caption>
    <tr>
        <th>Candidate</th>
        <th>Votes</th>
    </tr>
    <%
        for (Candidate candidate : candidates) { %>
    <tr>
        <td><%=candidate.getName()%>
        </td>
        <td><%=candidate.getVoices()%>
        </td>
    </tr>
    <%}%>

</table>

</body>
</html>
