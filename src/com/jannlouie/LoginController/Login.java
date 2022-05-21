package com.jannlouie.LoginController;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.Objects;

public class Login {
    // TODO -> Delete 'isFirstRun' when not needed
    private static boolean isFirstRUn = true;
    private static String password;

    public static void createFiles() {

        try {
            File file;
            file = new File("Database\\verification.txt");

            if (!file.isFile()) {
                FileWriter fileWriter = new FileWriter("Database\\verification.txt");
                fileWriter.append("Verified");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\password.txt");
                fileWriter.append("default");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\current student number.txt");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\from start log.txt");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\students.txt");
                fileWriter.close();

                System.out.println("\n[Password is \"default\"]\n");
            } else {
                file = new File("Database\\password.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                Login.password = bufferedReader.readLine();
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validateLogin(String userInput) {
        return Objects.equals(userInput, password);
    }

    public static void setFirstRunStatus(boolean status) {
        Login.isFirstRUn = status;
    }

    public static boolean getFirstRunStatus() {
        return Login.isFirstRUn;
    }
}
