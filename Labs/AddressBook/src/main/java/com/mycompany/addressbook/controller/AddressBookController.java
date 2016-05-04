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
public class AddressBookController {

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

        listAllAddresses();

        int input = consoleIo.getUserIntInputPositive("Please Enter An Address ID Number To Delete:");

        Address address = addressBook.get(input);

        if (address != null) {
            consoleIo.printStringToConsole(address.toString());
            consoleIo.printStringToConsole("Are you sure you want to delete this address?");
            String confirm = consoleIo.getUserStringInput("Press \"1\" to continue or anything else to abort:");

            if (confirm.equals("1")) {
                addressBook.delete(address);
            } else {
                consoleIo.printStringToConsole(" No Action Was Performed.");
            }
        } else {
                consoleIo.printStringToConsole(" That Address Could Not Be Found In The Records.");
        }
    }

    public void showBookSize() {
        consoleIo.printStringToConsole("  " + addressBook.size() + " Entries in address book.");
    }

    public void listAllAddresses() {
        for (Address address : addressBook.getAllAddresses()) {

            String addressString = address.toString();

            consoleIo.printStringToConsole(addressString);
        }

    }

    public void findByLastName() {

        listAllAddresses();
        String lastNameToFind = consoleIo.getUserStringInput("Please Enter A Last Name To Find:");

        String addressString = "";

        for (Address address : addressBook.getAllAddresses()) {

            if (address.getLastName().equalsIgnoreCase(lastNameToFind)) {
                addressString += address;
            }

        }

        consoleIo.printStringToConsole(addressString);

    }

    public void editAddress(Address address) {

        String firstName = consoleIo.getUserStringInput("Please Enter First Name:");
        if (firstName.equalsIgnoreCase("")) firstName = null;
        address.setFirstName(firstName);

        String inputString = consoleIo.getUserStringInput("Please Enter Last Name:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setLastName(inputString);

        inputString = consoleIo.getUserStringInput("What type of address is this?:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setType(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter PO Box:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setPoBox(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter Street Address:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setStreetAddress(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter City:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setCity(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter State:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setState(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter Zip Code:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setZipcode(inputString);

        inputString = consoleIo.getUserStringInput("Please Enter Country:");
        if (inputString.equalsIgnoreCase("")) inputString = null;
        address.setCountry(inputString);

    }

    public Address inputAddress() {
        Address newAddress = new Address();

        editAddress(newAddress);

        return newAddress;
    }

}
