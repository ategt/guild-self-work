/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.luckysevens;

import java.util.Scanner;
import java.text.*;
import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class LuckySevens {

    public static void main(String[] args) {

        double bet = 0;
        double rolls = 0;

        System.out.println("Welcome to Lucky Sevens: The game of futility!");

        //ask user for their bet
        bet = zeroCheck(bet, "How much money you like to put down? $",
                "Come on man, put something more than nothing!");

        //create a new double 'winnings' to be manipulated
        double winnings = bet;

        //get values for highest dollar amount, rolls when highest dollar amount earned, and total rolls
        double highDollar = getHighScore(winnings, rolls, 1);
        double highRoll = getHighScore(winnings, rolls, 2);
        rolls = getHighScore(winnings, rolls, 3);

        //show results
        cubaGooding(rolls, highRoll, bet, highDollar);

    }//end of main

    public static int diceRollandAdd() {

        Random diceRoll = new Random();
        int dice1 = diceRoll.nextInt(6) + 1;
        int dice2 = diceRoll.nextInt(6) + 1;
        int diceTotal = dice1 + dice2;

        return diceTotal;
    }

    public static double zeroCheck(double query, String message1, String message2) {

        Scanner keyboard = new Scanner(System.in);

        while (query <= 0) {

            System.out.print(message1);
            query = keyboard.nextDouble();
            if (query <= 0) {
                System.out.println("\n" + message2 + "\n");
            } else {
                System.out.println("");
            }//end validity check
        }//end validity loop
        return query;
    }

    public static double checkForSeven(int diceTotal, double winnings) {

        if (diceTotal == 7) {
            winnings += 4;
        } else {
            winnings -= 1;
        }//end of money change
        return winnings;
    }

    public static double getHighScore(double winnings, double rolls, int choice) {
        //returns a highScore based on choice

        double highScore = 0;
        double desiredScore;
        HighScoreTracker tracker = new HighScoreTracker();

        double[] highScores;
        highScores = tracker.getScores(winnings, rolls);

        switch (choice) {
            case 1:
                highScore = highScores[0];
                break;
            case 2:
                highScore = highScores[1];
                break;
            case 3:
                highScore = highScores[2];
                break;
            default:
                break;
        }
        desiredScore = highScore;
        
        return desiredScore;
    }

    public static void cubaGooding(double rolls, double highRoll, double startBet, double highDollar) {

        DecimalFormat moneyFormat = new DecimalFormat("0.00");
        DecimalFormat rollFormat = new DecimalFormat("#.##");

        System.out.println("You went broke after " + rollFormat.format(rolls) + " rolls.");
        if (startBet == highDollar) {
            System.out.println("You never earned more than your inital bet of $" + moneyFormat.format(startBet));
            System.out.println("That should teach you to never gamble.");
        } else {
            System.out.println("After " + rollFormat.format(highRoll) + " rolls, you had $"
                    + moneyFormat.format(highDollar) + "."
                    + " You should have quit there, but this is Lucky Sevens!\n"
                    + "You'll never win! Thus, a metaphor for life...");
        }

    }

}//end of class
