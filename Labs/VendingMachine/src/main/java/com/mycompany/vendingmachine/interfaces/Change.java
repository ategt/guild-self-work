/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.interfaces;

/**
 *
 * @author apprentice
 */
public interface Change {

    /**
     * @return the dimes
     */
    int getDimes();

    /**
     * @return the id
     */
    int getId();

    /**
     * @return the nickles
     */
    int getNickles();

    /**
     * @return the pennies
     */
    int getPennies();

    /**
     * @return the quarters
     */
    int getQuarters();

    /**
     * @param dimes the dimes to set
     */
    void setDimes(int dimes);

    /**
     * @param id the id to set
     */
    void setId(int id);

    /**
     * @param nickles the nickles to set
     */
    void setNickles(int nickles);

    /**
     * @param pennies the pennies to set
     */
    void setPennies(int pennies);

    /**
     * @param quarters the quarters to set
     */
    void setQuarters(int quarters);
    
}
