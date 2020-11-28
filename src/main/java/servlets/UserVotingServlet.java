package servlets;

import users.User;
import votingsystem.VotingSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_voting_servlet")
public class UserVotingServlet extends HttpServlet {

    VotingSystem votingSystem = VotingSystem.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = votingSystem.findUser(login, password);
        String userName = user.getName();

        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/user_voting.jsp");
        req.setAttribute("currentUser", user);
        dispatcher.forward(req, resp);
    }
}
