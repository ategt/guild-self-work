/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.consoleio.exceptions.UserWantsOutException;
import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.StateDao;
import com.mycompany.flooringmastery.dto.Order;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryController {

//    public static void main(String[] args) {
//        
//        String menuString = "*****************************************************************************"; 
//        System.out.println(menuString.length() + " - length.");
//    }
    ConsoleIO consoleIo = new ConsoleIO();

    ProductDao productDao = new ProductDao();
    StateDao stateDao = new StateDao();
    OrderDao orderDao = new OrderDao(productDao, stateDao);

    public void run() {
        boolean done = false;

        //String border = new String(new char[77]).replaceAll('\0','*');
        String border = "*****************************************************************************";

        String titleString = "Flooring Program";
        String menuString = border + "\n"
                + "*" + padText(75, "") + "*\n"
                + "*" + centerText(75, titleString) + "*\n"
                + "*" + padText(75, optionText) + "*\n"
                + "*" + padText(75, "") + "*\n"
                + border;

        while (!done) {

            int option = consoleIo.getUserIntInputRange(menuString, 0, 6);

            switch (option) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    save();
                    break;
                case 0:
                    displayExitMessage();
                    done = true;
                    break;

            }

        }

    }

    private void displayOrders() {

        try {
            java.util.Date orderDate = consoleIo.getUserDate("What Date Would You Like To See Orders For?:\n The System Likes YYYY-MM-DD Format Best.");

            List<Order> ordersForDate = orderDao.searchByDate(orderDate);

            if (ordersForDate.size() < 1) {
                consoleIo.printStringToConsole("No Records Could Be Found Matching That Date.");
            } else {
                displayOrders(ordersForDate);
            }
        } catch (UserWantsOutException ex) {
            consoleIo.printStringToConsole("You Have Choosen To Return To The Main Menu.");
        }

    }

    private void displayOrders(List<Order> orders) {
        String seperator = "------------------------------------------------------------------------------";

        for (Order order : orders) {
            displayOrder(order);

            consoleIo.printStringToConsole(seperator);

        }

    }

    private void displayOrder(Order order){
        String orderString = convertOrderToString(order);
        consoleIo.printStringToConsole(orderString);
        
    }
    
    private String convertOrderToString(Order order){
        String orderString = "";
        order.
        
        
        return orderString;
    }
    
}
