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
public class RockPaperScissorsStep2 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        int userInput;
        int computerInput;
        int currentRound = 0;

        System.out.println("How many rounds would you like to play?");
        int totalGameRounds = keyboard.nextInt();

        if (totalGameRounds < 10 && totalGameRounds > 1) {

            for (; currentRound < totalGameRounds; currentRound++) {
                System.out.println("What is your choice for this round?");
                System.out.println("\t1) Rock\t2) Paper\t3) Scissors\t4)Quit");
                System.out.println();

                userInput = keyboard.nextInt();

                computerInput = (int) Math.ceil(Math.random() * 3);

                if (userInput == computerInput) {
                    System.out.println("Tie!");
                } else {

                    switch (userInput) {
                        case 1:
                            System.out.println("You played rock.");

                            if (computerInput == 3) {
                                System.out.println("Player wins.");
                            } else {
                                System.out.println("Player looses.");
                            }

                            break;

                        case 2:
                            System.out.println("You played paper.");

                            if (computerInput == 1) {
                                System.out.println("Player wins.");
                            } else {
                                System.out.println("Player looses.");
                            }

                            break;

                        case 3:
                            System.out.println("You played scissors.");

                            if (computerInput == 2) {
                                System.out.println("Player wins.");
                            } else {
                                System.out.println("Player looses.");
                            }

                            break;
                        case 4:
                            System.out.println("You have choosen to quit.");
                            break;
                        default:
                            System.out.println("Your choice was not a valid option.");
                    }
                }
            }

        } else {
            System.out.println("Your input is not a valid range.");
        }
    }
}
