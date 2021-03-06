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
public class LuckySevens implements Game {

    // int startingBet = 0;
    int maxAmountHeld = 0;
    int rollNumberAtMaxAmountHeld = 0;

    public void run() {

        ConsoleIO consoleIO = new ConsoleIO();

        //int startingBet = 0;
        maxAmountHeld = 0;
        rollNumberAtMaxAmountHeld = 0;

        int startingBet = consoleIO.getUserIntInputRange("How many Dollars would you like to bet?",
                4, 15000, "Betting that amount does not seem like a good idea.");

        int rollCounter = 1;

        rollCounter = luckySevensGameLoop(rollCounter, startingBet);

        printEndingMessage(rollCounter, consoleIO);
    }

    public void printEndingMessage(int rollCounter, ConsoleIO consoleIO) {
        consoleIO.printStringToConsole("You are broke after " + --rollCounter + " rolls.\n");
        consoleIO.printStringToConsole("You should have quit after " + rollNumberAtMaxAmountHeld + " rolls when you had $" + maxAmountHeld + ".");
    }

    public int luckySevensGameLoop(int rollCounter, int startingBet) {
        int diceRoll;
        for (int currentBalance = startingBet; currentBalance > 0; rollCounter++) {

            diceRoll = rollDice();

            currentBalance = adjustCurrentBalance(diceRoll, currentBalance);

            updateHighBalance(currentBalance, rollCounter);
        }
        return rollCounter;
    }

    public void updateHighBalance(int currentBalance, int rollCounter) {
        if (currentBalance > maxAmountHeld) {
            maxAmountHeld = currentBalance;
            rollNumberAtMaxAmountHeld = rollCounter;
        }
    }

    public int adjustCurrentBalance(int diceRoll, int currentBalance) {
        if (isAWinner(diceRoll)) {
            currentBalance += 4;
        } else {
            currentBalance -= 1;
        }
        return currentBalance;
    }

    public boolean isAWinner(int diceRoll) {
        return (diceRoll == 7);
    }

    public int rollDice() {

        // Roll the virtual dice here.
        int dieOne = rollOneDie();
        int dieTwo = rollOneDie();

        return dieOne + dieTwo;

    }

    public int rollOneDie() {
        return (int) Math.ceil(Math.random() * 6);

    }

    @Override
    public String getName() {
        return "Lucky Sevens";
    }
}
