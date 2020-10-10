package userservice;

import votingsystem.Candidate;
import votingsystem.Voting;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User{

    public Admin(String name, String login, String password) {
        super(name, login, password);
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
