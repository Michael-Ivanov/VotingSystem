package userservice;

import users.Admin;
import users.Elector;
import users.User;
import votingsystem.Voting;
import votingsystem.VotingSystem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainUI {

    private List<User> users;
    private UserService userService;
    private Admin admin;

    public MainUI(List<User> users) {
        this.users = users;
        userService = new UserService(users);
        admin = new Admin("admin", "admin", "admin");
    }

    public void startMenu() {
        while (true) {
            System.out.println("\nHello. It's voting system.\n");
            System.out.println("1. Register new account");
            System.out.println("2. Log in existing account");
            System.out.println("3. Exit");
            System.out.print("\nPlease make your choice: > ");
            Scanner scanner = new Scanner(System.in);
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a correct number");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("\nRegistering new account\n");
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter new login: ");
                    String login;
                    do {
                        login = scanner.nextLine();
                        if (!userService.unique(login)) {
                            System.out.println("Login already in use. Please, choose another login.");
                        }
                    } while (!userService.unique(login));
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    userService.addUser(name, login, password);
                    break;
                case 2:
                    System.out.print("\nPlease enter login: ");
                    String userLogin = scanner.nextLine();
                    System.out.print("\nPlease enter password: ");
                    String userPassword = scanner.nextLine();
                    if ("admin".equals(userLogin) && "admin".equals(userPassword)) {
                        admin.startAdminMenu();
                        break;
                    }
                    User user = userService.findUser(userLogin, userPassword);
                    if (user != null) {
                        Elector elector = new Elector(user);
                        if (elector.isVoted()) {
                            System.out.println(user.getName() + " already voted.");
                        } else {
                            Voting voting = VotingSystem.getInstance().getCurrentVoting();
                            elector.vote(voting);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }
}
