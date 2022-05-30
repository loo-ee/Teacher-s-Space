package com.jannlouie.Apps;

import com.jannlouie.Config.Exam;
import com.jannlouie.Config.LinkedList;
import com.jannlouie.Config.MainDatabase;
import com.jannlouie.Config.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class ClassRoom {
    private static LinkedList<Exam> examLogs = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

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
                case '3' -> System.out.println("[Returning to previous page]");
                default -> System.out.println("[ERROR] You have entered an invalid input!");
            }
        } while (choice != '3');
        System.out.println();
    }

    private static void displayMenu() {
        System.out.println("\n[SELECT ACTION]");
        System.out.println("[1] Give tasks\n[2] Assign scores");
        System.out.println("[3] Return to previous stage");
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
                System.out.println("Enter maximum score: ");
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

/*    private static void addExamToLogs() {

        try {
            File file = new File("Database\\Logs\\Exam Logs.txt");
            FileWriter fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < examLogs.getListSize(); i++) {
            examLogs.appendList();
        }
    }*/
}
