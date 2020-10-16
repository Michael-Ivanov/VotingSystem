package users;

import users.User;
import votingsystem.Candidate;
import votingsystem.Voting;
import votingsystem.VotingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    private VotingSystem votingSystem;
    private static Admin admin;

    private Admin() {
        super("admin", "admin", "admin");
        votingSystem = VotingSystem.getInstance();
    }

    public static Admin getInstance() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }

    public Voting createVoting() {
        return null;
    }

    public void checkVotingResults() {

    }






}
