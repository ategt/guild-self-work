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
public class Hand {

    private java.util.ArrayList<Card> hand = new java.util.ArrayList<>();

    public void add(Card card) {
        hand.add(card);
    }
    
    public java.util.ArrayList<Card> getHand(){
        return hand;
    }

    public void clear() {
        hand.clear();
    }

    public boolean remove(Card card) {
        return hand.remove(card);
    }

    public String sketch() {

        String tableDrawing = "";
        String[] tableSketch = new String[5];

        for (int x = 0; x < tableSketch.length; x++) {

            tableSketch[x] = "";

            for (Card card : hand) {
                tableSketch[x] += card.sketchCard()[x] + " ";

            }

        }

        for (String cardLine : tableSketch) {
            tableDrawing += cardLine.trim() + "\n";
        }
        return tableDrawing;

    }

}
