package userservice;

import votingsystem.Candidate;
import votingsystem.Voting;

import java.util.List;
import java.util.Scanner;

public class Elector extends User {

    private boolean voted;

    public Elector(String name, String login, String password) {
        super(name, login, password);
    }

    public boolean isVoted() {
        return voted;
    }

    public void vote(Voting voting) {
        if (voting == null) {
            System.out.println("Sorry, nothing to vote for yet");
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
        System.out.println("Your choice is " + candidates.get(choice - 1).getName());
        candidates.get(choice - 1).addVoice();
        voted = true;
    }

    @Override
    public String toString() {
        return getName() + ": \n" +
                "login: " + getLogin() + ". isVoted: " + isVoted();
    }
}
