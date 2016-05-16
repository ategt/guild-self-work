/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.consoleio.exceptions.UserWantsOutException;
import com.mycompany.consoleio.exceptions.UserWantsToDeleteValueException;
import com.mycompany.flooringmastery.dao.ConfigDao;
import com.mycompany.flooringmastery.dao.OrderDao;
import com.mycompany.flooringmastery.dao.ProductDao;
import com.mycompany.flooringmastery.dao.StateDao;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.State;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ATeg
 */
public class AdminPanelController {

    ConsoleIO consoleIo;
    StateDao stateDao;
    ProductDao productDao;
    OrderDao orderDao;
    ConfigDao configDao;

    public AdminPanelController(ConsoleIO consoleIo, StateDao stateDao, ProductDao productDao, OrderDao orderDao, ConfigDao configDao) {
        this.consoleIo = consoleIo;
        this.stateDao = stateDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
        this.configDao = configDao;
    }

    public void run() {
        displayAdminMenu();
    }

    private void displayAdminMenu() {

        boolean done = false;
        while (!done) {
            try {
                String menu = "Administration Menu\n"
                        + "1. View State Taxes On File\n"
                        + "2. Add/Edit State \n"
                        //+ "2. Edit State \n"
                        + "3. Remove State \n"
                        //+ "4. Add Product \n"
                        + "4. View Product Information On File \n"
                        + "5. Add/Edit Product \n"
                        + "6. Remove Product \n"
                        + "7. Return to Main Menu";

                switch (consoleIo.getUserIntInputRange(menu, 0, 7)) {
                    case 1:
                        listStates();
                        break;
                    case 2:
                        addState();
                        break;
                    //  editState();
                    //  break;
                    case 3:
                        removeState();
                        break;
                    case 4:
                        listProducts();
//                    addProduct();
                        break;
                    case 5:
                        editProduct();
                        break;
                    case 6:
                        removeProduct();
                        break;
                    case 7:
                    default:
                        done = true;
                        break;

                }
            } catch (UserWantsOutException | UserWantsToDeleteValueException ex) {
                consoleIo.printStringToConsole("The User Has Requested To Return To The Main Menu.");
            }

        }
    }

    private void addState() throws UserWantsOutException, UserWantsToDeleteValueException {

        State state = editState(new State());

//        if (state == null) {
//            stateDao.create(state);
//        }
    }
//    
//    private State makeState() throws UserWantsOutException, UserWantsToDeleteValueException {
//        return 
//    }

    private State editState(State state) throws UserWantsOutException, UserWantsToDeleteValueException {

        String stateName = askForStateAdmin();

        if (stateDao.get(stateName) == null) {
            state.setState(stateName);
        } else {
            consoleIo.printStringToConsole("The State Of " + stateName + " Appears to Already be Listed In File.\n"
                    + " Enter a New Tax Rate To Change The Existing Value,"
                    + " or Type in 'EXIT' to Leave The Line Unchanged.\n");
        }

        double taxRate = consoleIo.getUserDouble("What is the Tax Rate in " + stateName + "?", 0.0d, 100.00);
        state.setStateTax(taxRate);

        updateWithStatus(state, stateName);

        return state;
    }

    private void updateWithStatus(State state, String stateName) {

        if (stateDao.get(state.getState()) == null) {
            stateDao.create(state);
        } else {
            stateDao.update(state);
        }

        if (stateDao.get(stateName) == state) {
            consoleIo.printStringToConsole("The Tax Rate For " + stateName + " Has Been Updated.");
        } else {
            consoleIo.printStringToConsole("Something Went Wrong During The Update Process, The Information May Not Have Been Saved.");
        }
    }

    private String askForStateAdmin() throws UserWantsOutException, UserWantsToDeleteValueException {
        String stateName = "";
        while (stateName.length() != 2) {
            stateName = consoleIo.getUserStringInput("Please Enter A Two Letter State Abbreviation: ").toUpperCase();
        }
        return stateName;
    }

    private void removeState() throws UserWantsOutException, UserWantsToDeleteValueException {
        String stateName = askForStateAdmin();

        if (stateDao.get(stateName) == null) {
            stateDao.delete(stateDao.get(stateName));
            if (stateDao.get(stateName) == null) {
                consoleIo.printStringToConsole(stateName + " was successfully deleted.");
            } else {
                consoleIo.printStringToConsole("There was an error. The State was not Deleted.");
            }
        } else {
            consoleIo.printStringToConsole("The State Of " + stateName + " Could Not Be Found");
        }

    }
// 
//    private void addProduct(){
//        
//        Product product = editProduct(new Product());
//        
//        if (product == null) {
//            productDao.create(product);
//        }
//
//    }

    //private Product editProduct(Product product){
    private Product editProduct() throws UserWantsOutException, UserWantsToDeleteValueException {

        String titleCaseName = askForProductAdmin();

        Product product = productDao.get(titleCaseName);

        boolean newProduct = false;

        if (product == null) {
            product = new Product();
            newProduct = true;
        } else {
            // I feel like something should go here.
        }

        product.setType(titleCaseName);

        double productCost = consoleIo.getUserDouble("Please Enter the Cost of One Square Foot of " + titleCaseName, 0, Double.MAX_VALUE);

        product.setCost(productCost);

        double laborCost = consoleIo.getUserDouble("Please Enter the Installation Cost of One Square Foot of " + titleCaseName, 0, Double.MAX_VALUE);

        product.setLaborCost(laborCost);

        updateWithStatus(product, titleCaseName);

        return product;
//        
//        
////        
////        //askForStateAdmin();
////        //S
////        String stateName = "";
////        while (stateName.length() != 2) {
////            stateName = consoleIo.getUserStringInput("Please Enter A Two Letter State Abbreviation: ").toUpperCase();
////        }
////        //return stateName;
//
//        if (stateDao.get(stateName) == null) {
//            state.setState(stateName);
//        } else {
//            consoleIo.printStringToConsole("The State Of " + stateName + " Appears to Already be List In File.\n"
//                    + " Enter a New Tax Rate To Change The Existing Value,"
//                    + " or Type in 'EXIT' to Leave The Line Unchanged.\n");
//        }
//
//        double taxRate = consoleIo.getUserDouble("What is the Tax Rate in " + stateName + "?", 0.0d, 100.00);
//        state.setStateTax(taxRate);
//
//        return state;

    }

    private void updateWithStatus(Product product, String titleCaseName) {
        if (productDao.get(product.getType()) == null) {
            productDao.create(product);
        } else {
            productDao.update(product);
        }
        if (productDao.get(titleCaseName) == product) {
            consoleIo.printStringToConsole("The Product Called " + titleCaseName + " Has Been Updated.");
        } else {
            consoleIo.printStringToConsole("Something Went Wrong During The Update Process, The Information May Not Have Been Saved.");
        }
    }

    private String askForProductAdmin() throws UserWantsOutException, UserWantsToDeleteValueException {
        return askForProductAdmin("Please Enter The Name Of The Product");
    }

    private String askForProductAdmin(String prompt) throws UserWantsOutException, UserWantsToDeleteValueException {
        //"";
        String productName = consoleIo.getUserStringInput(prompt).trim();
        String titleCaseName = com.mycompany.flooringmastery.utilities.TextUtilities.toTitleCase(productName);
        return titleCaseName;
    }

    private void removeProduct() throws UserWantsOutException, UserWantsToDeleteValueException {
        String productName = askForProductAdmin("Please Enter the Name of the Product You Would Like To Delete.");
        //Product product = productDao.get(productName);

        if (productDao.get(productName) == null) {
            productDao.delete(productDao.get(productName));
            if (productDao.get(productName) == null) {
                consoleIo.printStringToConsole(productName + " was successfully deleted.");
            } else {
                consoleIo.printStringToConsole("There was an error. The Product was not Deleted.");
            }
        } else {
            consoleIo.printStringToConsole("Information for " + productName + " Could Not Be Found");
        }

    }

    private String getStatesInfo() {
        String stateInfo = "  State |  Tax Rate\n";
        List<String> sortedList = stateDao.getList();
        Collections.sort(sortedList);

        for (String stateName : sortedList) {
            State state = stateDao.get(stateName);
            stateInfo += "  " + state.getState() + "  |  " + state.getStateTax() + "\n";
        }

        return stateInfo;

    }

    private void listStates() {
        consoleIo.printStringToConsole(getStatesInfo());
    }

    private void listProducts() {
        consoleIo.printStringToConsole(getProductInfo());
    }

    private String getProductInfo() {
        String productInfo = "";
        List<String> sortedList = productDao.getList();
        Collections.sort(sortedList);

        productInfo += "  " + "Product" + "\t" + "Material Cost" + "\t" + "Installation Cost" + "\n";

        for (String productName : sortedList) {
            Product product = productDao.get(productName);
            productInfo += "  " + product.getType() + "\t\t" + product.getCost() + "\t\t" + product.getLaborCost() + "\n";
        }

        return productInfo;

    }
}
