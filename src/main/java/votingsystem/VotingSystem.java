package votingsystem;

import users.Admin;
import users.Elector;
import users.User;
import userservice.MainUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VotingSystem {

    private static VotingSystem votingSystem;
    private List<User> users;
    private Voting currentVoting;
    private User currentUser;

    public VotingSystem() {
        this.users = new ArrayList<>();
    }

    public static void main(String[] args) {
        getInstance().starter();
    }

    private void starter() {
        new MainUI(users).startMenu();
    }

    public static VotingSystem getInstance() {
        if (votingSystem == null) {
            votingSystem = new VotingSystem();
        }
        return votingSystem;
    }

    public Voting getCurrentVoting() {
        return currentVoting;
    }

    public void setCurrentVoting(Voting currentVoting) {
        this.currentVoting = currentVoting;
    }

    public List<User> getUsers() {
        return users;
    }
}
