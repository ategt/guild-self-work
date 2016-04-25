/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class RockPaperScissorsStep3 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int userInput;
        int computerInput;
        int currentRound = 0;

        int wins = 0;
        int losses = 0;
        int ties = 0;

        System.out.println("How many rounds would you like to play?");
        int totalGameRounds = keyboard.nextInt();

        if (totalGameRounds <= 10 && totalGameRounds >= 1) {

            //while (currentRound < totalGameRounds) {
            for (; currentRound < totalGameRounds; currentRound++) {
                userInput = getUserInput(keyboard);

                computerInput = getComputerChoice();
                
                System.out.print("The computer played ");

                printRockPaperOrScissors(computerInput);


                if (userInput == computerInput) {
                    System.out.println("Tie!");
                    ties++;
                } else {

                    switch (userInput) {
                        case 1:
                            System.out.println("You played Rock.");

                            if (computerInput == 3) {
                                System.out.println("Player wins.");
                                wins++;
                            } else {
                                System.out.println("Player looses.");
                                losses++;
                            }

                            break;

                        case 2:
                            System.out.println("You played Paper.");

                            if (computerInput == 1) {
                                System.out.println("Player wins.");
                                wins++;
                            } else {
                                System.out.println("Player looses.");
                                losses++;
                            }

                            break;

                        case 3:
                            System.out.println("You played Scissors.");

                            if (computerInput == 2) {
                                System.out.println("Player wins.");
                                wins++;
                            } else {
                                System.out.println("Player looses.");
                                losses++;
                            }

                            break;
                        case 4:
                            System.out.println("You have choosen to quit.");
                            currentRound = totalGameRounds + 1;
                            break;
                        default:
                            System.out.println("Your choice was not a valid option.");
                    }
                }
                System.out.println("Current Score: Wins:" + wins + " Losses:" + losses + " Ties:" + ties);

            }

            printOverallOutcome(wins, losses);

        } else {
            System.out.println("Your input is not a valid range.");
        }

    }

    public static void printRockPaperOrScissors(int computerInput) {
        switch (computerInput) {
            case 1:
                System.out.print("Rock");
                break;
            case 2:
                System.out.print("Paper");
                break;
            case 3:
                System.out.print("Scissors");
                break;
        }
        
                        System.out.println(".");

    }

    public static int getComputerChoice() {
        int computerInput;
        computerInput = (int) Math.ceil(Math.random() * 3);
        //computerInput = 1;
        return computerInput;
    }

    public static int getUserInput(Scanner keyboard) {
        int userInput;
        System.out.println("What is your choice for this round?");
        System.out.println("\t1) Rock\t2) Paper\t3) Scissors");
        System.out.println();
        userInput = keyboard.nextInt();
        return userInput;
    }

    public static void printOverallOutcome(int wins, int losses) {
        if (wins > losses) {
            System.out.println("Player is the overall winner.");
        } else if (wins < losses) {
            System.out.println("Computer is the overall winner.");
        } else {
            System.out.println("No overall winner.");
        }
    }
}
