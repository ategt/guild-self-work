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
public class Card {

    private int cardValue;
    private int cardNumber;
    private int suitNumber;
    private String cardSuit;
    private String cardCode;
    private String cardASCIIValue;

    public Card(int value) {
        generateCard(value);
    }

    private void generateCard(int value) {
        this.cardValue = value;
        this.suitNumber = generateSuitNumber(value);
        this.cardSuit = generateSuit(suitNumber);
        this.cardNumber = generateNumber(value);
        this.cardCode = generateCode(cardNumber, cardSuit);
        this.cardASCIIValue = generateASCIIValue(cardNumber, cardSuit);
    }

    
    
    public String generateASCIIValue() {
        return generateASCIIValue(cardNumber, suitNumber);
    }
    
    public String getASCIISuit(){
        return getASCIISuit(suitNumber);
    }
    public String getASCIISuit(int suitNumber){
        String asciiSuit = "";

        return asciiSuit;
    }
    
    public String generateASCIIValue(int cardNumber, int suitNumber) {
        
        
        
        return  
    }

    private String generateCode(int cardNumber, String cardSuit) {
        return cardNumber + "" + cardSuit;

    }

    private int generateNumber(int value) {

        int number = value % 13;

        return number;
    }

    private String generateSuit(){
        return generateSuit(suitNumber);
    }
    private String generateSuit(Integer suitNumber) {
        String newSuit;


        switch (suitNumber) {
            case 0:
                newSuit = "Clubs";
                break;
            case 1:
                newSuit = "Hearts";
                break;
            case 2:
                newSuit = "Diamonds";
                break;
            case 3:
                newSuit = "Spades";
                break;
            default:
                newSuit = "Joker";

        }

        return newSuit;
    }
    
    public int generateSuitNumber(){
        return generateSuitNumber(cardValue);
    }

    public int generateSuitNumber(Integer value) {

        int suitNumber = (int) Math.round(Math.floor(value.floatValue() / 13f));
        return suitNumber;
    }

    /**
     * @return the cardValue
     */
    public int getCardValue() {
        return cardValue;
    }

    /**
     * @param cardValue the cardValue to set
     */
    private void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    /**
     * @return the cardNumber
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    private void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the cardSuit
     */
    public String getCardSuit() {
        return cardSuit;
    }

    /**
     * @param cardSuit the cardSuit to set
     */
    private void setCardSuit(String cardSuit) {
        this.cardSuit = cardSuit;
    }

    /**
     * @return the cardCode
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * @param cardCode the cardCode to set
     */
    private void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     * @return the cardASCIIValue
     */
    public String getCardASCIIValue() {
        return cardASCIIValue;
    }

    /**
     * @param cardASCIIValue the cardASCIIValue to set
     */
    private void setCardASCIIValue(String cardASCIIValue) {
        this.cardASCIIValue = cardASCIIValue;
    }

}
