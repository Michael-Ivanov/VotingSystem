package users;

import users.User;
import votingsystem.Candidate;
import votingsystem.Voting;
import votingsystem.VotingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    private VotingSystem votingSystem;

    public Admin(String name, String login, String password) {
        super(name, login, password);
        votingSystem = VotingSystem.getInstance();
    }

    public void startAdminMenu() {
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
                    votingSystem.setCurrentVoting(createVoting());
                    break;
                case 2:
                    checkResults(votingSystem.getCurrentVoting());
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
        while(true) {
            System.out.print("Enter a new candidate(\"end\" to finish): ");
            newCandidate = scanner.nextLine();
            if ("end".equalsIgnoreCase(newCandidate)) {
                break;
            }
            candidates.add(new Candidate(newCandidate));
        }
        return new Voting(title, candidates);
    }

    public void checkResults(Voting voting) {
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
}
