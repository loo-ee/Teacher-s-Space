package Config;

import java.util.Scanner;

public class MainDatabase {
    LinkedList<Student> studentRootDirectory  = new LinkedList<>();
    LinkedList<Teacher> teacherRootDirectory = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);
    String dataBaseName;
    int studentCount = 0;

    public MainDatabase(String name) {
        this.dataBaseName = name;
    }

    public void addStudentToRoot() {
        String name, email;
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

    public void removeStudent() {
        Student luckystudent = null;
        String toStringEquivalent;

        System.out.print("\nEnter name of student to remove: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter the ID of the student to remove: ");
        int studentID = Integer.parseInt(scanner.nextLine());
        toStringEquivalent = "Name: " + studentName + "\nID: " + studentID + "\n";

        if (studentRootDirectory.validateNode(toStringEquivalent)) {
            luckystudent = studentRootDirectory.returnNode(toStringEquivalent);
            studentRootDirectory.deleteNode(luckystudent);
        }
    }

    public void showStudentsDatabase() {
        Student studentPtr = null;

        System.out.println("\n[STUDENTS' MAIN DATABASE]");
        
        for (int i=0; i<studentRootDirectory.getListSize(); i++) {
            System.out.println("\n[Student #" + (i+1) + "]");
            studentPtr = studentRootDirectory.returnNode(i);
            studentPtr.showStudentData();
        }
    }

    public void showTeachersDatabase() {
        Teacher teacherPtr = null;

        System.out.println("\n[TEACHERS' MAIN DATABASE]");

        for (int i=0; i<teacherRootDirectory.getListSize(); i++) {
            System.out.println("\n[Teacher #]" + (i+1) + "]");
            teacherPtr = teacherRootDirectory.returnNode(i);
            teacherPtr.showTeacherData();
        }
    }

    public void addTeacherToRoot() {
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

    public void removeTeacher() {
        Teacher luckyTeacher = null;
        String toStringEquivalent;

        System.out.print("\nEnter name of teacher to remove: ");
        toStringEquivalent = "Name: " + scanner.nextLine() + "\n";

        if (teacherRootDirectory.validateNode(toStringEquivalent)) {
            luckyTeacher = teacherRootDirectory.returnNode(toStringEquivalent);
            teacherRootDirectory.deleteNode(luckyTeacher);
        }
    }
    
    public void showTeachersDataBase() {
        System.out.println("\n[STUDENTS' MAIN DATABASE]");
        teacherRootDirectory.displayListContents();
    }

    protected void finalize() {
        scanner.close();
    }
}
