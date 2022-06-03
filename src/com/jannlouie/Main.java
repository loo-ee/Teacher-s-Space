package com.jannlouie;

import com.jannlouie.Apps.ClassRecord;
import com.jannlouie.Apps.ClassRoom;
import com.jannlouie.Apps.Games.BettingGame.BettingGame;
import com.jannlouie.FileHandling.Login;
import com.jannlouie.FileHandling.Files;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static char choice;

    public static void main(String[] args) throws Exception {
        login();

        do {
            showHomePage();
            System.out.print("Enter choice here: ");
            Main.choice = scanner.next().charAt(0);
            scanner.nextLine();

            switch (choice) {
                case '1' -> ClassRecord.run();
                case '2' -> ClassRoom.run();
                case '3' -> playGames();
                case '4' -> {
                    String password;
                    int exitCode = 0;
                    int counter = 0;

                    while (true) {
                        if (counter == 5) {
                            System.out.println("\n[YOU HAVE BEEN BLOCKED]");
                            System.exit(exitCode);
                        }

                        System.out.print("Enter password to reset program: ");
                        password = scanner.nextLine();

                        if (Login.validateLogin(password)) {
                            if (Files.deleteAllFiles()) {
                                System.exit(exitCode);
                            }
                            else {
                                exitCode = 1;
                            }
                        } else {
                            System.out.println("\n[ERROR] You have entered an incorrect password");
                            System.out.println("You have " + (4 - counter) + " tries left.");
                        }
                        counter++;
                    }
                }
                case '5' -> {
                    System.out.println("[INFO] Closing application");
                    Files.saveData();
                }
                default -> System.out.println("[INFO] You have entered an invalid choice");
            }
            System.out.println("___________________________________________________________");
        } while (choice != '5');
    }

    public static void login() {
        String userInput;
        int counter = 0;
        boolean isVerified;

        System.out.println("TEACHER'S SPACE\n[ENTER PASSWORD]");

        try {
            Files.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            if (counter == 5) {
                System.out.println("[YOU HAVE BEEN BLOCKED!]");
                System.exit(1);
            }

            System.out.print("Enter password here: ");
            userInput = scanner.nextLine();

            isVerified = Login.validateLogin(userInput);
            counter++;

            if (!isVerified) {
                System.out.println("\n[INFO] You have entered an incorrect password");
                System.out.println("You have " + (5 - counter) + " tries left.");
            }
        } while (!isVerified);

        System.out.println("\n[LOGIN SUCCESS]");
    }

    private static void showHomePage() {
        System.out.println("\n[SELECT ACTION]");
        System.out.println("[1] Open class record\n[2] Open classroom\n[3] Play games");
        System.out.println("[4] Reset program\n[5] Exit program");
    }

    public static void playGames() throws Exception {
        char gameChoice;

        do {
            System.out.println("\n[SELECT ACTION]");
            System.out.println("[1] Betting game\n[2] \n[3] ");
            System.out.println("[4] Return to previous stage");
            System.out.print("Enter the number of the game you want to play: ");
            gameChoice = scanner.next().charAt(0);
            scanner.nextLine();

            switch (gameChoice) {
                case '1' -> BettingGame.run(true);
                case '4' -> System.out.println("[INFO] Returning to home page");
                default -> System.out.println("[INFO] You have entered an invalid choice");
            }
        } while (gameChoice != '4');
    }
}
