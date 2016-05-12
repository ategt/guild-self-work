/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.consoleio.exceptions.UserWantsOutException;
import com.mycompany.consoleio.exceptions.UserWantsToDeleteDateException;
import com.mycompany.consoleio.exceptions.UserWantsToDeleteValueException;
import com.mycompany.flooringmastery.dao.ConfigDao;
import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.StateDao;
import com.mycompany.flooringmastery.dto.Config;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exceptions.ConfigurationFileCorruptException;
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
    ConfigDao configDao;
    Config config;

    public void run() {

        try {
            configDao = new ConfigDao();
        } catch (ConfigurationFileCorruptException ex) {
            Logger.getLogger(FlooringMasteryController.class.getName()).log(Level.SEVERE, null, ex);
            consoleIo.printStringToConsole("There is something wrong with the program!");
            consoleIo.printStringToConsole(ex.getMessage());
            try {
                consoleIo.getUserConfirmation("Please Read The Above Message Before Continuing\n Enter \"Y\" To Continue");
            } catch (UserWantsOutException ex1) {
                consoleIo.printStringToConsole(ex1.getMessage());
            } catch (UserWantsToDeleteValueException ex1) {
                consoleIo.printStringToConsole(ex1.getMessage());
            }
        }
        config = configDao.get();

        boolean done = false;

        //String border = new String(new char[77]).replaceAll('\0','*');
        String border = "*****************************************************************************";

        String titleString = "Flooring Program";
//        String menuString = border + "\n"
//                + "*" + padText(75, "") + "*\n"
//                + "*" + centerText(75, titleString) + "*\n"
//                + "*" + padText(75, optionText) + "*\n"
//                + "*" + padText(75, "") + "*\n"
//                + border;

        String menuString = border + "\n"
                + "*\t\n"
                + "*\t" + titleString + "\n"
                + "*\t1. Display Orders\n"
                + "*\t2. Add an Order\n"
                + "*\t3. Edit an Order\n"
                + "*\t4. Remove an Order\n"
                + "*\t5. Save All Orders\n"
                + "*\t0. Quit\n"
                + "*\n"
                + border;

        while (!done) {
            try {
                int option;

                option = consoleIo.getUserIntInputRange(menuString, 0, 6);

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

            } catch (UserWantsOutException ex) {
                done = true;
            } catch (UserWantsToDeleteValueException ex) {
                done = true;
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
        } catch (UserWantsToDeleteValueException ex) {
            consoleIo.printStringToConsole("You Have Choosen To Delete This Value");
        }

    }

    private void displayOrders(List<Order> orders) {
        String seperator = "------------------------------------------------------------------------------";

        for (Order order : orders) {
            displayOrder(order);

            consoleIo.printStringToConsole(seperator);

        }

    }

    private void displayOrder(Order order) {
        String orderString = convertOrderToString(order);
        consoleIo.printStringToConsole(orderString);

    }

    private String convertOrderToString(Order order) {
        //String orderString = "";
        String orderString = orderDao.addLabels(order, System.lineSeparator(), ":\t");

        return orderString;
    }

    private void addOrder() {
        try {
            Order order = inputOrder();

            displayOrder(order);

            boolean confirm = consoleIo.getUserConfirmation("Is this information correct? \n Please Enter \"Y\" To Confirm Or Any Other Key To Escape.");

            if (confirm) {
                orderDao.create(order);
            }
        } catch (UserWantsOutException ex) {
            //Logger.getLogger(FlooringMasteryController.class.getName()).log(Level.SEVERE, null, ex);
            consoleIo.printStringToConsole(ex.getMessage());
        } catch (UserWantsToDeleteValueException ex) {
            consoleIo.printStringToConsole(ex.getMessage());
        }

    }

    private Order inputOrder() throws UserWantsOutException {
        Order newOrder = new Order();

        String customerName = consoleIo.getUserStringInput("Please Enter Customer Name: ");
        newOrder.setName(customerName);

        java.util.Date orderDate = consoleIo.getUserDate("Please Enter Order Date: ");
        newOrder.setDate(orderDate);

        boolean valid = false;
        while (!valid) {
            String customerState = consoleIo.getUserStringInput("Please Enter State: ");
            com.mycompany.flooringmastery.dto.State state = stateDao.get(customerState);
            if (state != null) {
                newOrder.setState(state);
                valid = true;
            }
        }

        double taxRate = consoleIo.getUserDoubleRange("Please Enter Tax Rate: ", 0, 100.00d);
        newOrder.setTaxRate(taxRate);

        boolean validProduct = false;
        while (!validProduct) {
            String customerProduct = consoleIo.getUserStringInput("Please Enter Product Name: ");
            com.mycompany.flooringmastery.dto.Product product = productDao.get(customerProduct);
            if (product != null) {
                newOrder.setProduct(product);
                validProduct = true;
            }
        }

        double area = consoleIo.getUserDoubleRange("Please Enter Area Of Floor: ", 0, Double.MAX_VALUE);
        newOrder.setArea(area);

        double costPerSqrFt = consoleIo.getUserDoubleRange("Please Enter Material Cost Per Square Foot: ", 0, Double.MAX_VALUE);
        newOrder.setCostPerSquareFoot(costPerSqrFt);

        double materialCost = consoleIo.getUserDoubleRange("Please Enter Material Cost: ", 0, Double.MAX_VALUE);
        newOrder.setMaterialCost(materialCost);

        double laborCost = consoleIo.getUserDoubleRange("Please Enter Labor Cost: ", 0, Double.MAX_VALUE);
        newOrder.setLaborCost(laborCost);

        double tax = consoleIo.getUserDoubleRange("Please Enter Total Tax: ", 0, Double.MAX_VALUE);
        newOrder.setTax(tax);

        double totalCost = consoleIo.getUserDoubleRange("Please Enter Total Project Cost: ", 0, Double.MAX_VALUE);
        newOrder.setTotal(totalCost);

        return newOrder;

    }

    private void editOrder() {
        Order order = askForOrder();
        if (order != null) {
            displayOrder(order);
            if (consoleIo.getUserConfirmation("Would You Like To Edit This Order?\n Please Enter \"Y\" To Edit or \"n\" To Return To The Main Menu.")) {
                order = editOrder(order);
                if (order != null) {
                    orderDao.update(order);
                }
            }
        } else {
            consoleIo.printStringToConsole("Information For That Order Could Not Be Found.");
        }

    }

    private Order editOrder(Order order) throws UserWantsOutException {
        Order newOrder = order;

        if (newOrder == null) {
            consoleIo.printStringToConsole("To Delete an Existing Entry, Enter a Dash \"-\".");
            consoleIo.printStringToConsole("You May Leave Lines Empty And Return To Edit Them Later.");
            newOrder = new Order();
        }

        String oldName;
        if (order != null) {
            oldName = order.getName();
        } else {
            oldName = "";
        }

        String customerName = consoleIo.getUserStringInput("\n" + oldName + "\nPlease Enter Customer Name: ");

        if (customerName.equalsIgnoreCase("")) {
            customerName = null;
        } else if (customerName.equalsIgnoreCase("-")) {
            customerName = null;
            newOrder.setName(customerName);
        } else {
            newOrder.setName(customerName);
        }

//
//        //newOrder.setName(customerName);
//        
//        
//                if (inputString.equalsIgnoreCase("")) {
//            inputString = null;
//        } else if (inputString.equalsIgnoreCase("-")) {
//            inputString = null;
//            address.setLastName(inputString);
//        } else {
//            address.setType(inputString);
//
//        }
        java.util.Date orderDate = null;
        String oldDate;
        if (order == null) {
            oldDate = "";
        } else if (order.getDate() != null) {
            java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd-MM-yyyy");

            oldDate = fmt.format(order.getDate());
        } else {
            oldDate = "";
        }

        try {
            orderDate = consoleIo.getUserDate("\n" + oldDate + "\nPlease Enter Order Date: ", true);

            if (orderDate == null) {

                orderDate = order.getDate();
                newOrder.setDate(orderDate);
            } else {
                newOrder.setDate(orderDate);
            }

        } catch (UserWantsToDeleteValueException ex) {
            orderDate = null;
            newOrder.setDate(orderDate);
            order.setDate(orderDate);
            //Logger.getLogger(FlooringMasteryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserWantsToDeleteDateException ex) {
            orderDate = null;
            newOrder.setDate(orderDate);
            order.setDate(orderDate);
        }
//
//        java.util.Date orderDate = consoleIo.getUserDate("Please Enter Order Date: ");
//        newOrder.setDate(orderDate);

        String oldState;
        if (order == null) {
            oldState = "";
        } else if (order.getState() != null) {
            oldState = order.getState().getState();
        } else {
            oldState = "";
        }

        boolean valid = false;
        while (!valid) {
            String customerState = consoleIo.getUserStringInputSimple("\n" + oldState + "\nPlease Enter State: ");

            com.mycompany.flooringmastery.dto.State state = stateDao.get(customerState);

            if (customerState.equalsIgnoreCase("")) {
                customerState = null;
                valid = true;
            } else if (customerState.equalsIgnoreCase("-")) {
                customerState = null;
                newOrder.setState(null);
                valid = true;
            } else if (state != null) {
                newOrder.setState(state);
                valid = true;
            } //newOrder.setName(customerState);

//            
//            com.mycompany.flooringmastery.dto.State state = stateDao.get(customerState);
//            if (state != null) {
//                newOrder.setState(state);
//                valid = true;
//            }
//        }
//        


        double oldTaxRateDouble = 0.0d;
            String oldTaxRate;
            if (order == null) {
                oldTaxRate = "";
            } else if (order.getTaxRate() != 0.0d) {
                oldTaxRate = Double.toString(order.getTaxRate());
                oldTaxRateDouble = order.getTaxRate();
            } else {
                oldTaxRate = "";
            }

            double taxRate;
            try {
                taxRate = consoleIo.getUserDoubleMinMax("\n" + oldTaxRate + "\nPlease Enter Tax Rate: ", 0, 100.00d, oldTaxRateDouble);
                newOrder.setTaxRate(taxRate);

            } catch (UserWantsToDeleteValueException ex) {
                newOrder.setTaxRate(0.0d);
            }

        }

        //newOrder.setTaxRate(taxRate);

        boolean validProduct = false;
        while (!validProduct) {
            String customerProduct = consoleIo.getUserStringInput("Please Enter Product Name: ");
            com.mycompany.flooringmastery.dto.Product product = productDao.get(customerProduct);
            if (product != null) {
                newOrder.setProduct(product);
                validProduct = true;
            }
        }

        double area = consoleIo.getUserDoubleRange("Please Enter Area Of Floor: ", 0, Double.MAX_VALUE);
        newOrder.setArea(area);

        double costPerSqrFt = consoleIo.getUserDoubleRange("Please Enter Material Cost Per Square Foot: ", 0, Double.MAX_VALUE);
        newOrder.setCostPerSquareFoot(costPerSqrFt);

        double materialCost = consoleIo.getUserDoubleRange("Please Enter Material Cost: ", 0, Double.MAX_VALUE);
        newOrder.setMaterialCost(materialCost);

        double laborCost = consoleIo.getUserDoubleRange("Please Enter Labor Cost: ", 0, Double.MAX_VALUE);
        newOrder.setLaborCost(laborCost);

        double tax = consoleIo.getUserDoubleRange("Please Enter Total Tax: ", 0, Double.MAX_VALUE);
        newOrder.setTax(tax);

        double totalCost = consoleIo.getUserDoubleRange("Please Enter Total Project Cost: ", 0, Double.MAX_VALUE);
        newOrder.setTotal(totalCost);

        return newOrder;
    }
}

//Order ID#: 3
//Customer Name: Steve the Awsome,,est.
//State: KC
//Tax Rate: 20.25
//Product Name: Grass
//Area: 150.0
//Cost Per Square Foot: 25.15
//Labor Cost Per Square Foot: 0.75
//Material Cost: 1.55
//Labor Cost: 400.0
//Tax: 3.08
//Total: 4.88
