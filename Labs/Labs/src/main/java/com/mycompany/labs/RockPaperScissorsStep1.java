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
public class RockPaperScissorsStep1 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        int userInput;

        do {

            //for ( int i = 0 ; i < 24 ; i++){
            int randomNum = (int) Math.ceil(Math.random() * 3);

            System.out.print(randomNum);

            switch (randomNum) {
                case 1:
                    System.out.print(" - Rock");
                    break;
                case 2:
                    System.out.print(" - Paper");
                    break;
                case 3:
                    System.out.print(" - Scissors");
                    break;
            }

            System.out.println();
            //}

            int computerInput = randomNum;

            System.out.println("What is your choice for this round?");
            System.out.println("\t1) Rock\t2) Paper\t3) Scissors\t4)Quit");
            System.out.println();

            userInput = keyboard.nextInt();

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
                }

            }

        } while (userInput != 4);

    }
}
