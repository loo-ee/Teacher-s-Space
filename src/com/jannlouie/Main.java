package com.jannlouie;

import com.jannlouie.Apps.ClassRecord;
import com.jannlouie.Apps.ClassRoom;
import com.jannlouie.Apps.BettingGame.BettingGame;
import com.jannlouie.FileHandling.Login;
import com.jannlouie.FileHandling.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
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
                case '3' -> BettingGame.run(true);
                case '4' -> {
                    /*
                     In this method, the system verifies if the user is authorized to create admin
                     actions. If so, the system tries to delete files and if successful, the system
                     forcefully exits. Otherwise, the system will exit with an error code.
                    */
                    if (confirmAdminAction()) {
                        if (Files.deleteAllFiles()) {
                            System.exit(0);
                        } else {
                            System.exit(1);
                        }
                    } else {
                        System.exit(1);
                    }
                }
                case '5' -> {
                    String newPassword;
                    String confirmPassword;

                    if (confirmAdminAction()) {
                        while (true) {
                            System.out.print("\nEnter new password here: ");
                            newPassword = scanner.nextLine();
                            System.out.print("Confirm password here: ");
                            confirmPassword = scanner.nextLine();

                            if (Objects.equals(newPassword, confirmPassword)) {
                                changePassword(newPassword);
                                break;
                            } else {
                                System.out.println("[ERROR] Passwords do not match");
                                System.out.println("Please try again");
                            }
                        }
                    } else {
                        System.exit(1);
                    }
                }
                case '6' -> {
                    System.out.println("[INFO] Closing application");
                    Files.saveData();
                }
                default -> System.out.println("[INFO] You have entered an invalid choice");
            }
            System.out.println("___________________________________________________________");
        } while (choice != '6');
    }

    public static boolean confirmAdminAction() {
        String password;
        boolean isPermitted = false;
        int counter = 0;

        while (true) {
            if (counter == 5) {
                System.out.println("\n[YOU HAVE BEEN BLOCKED]");
                break;
            }

            System.out.print("Enter password to continue: ");
            password = scanner.nextLine();

            if (Login.validateLogin(password)) {
                isPermitted = true;
                break;
            } else {
                System.out.println("\n[ERROR] You have entered an incorrect password");
                System.out.println("You have " + (4 - counter) + " tries left.");
            }
            counter++;
        }
        return isPermitted;
    }

    private static void login() {
        System.out.println("TEACHER'S SPACE\n[ENTER PASSWORD]");

        try {
            Files.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (confirmAdminAction()) {
            System.out.println("\n[LOGIN SUCCESS]");
        } else {
            System.exit(1);
        }
    }

    private static void showHomePage() {
        System.out.println("\n[SELECT ACTION]");
        System.out.println("[1] Open class record\n[2] Open classroom\n[3] Play a game");
        System.out.println("[4] Reset program\n[5] Change password\n[6] Exit program");
    }

    private static void changePassword(String newPassword) throws IOException {
        FileWriter fileWriter;

        fileWriter = new FileWriter("Database\\password.txt");
        fileWriter.write(newPassword);
        fileWriter.close();

        Login.setPassword(newPassword);
        System.out.println("\n[INFO] Password was changed successfully");
    }
}
