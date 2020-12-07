package servlets;

import users.User;
import votingsystem.VotingSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login_servlet")
public class LoginServlet extends HttpServlet {

    VotingSystem votingSystem = VotingSystem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = votingSystem.findUser(login, password);

        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/user_voting.jsp");

        if (user == null) {
            dispatcher = req.getServletContext().getRequestDispatcher("/index.jsp");
        }

        if ("admin".equals(login) && "admin".equals(password)) {
            dispatcher = req.getServletContext().getRequestDispatcher("/admin_servlet");
        }

        HttpSession session = req.getSession(false);
        session.setAttribute("user", user);

        dispatcher.forward(req, resp);

    }
}
