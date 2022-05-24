package com.jannlouie.Config;

import java.util.Scanner;

public class Student extends HumanData {
    String course;
    int id;
    int yearLevel;
    int grade;

    Scanner scanner = new Scanner(System.in);

    public Student(String name, String email, int id, int age) {
        super(name, age, email);
        this.id = id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setStudentInfo() {
        System.out.print("\nEnter your course: ");
        this.course = scanner.nextLine();
        System.out.print("Enter your year level: ");
        this.yearLevel = scanner.nextInt();
        scanner.nextLine();
    }

   public void addGrade(int grade) {
        this.grade = (this.grade + grade) / 2;
   }

   public String getEmail() {
        return this.email;
   }

   public int getId() {
        return this.id;
   }

   public int age() {
        return this.age;
   }

    public int getGrade() {
        return this.grade;
    }

    public void showStudentData() {
        System.out.println("Name: \t" + this.name);
        System.out.println("Age: \t" + this.age);
        System.out.println("ID: \t" + this.id);
        System.out.println("Email: \t" + this.email);
        System.out.println("Course: " + this.course);
        System.out.println("Year Level: " + this.yearLevel);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nID: " + this.id + "\n";
    }
}
