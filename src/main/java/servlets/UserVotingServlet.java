package servlets;

import users.User;
import votingsystem.Candidate;
import votingsystem.Voting;
import votingsystem.VotingSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user_voting_servlet")
public class UserVotingServlet extends HttpServlet {

    VotingSystem votingSystem = VotingSystem.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = votingSystem.findUser(login, password);
        votingSystem.setCurrentUser(user);
        Voting voting = votingSystem.getCurrentVoting();
        List<Candidate> list = voting.getCandidates();


        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/user_voting.jsp");
        req.setAttribute("currentUser", user);
        req.setAttribute("candidates", list);
        dispatcher.forward(req, resp);
    }
}
