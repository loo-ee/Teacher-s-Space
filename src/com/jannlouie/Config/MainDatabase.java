package com.jannlouie.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class MainDatabase {
    private static LinkedList<Student> studentRootDirectory  = new LinkedList<>();
    private static int currentStudentNumber = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static void addStudentToRoot() {
        String name;
        String email;
        int age;

        System.out.print("\nEnter name of student: ");
        name = scanner.nextLine();
        System.out.print("Enter email of student: ");
        email = scanner.nextLine();
        System.out.print("Enter age of student: ");
        age = Integer.parseInt(scanner.nextLine());
        currentStudentNumber++;

        Student newStudent = new Student(name, email, currentStudentNumber, age);
        studentRootDirectory.appendList(newStudent);
    }

    public static void addStudentToRoot(Student student) {
         studentRootDirectory.appendList(student);
    }

    public static void removeStudent() throws IOException {
        Student luckyStudent;
        String toStringEquivalent;
        File file;
        FileWriter fileWriter;

        showStudentsDatabase();

        System.out.println("\nPress [R] to return to previous page");
        System.out.print("Enter name of student to remove: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter the ID of the student to remove: ");
        int studentID = Integer.parseInt(scanner.nextLine());
        toStringEquivalent = "Name: " + studentName + "\nID: " + studentID + "\n";

        if (Objects.equals(studentName, "r") || Objects.equals(studentName, "R")) {
            return;
        }

        if (studentRootDirectory.validateNode(toStringEquivalent)) {
            luckyStudent = studentRootDirectory.returnNode(toStringEquivalent);
            file = new File("Database\\Students\\" + luckyStudent.getName() + ".txt");

            if (file.isFile()) {
                file.delete();
            }

            studentRootDirectory.deleteNode(luckyStudent);
            fileWriter = new FileWriter("Database\\student names.txt");

            for (int i = 0; i < MainDatabase.getListSize(); i++) {
                fileWriter.append(MainDatabase.getStudent(i).getName()).append("\n");
            }

            fileWriter.close();
            System.out.println("\n[INFO] Record deleted");
        } else {
            System.out.println("\n[INFO] Record not found");
        }
    }

    public static void setCurrentStudentNumber(int currentNumber) {
         MainDatabase.currentStudentNumber = currentNumber;
    }

    private static boolean validateStudent(String combination) {
         return studentRootDirectory.validateNode(combination);
    }

    public static Student getStudentInfo(String name, int id) {
        // For explanation on how the 'returnNode()' method works -> look at the LinkedList class
         if (validateStudent("Name: "+ name+ "\nID: " + id + "\n")) {
            return studentRootDirectory.returnNode("Name: "+ name+ "\nID: " + id + "\n");
         }

        System.out.println("\n[Student not found]");
         return null;
    }

    public static void showStudentsDatabase() {
        Student studentPtr;

        System.out.println("\n[SHOWING STUDENT LIST]");

        if (studentRootDirectory.checkIfNull()) {
            System.out.println("List is empty!");
        } else {
            for (int i=0; i<studentRootDirectory.getListSize(); i++) {
                System.out.println("\n[Student #" + (i+1) + "]");
                studentPtr = studentRootDirectory.returnNode(i);
                System.out.println("Name: \t" + studentPtr.getName());
                System.out.println("ID: \t" + studentPtr.getId());
            }
        }
    }

    public static int getListSize() {
         return studentRootDirectory.getListSize();
    }

    public static Student getStudent(int index) {
         return studentRootDirectory.returnNode(index);
    }

    public static boolean isListNull()  {
        return studentRootDirectory.checkIfNull();
    }

    public static void deleteRecords() {
        studentRootDirectory.clearList();
    }
}
