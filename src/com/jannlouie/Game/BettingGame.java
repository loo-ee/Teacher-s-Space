package com.jannlouie.Game;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class BettingGame {
    private static Dealer dealer = new Dealer();
    private static Player[] players;
    private static int maxTurns = 0;
    private static int points = 0;
    private static int howManyPlayers = 0;
    private static String playerName;
    private static char reload;

    private static Scanner scanner = new Scanner(System.in);

    public static void Run(boolean status) throws Exception {
        boolean onLoop = true;

        if (status) {
            do {
                try {
                    System.out.print("Enter how many players who wants to play: ");
                    howManyPlayers = scanner.nextInt();
                    scanner.nextLine();
                    players = new Player[howManyPlayers];
                    onLoop = false;
                }
                catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Invalid number! Please try again\n");
                }
            } while (onLoop);

            for (int i = 0; i < howManyPlayers; i++) {
                System.out.print("Enter a name for player #" + (i+1) + ": ");
                playerName = scanner.nextLine();
                players[i] = new Player(playerName);
            }

            do {
                try {
                    System.out.print("Enter number of maximum turns for this game: ");
                    maxTurns = scanner.nextInt();
                    scanner.nextLine();
                    onLoop = false;
                }
                catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Invalid number! Please try again\n");
                    onLoop = true;
                }
            } while (onLoop);

            do {
                try {
                    System.out.print("Enter points to be given to winning player/s: ");
                    points = scanner.nextInt();
                    scanner.nextLine();
                    onLoop = false;
                }
                catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Invalid number of points! Please try again\n");
                    onLoop = true;
                }
            } while (onLoop);
        }

        for (int rounds = 0; rounds < maxTurns; rounds++) {
            System.out.println("\nGame is now playing round " + (rounds+1) + "...");
            dealer.rollDice();

            for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
                players[currentPlayer].makeGuess();
            }
            roundResult();
        }
        displayOverallResult();

        do {
            System.out.print("\nPress [R] to reset game, [A] to play again: ");
            reload = scanner.next().charAt(0);
            reload = Character.toLowerCase(reload);

            if (reload == 'r') {
                for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
                    players[currentPlayer] = null;
                }
                System.out.println();
                Run(true);
            }
            else if (reload == 'a') {
                Run(false);
            }
            else {
                System.out.println("You have entered an invalid input. Please try again.");
                onLoop = true;
            }
        } while (onLoop);
    }

    public static void roundResult() {
        System.out.println("\nDisplaying results...");
        System.out.println("Die 1: " + dealer.getDie1Value());
        System.out.println("Die 2: " + dealer.getDie2Value());
        System.out.println("Dice results: " + dealer.getResult());
        System.out.println("\nPlayers' guesses...");

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            getGuesses(players[currentPlayer]);
        }
    }

    public static void getGuesses(Player player) {
        String guess = player.getGuess();

        System.out.println(player.getName() + " guessed " + guess);

        if (Objects.equals(guess, dealer.getResult())) {
            System.out.println("Awarding points to " + player.getName());
            player.addPoints(points);
        }
    }

    public static void displayOverallResult() {
        String winner = "";
        int top1 =0;
        int sum = 0;

        System.out.println("\nDisplaying overall results...");

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            System.out.println(players[currentPlayer].getName() + "'s points: " + players[currentPlayer].getPoints());
        }

        System.out.println("The overall winner is: ");

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            if (players[currentPlayer].getPoints() > top1) {
                top1 = players[currentPlayer].getPoints();
                winner = players[currentPlayer].getName();
            }
            else if (players[currentPlayer].getPoints() == top1) {
                winner += " and " + players[currentPlayer].getName();
            }
        }

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            sum += players[currentPlayer].getPoints();
        }

        if (sum == 0) {
            System.out.println("none, no one scored a point.");
        }
        else {
            System.out.println(winner + ". Congrats!");
        }
    }
}
