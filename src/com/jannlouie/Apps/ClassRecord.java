package com.jannlouie.Apps;

import com.jannlouie.Config.MainDatabase;
import com.jannlouie.Config.Student;
import com.jannlouie.FileHandling.Files;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class ClassRecord {
    private static final Scanner scanner = new Scanner(System.in);

    private static void displayMenu() {
        System.out.println("\n[SELECT ACTION]");
        System.out.println("[1] Add student\n[2] Remove student\n[3] View student data");
        System.out.println("[4] Delete all student records\n[5] Return to previous stage");
    }

    public static void run() throws IOException {
        char action;

        do {
            displayMenu();
            System.out.print("Enter choice here: ");
            action = scanner.next().charAt(0);
            scanner.nextLine();

            switch (action) {
                case '1' -> MainDatabase.addStudentToRoot();
                case '2' -> MainDatabase.removeStudent();
                case '3' -> {
                    boolean repeat;
                    Student luckyStudent;
                    MainDatabase.showStudentsDatabase();

                    do {
                        System.out.println("\nPress [R] to return to previous page");
                        System.out.print("Enter name of student: ");
                        String name = scanner.nextLine();

                        if (name.toLowerCase(Locale.ROOT).equals("r")) {
                            System.out.println("\n[INFO] Returning to previous page");
                            break;
                        }

                        System.out.print("Enter id of student: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        luckyStudent = MainDatabase.getStudentInfo(name, id);

                        if (luckyStudent != null) {
                            System.out.println("\n[SHOWING STUDENT DATA]");
                            luckyStudent.showStudentData();
                            repeat = false;
                        } else {
                            System.out.println("Try again...");
                            repeat = true;
                        }
                    } while (repeat);
                }
                case '4' -> {
                    try {
                        Files.clearAllStudentRecords();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case '5' -> System.out.println("[INFO] Returning to main page");
                default -> System.out.println("[INFO] You have entered an invalid choice");
            }
        } while (action != '5');
    }
}
