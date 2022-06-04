package com.jannlouie.Apps.Games.BettingGame;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class BettingGame {
    private static final Dealer dealer = new Dealer();
    private static Player[] players;
    private static int maxTurns = 0;
    private static int points = 0;
    private static int howManyPlayers = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static void run(boolean isFirstRun) throws Exception {
        boolean onLoop = true;

        if (isFirstRun) {
            do {
                System.out.print("[HELP] Do you want to show tutorial page?(Y/N): ");
                char choice = scanner.next().charAt(0);
                scanner.nextLine();

                if (choice == 'y' || choice == 'Y') {
                    showTutorial();
                }

                try {
                    System.out.print("\nEnter how many players who wants to play: ");
                    howManyPlayers = Integer.parseInt(scanner.nextLine());
                    players = new Player[howManyPlayers];
                    onLoop = false;
                }
                catch (Exception e) {
                    System.out.println("Invalid number! Please try again\n");
                }
            } while (onLoop);

            for (int i = 0; i < howManyPlayers; i++) {
                System.out.print("Enter a name for player #" + (i+1) + ": ");
                String playerName = scanner.nextLine();
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
            System.out.println("\nPress [R] to reset game\nPress [A] to play again\nPress [E] to exit");
            System.out.print("Enter choice: ");
            char reload = scanner.next().charAt(0);
            reload = Character.toLowerCase(reload);

            switch (reload) {
                case 'r' -> {
                    for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
                        players[currentPlayer] = null;
                    }
                    onLoop = false;
                    System.out.println();
                    run(true);
                }
                case 'a' -> {
                    onLoop = false;
                    run(false);
                }
                case 'e' -> {
                    for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
                        players[currentPlayer] = null;
                    }
                    onLoop = false;
                    System.out.println("\nThank you for playing!");
                }
                default -> {
                    System.out.println("You have entered an invalid input. Please try again.");
                    onLoop = true;
                }
            }
        } while (onLoop);
    }

    private static void roundResult() {
        System.out.println("\nDisplaying results...");
        System.out.println("Die 1: " + dealer.getDie1Value());
        System.out.println("Die 2: " + dealer.getDie2Value());
        System.out.println("Dice results: " + dealer.getResult());
        System.out.println("\nPlayers' guesses...");

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            getGuesses(players[currentPlayer]);
        }
    }

    private static void getGuesses(Player player) {
        String guess = player.getGuess();

        System.out.println(player.getName() + " guessed " + guess);

        if (Objects.equals(guess, dealer.getResult())) {
            System.out.println("Awarding points to " + player.getName());
            player.addPoints(points);
        }
    }

    private static void displayOverallResult() {
        StringBuilder winner = new StringBuilder();
        int top1 =0;
        int sum = 0;

        System.out.println("\nDisplaying overall results...");

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            System.out.println(players[currentPlayer].getName() + "'s points: " + players[currentPlayer].getPoints());
        }

        System.out.print("The overall winner is ");

        for (int currentPlayer = 0; currentPlayer < howManyPlayers; currentPlayer++) {
            if (players[currentPlayer].getPoints() > top1) {
                top1 = players[currentPlayer].getPoints();
                winner = new StringBuilder(players[currentPlayer].getName());
            }
            else if (players[currentPlayer].getPoints() == top1) {
                winner.append(" and ").append(players[currentPlayer].getName());
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

    private static void showTutorial() {

        System.out.println("\n[Betting Game Tutorial]");
        System.out.println("\n[1] The game starts by asking information about the players.");
        System.out.println("\t-> Enter the needed player information ");
        System.out.println("[2] The game then asks for settings that the game will follow.");
        System.out.println("\t-> Enter the settings you want to apply.");
        System.out.println("[3] Game will now start the first round.");
        System.out.println("\t-> Enter the choices of the players in each round");
        System.out.println("\t-> The game will show results in each round");
        System.out.print("\t-> After all the rounds, the game will then compute the final scores of");
        System.out.print("each player.\n\t-> The game will then show who is the winner");
        System.out.println("[4] The game will then ask if you want to play again using the previous settings");
        System.out.println("\t-> Or you can choose to reset the settings or terminate the game.");
        System.out.println("\nThat's it! Thank you for your time :>");
        System.out.print("Press any key to continue....");

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
