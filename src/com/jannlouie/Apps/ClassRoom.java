package com.jannlouie.Apps;

import com.jannlouie.Config.Exam;
import com.jannlouie.Config.LinkedList;
import com.jannlouie.Config.MainDatabase;
import com.jannlouie.Config.Student;
import com.jannlouie.FileHandling.Files;
import java.util.Scanner;

public class ClassRoom {
    private static LinkedList<Exam> examLogs = new LinkedList<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static int currentExamNumber;

    public static void run() {
        char choice;

        do {
            displayMenu();
            System.out.print("Enter choice here: ");
            choice = scanner.next().charAt(0);
            scanner.nextLine();

            switch (choice) {
                case '1' -> giveTask();
                case '2' -> assignScores();
                case '3' -> showExamLog();
                case '4' -> showStudentsGrades();
                case '5' -> {
                    char deleteRecordsChoice;

                    System.out.println("\nPress [D] to delete all exam records\nPress any other key to return");
                    System.out.print("Enter choice here: ");
                    deleteRecordsChoice = scanner.next().charAt(0);
                    scanner.nextLine();

                    if (deleteRecordsChoice == 'D' || deleteRecordsChoice == 'd') {
                        try {
                            Files.clearExamRecords();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("\n[INFO] Exam records were deleted successfully");
                }
                case '6' -> System.out.println("[Returning to previous page]");
                default -> System.out.println("[ERROR] You have entered an invalid input!");
            }
        } while (choice != '6');
        System.out.println();
    }

    private static void displayMenu() {
        System.out.println("\n[SELECT ACTION]");
        System.out.println("[1] Give tasks\n[2] Assign scores\n[3] Show exam log");
        System.out.println("[4] Show students' grades\n[5] Clear all exam records");
        System.out.println("[6] Return to previous stage");
    }

    private static void giveTask() {
        System.out.println("[INFO] Students are now doing their tasks...");
    }

    private static void assignScores() {
        float score;
        float maxScore;
        Student student;

        while (true) {
            try {
                System.out.print("\nEnter maximum score: ");
                maxScore = Float.parseFloat(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Enter a number!");
                e.printStackTrace();
            }
        }

        currentExamNumber++;
        Exam exam = new Exam(currentExamNumber, maxScore);

        for (int i = 0; i < MainDatabase.getListSize(); i++) {
            student = MainDatabase.getStudent(i);

            while (true) {
                try {
                    System.out.print("Enter score for " + student.getName() + ": ");
                    score = Float.parseFloat(scanner.nextLine());
                    exam.addStudentName(student.getName());
                    exam.addStudentScore(score);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Enter a valid score!");
                    e.printStackTrace();
                }
            }

            float finalGrade = (score / maxScore) * 100;
            student.addGrade(finalGrade);
        }
        examLogs.appendList(exam);

        try {
            Files.addExamToLogs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void showExamLog() {
        Exam exam;
        char choice;

        do {
            System.out.println("[SHOWING ALL EXAM LOGS]");
            System.out.println("\nID\tExam");

            for (int i = 0; i < examLogs.getListSize(); i++) {
                exam = examLogs.returnNode(i);

                System.out.println(exam.getID() + "\t" + exam);
            }
            System.out.println("\n[END OF LIST]");
            System.out.println("\nPress [Q] to exit");
            System.out.print("Enter ID of exam log to show contents: ");
            choice = scanner.next().charAt(0);
            scanner.nextLine();
            String toFind = "Exam #" + choice;

            if (choice == 'q' || choice == 'Q') {
                return;
            }

            if (examLogs.validateNode(toFind)) {
                exam = examLogs.returnNode(toFind);
                Files.loadExamInfo(exam);

                System.out.println("\nPress [F] to find another exam log");
                System.out.println("Press [Q] to quit");
                System.out.print("Enter choice here: ");
                choice = scanner.next().charAt(0);
                scanner.nextLine();

                if (choice == 'q' || choice == 'Q') {
                    System.out.println("[INFO] Returning to previous page");
                    break;
                }
            } else {
                System.out.println("\n[ERROR] Record not found!");
            }
        } while (true);
    }

    private static void showStudentsGrades() {
        Student student;

        System.out.println("\n[ID]\t[Name]\t[Grade]");

        for (int i = 0; i < MainDatabase.getListSize(); i++) {
            student = MainDatabase.getStudent(i);
            System.out.println(student.getId() + "\t\t" + student.getName() + "\t\t" + student.getGrade());
        }

        if (MainDatabase.isListNull()) {
            System.out.println("List is empty!");
        }
        System.out.println("\n[END OF RECORD]");
    }

    public static void setCurrentExamNumber(int currentNumber) {
        currentExamNumber = currentNumber;
    }

    public static int getCurrentExamNumber() {
        return currentExamNumber;
    }

    public static int getRecordSize() {
        return examLogs.getListSize();
    }

    public static Exam getExam(int index) {
        return examLogs.returnNode(index);
    }

    public static void addExam(Exam exam) {
        examLogs.appendList(exam);
    }

    public static void clearRecords() {
        examLogs.clearList();
        currentExamNumber = 0;
    }
}
