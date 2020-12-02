package servlets;

import votingsystem.VotingSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user_voting_result")
public class UserVotingResult extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usersChoice = req.getParameter("users_choice");
        VotingSystem votingSystem = VotingSystem.getInstance();

        votingSystem.setCurrentUserVoted(true);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Current user = " + votingSystem.getCurrentUser().getName() + "<br>");

        //todo: Продолжить. Сделать, чтобы голос пользователя прибавлялся кандидату, и записывался в базу.

    }
}
