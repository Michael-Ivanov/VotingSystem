package ui;

import java.util.Scanner;

public class UserInterface {

    public void startMenu() {
        System.out.println("Hello. It's voting system.");
        System.out.println("1. Register new account");
        System.out.println("2. Log in existing account");
        System.out.println("3. Exit");
        System.out.print("Please make your choice: > ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("Incorrect input");
                startMenu();
                break;
        }
    }
}
