package com.jannlouie.FileHandling;

import com.jannlouie.Config.MainDatabase;
import com.jannlouie.Config.Student;

import java.io.*;
import java.util.Vector;

public class Files {
    private static Vector<String> fromStartLog = new Vector<>();
    private static Vector<String> students = new Vector<>();
    private static Vector<String> studentCredentials = new Vector<>();
    private static int currentStudentNumber;

    public static void saveData() throws IOException {
        Student student;
        File file;
        FileWriter fileWriter;

        currentStudentNumber = MainDatabase.getStudent(MainDatabase.getListSize() -1).getId();

        for (int i = 0; i < MainDatabase.getListSize(); i++) {
            student = MainDatabase.getStudent(i);
            file = new File("Database\\" + student.getName() + ".txt");
            fileWriter = new FileWriter(file);

            fileWriter.write(student.getName());
            fileWriter.append("\n");
            fileWriter.append(student.getEmail()).append("\n");
            fileWriter.append(String.valueOf(student.getId())).append("\n");
            fileWriter.append(String.valueOf(student.age())).append("\n");
            fileWriter.append(String.valueOf(student.getGrade())).append("\n");
            fileWriter.close();
        }
        fileWriter = new FileWriter("Database\\students.txt");

        for (int i = 0; i < MainDatabase.getListSize(); i++) {
            fileWriter.append(MainDatabase.getStudent(i).getName()).append("\n");
        }
        fileWriter.close();

        fileWriter = new FileWriter("Database\\current student number.txt");
        fileWriter.write(String.valueOf(currentStudentNumber));
        fileWriter.close();
    }

    public static void load() throws IOException {
        File file;
        Student student;
        FileReader fileReader;
        BufferedReader bufferedReader;

        int count = 0;
        String name, email;
        int id, age, grade;

        file = new File("Database\\verification.txt");

        if (!file.isFile()) {
            createFiles();
            System.out.println("[INFO]");
        } else {
            String line;
            file = new File("Database\\students.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                students.add(line);
            }

            fileReader.close();
            bufferedReader.close();

            // TODO -> Create a method for current student number
            file = new File("Database\\current student number.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            fileReader.close();
            bufferedReader.close();

            MainDatabase.setCurrentStudentNumber(Integer.parseInt(line));

            for (String s : students) {
                file = new File("Database\\" + s + ".txt");
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                while ((line = bufferedReader.readLine()) != null) {
                    studentCredentials.add(line);
                    count++;
                }
                fileReader.close();
                bufferedReader.close();
            }

            for (int i = 0; i < count; i += 5) {
                name = studentCredentials.get(i);
                email = studentCredentials.get(i + 1);
                id = Integer.parseInt(studentCredentials.get(i + 2));
                age = Integer.parseInt(studentCredentials.get(i + 3));
                grade = Integer.parseInt(studentCredentials.get(i + 4));

                student = new Student(name, email, id, age);
                student.setGrade(grade);

                MainDatabase.addStudentToRoot(student);
            }
        }
    }

    private static void createFiles() {

        try {
            File file;
            file = new File("Database\\verification.txt");

            if (!file.isFile()) {
                FileWriter fileWriter = new FileWriter("Database\\verification.txt");
                fileWriter.append("Verified");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\password.txt");
                fileWriter.append("default");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\current student number.txt");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\students.txt");
                fileWriter.close();

                System.out.println("\n[Password is \"default\"]\n");
            } else {
                file = new File("Database\\password.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                Login.setPassword(bufferedReader.readLine());
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
