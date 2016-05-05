/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

/**
 *
 * @author apprentice
 */
public class Change {

    private int quarters;
    private int nickles;
    private int dimes;
    private int pennies;
    private int id;

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the nickles
     */
    public int getNickles() {
        return nickles;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }

    /**
     * @param quarters the quarters to set
     */
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /**
     * @param nickles the nickles to set
     */
    public void setNickles(int nickles) {
        this.nickles = nickles;
    }

    /**
     * @param dimes the dimes to set
     */
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /**
     * @param pennies the pennies to set
     */
    public void setPennies(int pennies) {
        this.pennies = pennies;
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

}
