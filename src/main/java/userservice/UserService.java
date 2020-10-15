package userservice;

import users.Elector;
import users.User;

import java.util.List;

public class UserService {

    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public void addUser(String name, String login, String password) {
        users.add(new User(name, login, password));
    }

    public boolean unique(String login) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public User findUser(String login, String password) {
        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
