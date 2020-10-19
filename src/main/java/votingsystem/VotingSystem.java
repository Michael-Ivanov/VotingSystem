package votingsystem;

import database.DBService;
import ui.UI;
import users.Admin;
import users.User;

import java.util.List;

public class VotingSystem {

    private static VotingSystem votingSystem;
    private List<User> users;
    private Voting currentVoting;
    private DBService dbService;

    public VotingSystem() {
        dbService = new DBService();
        this.users = getUsersListFromDB();
//        dbService.printConnectionInfo();  // Проверка подключения к базе
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

    private List<User> getUsersListFromDB() {
        return dbService.getUsersListFromDB();
    }

    public List<User> getUsers() {
        return users;
    }

    //    Добавление нового пользователя
    public int addUser(String name, String login, String password) {
        int result = dbService.addUserToDB(name, login, password);
        users = getUsersListFromDB();
        return result;
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
