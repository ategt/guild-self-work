/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import com.mycompany.vendingmachine.interfaces.Change;

/**
 *
 * @author apprentice
 */
public class ChangeImplementation implements Change {

    private int quarters;
    private int nickles;
    private int dimes;
    private int pennies;
    private int id;

    /**
     * @return the quarters
     */
    @Override
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the nickles
     */
    @Override
    public int getNickles() {
        return nickles;
    }

    /**
     * @return the dimes
     */
    @Override
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the pennies
     */
    @Override
    public int getPennies() {
        return pennies;
    }

    /**
     * @param quarters the quarters to set
     */
    @Override
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /**
     * @param nickles the nickles to set
     */
    @Override
    public void setNickles(int nickles) {
        this.nickles = nickles;
    }

    /**
     * @param dimes the dimes to set
     */
    @Override
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /**
     * @param pennies the pennies to set
     */
    @Override
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    /**
     * @return the id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

}
