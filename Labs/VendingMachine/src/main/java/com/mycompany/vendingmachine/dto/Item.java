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
public interface Item {

    /**
     * @return the id
     */
    int getId();

    /**
     * @return the itemCost
     */
    int getItemCost();

    /**
     * @return the itemName
     */
    String getItemName();

    /**
     * @return the quantityInInventory
     */
    int getQuantityInInventory();

    /**
     * @param id the id to set
     */
    void setId(int id);

    /**
     * @param itemCost the itemCost to set
     */
    void setItemCost(int itemCost);

    /**
     * @param itemName the itemName to set
     */
    void setItemName(String itemName);

    /**
     * @param quantityInInventory the quantityInInventory to set
     */
    void setQuantityInInventory(int quantityInInventory);
    
}
