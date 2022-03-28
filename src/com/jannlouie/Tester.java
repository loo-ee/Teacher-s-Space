import Config.*;

import java.util.Scanner;
import Config.LinkedList;

public class Tester {
    public static void main(String[] args) {
       LinkedList<Teacher> teachers = new LinkedList<>();

       Teacher teacher1 = new Teacher("A", 20, "@", "Math");
       Teacher teacher2 = new Teacher("B", 20, "@", "Math");
       Teacher teacher3 = new Teacher("C", 20, "@", "Math");
       
        teachers.insertList(teacher1);
        teachers.insertList(teacher2);
        teachers.insertList(teacher3);
        // teachers.displayListContents();

        Teacher teacherPtr = null;
        
        System.out.println("\n[TEACHERS' MAIN DATABASE]");
        
        for (int i=0; i<teachers.getListSize(); i++) {
            System.out.println("\n[Teacher #" + (i+1) + "]");
            teacherPtr = teachers.returnNode(i);
            teacherPtr.showTeacherData();
        }

        teachers.clearList();
        teachers.displayListContents();

        // teachers.deleteNode(teacher1);
        // teachers.displayListContents();

        // Student test = students.returnNode("A");
        // System.out.print("Name: " + test.getName());

        // LinkedList<Student> students = new LinkedList<>();

        // Student student1 = new Student("A", "@", 1, 19);
        // Student student2 = new Student("B", "@", 2, 22);
        // Student student3 = new Student("C", "@", 3, 21);

        // students.insertList(student1);
        // students.insertList(student2);
        // students.insertList(student3);

        // Student studentPtr = null;

        // System.out.println("\n[STUDENTS' MAIN DATABASE]");
        
        // for (int i=0; i<students.getListSize(); i++) {
        //     System.out.println("\n[Student #" + (i+1) + "]");
        //     studentPtr = students.returnNode(i);
        //     studentPtr.showStudentData();
        // }

    }
}