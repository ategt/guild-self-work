/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

/**
 *
 * @author apprentice
 */
public class BlackJack {

    public void run() {

        ConsoleIO consoleIo = new ConsoleIO();

        consoleIo.printStringToConsole("Welcome to BlackJack.");
        consoleIo.printStringToConsole("");

        boolean keepPlaying = true;
        int balance = 100;
        int hands = 0;
        int winningHands = 0;
        int losingHands = 0;

        consoleIo.printStringToConsole("You are starting out with $100.");
        while (keepPlaying) {
            int bet = consoleIo.getUserIntInputRange("How much would you like to bet?: ", 1, balance);

            boolean winner = playAHandOfBlackJack(consoleIo);

            hands++;

            if (winner) {
                balance += bet;
                winningHands++;
            } else {
                balance -= bet;
                losingHands++;
            }

            consoleIo.printStringToConsole("Your new balance is $" + balance);

            if (1 == consoleIo.getUserIntInputRange("Would you like to play again?\n Please Enter\"1\" for yes and \"2\" for no.", 1, 2)) {
                keepPlaying = true;
            } else {
                keepPlaying = false;
            }

        }

        displayEndingMessage(consoleIo, hands, winningHands, losingHands);

    }

    public void displayEndingMessage(ConsoleIO consoleIo, int hands, int winningHands, int losingHands) {
        consoleIo.printStringToConsole("You have choosen to leave the table after " + hands + " hands.");
        consoleIo.printStringToConsole("You won " + winningHands + " and lost " + losingHands + "");
        consoleIo.printStringToConsole("Thanks for playing.");
    }

    public boolean playAHandOfBlackJack(ConsoleIO consoleIo) {

        consoleIo.printStringToConsole("\n\n");

        boolean winner = false;
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        Hand player = new Hand();

        // Draw two cards for the dealer and place one face up.
        Hand dealer = new Hand();

        Card card = deck.draw();
        card.setFaceUp(true);
        dealer.add(card);

        card = deck.draw();
        card.setFaceUp(false);
        dealer.add(card);

        consoleIo.printStringToConsole("The dealer drew two cards.");

        // Draw two cards for player.
        player.add(deck.draw());

        player.add(deck.draw());

        consoleIo.printStringToConsole("You have drawn two cards.");

        displayStatus(dealer, player, consoleIo);
        boolean keepPlaying = true;

        while (keepPlaying) {
            int inputHitOrStay = consoleIo.getUserIntInputRange("Would you like to hit or stay?\n \"1\" to hit, \"2\" to stay.", 1, 2);

            if (inputHitOrStay == 1) {
                player.add(deck.draw());
                displayStatus(dealer, player, consoleIo);

                if (player.getTotalPoints() > 21) {
                    keepPlaying = false;
                }
            } else {
                consoleIo.printStringToConsole("You have choosen to stay.");
                keepPlaying = false;
            }
        }

        // Check to see if the player has lost.
        if (player.getTotalPoints() <= 21) {
            // If not the extremely simple 'AI' dealer will keep drawing cards until he gets more points than the player.
            consoleIo.printStringToConsole("It is now the dealers turn.");
            dealer.flipAllCardsUp();
            while (dealer.getTotalPoints() < player.getTotalPoints()) {
                dealer.add(deck.draw());
                calculateTotalPoints(dealer);
            }

            displayStatus(dealer, player, consoleIo);

        }

        winner = applyWinOrLoseLogic(player, consoleIo, dealer);

        return winner;
    }

    public boolean applyWinOrLoseLogic(Hand player, ConsoleIO consoleIo, Hand dealer) {
        boolean winner;
        if (player.getTotalPoints() > 21) {
            consoleIo.printStringToConsole("You have gone over 21.");
            winner = false;
        } else if (dealer.getTotalPoints() > 21) {
            consoleIo.printStringToConsole("The House has gone over 21.  You Win.");
            winner = true;
        } else if (dealer.getTotalPoints() < player.getTotalPoints()) {
            consoleIo.printStringToConsole("You have " + player.getTotalPoints() + " and the house has " + dealer.getTotalPoints() + ".");
            consoleIo.printStringToConsole("You have won.");
            winner = true;
        } else {
            consoleIo.printStringToConsole("You have " + player.getTotalPoints() + " and the house has " + dealer.getTotalPoints() + ".");
            if (player.getTotalPoints() == dealer.getTotalPoints()) {
                consoleIo.printStringToConsole("In a tie, the house wins.");
                winner = false;
            } else {
                consoleIo.printStringToConsole("The house wins.");
                winner = false;
            }
        }
        return winner;
    }

    public void calculateTotalPoints(Hand hand) {

        // Calculate points with Ace as 11.
        int points = 0;
        int totalPoints = 0;
        String handValue = "";

        for (Card yourCard : hand.getHand()) {
            points = convertToPoints(yourCard.getCardNumber(), true);
            totalPoints += points;
            handValue += points + ",";
        }
        handValue = handValue.substring(0, handValue.length() - 1);

        if (totalPoints > 21) {
            // Recalculate points with Ace as 1.
            points = 0;
            totalPoints = 0;
            handValue = "";
            for (Card yourCard : hand.getHand()) {
                points = convertToPoints(yourCard.getCardNumber(), false);
                totalPoints += points;
                handValue += points + ",";
            }
            handValue = handValue.substring(0, handValue.length() - 1);

        }

        hand.setHandValue(handValue);
        hand.setTotalPoints(totalPoints);
    }

    public void displayStatus(Hand dealer, Hand hand, ConsoleIO consoleIo) {
        consoleIo.printStringToConsole(dealer.sketch());
        consoleIo.printStringToConsole("________________________________________");
        consoleIo.printStringToConsole(hand.sketch());
        consoleIo.printStringToConsole("You have " + hand.getHand().size() + " cards.");
        calculateTotalPoints(hand);
        calculateTotalPoints(dealer);

        consoleIo.printStringToConsole("Your cards are: " + hand.getHandValue() + " for a total of " + hand.getTotalPoints());
        consoleIo.printStringToConsole("The Dealer has: " + dealer.getHandValue() + " for a total of " + dealer.getTotalPoints());

    }

    public int convertToPoints(int cardValue, boolean aceHigh) {
        int value = 0;

        if (cardValue > 10) {
            value = 10;
        } else {
            value = cardValue;
        }

        if (aceHigh) {
            if (cardValue == 1) {
                value = 11;
            }
        }
        return value;
    }

}
