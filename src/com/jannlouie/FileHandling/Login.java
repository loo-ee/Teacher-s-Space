package com.jannlouie.FileHandling;

import java.io.*;
import java.util.Objects;

public class Login {
    private static String password = "default";

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Login.password = password;
    }

    public static boolean validateLogin(String userInput) {
        return Objects.equals(userInput, password);
    }
}
