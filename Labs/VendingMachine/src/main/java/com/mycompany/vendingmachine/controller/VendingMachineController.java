/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dao.Inventory;
import com.mycompany.vendingmachine.dto.Item;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {
    
    ConsoleIO consoleIo = new ConsoleIO();
    Inventory inventory = new Inventory();
    int balance;
    
    public void run() {
        
        boolean keepLooping = true;
        
        while (keepLooping) {
            
            displayChart();
            
            String mainMenu = "== Main Menu == \n"
                    + "1. Put in money\n"
                    + "2. Push the select button\n"
                    + "3. Push the coin return button\n"
                    + "0. Walk Away";
            
            int selection = consoleIo.getUserIntInputRange(mainMenu, 0, 5);
            
            switch (selection) {
                case 1:
                    inputMoney();
                    break;
                case 2:
                    selectItem();
                    break;
                case 3:
                    refundMoney();
                    break;
                case 4:
                    addItem();
                    break;
                case 5:
                    refillItem();
                    break;
                case 0:
                    keepLooping = false;
                    walkAway();
                    break;
                
            }
        }
    }
    
    private void vendItem(Item item) {
        
        dispenseItem(item);
        dispenseChange();
        
    }
    
    private void dispenseItem(Item item) {

        //consoleIo.printToConsole("Vending one " + item.getItemName(), false);
        consoleIo.printStringToConsole("Vending one " + item.getItemName());
        
        for (int x = 0; x < 20; x++) {
            
            try {
                Thread.sleep(300);
                //consoleIo.printToConsole(".", false);
                consoleIo.printStringToConsole(".");
            } catch (InterruptedException ex) {
                Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        consoleIo.printStringToConsole("Done!");
        
        item.setQuantityInInventory(item.getQuantityInInventory() - 1);
        balance -= item.getItemCost();
        inventory.update(item);
    }
    
    private void inputMoney() {
        
        balance += consoleIo.getUserIntInputPositive("Please Enter An Amount To Input Into The Simulated Machine:");
        
        if (!doesMachineHaveInventory()) {
            refundMoney();
        }
        
    }
    
    private void selectItem() {
        Item selection = askForSelection();
        
        if (selection != null) {
            int price = priceSelected(selection);
            
            if (price > balance) {
                informUserOfInsufficentFunds();
            } else if (doesMachineHaveInventory(selection)) {
                vendItem(selection);
            }
        } else {
            consoleIo.printStringToConsole("That button is not associated with an item.");
        }
        
    }
    
    private void refundMoney() {
        dispenseChange();
    }
    
    private void dispenseChange() {
        
        Change change = new ChangeMaker().makeChange(balance);
        
        int quarters = change.getQuarters();
        int dimes = change.getDimes();
        int nickles = change.getNickles();
        int pennies = change.getPennies();
        
        String receipt = "\nChange generated:\n"
                + " " + quarters + " Quarters\n"
                + " " + dimes + " Dimes\n"
                + " " + nickles + " Nickles\n"
                + " " + pennies + " Pennies\n";
        
        consoleIo.printStringToConsole(receipt);
        balance = 0;
        
    }
    
    private void informUserOfInsufficentFunds() {
        
        consoleIo.printStringToConsole("The Balance Currently In The Machine Is Insufficent For This Action.");
    }
    
    private void displayChart() {
        
        String chart = "\nCurrent Balance: " + balance + " cents.\n";
        chart += "ID#\tItem Name:\tItem Cost:\tItem Quantity:\n";
        for (Item item : inventory.getList()) {
            chart += item.getId() + ") \t" + item.getItemName() + "\t" + item.getItemCost() + "\t\t" + item.getQuantityInInventory() + "\n";
            
        }
        
        consoleIo.printStringToConsole(chart);
        
    }
    
    private void addItem() {
        
        Item item = new Item();
        
        String itemName = consoleIo.getUserStringInput("Please Enter The Name Of This Item.");
        item.setItemName(itemName);
        
        int itemCost = consoleIo.getUserIntInputPositive("Please Enter The Value ( in pennies) of one " + itemName + ".");
        item.setItemCost(itemCost);
        
        int quantity = consoleIo.getUserIntInputPositive("Please Enter The Quantity For " + itemName + ".");
        item.setQuantityInInventory(quantity);
        
        inventory.create(item);
    }
    
    private void refillItem() {
        
        Item item = askForSelection();
        int quantity = consoleIo.getUserIntInputPositive("Please Enter The New Quantity For " + item.getItemName() + ":");
        item.setQuantityInInventory(quantity);
        inventory.update(item);
    }
    
    private Item askForSelection() {
        displayChart();
        int idNumber = consoleIo.getUserIntInputPositive("Please Enter An ID Number To Select:");
        Item item = inventory.get(idNumber);
        return item;
    }
    
    private boolean doesMachineHaveInventory() {
        boolean hasInventory = true;
        
        if (inventory.getList().size() < 1) {
            hasInventory = false;
        }
        if (inventory.getList() == null) {
            hasInventory = false;
        }
        
        for (Item item : inventory.getList()) {
            hasInventory = doesMachineHaveInventory(item);
        }
        
        return hasInventory;
    }
    
    private boolean doesMachineHaveInventory(Item item) {
        Boolean hasInventory = true;
        
        if (item.getQuantityInInventory() < 1) {
            hasInventory = false;
        }
        return hasInventory;
    }
    
    private int priceSelected(Item item) {
        
        return item.getItemCost();
    }
    
    private void walkAway() {
        consoleIo.printStringToConsole("You Have Walked Away");
    }
    
}
