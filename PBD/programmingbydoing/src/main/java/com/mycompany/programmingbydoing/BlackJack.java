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

        System.out.println("Welcome to BlackJack.");
        System.out.println("");

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

        System.out.println("The dealer drew two cards.");

        // Draw two cards for player.
        player.add(deck.draw());

        player.add(deck.draw());

        System.out.println("You have drawn two cards.");

        displayStatus(dealer, player);
        boolean keepPlaying = true;

        while (keepPlaying) {
            int inputHitOrStay = consoleIo.getUserIntInputRange("Would you like to hit or stay?\n \"1\" to hit, \"2\" to stay.", 1, 2);

            if (inputHitOrStay == 1) {
                player.add(deck.draw());
                displayStatus(dealer, player);

                if (player.getTotalPoints() > 21) {
                    System.out.println("You have gone over 21.");
                    keepPlaying = false;
                }
            } else {
                System.out.println("You have choosen to stay.");
                keepPlaying = false;
            }
        }

        // Check to see if the player has lost.
        if (player.getTotalPoints() <= 21) {
            // If not the extremely simple 'AI' dealer will keep drawing cards until he gets more points than the player.
            System.out.println("It is now the dealers turn.");
            dealer.flipAllCardsUp();
            while (dealer.getTotalPoints() < player.getTotalPoints()) {
                dealer.add(deck.draw());
                calculateTotalPoints(dealer);
            }

            displayStatus(dealer, player);

        }

        if (player.getTotalPoints() > 21) {
            System.out.println("You have gone over 21.");

        } else if (dealer.getTotalPoints() > 21) {
            System.out.println("The House has gone over 21.  You Win.");
        } else if (dealer.getTotalPoints() < player.getTotalPoints()) {
            System.out.println("You have " + player.getTotalPoints() + " and the house has " + dealer.getTotalPoints() + ".");
            System.out.println("You have won.");
        } else {
            System.out.println("You have " + player.getTotalPoints() + " and the house has " + dealer.getTotalPoints() + ".");
            if (player.getTotalPoints() == dealer.getTotalPoints()) {
                System.out.println("In a tie, the house wins.");
            } else {
                System.out.println("The house wins.");
            }
        }

    }

    public void calculateTotalPoints(Hand hand) {
        //System.out.println("You have " + hand.getHand().size() + " cards.");
        

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

        int aceHighTotalPoints = totalPoints;
        String aceHighHandValue = handValue;
        
        if (totalPoints > 21){
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

        int aceLowTotalPoints = totalPoints;
        String aceLowHandValue = handValue;
        }

        //System.out.println("Your cards are: " + handValue + " for a total of " + totalPoints);
        hand.setHandValue(handValue);
        hand.setTotalPoints(totalPoints);
        //hand.setPoints(points);
        //return totalPoints;

    }

    public void displayStatus(Hand dealer, Hand hand) {
        System.out.println(dealer.sketch());
        System.out.println("________________________________________");
        System.out.println(hand.sketch());
        System.out.println("You have " + hand.getHand().size() + " cards.");
//        int points = 0;
//        int totalPoints = 0;
//        String handValue = "";
//        for (Card yourCard : hand.getHand()) {
//            points = convertToPoints(yourCard.getCardNumber());
//            totalPoints += points;
//            handValue += points + ",";
//        }
//        handValue = handValue.substring(0, handValue.length() - 2);
        calculateTotalPoints(hand);
        calculateTotalPoints(dealer);

        System.out.println("Your cards are: " + hand.getHandValue() + " for a total of " + hand.getTotalPoints());
        System.out.println("The Dealer has: " + dealer.getHandValue() + " for a total of " + dealer.getTotalPoints());

        //return hand.getTotalPoints();
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

    public static void main(String[] args) {
        new BlackJack().run();

//        DeckOfCards deck = new DeckOfCards();
//
//        Hand hand = new Hand();
//
//        for (int i = 0; i < 5; i++) {
//            hand.add(deck.draw());
//        }
//
//        System.out.println(hand.sketch());
//        System.out.println(deck.cardsRemaining() + " cards remaining.");
//
//        deck.shuffle();
//
//        hand = new Hand();
//
//        for (int i = 0; i < 5; i++) {
//            hand.add(deck.draw());
//        }
//
//        System.out.println(hand.sketch());
//        System.out.print("     ");
//        for (Card card : hand.getHand()) {
//            System.out.print(card.getCardNumber() + "          ");
//        }
//        System.out.println("");
//        System.out.println(hand.getHand().size() + " cards in hand.");
//        System.out.println(deck.cardsRemaining() + " cards remaining.");
//
//        
//        Card card = deck.draw();
//        Card card2 = deck.draw();
//        Card card3 = deck.draw();
//        Card card4 = deck.draw();
//        
//        System.out.println("Card Value: " + card.getCardValue());
//        System.out.println("Card Number: " + card.getCardNumber());
//        System.out.println("Card Face: " + card.getCardFace());
//        System.out.println("Card Unicode: " + card.getCardUnicodeValue());
//
//        System.out.println("Card String: " + card.getCardString());
//
//        String[] cardSketch = card.sketchCard();
//        String[] tableSketch = new String[5];
//        
//        for (int x = 0 ; x < 5 ; x++){
//            tableSketch[x] = card.sketchCard()[x] + " " + card2.sketchCard()[x] + " " + card3.sketchCard()[x] + " " + card4.sketchCard()[x];
//        }
//        
//        for (String cardLine : cardSketch) {
//            System.out.println(cardLine);
//            //tableSketch
//        }
// 
//        System.out.println("");
//        
//        for (String cardLine : tableSketch) {
//            System.out.println(cardLine);
//            //tableSketch
//        }
//
// 
    }

}
