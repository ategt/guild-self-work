/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {
    
    ConsoleIO consoleIo = new ConsoleIO();
    Inventory inventory = new Inventory();
    int balance;
    
    public void run() {
        
    }
    
    private void vendItem(Item item) {
        
        
        displayChange();
        
    }
    
    private void inputMoney() {
        
        Item selection;
        int price;
        
        balance = consoleIo.getUserIntInputPositive("Please Enter An Amount To Input Into The Simulated Machine:");
        
        if (doesMachineHaveInventory()) {
            
            displayChart();
            
            selection = askForSelection();
            
            price = priceSelected(selection);
            
            if (price > balance) {
                informUserOfInsufficentFunds();
            } else if (doesMachineHaveInventory(selection)) {
                vendItem(selection);
            }
            
        }
        
    }
    
    private void selectItem() {
        
    }
    
    private void refundMoney() {
        displayChange();
    }
    
    private void displayChange() {
        
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
    
}
