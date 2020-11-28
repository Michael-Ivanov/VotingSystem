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

@WebServlet("/login_servlet")
public class LoginServlet extends HttpServlet {

    VotingSystem votingSystem = VotingSystem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = votingSystem.findUser(login, password);

        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/user_voting_servlet");

        if (user == null) {
            // чтобы различать на index.jsp, пришел некорректный запрос или запроса не было
            // направляем на ту же страницу, возвращая специальное значение пользователя.
            dispatcher = req.getServletContext().getRequestDispatcher("/index.jsp");
            user = new User("nobody", "nobody", "nobody");
        }

        if ("admin".equals(login) && "admin".equals(password)) {
            dispatcher = req.getServletContext().getRequestDispatcher("/admin_servlet");
        }

//        req.setAttribute("existingUser", user);
        dispatcher.forward(req, resp);

    }
}
