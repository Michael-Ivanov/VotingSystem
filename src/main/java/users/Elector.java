package users;

import users.User;
import votingsystem.Candidate;
import votingsystem.Voting;

import java.util.List;
import java.util.Scanner;

public class Elector extends User {

    private boolean voted; // todo: Не работает флаг. Требует рефакторинга.

    public Elector(String name, String login, String password) {
        super(name, login, password);
    }

    public Elector(User user) {
        super(user.getName(), user.getLogin(), user.getPassword());
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    @Override
    public String toString() {
        return getName() + ": \n" +
                "login: " + getLogin() + ". isVoted: " + isVoted();
    }
}
