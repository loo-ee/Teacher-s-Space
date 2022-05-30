package com.jannlouie.FileHandling;

import com.jannlouie.Apps.ClassRoom;
import com.jannlouie.Config.Exam;
import com.jannlouie.Config.MainDatabase;
import com.jannlouie.Config.Student;
import java.io.*;
import java.util.Vector;

public class Files {
    private static Vector<String> students = new Vector<>();
    private static Vector<String> studentCredentials = new Vector<>();

    public static void saveData() throws IOException {
        Student student;
        FileWriter fileWriter;
        int currentStudentNumber = MainDatabase.getStudent(MainDatabase.getListSize() - 1).getId();

        for (int i = 0; i < MainDatabase.getListSize(); i++) {
            student = MainDatabase.getStudent(i);
            fileWriter = new FileWriter("Database\\Students\\" + student.getName() + ".txt");

            fileWriter.write(student.getName());
            fileWriter.append("\n");
            fileWriter.append(student.getEmail()).append("\n");
            fileWriter.append(String.valueOf(student.getId())).append("\n");
            fileWriter.append(String.valueOf(student.getAge())).append("\n");
            fileWriter.append(String.valueOf(student.getGrade())).append("\n");
            fileWriter.close();
        }
        fileWriter = new FileWriter("Database\\student names.txt");

        for (int i = 0; i < MainDatabase.getListSize(); i++) {
            fileWriter.append(MainDatabase.getStudent(i).getName()).append("\n");
        }
        fileWriter.close();

        fileWriter = new FileWriter("Database\\current student number.txt");
        fileWriter.write(String.valueOf(currentStudentNumber));
        fileWriter.close();

        fileWriter = new FileWriter("Database\\Logs\\Current Exam Number.txt");
        fileWriter.write(String.valueOf(ClassRoom.getCurrentExamNumber()));
        fileWriter.close();

        for (int i = 0; i < ClassRoom.getRecordSize(); i++) {
            Exam exam = ClassRoom.getExam(i);
            Vector<Float> studentScores = exam.getStudentScoresRecord();
            fileWriter = new FileWriter("Database\\Logs\\" + exam + ".txt");

            fileWriter.append(String.valueOf(exam.getID())).append("\n");
            fileWriter.append(String.valueOf(exam.getMaxScore())).append("\n");

            for (int j = 0; j < exam.getRecordSize(); j++) {
                fileWriter.append(String.valueOf(studentScores.get(j))).append("\n");
            }
            fileWriter.close();
        }
    }

    public static void load() throws IOException {
        File file;
        Student student;
        FileReader fileReader;
        BufferedReader bufferedReader;

        String name;
        String email;
        int count = 0;
        int id;
        int age;
        float grade;

        file = new File("Database\\verification.txt");

        if (!file.isFile()) {
            createFiles();
            System.out.println("[INFO]");
        } else {
            String line;
            file = new File("Database\\student names.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                students.add(line);
            }

            fileReader.close();
            bufferedReader.close();

            file = new File("Database\\current student number.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            fileReader.close();
            bufferedReader.close();

            MainDatabase.setCurrentStudentNumber(Integer.parseInt(line));

            file = new File("Database\\Logs\\Current Exam Number.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            fileReader.close();
            bufferedReader.close();

            ClassRoom.setCurrentExamNumber(Integer.parseInt(line));

            for (String s : students) {
                file = new File("Database\\Students\\" + s + ".txt");
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
                grade = Float.parseFloat(studentCredentials.get(i + 4));

                student = new Student(name, email, id, age);
                student.setGrade(grade);

                MainDatabase.addStudentToRoot(student);
            }

            for (int i = 0; i < ClassRoom.getCurrentExamNumber(); i++) {
                file = new File("Database\\Logs\\Exam #" + i + ".txt");

                if (file.isFile()) {
                    fileReader = new FileReader(file);
                    bufferedReader = new BufferedReader(fileReader);
                    Vector<Float> studentScores = new Vector<>();

                    int ID = Integer.parseInt(bufferedReader.readLine());
                    float maxScore = Float.parseFloat(bufferedReader.readLine());

                    while ((line = bufferedReader.readLine()) != null) {
                        studentScores.add(Float.parseFloat(line));
                    }

                    Exam exam = new Exam(ID, maxScore);
                    exam.setVector(studentScores);
                    exam.setVector(studentScores);
                    ClassRoom.addExam(exam);
                }
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

                fileWriter = new FileWriter("Database\\student names.txt");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\Logs\\Exam Logs.txt");
                fileWriter.close();

                fileWriter = new FileWriter("Database\\Logs\\Current Exam Number.txt");
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
