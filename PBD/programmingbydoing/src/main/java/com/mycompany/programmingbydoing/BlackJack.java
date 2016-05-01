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
        Card card2 = deck.draw();
        Card card3 = deck.draw();
        Card card4 = deck.draw();
        
        System.out.println("Card Value: " + card.getCardValue());
        System.out.println("Card Number: " + card.getCardNumber());
        System.out.println("Card Face: " + card.getCardFace());
        System.out.println("Card Unicode: " + card.getCardUnicodeValue());

        System.out.println("Card String: " + card.getCardString());

        String[] cardSketch = card.sketchCard();
        String[] tableSketch = new String[5];
        
        for (int x = 0 ; x < 5 ; x++){
            tableSketch[x] = card.sketchCard()[x] + " " + card2.sketchCard()[x] + " " + card3.sketchCard()[x] + " " + card4.sketchCard()[x];
        }
        
        for (String cardLine : cardSketch) {
            System.out.println(cardLine);
            //tableSketch
        }
 
        System.out.println("");
        
        for (String cardLine : tableSketch) {
            System.out.println(cardLine);
            //tableSketch
        }

 
    }

}
