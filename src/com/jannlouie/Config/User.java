package com.jannlouie.Config;

public class User {
    String name;
    String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public void userInfo() {
        System.out.print("Name: " + name);
        System.out.print("Age: " + age);
    }
}
