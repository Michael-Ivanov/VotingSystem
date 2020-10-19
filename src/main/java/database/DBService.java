package database;

import users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class DBService {

    private final Connection connection;

    public DBService() {
        connection = getPostgresConnection();
    }

    private Connection getPostgresConnection() {
        String dbUrl = "jdbc:postgresql://localhost/users";
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "postgres");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void printConnectionInfo() {
        try {
            System.out.println("Connection: ");
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsersListFromDB() {
        List<User> users = new ArrayList<>(Collections.emptyList());
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String login = resultSet.getString(3);
                String password = resultSet.getString(4);
                users.add(new User(name, login, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public int addUserToDB(String name, String login, String password) {
        String sql = "INSERT INTO users (name, login, password)" +
                " VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
