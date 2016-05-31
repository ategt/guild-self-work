/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luckysevenswebapp;

/**
 *
 * @author apprentice
 */
public class LuckySevensGameLogic {

    public LuckySevensGame luckySevensGameLoop(LuckySevensGame game) {

        int rollCounter = game.getRollCounter();
        int diceRoll;

        for (int currentBalance = game.getStartingBet(); currentBalance > 0; rollCounter++) {

            diceRoll = rollDice();

            currentBalance = adjustCurrentBalance(diceRoll, currentBalance);

            game.setRollCounter(rollCounter);
            game.setCurrentBalance(currentBalance);
            updateHighBalance(game);
        }

        game.setRollCounter(rollCounter);
        return game;
    }

    public void updateHighBalance(LuckySevensGame game) {
        if (game.getCurrentBalance() > game.getMaxAmountHeld()) {
            game.setMaxAmountHeld(game.getCurrentBalance());
            game.setRollNumberAtMaxAmountHeld(game.getRollCounter());
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

        int dieOne = rollOneDie();
        int dieTwo = rollOneDie();

        return dieOne + dieTwo;

    }

    public int rollOneDie() {
        return (int) Math.ceil(Math.random() * 6);
    }

}
