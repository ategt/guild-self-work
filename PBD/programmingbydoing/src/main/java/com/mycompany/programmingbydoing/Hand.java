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

    private int points = 0;
    private int totalPoints = 0;
    private String handValue = "";

    
    
    public void add(Card card) {
        hand.add(card);
    }

    public java.util.ArrayList<Card> getHand() {
        return hand;
    }

    public void clear() {
        hand.clear();
    }

    public boolean remove(Card card) {
        return hand.remove(card);
    }

    public void flipAllCardsUp(){
        
        for (Card card : hand){
            card.setFaceUp(true);
        }
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

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the totalPoints
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * @param totalPoints the totalPoints to set
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * @return the handValue
     */
    public String getHandValue() {
        return handValue;
    }

    /**
     * @param handValue the handValue to set
     */
    public void setHandValue(String handValue) {
        this.handValue = handValue;
    }

}
