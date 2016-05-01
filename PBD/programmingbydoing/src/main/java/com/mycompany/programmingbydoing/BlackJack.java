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

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        Card card = deck.draw();
        System.out.println("Card Value: " + card.getCardValue());
        System.out.println("Card Number: " + card.getCardNumber());
        System.out.println("Card Face: " + card.getCardFace());
        System.out.println("Card Unicode: " + card.getCardUnicodeValue());

        System.out.println("Card String: " + card.getCardString());

        String[] cardSketch = card.sketchCard();

        for (String cardLine : cardSketch) {
            System.out.println(cardLine);
        }

    }

}
