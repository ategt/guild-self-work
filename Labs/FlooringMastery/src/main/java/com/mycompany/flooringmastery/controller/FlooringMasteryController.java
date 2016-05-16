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
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.State;
import com.mycompany.flooringmastery.exceptions.ConfigurationFileCorruptException;
import com.mycompany.flooringmastery.exceptions.FileCreationException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryController {

    ConsoleIO consoleIo;

    ProductDao productDao;
    StateDao stateDao;
    OrderDao orderDao;
    ConfigDao configDao;

    private void init() {
        try {
            configDao = new ConfigDao();
        } catch (ConfigurationFileCorruptException ex) {
            Logger.getLogger(FlooringMasteryController.class.getName()).log(Level.SEVERE, null, ex);
            consoleIo.printStringToConsole("There is something wrong with the program!");
            consoleIo.printStringToConsole(ex.getMessage());
            try {
                consoleIo.getUserConfirmation("Please Read The Above Message Before Continuing", "\n Enter \"Y\" To Continue");
            } catch (UserWantsOutException | UserWantsToDeleteValueException ex1) {
                consoleIo.printStringToConsole(ex1.getMessage());
            }
        } catch (FileCreationException ex) {
            Logger.getLogger(FlooringMasteryController.class.getName()).log(Level.SEVERE, null, ex);

            try {
                consoleIo.getUserConfirmation("Please Read The Above Message Before Continuing", "\n Enter \"Y\" To Continue");
            } catch (UserWantsOutException | UserWantsToDeleteValueException ex1) {
            }
        }

        consoleIo = new ConsoleIO();

        int response = 0;

        try {
            response = consoleIo.getUserIntInputRange("This program supports two Modes:\n 1 - Test Mode\n 2 - Production Mode\n Please Choose a Mode to Continue", 1, 2, "You must choose a Mode Before Proceeding.");
        } catch (UserWantsOutException | UserWantsToDeleteValueException ex) {
        }

        if (response == 2) {
            setProductionMode();

        } else {
            setTestMode();
        }

        productDao = new ProductDao(configDao);
        stateDao = new StateDao(configDao);
        orderDao = new OrderDao(productDao, stateDao, configDao);

    }

    private void setProductionMode() {
        // Production Mode
        configDao.get().setInTestMode(false);
    }

    private void setTestMode() {
        // Test Mode
        configDao.get().setInTestMode(true);
        configDao.get().setOrdersDirectory(configDao.get().getTestDirectory());
    }

    public void run() {

        init();

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
                + "*\t6. Administration Menu\n"
                + "*\t7. Quit\n"
                + "*\n"
                + border;

        while (!done) {
            try {
                int option;

                option = consoleIo.getUserIntInputRange(menuString, 0, 7);

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
                    case 6:
                        displayAdminMenu();
                        break;
                    case 7:
                        displayExitMessage();
                        done = true;
                        break;
                    case 0:
                        displayExitMessage();
                        done = true;
                        break;
                }

            } catch (UserWantsOutException | UserWantsToDeleteValueException ex) {
                displayExitMessage();
                done = true;
            }

        }

    }

    private void displayOrders() {

        try {

            int input = consoleIo.getUserIntInputRange("Would You Like To View By Order Number, Date, Or Order Name?\n 1 - Order Number\n 2 - Order Date\n 3 - Order Name\n  ", 0, 4);
            switch (input) {
                case 1:
                    displayOrdersByNumber();
                    break;
                case 2:
                    displayOrdersByDate();
                    break;
                case 3:
                    displayOrdersByName();
                    break;

            }
        } catch (UserWantsOutException | UserWantsToDeleteValueException ex) {
            consoleIo.printStringToConsole("You Have Choosen To Return To The Main Menu.");
        }
    }

    private void displayOrdersByDate() throws UserWantsOutException, UserWantsToDeleteValueException {

        if (orderDao.listOrderDates().size() > 1) {
            consoleIo.printStringToConsole("\nDates Currently On File:\n" + getDatesOnFile());

            java.util.Date orderDate = consoleIo.getUserDate("What Date Would You Like To See Orders For?:\n The System Likes DD-MM-YYYY Format Best.");

            List<Order> ordersForDate = orderDao.searchByDate(orderDate);

            if (ordersForDate.size() < 1) {
                consoleIo.printStringToConsole("No Records Could Be Found Matching That Date.");
            } else if (ordersForDate.size() == 1) {
                displayAndEdit(ordersForDate.get(0));
            } else {
                displayOrder(ordersForDate);

                int orderNumber = consoleIo.getUserIntInputRange("You May Enter An Order Number To View or \"0\" to Exit:", 0, Integer.MAX_VALUE);

                Order orderForNumber = orderDao.get(orderNumber);

                if (orderForNumber == null) {
                    consoleIo.printStringToConsole("No Records Could Be Found Matching That Number.");
                } else {
                    displayAndEdit(orderForNumber);

                }

            }

        } else if (orderDao.listOrderDates().size() == 1) {
            consoleIo.printStringToConsole("Only One Order Could Be Found.");
            displayAndEdit(orderDao.searchByDate(orderDao.listOrderDates().get(0)).get(0));
        } else {
            consoleIo.printStringToConsole("- The Program Could Not Find Any Orders. - ");
        }

    }

    private void displayOrdersByName() throws UserWantsOutException, UserWantsToDeleteValueException {

        if (orderDao.size() > 1) {
            String inputName = consoleIo.getUserStringInput("Please Enter A Name To Search For:");

            List<Order> orderList = orderDao.searchByName(inputName);

            if (!orderList.isEmpty()) {
                String orderNames = "";
                //orderList.sort(c);
                for (Order order : orderList) {
                    if (order != null) {
                        orderNames += order.getId() + "\t" + order.getName() + "\t";
                        if (order.getState() == null) {
                            orderNames += " - ";
                        } else {
                            orderNames += order.getState().getState();
                        }

                        orderNames += "\t";

                        if (order.getProduct() == null) {
                            orderNames += " - No Product - ";
                        } else {
                            orderNames += order.getProduct().getType();
                        }

                        orderNames += "\n";
                    }
                }

                int orderNum = consoleIo.getUserIntInputPositive("Orders Matching Your Search: \n" + orderNames + "\nPlease Enter an Order Number to View or 0 to Exit.");

                Order order;
                if (orderNum == 0) {
                } else {
                    order = orderDao.get(orderNum);

                    displayAndEdit(order);
                }
            } else {
                consoleIo.printStringToConsole("Your search did not return any records.");
            }

        } else if (orderDao.size() == 1) {
            if (consoleIo.getUserConfirmation("There Is Currently Only One Order On Record. Would You Like To See It?")) {
                displayAndEdit(orderDao.get(orderDao.listOrderNumbers().get(0)));
            }
        } else {
            consoleIo.printStringToConsole(" - There Are Currently No Orders On Record. -");
        }

    }

    private void displayAndEdit(Order order) throws UserWantsToDeleteValueException, UserWantsOutException {
        displayOrder(order);
        if (consoleIo.getUserConfirmation("Would You Like to Edit This File?")) {
            editOrder(order);
        }
    }

    private void displayOrdersByNumber() throws UserWantsOutException, UserWantsToDeleteValueException {

        if (orderDao.size() > 1) {
            consoleIo.printStringToConsole("\nOrder Numbers Currently On File:\n" + getOrderNumbers());
            int orderNumber = consoleIo.getUserIntInputRange("Please Enter An Order Number To View:", 0, Integer.MAX_VALUE);

            Order orderForNumber = orderDao.get(orderNumber);

            if (orderForNumber == null) {
                consoleIo.printStringToConsole(" - No Records Could Be Found Matching That Number. - ");
            } else {
                displayAndEdit(orderForNumber);

            }
        } else if (orderDao.size() == 1) {
            consoleIo.printStringToConsole(" - Only One Order Could Be Found. - ");
            Order onlyOrder = orderDao.get(orderDao.listOrderNumbers().get(0));
            displayAndEdit(onlyOrder);
        } else {
            consoleIo.printStringToConsole(" - There Are Currently No Records On File. - ");
        }
    }

    private String getOrderNumbers() {
        String orderNumbers = "";
        for (Integer num : orderDao.listOrderNumbers()) {
            orderNumbers += num + "\n";
        }
        return orderNumbers;
    }

    private String getDatesOnFile() {
        String orderDateString = "";
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd-MM-yyyy");
        for (java.util.Date orderDate : orderDao.listOrderDates()) {
            orderDateString += fmt.format(orderDate) + "\n";
        }
        return orderDateString;
    }

    private void displayOrder(List<Order> orders) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String orderString = "Order Date:\t" + sdf.format(order.getDate()) + "\n" + orderDao.addLabels(order, "\n", ":\t");

        return orderString;
    }

    private void addOrder() {
        try {
            Order order = inputOrder();

            displayOrder(order);

            boolean confirm = consoleIo.getUserConfirmation("Is this information correct?", " \n Please Enter \"Y\" To Confirm Or Any Other Key To Escape.");

            if (confirm) {
            } else {
                orderDao.delete(order);
            }
        } catch (UserWantsOutException | UserWantsToDeleteValueException ex) {
            consoleIo.printStringToConsole(ex.getMessage());
        }

    }

    private Order inputOrder() throws UserWantsOutException {
        Order newOrder = editOrder(null);
        return newOrder;
    }

    private void editOrder() {
        try {
            Order order = askForOrder();
            if (order != null) {
                displayOrder(order);
                if (consoleIo.getUserConfirmation("Would You Like To Edit This Order?", "\n Please Enter \"Y\" To Edit or \"n\" To Return To The Main Menu.")) {
                    order = editOrder(order);
                    if (order != null) {
                    }
                }
            } else {
                consoleIo.printStringToConsole("Information For That Order Could Not Be Found.");
            }

        } catch (UserWantsOutException | UserWantsToDeleteValueException | UserWantsToDeleteDateException ex) {
            consoleIo.printStringToConsole("You Have Asked To Return To The Main Menu.");

        }

    }

    private void removeOrder() {
        try {
            Order order = askForOrder();
            if (order != null) {
                displayOrder(order);
                if (consoleIo.getUserConfirmation("Are You Sure You Want To Delete This Order?", "\n Please Enter \"Y\" To Remove or \"n\" To Return To The Main Menu.")) {
                    if (order != null) {
                        orderDao.delete(order);
                    }
                }
            } else {
                consoleIo.printStringToConsole("Information For That Order Could Not Be Found.");
            }

        } catch (UserWantsOutException | UserWantsToDeleteValueException | UserWantsToDeleteDateException ex) {
            consoleIo.printStringToConsole("You Have Asked To Return To The Main Menu.");
        }
    }

    private Order editOrder(Order order) throws UserWantsOutException {
        Order newOrder = new Order();

        if (order == null) {
            consoleIo.printStringToConsole("To Delete an Existing Entry, Enter a Dash \"-\".");
            consoleIo.printStringToConsole("You May Leave Lines Empty And Return To Edit Them Later.");
            orderDao.create(newOrder);
        } else {
            newOrder.setId(order.getId());
        }

        askCustomerName(order, newOrder);
        askOrderDate(order, newOrder);
        askState(order, newOrder);

        if (newOrder.getState() != null) {
            newOrder.setTaxRate(stateDao.get(newOrder.getState().getState()).getStateTax());
            double taxRate = newOrder.getState().getStateTax();
            newOrder.setTaxRate(taxRate);
        }

        askProduct(order, newOrder);

        askArea(order, newOrder);

        double laborCostPerFoot = 0.0;
        double materialCost = 0.0;
        double taxRate = 0.0;

        if (newOrder.getProduct() != null) {
            newOrder.setCostPerSquareFoot(newOrder.getProduct().getCost());

            materialCost = newOrder.getProduct().getCost() * newOrder.getArea();
            newOrder.setMaterialCost(materialCost);

            laborCostPerFoot = newOrder.getProduct().getLaborCost();
            newOrder.setLaborCostPerSquareFoot(laborCostPerFoot);
        }

        double totalLaborCost = laborCostPerFoot * newOrder.getArea();
        newOrder.setLaborCost(totalLaborCost);

        double subTotal = totalLaborCost + materialCost;

        double totalTax = (subTotal * (taxRate / 100));

        newOrder.setTax(totalTax);

        double totalCost = subTotal + totalTax;

        newOrder.setTotal(totalCost);
        orderDao.update(newOrder);

        return newOrder;
    }

    private void askCustomerName(Order order, Order newOrder) throws UserWantsOutException {
        String oldName;
        String currentValue = "";

        if (order == null) {
            oldName = "";
        } else if (order.getName() != null) {
            oldName = order.getName();
            currentValue = "\n" + oldName + "\n";
        } else {
            oldName = "";
        }

        String customerName = consoleIo.getUserStringInputSimple(currentValue + "Please Enter Customer Name: ");

        if (customerName.equalsIgnoreCase("")) {
            customerName = null;
            newOrder.setName(order.getName());
        } else if (customerName.equalsIgnoreCase("-")) {
            customerName = null;
            newOrder.setName(customerName);
        } else {
            newOrder.setName(customerName);
        }
    }

    private void askOrderDate(Order order, Order newOrder) throws UserWantsOutException {
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
            orderDate = consoleIo.getUserDate("\n" + oldDate + "\nPlease Enter Order Date: \n The System Likes DD-MM-YYYY Format Best.", true);

            if (orderDate == null) {

                orderDate = order.getDate();
                newOrder.setDate(orderDate);
            } else {
                newOrder.setDate(orderDate);
            }

        } catch (UserWantsToDeleteValueException | UserWantsToDeleteDateException ex) {
            orderDate = null;
            newOrder.setDate(orderDate);
        }
    }

    private void askState(Order order, Order newOrder) {
        String oldState;
        String existingInfo = "";
        State oldStateObj = null;

        if (order == null) {
            oldState = "";
        } else if (order.getState() != null) {
            oldStateObj = order.getState();
            oldState = order.getState().getState();
            existingInfo = "\nCurrent State: " + oldState + "\n";
        } else {
            oldState = "";
        }

        boolean valid = false;
        while (!valid) {
            String customerState = consoleIo.getUserStringInputSimple(existingInfo + "Please Enter State: \n ( Valid Choices Are: " + getValidStates() + ")");

            com.mycompany.flooringmastery.dto.State state = stateDao.get(customerState);

            if (customerState.equalsIgnoreCase("")) {
                valid = true;
                newOrder.setState(oldStateObj);
            } else if (customerState.equalsIgnoreCase("-")) {
                customerState = null;
                newOrder.setState(null);
                valid = true;
            } else if (state != null) {
                newOrder.setState(state);
                valid = true;
            }
        }
    }

    private void askProduct(Order order, Order newOrder) {

        displayProducts();

        String oldProduct;
        String prompt = "";
        String currentValue = "";
        Product oldProductObj = null;

        if (order == null) {
            oldProduct = "";
            prompt = "Please Enter Product Name: ";
        } else if (order.getProduct() != null) {
            oldProductObj = order.getProduct();
            oldProduct = order.getProduct().getType();
            currentValue += "\nCurrent Product : " + oldProduct + "\n";
            prompt = "Please Enter New Product Name: ";
        } else {
            oldProduct = "";
            prompt = "Please Enter Product Name: ";
        }

        boolean validProduct = false;
        while (!validProduct) {

            String customerProduct = consoleIo.getUserStringInputSimple(currentValue + prompt);
            com.mycompany.flooringmastery.dto.Product product = productDao.get(customerProduct);

            if (customerProduct.equalsIgnoreCase("")) {
                customerProduct = null;
                validProduct = true;
                newOrder.setProduct(oldProductObj);
            } else if (customerProduct.equalsIgnoreCase("-")) {
                customerProduct = null;
                newOrder.setProduct(null);
                validProduct = true;
            } else if (product != null) {
                newOrder.setProduct(product);
                validProduct = true;
            }
        }
    }

    private void askArea(Order order, Order newOrder) throws UserWantsOutException {

        double oldAreaDouble = 0.0d;
        String oldArea;
        if (order == null) {
            oldArea = "";
        } else if (order.getArea() != 0.0d) {
            oldArea = "Previous Area: " + Double.toString(order.getArea());
            oldAreaDouble = order.getArea();
        } else {
            oldArea = "";
        }

        double area;
        try {
            area = consoleIo.getUserDoubleMinMax("\n" + oldArea + "\nPlease Enter Area Of Floor: ", 0, Double.MAX_VALUE, oldAreaDouble);
            newOrder.setArea(area);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setArea(0.0d);
        }
    }

    private void askCostPerSquareFoot(Order order, Order newOrder) throws UserWantsOutException {
        double oldcostPerSqrFtDouble = 0.0d;
        String oldcostPerSqrFt;
        if (order == null) {
            oldcostPerSqrFt = "";
        } else if (order.getCostPerSquareFoot() != 0.0d) {
            oldcostPerSqrFt = Double.toString(order.getCostPerSquareFoot());
            oldcostPerSqrFtDouble = order.getCostPerSquareFoot();
        } else {
            oldcostPerSqrFt = "";
        }

        double costPerSqrFt;
        try {
            costPerSqrFt = consoleIo.getUserDoubleMinMax("\n" + oldcostPerSqrFt + "\nPlease Enter Material Cost Per Square Foot: ", 0, Double.MAX_VALUE, oldcostPerSqrFtDouble);
            newOrder.setCostPerSquareFoot(costPerSqrFt);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setCostPerSquareFoot(0.0d);
        }
    }

    private void askMaterialCost(Order order, Order newOrder) throws UserWantsOutException {
        double oldmaterialCostDouble = 0.0d;
        String oldmaterialCost;
        if (order == null) {
            oldmaterialCost = "";
        } else if (order.getMaterialCost() != 0.0d) {
            oldmaterialCost = Double.toString(order.getMaterialCost());
            oldmaterialCostDouble = order.getMaterialCost();
        } else {
            oldmaterialCost = "";
        }

        double materialCost;
        try {
            materialCost = consoleIo.getUserDoubleMinMax("\n" + oldmaterialCost + "\nPlease Enter Total Material Cost: ", 0, Double.MAX_VALUE, oldmaterialCostDouble);
            newOrder.setMaterialCost(materialCost);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setMaterialCost(0.0d);
        }
    }

    private void askLaborCost(Order order, Order newOrder) throws UserWantsOutException {
        double oldlaborCostDouble = 0.0d;
        String oldlaborCost;
        if (order == null) {
            oldlaborCost = "";
        } else if (order.getLaborCost() != 0.0d) {
            oldlaborCost = Double.toString(order.getLaborCost());
            oldlaborCostDouble = order.getLaborCost();
        } else {
            oldlaborCost = "";
        }

        double laborCost;
        try {
            laborCost = consoleIo.getUserDoubleMinMax("\n" + oldlaborCost + "\nPlease Enter Total Labor Cost: ", 0, Double.MAX_VALUE, oldlaborCostDouble);
            newOrder.setLaborCost(laborCost);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setLaborCost(0.0d);
        }
    }

    private void askTax(Order order, Order newOrder) throws UserWantsOutException {
        double oldtaxDouble = 0.0d;
        String oldtax;
        if (order == null) {
            oldtax = "";
        } else if (order.getTax() != 0.0d) {
            oldtax = Double.toString(order.getTax());
            oldtaxDouble = order.getTax();
        } else {
            oldtax = "";
        }

        double tax;
        try {
            tax = consoleIo.getUserDoubleMinMax("\n" + oldtax + "\nPlease Enter Total Tax: ", 0, Double.MAX_VALUE, oldtaxDouble);
            newOrder.setTax(tax);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setTax(0.0d);
        }
    }

    private void askTotalCost(Order order, Order newOrder) throws UserWantsOutException {
        double oldtotalCostDouble = 0.0d;
        String oldtotalCost;
        if (order == null) {
            oldtotalCost = "";
        } else if (order.getTotal() != 0.0d) {
            oldtotalCost = Double.toString(order.getTotal());
            oldtotalCostDouble = order.getTotal();
        } else {
            oldtotalCost = "";
        }

        double totalCost;
        try {
            totalCost = consoleIo.getUserDoubleMinMax("\n" + oldtotalCost + "\nPlease Enter Total Project Cost: ", 0, Double.MAX_VALUE, oldtotalCostDouble);
            newOrder.setTotal(totalCost);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setTotal(0.0d);
        }
    }

    private void askTaxRate(Order order, Order newOrder) throws UserWantsOutException {
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

    private Order askForOrder() throws UserWantsOutException, UserWantsToDeleteDateException, UserWantsToDeleteValueException {
        java.util.Date date = consoleIo.getUserDate("Please Enter A Date To Search For: ", false);
        List<Order> ordersOnDate = orderDao.searchByDate(date);
        displayOrder(ordersOnDate);
        int orderNumber = consoleIo.getUserIntInputPositive("Please Enter An Order Number: ");
        return orderDao.get(orderNumber);
    }

    private void save() {

        for (Order order : orderDao.getList()) {
            orderDao.update(order);
        }

    }

    private void displayProducts() {
        String productList = "";

        for (String product : productDao.getList()) {
            Product productObject = productDao.get(product);
            productList += productObject.getType() + "\t" + productObject.getCost() + "\t" + productObject.getLaborCost() + "\n";
        }
        consoleIo.printStringToConsole(productList);

    }

    private void displayExitMessage() {
        orderDao.purgeTestFiles();
        consoleIo.printStringToConsole("Have A Nice Day!");
    }

    private void askLaborCostPerSquareFoot(Order order, Order newOrder) throws UserWantsOutException {
        double oldLaborCostPerSquareFootDouble = 0.0d;
        String oldLaborCostPerSquareFoot;
        if (order == null) {
            oldLaborCostPerSquareFoot = "";
        } else if (order.getLaborCostPerSquareFoot() != 0.0d) {
            oldLaborCostPerSquareFoot = Double.toString(order.getLaborCostPerSquareFoot());
            oldLaborCostPerSquareFootDouble = order.getLaborCostPerSquareFoot();
        } else {
            oldLaborCostPerSquareFoot = "";
        }

        double laborCostPerSquareFoot;
        try {
            laborCostPerSquareFoot = consoleIo.getUserDoubleMinMax("\n" + oldLaborCostPerSquareFoot + "\nPlease Enter Labor Cost Per Square Foot: ", 0, Double.MAX_VALUE, oldLaborCostPerSquareFootDouble);
            newOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);

        } catch (UserWantsToDeleteValueException ex) {
            newOrder.setLaborCostPerSquareFoot(0.0d);
        }
    }

    private String getValidStates() {
        String validStates = "";
        List<String> sortedList = stateDao.getList();
        Collections.sort(sortedList);

        java.util.Iterator<String> stateNamesIterator = sortedList.listIterator();

        while (stateNamesIterator.hasNext()) {
            validStates += stateNamesIterator.next();
            if (stateNamesIterator.hasNext()) {
                validStates += ", ";
            }

        }

        return validStates;

    }

    private String getAvailibleProducts() {
        return getAvailibleProducts("\n");
    }

    private String getAvailibleProducts(String token) {

        String validProducts = "";
        List<String> sortedList = productDao.getList();
        Collections.sort(sortedList);

        java.util.Iterator<String> productsNamesIterator = sortedList.listIterator();

        while (productsNamesIterator.hasNext()) {
            validProducts += productsNamesIterator.next();
            if (productsNamesIterator.hasNext()) {
                validProducts += token;
            }
        }
        return validProducts;
    }

    private void displayAdminMenu() {
        AdminPanelController adminPanel = new AdminPanelController(consoleIo, stateDao, productDao, orderDao, configDao);
        adminPanel.run();
    }

}
