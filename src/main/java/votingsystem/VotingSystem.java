package votingsystem;

import users.Admin;
import users.Elector;
import users.User;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

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
        new UI(users).startMenu();
    }

//    Получение экземпляра VotingSystem
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

//    Добавление нового пользователя
    public void addUser(String name, String login, String password) {
        users.add(new Elector(name, login, password));
    }

//    Проверка добавляемого логина на уникальность
    public boolean isUnique(String login) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

//    Поиск пользователя по логину и паролю
    public User findUser(String login, String password) {
        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

//    Получение экземпляра пользователя по логину и паролю
    public User getLoggedUser(String login, String password) {
        if (login.length() > 0 && password.length() > 0) {
            if ("admin".equals(login) && "admin".equals(password)) {
                return Admin.getInstance();
            } else {
                return findUser(login, password);
            }
        }
        return null;
    }


}
