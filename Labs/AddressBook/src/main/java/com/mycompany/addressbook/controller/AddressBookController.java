/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.controller;

import com.mycompany.addressbook.dao.AddressBook;
import com.mycompany.addressbook.dao.AddressBookImpl;
import com.mycompany.addressbook.dao.AddressBookLambdaImpl;
import com.mycompany.addressbook.dto.Address;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class AddressBookController {

    ConsoleIO consoleIo = new ConsoleIO();
    AddressBook addressBook;

    public void run() {

        String lambdaMenu = "Would You Like To Use Lambdas Or Enhanced For Loops?\n"
                + "   1. For Loops\n"
                + "   2. Lambdas";

        int lambdaChoice = consoleIo.getUserIntInputRange(lambdaMenu, 1, 2, "Please Enter Either \"1\" or \"2\"");
        if (lambdaChoice == 1) {
            addressBook = new AddressBookImpl();
        } else {
            addressBook = new AddressBookLambdaImpl();
        }

        boolean choseToExit = false;
        while (!choseToExit) {

            String mainMenu = "== Main Menu == \n"
                    + "1. Add Address\n"
                    + "2. Remove Address\n"
                    + "3. Address Book Size\n"
                    + "4. List Addresses\n"
                    + "5. Find Address by Last Name\n"
                    + "6. Find Address by ID Number\n"
                    + "7. Find Address by City\n"
                    + "8. Find Address by State\n"
                    + "0. Exit\n";

            int userChoice = consoleIo.getUserIntInputRange(mainMenu, 0, 8);

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
                case 6:
                    editById();
                    break;
                case 7:
                    searchByCity();
                    break;
                case 8:
                    searchByState();
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
        List<Address> foundAddresses = addressBook.searchByLastName(lastNameToFind);

        for (Address address : foundAddresses) {
            addressString += address;
        }

        consoleIo.printStringToConsole(addressString);

        if (foundAddresses.size() == 1) {
            String editInput = consoleIo.getUserStringInput("Would You Like To Edit This Address?\n Press \"Y\" to edit.");
            if (editInput != null && foundAddresses.get(0) != null) {
                if (editInput.equalsIgnoreCase("Y")) {
                    Address addressToEdit = foundAddresses.get(0);
                    editAddress(addressToEdit);
                    addressBook.update(addressToEdit);

                }
            }
        }

    }

    public void editAddress(Address address) {
        if (address != null) {
            consoleIo.printStringToConsole("To Delete an Existing Entry, Enter a Dash \"-\".");
            consoleIo.printStringToConsole("You May Leave Lines Empty And Return To Edit Them Later.");

            String firstName = consoleIo.getUserStringInput("Please Enter First Name:");
            if (firstName.equalsIgnoreCase("")) {

            } else if (firstName.equalsIgnoreCase("-")) {
                firstName = null;
                address.setFirstName(firstName);
            } else {
                address.setFirstName(firstName);

            }

            String inputString = consoleIo.getUserStringInput("Please Enter Last Name:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);

            } else {

                address.setLastName(inputString);
            }

            inputString = consoleIo.getUserStringInput("What type of address is this?:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setType(inputString);

            }

            inputString = consoleIo.getUserStringInput("Please Enter PO Box:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setPoBox(inputString);

            }

            inputString = consoleIo.getUserStringInput("Please Enter Street Address:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setStreetAddress(inputString);

            }

            inputString = consoleIo.getUserStringInput("Please Enter City:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setCity(inputString);

            }

            inputString = consoleIo.getUserStringInput("Please Enter State:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setState(inputString);

            }

            inputString = consoleIo.getUserStringInput("Please Enter Zip Code:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setZipcode(inputString);

            }

            inputString = consoleIo.getUserStringInput("Please Enter Country:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                address.setLastName(inputString);
            } else {
                address.setCountry(inputString);

            }

        }
    }

    public Address inputAddress() {
        Address newAddress = new Address();

        editAddress(newAddress);

        return newAddress;
    }

    private void editById() {
        listAllAddresses();

        int idNumber = consoleIo.getUserIntInputPositive("Please Enter An ID Number To Find:");

        Address addressToEdit = addressBook.get(idNumber);

        editAddress(addressToEdit);
        addressBook.update(addressToEdit);

    }

    private void searchByCity() {

        String city = consoleIo.getUserStringInput("Please Enter A City Name To Search For:");
        List<Address> addressesByCity = addressBook.searchByCity(city);

        if (addressesByCity.size() < 1) {
            consoleIo.printStringToConsole("No Entries Could Be Found For That City.");
        } else {
            for (Address address : addressesByCity) {

                String addressString = address.toString();

                consoleIo.printStringToConsole(addressString);
            }
        }

    }

    private void searchByState() {

        String state = consoleIo.getUserStringInput("Please Enter A State Name To Search For:");
        java.util.Map<String, List<Address>> addressesByState = addressBook.searchByState(state);

        if (addressesByState.size() < 1) {
            consoleIo.printStringToConsole("No Entries Could Be Found For That City.");
        } else {
            for (String cityName : addressesByState.keySet()) {
                consoleIo.printStringToConsole(cityName + ": ");
                for (Address address : addressesByState.get(cityName)) {

                    String addressString = address.toString();

                    consoleIo.printStringToConsole("\t" + addressString);
                }
            }
        }

    }

}
