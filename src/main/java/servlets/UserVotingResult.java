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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String usersChoice = req.getParameter("users_choice");
        VotingSystem votingSystem = VotingSystem.getInstance();

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Current user = " + user.getName() + "<br>");

        if (!user.isVoted() && usersChoice != null) {
//            Если голос юзера ранее не был уже учтен, добавляем его голос.
            votingSystem.getDbService().addVoiceToCandidate(usersChoice);
            votingSystem.setUserVoted(user, true);
            printWriter.write("You voted successfully. Your choice is " + usersChoice + ".<br>");
        } else {
            printWriter.write("You already voted. ");
        }
        req.getRequestDispatcher("/jsp/voting_results.jsp").include(req, resp);
        //todo: не показывает новый результат. исправить. попробовать передавать лист через сервлет.

    }
}
