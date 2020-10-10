package votingsystem;

import userservice.Admin;
import userservice.Elector;
import userservice.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VotingSystem {

    private List<User> users;
    private Voting currentVoting;
    private User currentUser;

    public VotingSystem() {
        this.users = new ArrayList<>();
    }

    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        votingSystem.startMenu();
    }

    private void startMenu() {
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
                        if (!unique(login)) {
                            System.out.println("Login already in use. Please, choose another login.");
                        }
                    } while (!unique(login));
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    addUser(name, login, password);
                    break;
                case 2:
                    System.out.print("\nPlease enter login: ");
                    String userLogin = scanner.nextLine();
                    System.out.print("\nPlease enter password: ");
                    String userPassword = scanner.nextLine();
                    if ("admin".equals(userLogin) && "admin".equals(userPassword)) {
                        startAdminMenu();
                        break;
                    }
                    Elector elector = (Elector) findUser(userLogin, userPassword);
                    if (elector != null) {
                        if (!elector.isVoted()) {
                            elector.vote(currentVoting);
                        } else {
                            System.out.println(elector.getName() + " already voted.");
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

    private void addUser(String name, String login, String password) {
        users.add(new Elector(name, login, password));
    }

    private boolean unique(String login) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    private User findUser(String login, String password) {
        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    private void startAdminMenu() {
        Admin admin = new Admin("admin", "admin", "admin");
        while (true) {
            System.out.println("\nHello, admin.");
            System.out.println("1. Create new voting.");
            System.out.println("2. Check voting results.");
            System.out.println("3. Get list of electors.");
            System.out.println("4. Exit.");
            Scanner scanner = new Scanner(System.in);
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a correct number");
                continue;
            }
            switch (choice) {
                case 1:
                    currentVoting = admin.createVoting();
                    break;
                case 2:
                    admin.checkResults(currentVoting);
                    break;
                case 3:
                    for (User user : users) {
                        System.out.println(user);
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Please enter a correct number");
                    break;
            }


        }
    }

}
