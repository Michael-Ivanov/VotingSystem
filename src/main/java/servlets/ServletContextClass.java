package servlets;

import votingsystem.Voting;
import votingsystem.VotingSystem;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextClass implements ServletContextListener {

    private VotingSystem votingSystem;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        votingSystem = VotingSystem.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        votingSystem.getDbService().closeConnectionToDB();
    }
}
