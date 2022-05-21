package com.jannlouie;

import com.jannlouie.Config.MainDatabase;
import com.jannlouie.Config.Student;

import java.util.Scanner;

public class Classroom {
    private static char action;
    private static Scanner scanner = new Scanner(System.in);

    private static void displayMenu() {
        System.out.println("[SELECT ACTION]");
        System.out.println("[1] Add student\n[2] Remove student\n[3] View student data");
        System.out.println("[4] Return to previous stage");
    }

    public static void run() {
        displayMenu();

        do {
            System.out.print("Enter choice here: ");
            Classroom.action = scanner.next().charAt(0);
            scanner.nextLine();

            switch (Classroom.action) {
                case '1' -> MainDatabase.addStudentToRoot();
                case '2' -> MainDatabase.removeStudent();
                case '3' -> {
                    boolean repeat;
                    Student luckyStudent;
                    MainDatabase.showStudentsDatabase();

                    do {
                        System.out.print("Enter name of student: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter id of student: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        luckyStudent = MainDatabase.getStudentInfo(name, id);

                        if (luckyStudent != null) {
                            luckyStudent.showStudentData();
                            repeat = false;
                        } else {
                            System.out.println("Try again...");
                            repeat = true;
                        }
                    } while (repeat);
                }
            }
        } while (Classroom.action != '4');
    }
}
