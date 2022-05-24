package com.jannlouie;

import com.jannlouie.Config.*;

import java.io.*;
import java.util.*;

public class Tester {
    static Vector<String> vector = new Vector<>();
    static User[] users;

    public static void main(String[] args) throws Exception {
        readFile();
        writeFile();
    }

    public static void writeFile() throws Exception {
        try {
            File testFile = new File("Texts\\Test File.txt");
            FileWriter fileWriter = new FileWriter(testFile);

            for (String write: vector) {
                fileWriter.append(write);

            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile() throws FileNotFoundException {
        try
        {
            File file=new File("D:\\GitHub\\JAVA\\Student-Database-2.0\\src\\com\\jannlouie\\test.txt");    //creates a new file instance
            FileReader fr= new FileReader(file);   //reads the file
            BufferedReader br= new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            String name;
            String age;

            int count = 0;

            while((line=br.readLine())!=null) {

                vector.add(line + "\n");
                count++;
            }
            fr.close();    //closes the stream and release the resources

            users = new User[count/2];

            for (int i = 0; i < count; i+=2) {
                name = vector.get(i);
                age = vector.get(i+1);

                users[i/2] = new User(name, age);
            }

            for (int i = 0; i < (count/2); i++) {
                users[i].userInfo();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}