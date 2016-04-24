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
public class LuckySevens {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        boolean isValidNumber = false;

        int startingBet = 0;
        int maxAmountHeld = 0;
        int rollNumberAtMaxAmountHeld = 0;

        while (!isValidNumber) {

            System.out.print("How many dollars do you have? ");
            startingBet = keyboard.nextInt();
            System.out.println("");

            if (startingBet > 0) {
                isValidNumber = true;
            } else {
                isValidNumber = false;
                System.out.println("That input is not supported by this program.");
            }
        }

        int diceRoll = 0;
        int rollCounter = 1;

        for (int currentBalance = startingBet; currentBalance > 0; rollCounter++) {

            diceRoll = rollDice();

            if (diceRoll == 4) {
                currentBalance += 4;
            } else {
                currentBalance -= 1;
            }

            if (currentBalance > maxAmountHeld) {
                maxAmountHeld = currentBalance;
                rollNumberAtMaxAmountHeld = rollCounter;
            }
        }

        System.out.println("You are broke after " + --rollCounter + " rolls.\n");
        System.out.println("You should have quit after " + rollNumberAtMaxAmountHeld + " rolls when you had $" + maxAmountHeld + ".");
    }

    public static int rollDice() {

        int dieOne = 0;
        int dieTwo = 0;

        // Roll the virtual dice here.
        dieOne = (int) Math.ceil(Math.random() * 6);
        dieTwo = (int) Math.ceil(Math.random() * 6);

        return dieOne + dieTwo;

    }
}
