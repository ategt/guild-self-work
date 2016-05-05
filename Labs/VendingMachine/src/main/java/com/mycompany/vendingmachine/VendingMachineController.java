/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

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
        
        while (keepLooping){
        
        String mainMenu = "== Main Menu == \n" + 
                    "1. Put in money\n" + 
                    "2. Push the select button\n" +
                    "3. Push the coin return button\n" +
                    "0. Walk Away";
        
        int selection = consoleIo.getUserIntInputRange(mainMenu, 0, 5);
        
        switch(selection){
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
    }}

    private void vendItem(Item item) {

        dispenseItem(item);
        dispenseChange();

    }
    
    private void dispenseItem(Item item){
        
        consoleIo.printToConsole("Vending one " + item.getItemName(), false);
        
        for ( int x = 0 ; x < 20 ; x++ ){
            
            consoleIo.printToConsole(".", false);
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        consoleIo.printStringToConsole("Done!");
        
    }

    private void inputMoney() {


        balance = consoleIo.getUserIntInputPositive("Please Enter An Amount To Input Into The Simulated Machine:");

        if (doesMachineHaveInventory()) {

            //displayChart();

           // ask for selection
        } else {
            refundMoney();
        }

    }

    private void selectItem() {
        //Item item = askForSelection();
        Item selection = askForSelection();

            int price = priceSelected(selection);

            if (price > balance) {
                informUserOfInsufficentFunds();
            } else if (doesMachineHaveInventory(selection)) {
                vendItem(selection);
            }

        
        
    }

    private void refundMoney() {
        dispenseChange();
    }

    private void dispenseChange() {

        Change change = new Change(balance);

        int quarters = change.getQuarters();
        int dimes = change.getDimes();
        int nickles = change.getNickles();
        int pennies = change.getPennies();

        String receipt = "Change generated:\n"
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

        String chart = "";
        for ( Item item : inventory.getList() ) {
            chart += item.getId() + "" + item.getItemName() + "" + item.getItemCost() + "" + item.getQuantityInInventory() + "\n";
            
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
        
        for ( Item item : inventory.getList() ){
            
            hasInventory = doesMachineHaveInventory(item);
            
        }
        return hasInventory;
    }

    private boolean doesMachineHaveInventory(Item item) {
        Boolean hasInventory = true;
        
        if (item.getQuantityInInventory() < 1 ){
            hasInventory = false;
        }
        return hasInventory;
    }

    private int priceSelected(Item item){
        
        
        return item.getItemCost();
    }
    
    private void walkAway(){
        consoleIo.printStringToConsole("You Have Walked Away");
    }

    
}
