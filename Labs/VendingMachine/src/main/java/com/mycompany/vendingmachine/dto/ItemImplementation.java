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
public class ItemImplementation implements Item {
    private String itemName;
    private int itemCost; // in pennies
    private int quantityInInventory;
    private int id;

    /**
     * @return the itemName
     */
    @Override
    public String getItemName() {
        return itemName;
    }

    /**
     * @return the itemCost
     */
    @Override
    public int getItemCost() {
        return itemCost;
    }

    /**
     * @return the quantityInInventory
     */
    @Override
    public int getQuantityInInventory() {
        return quantityInInventory;
    }

    /**
     * @return the id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param itemName the itemName to set
     */
    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @param itemCost the itemCost to set
     */
    @Override
    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    /**
     * @param quantityInInventory the quantityInInventory to set
     */
    @Override
    public void setQuantityInInventory(int quantityInInventory) {
        this.quantityInInventory = quantityInInventory;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
}
