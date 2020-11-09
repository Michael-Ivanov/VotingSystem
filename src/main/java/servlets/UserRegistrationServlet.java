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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user_registration_servlet")
public class UserRegistrationServlet extends HttpServlet {

    private VotingSystem votingSystem = VotingSystem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String correctness = checkCorrectInput(name, login, password); // todo: доделать, чтобы проверяло на нулевые значения ввода


        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/user_registration.jsp");

        User user = new User(name, login, password);
        Boolean unique = false;

        if (votingSystem.isUnique(login)) {
            votingSystem.addUser(name, login, password);
            unique = true;
        }
        req.setAttribute("unique", unique);
        req.setAttribute("regUser", user);
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/user_registration.jsp");
        dispatcher.forward(req, resp);
    }

    private String checkCorrectInput(String name, String login, String password) {
        List<String> errors = new ArrayList<>();
        if (name.trim().length() == 0) {
            errors.add("Name field is empty");
        }
        if (login.trim().length() == 0) {
            errors.add("Login field is empty");
        }
        if (password.trim().length() == 0) {
            errors.add("Password field is empty");
        }
        StringBuilder result = new StringBuilder();
        int size = errors.size();
        int count = 1;
        if (size > 0) {
            for (String error : errors) {
                if (count == size) {
                    result.append(error).append(".");
                } else {
                    result.append(error).append(", ");
                }
            }
        }
        return result.toString();
    }
}
