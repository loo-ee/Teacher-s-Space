import java.util.Scanner;

import Config.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // System.out.print("Enter name: ");
        // int number = Integer.parseInt(scanner.nextLine());
        // System.out.println("Number: " + number);

        try {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            validatePassword(password);
            System.out.println("Password: " + password);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
        catch (InputException e) {
            System.out.println(e.getMessage());
            System.out.println("You have entered: " + e.getError());
        }
        scanner.close();    
    }

    public static void validatePassword(String password) throws InputException {
        if (password.compareTo("testing") != 0) {
            throw new InputException("You have entered a wrong password!", password);
        }
    }
}
