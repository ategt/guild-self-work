/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class RockPaperScissorsStep4 implements Game {

    public void run(ConsoleIO consoleIo) {

        playRockPaperScissors(consoleIo);
    }

    public static void playRockPaperScissors(ConsoleIO consoleIo) {
        //Scanner keyboard = new Scanner(System.in);

        int userInput;
        int computerInput;
        // int currentRound = 0;
        int totalGameRounds = 0;

        int wins = 0;
        int losses = 0;
        int ties = 0;

        boolean keepPlaying = true;

        while (keepPlaying) {

            totalGameRounds = promptUserForAnInt("How many rounds would you like to play?", keyboard);

            // reset wins and losses;
            wins = 0;
            losses = 0;
            ties = 0;

            if (totalGameRounds <= 10 && totalGameRounds >= 1) {

                //while (currentRound < totalGameRounds) {
                for (int currentRound = 0; currentRound < totalGameRounds; currentRound++) {

                    userInput = promptUserForAnInt("What is your choice for this round?\n"
                            + "\t1) Rock\t2) Paper\t3) Scissors\n", keyboard);

                    computerInput = generateComputerInput();
                    //computerInput = 1;

                    printWhoPlayedWhat(computerInput, userInput);

                    if (userInput == computerInput) {
                        System.out.println("Tie!");
                        ties++;
                    } else if (userInput == 4) {
                        System.out.println("You have choosen to quit.");
                        currentRound = totalGameRounds + 1;

                    } else if (isPlayerTheWinner(computerInput, userInput)) {
                        System.out.println("Player wins.");
                        wins++;
                    } else {
                        System.out.println("Player looses.");
                        losses++;
                    }
                    printEndOfRoundMessage(wins, losses, ties);

                }

                printEndOfBoutMessage(wins, losses);

            } else {
                System.out.println("Your input is not a valid range.");
            }

            System.out.println("Would you like to play again?");
            System.out.println("Enter '1' to play again, anything else to exit.");
            if (keyboard.nextInt() == 1) {
                System.out.println("Playing again");
                keepPlaying = true;
            } else {
                System.out.println("Thanks for playing!");
                keepPlaying = false;
            };
        }
    }

    public static void printEndOfBoutMessage(int wins, int losses) {
        if (wins > losses) {
            System.out.println("Player is the overall winner.");
        } else if (wins < losses) {
            System.out.println("Computer is the overall winner.");
        } else {
            System.out.println("No overall winner.");
        }
    }

    public static void printEndOfRoundMessage(int wins, int losses, int ties) {
        System.out.println("Current Score: Wins:" + wins + " Losses:" + losses + " Ties:" + ties);
    }

    public static void printWhoPlayedWhat(int computerInput, int userInput) {
        System.out.println("The computer played " + returnRockPaperOrScissors(computerInput) + ".");
        System.out.println("You played " + returnRockPaperOrScissors(userInput) + ".");
    }

    public static String returnRockPaperOrScissors(int inputValue) {

        String rockPaperOrScissors = "";

        switch (inputValue) {
            case 1:
                rockPaperOrScissors = "Rock";
                break;
            case 2:
                rockPaperOrScissors = "Paper";
                break;
            case 3:
                rockPaperOrScissors = "Scissors";
                break;
        }
        return rockPaperOrScissors;
    }

    public static int promptUserForAnInt(String promptString, Scanner keyboard) {
        System.out.println(promptString);
        return keyboard.nextInt();
    }

    public static boolean isPlayerTheWinner(int computerInput, int userInput) {
        boolean isPlayerAWinner = false;

        switch (userInput) {
            case 1:
                if (computerInput == 3) {
                    isPlayerAWinner = true;
                } else {
                    isPlayerAWinner = false;
                }

                break;

            case 2:
                if (computerInput == 1) {
                    isPlayerAWinner = true;
                } else {
                    isPlayerAWinner = false;
                }

                break;

            case 3:
                if (computerInput == 2) {
                    isPlayerAWinner = true;
                } else {
                    isPlayerAWinner = false;
                }

                break;
            default:
                System.out.println("Your choice was not a valid option.");

        }

        return isPlayerAWinner;
    }

    public static int generateComputerInput() {
        // Just in case I go nuts and decide to make an AI later.

        Random random = new Random();

        // (int) Math.ceil(Math.random() * 3)
        //     computerInput = ( random.nextInt(3) + 1 );
        return (random.nextInt(3) + 1);
    }

    @Override
    public String getName() {
        return "Rock-Paper-Scissors-v4";
    }
}
