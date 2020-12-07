package votingsystem;

import database.DBService;
import users.Admin;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class VotingSystem {

    private static VotingSystem votingSystem;
    private List<User> users;
    private Voting currentVoting;
    private DBService dbService;

    private VotingSystem() {
        dbService = new DBService();
        this.users = getUsersListFromDB();

        currentVoting = new Voting("The best male actor ever", getCandidatesFromDB()); /*временная заглушка*/
//        dbService.printConnectionInfo();  // Проверка подключения к базе
    }

    //    Получение экземпляра VotingSystem
    public static VotingSystem getInstance() {
        if (votingSystem == null) {
            votingSystem = new VotingSystem();
        }
        return votingSystem;
    }

    public DBService getDbService() {
        return dbService;
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
//        обновление ссписка пользователей в памяти
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
        return null;  //todo: переделать, чтобы не возвращало null
    }

    public void setUserVoted(User user, boolean isVoted) {
        if (user != null) {
            dbService.setUserVoted(user, isVoted);
            user.setVoted(isVoted);
        }
    }


    private List<Candidate> getCandidatesFromDB() {
        return dbService.getCandidatesListFromDB();
    }
}
