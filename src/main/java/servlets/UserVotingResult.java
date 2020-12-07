package servlets;

import users.User;
import votingsystem.VotingSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/voting_result")
public class UserVotingResult extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String usersChoice = req.getParameter("users_choice");
        VotingSystem votingSystem = VotingSystem.getInstance();

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        votingSystem.setUserVoted(user, true);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Current user = " + user.getName() + "<br>");
        printWriter.write("User's choice is " + usersChoice + "<br>");

        //todo: Продолжить. Сделать, чтобы голос пользователя прибавлялся кандидату, и записывался в базу.

    }
}
