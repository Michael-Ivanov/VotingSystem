package servlets;

import database.DBService;
import users.User;
import votingsystem.Candidate;
import votingsystem.VotingSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin_servlet")
public class AdminServlet extends HttpServlet {

    private VotingSystem votingSystem;
    private DBService dbService;
    private List<User> users;
    private List<Candidate> candidates;


    @Override
    public void init() throws ServletException {
        votingSystem = VotingSystem.getInstance();
        dbService = votingSystem.getDbService();
        users = votingSystem.getUsers();
        candidates = votingSystem.getCurrentVoting().getCandidates();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pressedButton = req.getParameter("button");

        if ("newCandidate".equals(pressedButton)) {
            req.setAttribute("visibility", "visible");
        }

        req.setAttribute("users", users);
        req.setAttribute("candidates", candidates);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/admin.jsp");
        dispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("users", users);
        req.setAttribute("candidates", candidates);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/admin.jsp");
        dispatcher.forward(req, resp);
    }
}
