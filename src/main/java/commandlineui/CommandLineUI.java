package commandlineui;

import users.Admin;
import users.Elector;
import users.User;
import votingsystem.Candidate;
import votingsystem.Voting;
import votingsystem.VotingSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandLineUI {

    private Scanner scanner = new Scanner(System.in);

    private VotingSystem votingSystem;
    private List<User> users;
    private Admin admin;
    private Elector currentElector;

    public CommandLineUI(List<User> users) {
        this.users = users;
        admin = Admin.getInstance();
        votingSystem = VotingSystem.getInstance();
    }

    public void startMenu() {
        while (true) {
            System.out.println("\nHello. Voting System main menu.");
            System.out.println("1. Register new account.");
            System.out.println("2. Log in existing account.");
            System.out.println("3. Exit.");
            System.out.print("\nPlease make your choice: > ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a correct number.");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("\nRegistering new account.");
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter new login: ");
                    String login = getUniqueLogin(); // todo: переделать на findUser
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    int result = votingSystem.addUser(name, login, password);
                    if (result > 0) {
                        System.out.println("Processed " + result + " row");
                        System.out.println("New user " + name + " registered.");
                    } else {
                        System.out.println("Unexpected error happened. " +
                                "Result code for adding user operation: " + result);
                    }
                    break;
                case 2:
                    System.out.print("\nPlease enter login: ");
                    String userLogin = scanner.nextLine();
                    System.out.print("Please enter password: ");
                    String userPassword = scanner.nextLine();
                    User user = votingSystem.getLoggedUser(userLogin, userPassword);
                    defineUser(user);
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

    private void defineUser(User user) {
        if (user != null) {
            Elector elector = new Elector(user);
            if (user.getClass() == Admin.class) {
                startAdminMenu();
            } else {
                if (elector.isVoted()) {
                    System.out.println(user.getName() + " already voted.");
                } else {
                    Voting voting = votingSystem.getCurrentVoting();
                    votingUI(voting, elector);
                }
            }
        } else {
            System.out.println("No user with such name or password found.");
        }
    }

    //    Получение от пользователя корректного уникального нового логина для регистрации
    private String getUniqueLogin() {
        String login;
        while (true) {
            login = scanner.nextLine();
            if (votingSystem.isUnique(login)) {
                return login;
            } else {
                System.out.println("Login already in use. Please, choose another login.");
            }
        }
    }

    public void startAdminMenu() {
        while (true) {
            System.out.println("\nHello, admin.");
            System.out.println("1. Create new voting.");
            System.out.println("2. Check voting results.");
            System.out.println("3. Get list of electors.");
            System.out.println("4. Exit.");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a correct number");
                continue;
            }

            switch (choice) {
                case 1:
                    votingSystem.setCurrentVoting(createVoting());
                    break;
                case 2:
                    checkVotingResults(votingSystem.getCurrentVoting());
                    break;
                case 3:
                    List<User> users = votingSystem.getUsers();
                    if (users.size() == 0) {
                        System.out.println("No users registered yet");
                    }
                    for (User user : users) {
                        Elector elector = new Elector(user);
                        System.out.println(elector);
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

    public Voting createVoting() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a new voting title: ");
        String title = scanner.nextLine();
        List<Candidate> candidates = new ArrayList<>();
        String newCandidate = "";
        while (true) {
            System.out.print("Enter a new candidate(\"end\" to finish): ");
            newCandidate = scanner.nextLine();
            if ("end".equalsIgnoreCase(newCandidate)) {
                break;
            }
            candidates.add(new Candidate(newCandidate));
        }
        return new Voting(title, candidates);
    }

    public void checkVotingResults(Voting voting) {
        List<Candidate> candidates = voting.getCandidates();
        if (candidates.isEmpty()) {
            System.out.println("No results yet.");
            return;
        }
        System.out.println("Results for \"" + voting.getTitle() + "\"");
        int count = 1;
        for (Candidate candidate : candidates) {
            System.out.println(count + ". " + candidate);
            count++;
        }
    }

    private void votingUI(Voting voting, Elector elector) {
        if (voting == null) {
            System.out.println("No voting available.");
            return;
        }
        System.out.println("Voting \"" + voting.getTitle() + "\"");
        System.out.println("Please vote for one of the following candidates: ");
        List<Candidate> candidates = voting.getCandidates();
        int count = 1;
        for (Candidate candidate : candidates) {
            System.out.println(count + ". " + candidate.getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Your choice is " + candidates.get(choice - 1).getName() + ".");
        candidates.get(choice - 1).addVoice();
        elector.setVoted(true);
        System.out.println("Thanks for your voice.");
    }
}
// todo: сделать "press any key to continue..." после сообщений пользователю.
