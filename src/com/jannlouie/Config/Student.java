package com.jannlouie.Config;

import java.util.Scanner;

public class Student extends HumanData {
    private int id;
    private float grade = 100;

    private Scanner scanner = new Scanner(System.in);

    public Student(String name, String email, int id, int age) {
        super(name, age, email);
        this.id = id;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

   public void addGrade(float grade) {
        this.grade = (this.grade + grade) / 2;
   }

   public int getId() {
        return this.id;
   }

    public float getGrade() {
        return this.grade;
    }

    public void showStudentData() {
        System.out.println("Name: \t" + this.name);
        System.out.println("Age: \t" + this.age);
        System.out.println("ID: \t" + this.id);
        System.out.println("Email: \t" + this.email);
        System.out.println("Grade: " + this.grade);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nID: " + this.id + "\n";
    }
}
