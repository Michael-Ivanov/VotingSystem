package users;

public class User {

    private String name;
    private String login;
    private String password;
    private boolean isVoted;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        isVoted = false;
    }

    public User(String name, String login, String password, boolean isVoted) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.isVoted = isVoted;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isVoted=" + isVoted +
                '}';
    }
}
