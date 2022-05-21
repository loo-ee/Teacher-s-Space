package com.jannlouie;

import com.jannlouie.LoginController.Login;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        login();
        Classroom.run();
    }

    public static void login() {
        String userInput;
        int counter = 0;
        boolean isVerified;

        if (Login.getFirstRunStatus()) {
            System.out.println("TEACHER'S SPACE\n[ENTER PASSWORD]");
            Login.createFiles();

            do {
                if (counter == 5) {
                    System.out.println("[YOU HAVE BEEN BLOCKED!]");
                    System.exit(1);
                }

                System.out.print("Enter password here: ");
                userInput = scanner.nextLine();

                isVerified = Login.validateLogin(userInput);
                counter++;
            } while (!isVerified);

            System.out.println("[LOGIN SUCCESS]");

        }
    }
}
