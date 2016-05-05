/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague;

/**
 *
 * @author apprentice
 */
public class Player {
 
    private String playerName;
    private int id;
    private float battingAverage;
    private int playerNumber;
    
    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the battingAverage
     */
    public float getBattingAverage() {
        return battingAverage;
    }

    /**
     * @param battingAverage the battingAverage to set
     */
    public void setBattingAverage(float battingAverage) {
        this.battingAverage = battingAverage;
    }

    /**
     * @return the playerNumber
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * @param playerNumber the playerNumber to set
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }


}
