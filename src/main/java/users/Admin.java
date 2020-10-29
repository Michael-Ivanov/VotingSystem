package users;

import database.DBService;
import users.User;
import votingsystem.Candidate;
import votingsystem.Voting;
import votingsystem.VotingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    private final VotingSystem votingSystem;
    private static Admin admin;
    private final DBService dbService;

    private Admin() {
        super("admin", "admin", "admin");
        votingSystem = VotingSystem.getInstance();
        dbService = votingSystem.getDbService();
    }

    public static Admin getInstance() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }


}
