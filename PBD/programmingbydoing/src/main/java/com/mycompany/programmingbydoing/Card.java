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

    private final char SPADE = '\u2660';
    private final char DIAMOND = '\u2666';
    private final char CLUB = '\u2663';
    private final char HEART = '\u2764';

    private boolean faceUp;
    
    private int cardValue;
    private int cardNumber;
    private int suitNumber;
    private String cardSuit;
    private String cardCode;
    private String cardUnicodeValue;
    private String cardUnicodeSuit;
    private String cardFace;
    private String cardString;

    public Card(int value) {
        generateCard(value);
    }

    private void generateCard(int value) {
        this.cardValue = value;
        this.suitNumber = generateSuitNumber(value);
        this.cardSuit = generateSuit(suitNumber);
        this.cardNumber = generateNumber(value);
        this.cardCode = generateCode(cardNumber, cardSuit);
        this.cardUnicodeValue = generateUnicodeValue(cardNumber, suitNumber);
        this.cardFace = generateCardFace(cardNumber);
        this.cardUnicodeSuit = generateUnicodeSuit(suitNumber);
        this.cardString = generateCardString(cardFace, cardUnicodeSuit);
        faceUp = true;
    }

    public String[] sketchCard() {
        return sketchCard(isFaceUp());
    }

    public String[] sketchCard(boolean faceUp) {
        String[] cardLines = new String[5];

        cardLines[0] = "+--------+";
        if (faceUp) {
            cardLines[1] = "| " + cardString;
            int lengthOfLineOne = cardLines[1].length();
            for (int x = lengthOfLineOne; x < 9; x++) {
                cardLines[1] += " ";
            }
            cardLines[1] += "|";
            cardLines[2] = "|        |";
            cardLines[3] = cardLines[2];
        } else {
            cardLines[2] = "|XXXXXXXX|";
            cardLines[1] = cardLines[2];
            cardLines[3] = "|XX SG XX|";
        }
        cardLines[4] = cardLines[2];
        return cardLines;
    }

    public String generateCardString() {
        return generateCardString(cardFace, cardUnicodeSuit);
    }

    public String generateCardString(String cardFace, String cardUnicodeSuit) {
        return cardFace + "" + cardUnicodeSuit;
    }

    public String generateCardFace() {
        return generateCardFace(cardNumber);
    }

    public String generateCardFace(int number) {
        String returnString = "???????";

        switch (number) {
            case 0:
                returnString = "A";
                break;
            case 1:
                returnString = "2";
                break;
            case 2:
                returnString = "3";
                break;
            case 3:
                returnString = "4";
                break;
            case 4:
                returnString = "5";
                break;
            case 5:
                returnString = "6";
                break;
            case 6:
                returnString = "7";
                break;
            case 7:
                returnString = "8";
                break;
            case 8:
                returnString = "9";
                break;
            case 9:
                returnString = "10";
                break;
            case 10:
                returnString = "J";
                break;
            case 11:
                returnString = "Q";
                break;
            case 12:
                returnString = "K";
                break;

        }

        return returnString;
    }

    public String generateUnicodeValue() {
        return generateUnicodeValue(cardNumber, suitNumber);
    }

    public String generateUnicodeSuit() {
        return generateUnicodeSuit(suitNumber);
    }

    public String generateUnicodeValue(int cardNumber, int suitNumber) {

        return cardNumber + "" + generateUnicodeSuit(suitNumber);
    }

    private String generateCode(int cardNumber, String cardSuit) {
        return cardNumber + "" + cardSuit;

    }

    private int generateNumber(int value) {

        int number = value % 13;

        return number;
    }

    public String generateUnicodeSuit(int suitNumber) {
        Character unicodeSuit = '\u0000';

        switch (suitNumber) {
            case 0:
                unicodeSuit = SPADE;
                break;
            case 1:
                unicodeSuit = DIAMOND;
                break;
            case 2:
                unicodeSuit = CLUB;
                break;
            case 3:
                unicodeSuit = HEART;
                break;

        }

        return unicodeSuit.toString();
    }

    private String generateSuit() {
        return generateSuit(suitNumber);
    }

    private String generateSuit(Integer suitNumber) {
        String newSuit;

        switch (suitNumber) {
            case 0:
                newSuit = "Spades";
                break;
            case 1:
                newSuit = "Diamonds";
                break;
            case 2:
                newSuit = "Clubs";
                break;
            case 3:
                newSuit = "Hearts";
                break;
            default:
                newSuit = "Joker";

        }

        return newSuit;
    }

    public int generateSuitNumber() {
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
        return cardNumber + 1;
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
    public String getCardUnicodeValue() {
        return cardUnicodeValue;
    }

    /**
     * @param cardASCIIValue the cardASCIIValue to set
     */
    private void setCardUnicodeValue(String cardASCIIValue) {
        this.cardUnicodeValue = cardASCIIValue;
    }

    public String getCardFace() {
        return cardFace;
    }

    public String getCardString() {
        return cardString;
    }

    /**
     * @return the faceUp
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * @param faceUp the faceUp to set
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

}
