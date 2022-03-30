package com.jannlouie.Config;

import java.util.Scanner;

public class MainDatabase {
    private static LinkedList<Student> studentRootDirectory  = new LinkedList<>();
    private static LinkedList<Teacher> teacherRootDirectory = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String dataBaseName;
    private static int studentCount = 0;

     MainDatabase(String name) {
        dataBaseName = name;
    }

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
        studentCount++;

        Student newStudent = new Student(name, email, studentCount, age);
        studentRootDirectory.insertList(newStudent);
    }

    public static void removeStudent() {
        Student luckyStudent;
        String toStringEquivalent;

        System.out.print("\nEnter name of student to remove: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter the ID of the student to remove: ");
        int studentID = Integer.parseInt(scanner.nextLine());
        toStringEquivalent = "Name: " + studentName + "\nID: " + studentID + "\n";

        if (studentRootDirectory.validateNode(toStringEquivalent)) {
            luckyStudent = studentRootDirectory.returnNode(toStringEquivalent);
            studentRootDirectory.deleteNode(luckyStudent);
        }
    }

    public static void showStudentsDatabase() {
        Student studentPtr;

        System.out.println("\n[STUDENTS' MAIN DATABASE]");
        
        for (int i=0; i<studentRootDirectory.getListSize(); i++) {
            System.out.println("\n[Student #" + (i+1) + "]");
            studentPtr = studentRootDirectory.returnNode(i);
            studentPtr.showStudentData();
        }
    }

    public static void showTeachersDatabase() {
        Teacher teacherPtr;

        System.out.println("\n[TEACHERS' MAIN DATABASE]");

        for (int i=0; i<teacherRootDirectory.getListSize(); i++) {
            System.out.println("\n[Teacher #]" + (i+1) + "]");
            teacherPtr = teacherRootDirectory.returnNode(i);
            teacherPtr.showTeacherData();
        }
    }

    public static void addTeacherToRoot() {
        String name, email, subjectCourse;
        int age;

        System.out.print("\nEnter name of new teacher: ");
        name = scanner.nextLine();
        System.out.print("Enter email address of teacher " + name + ": ");
        email = scanner.nextLine();
        System.out.print("Enter age of teacher " + name + ": ");
        age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter assigned subject course of teacher " + name + ": ");
        subjectCourse = scanner.nextLine();

        Teacher newTeacher = new Teacher(name, age, email, subjectCourse);
        teacherRootDirectory.insertList(newTeacher);
    }

    public static void removeTeacher() {
        Teacher luckyTeacher;
        String toStringEquivalent;

        System.out.print("\nEnter name of teacher to remove: ");
        toStringEquivalent = "Name: " + scanner.nextLine() + "\n";

        if (teacherRootDirectory.validateNode(toStringEquivalent)) {
            luckyTeacher = teacherRootDirectory.returnNode(toStringEquivalent);
            teacherRootDirectory.deleteNode(luckyTeacher);
        }
    }
    
    public static void showTeachersDataBase() {
        System.out.println("\n[STUDENTS' MAIN DATABASE]");
        teacherRootDirectory.displayListContents();
    }
}
