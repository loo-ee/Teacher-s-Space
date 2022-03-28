package Config;

import java.util.Scanner;

public class Teacher extends HumanData{
    String subjectCourse;
    LinkedList<Student> classRecord = new LinkedList<Student>();
    Scanner scanner = new Scanner(System.in);

    public Teacher(String name, int age, String email, String subjectCourse) {
        super(name, age, email);
        this.subjectCourse = subjectCourse;
    }

    public void assignClass(LinkedList<Student> getClass) {
        for (int nodePtr=1; nodePtr<=getClass.getListSize(); nodePtr++) {
            this.classRecord.appendList(getClass.returnNode(nodePtr));
        }
    }

    public void addStudent(Student student) {
        classRecord.insertList(student);
    }

    public void removeStudent() {
        Student luckyStudent = null;
        String toStringEquivalent;

        showStudentsUnderTeacher();
        System.out.print("\nEnter name of student to remove: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter ID of student to remove: ");
        int studentID = Integer.parseInt(scanner.nextLine());
        toStringEquivalent = "Name: " + studentName + "\nID: " + studentID + "\n";
        
        luckyStudent = classRecord.returnNode(toStringEquivalent);
        classRecord.deleteNode(luckyStudent);
    }

    public void showStudentsUnderTeacher() {
        System.out.println("\n[Showing students under teacher " + this.name + "]\n");
        classRecord.displayListContents();
    }

    public void expandStudentsInfo() {
        Student studentPtr = null;

        for (int i=0; i<classRecord.getListSize(); i++) {
            studentPtr = classRecord.returnNode(i);
            System.out.println("\nStudent #" + (i+1) + ":");
            studentPtr.showStudentData();
        }
    }

    public void showTeacherData() {
        System.out.println("Name: \t" + this.name);
        System.out.println("Age: \t" + this.age);
        System.out.println("Email: \t" + this.email);
        System.out.println("Subject Course: " + this.subjectCourse);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n";
    }

    protected void finalize() {
        scanner.close();
    }
}
