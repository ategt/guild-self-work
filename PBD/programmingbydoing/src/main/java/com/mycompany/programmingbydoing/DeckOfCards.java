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
public class DeckOfCards {

    java.util.ArrayList<Card> deck = new java.util.ArrayList<>();

    DeckOfCards() {
        buildDeck();
    }

    private void buildDeck() {
        java.util.ArrayList<Card> newDeck = new java.util.ArrayList<>();

        for (int x = 0; x < 52; x++) {
            newDeck.add(new Card(x));
        }

        deck = newDeck;

    }

    public void shuffle() {
        java.util.Random random = new java.util.Random();

        java.util.ArrayList<Card> newDeck = new java.util.ArrayList<>();

        //for (int x = 0; x < deck.size(); x++) {
        while (deck.size() > 0) {

            Integer randomCard = random.nextInt(deck.size());
            Card card = deck.get(randomCard);
            newDeck.add(card);
            deck.remove(card);
        }

        deck = newDeck;

    }
    
    public int cardsRemaining(){
        return deck.size();
    }

    public void discard(Card card) {

        deck.add(card);
    }

    public Card draw() {

        return deck.remove(0);
    }

}
