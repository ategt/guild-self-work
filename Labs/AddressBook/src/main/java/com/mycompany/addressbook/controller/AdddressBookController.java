/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.controller;

import com.mycompany.addressbook.dao.AddressBook;
import com.mycompany.addressbook.dto.Address;

/**
 *
 * @author apprentice
 */
public class AdddressBookController {

    ConsoleIO consoleIo = new ConsoleIO();
    AddressBook addressBook = new AddressBook();

    public void run() {

        boolean choseToExit = false;
        while (!choseToExit) {
            String mainMenu = "== Main Menu == \n"
                    + "1. Add Address\n"
                    + "2. Remove Address\n"
                    + "3. Address Book Size\n"
                    + "4. List Addresses\n"
                    + "5. Find Address by Last Name\n"
                    + "0. Exit\n";

            int userChoice = consoleIo.getUserIntInputRange(mainMenu, 0, 5);

            switch (userChoice) {
                case 1:
                    addAddress();
                    break;
                case 2:
                    removeAddress();
                    break;
                case 3:
                    showBookSize();
                    break;
                case 4:
                    listAllAddresses();
                    break;
                case 5:
                    findByLastName();
                    break;
                case 0:
                    choseToExit = true;
            }

        }
    }

    private void addAddress() {
        Address newAddress = inputAddress();
        addressBook.create(newAddress);

    }

    public void removeAddress() {

    }

    public void showBookSize() {

    }

    public void listAllAddresses() {

    }

    public void findByLastName() {

    }

    public void editAddress(Address address) {

        String firstName = consoleIo.getUserStringInput("Please Enter First Name:");
        address.setFirstName(firstName);
        
        String inputString = consoleIo.getUserStringInput("Please Enter Last Name:");
        address.setLastName(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter PO Box:");
        address.setPoBox(inputString);
        
        inputString = consoleIo.getUserStringInput("Please Enter Street Address:");
        address.setStreetAddress(inputString);
        
        inputString = consoleIo.getUserStringInput("Please Enter City:");
        address.setCity(inputString);
        
        inputString = consoleIo.getUserStringInput("Please Enter State:");
        address.setState(inputString);
        
        inputString = consoleIo.getUserStringInput("Please Enter Zip Code:");
        address.setZipcode(inputString);
        
        inputString = consoleIo.getUserStringInput("Please Enter Country:");
        address.setCountry(inputString);
        
        
        
    }

    public Address inputAddress() {
        Address newAddress = new Address();
        
        editAddress(newAddress);
        
        return newAddress;
    }

}
