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
public class Item {
    private String itemName;
    private int itemCost; // in pennies
    private int quantityInInventory;
    private int id;

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @return the itemCost
     */
    public int getItemCost() {
        return itemCost;
    }

    /**
     * @return the quantityInInventory
     */
    public int getQuantityInInventory() {
        return quantityInInventory;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @param itemCost the itemCost to set
     */
    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    /**
     * @param quantityInInventory the quantityInInventory to set
     */
    public void setQuantityInInventory(int quantityInInventory) {
        this.quantityInInventory = quantityInInventory;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
