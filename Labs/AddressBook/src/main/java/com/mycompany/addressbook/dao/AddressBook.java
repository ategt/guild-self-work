/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

import com.mycompany.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class AddressBook {

    List<Address> addresses;
    int nextId = 1;
    File addressFile = new File("addressData.txt");

    public AddressBook() {
        this(false);
    }

    protected AddressBook(boolean isATest) {

        if (isATest) {
            addressFile = new File("testAddressData.txt");
        }

        addresses = decode();

        if (addresses == null) {
            addresses = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public Address create(Address address) {
        address.setId(nextId);
        nextId++;

        addresses.add(address);

        encode();

        return address;
    }

    public Address get(Integer id) {

        for (Address address : addresses) {
            if (address.getId() == id) {
                return address;
            }

        }

        return null;
    }

    public void update(Address address) {

        Address foundAddress = null;
        
        for (Address currentAddress : addresses) {
            if (currentAddress.getId() == address.getId()) {
                foundAddress = currentAddress;
                break;
            }

        }
                addresses.remove(foundAddress);
                addresses.add(address);

        encode();

    }

    public void delete(Address address) {
        Address found = null;

        for (Address currentAddress : addresses) {
            if (currentAddress.getId() == address.getId()) {
                found = currentAddress;
                break;
            }
        }

        if (found != null) {
            addresses.remove(found);
        }

        encode();

    }

    public List<Address> getAllAddresses() {

        return addresses;
    }

    public int size() {
        return addresses.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (Address address : addresses) {
            if (highestId < address.getId()) {
                highestId = address.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        try {

            PrintWriter out = new PrintWriter(new FileWriter(addressFile));

            for (Address address : addresses) {

                out.print(address.getId());
                out.print(TOKEN);
                out.print(address.getFirstName());
                out.print(TOKEN);
                out.print(address.getLastName());
                out.print(TOKEN);
                out.print(address.getType());
                out.print(TOKEN);
                out.print(address.getStreetAddress());
                out.print(TOKEN);
                out.print(address.getState());
                out.print(TOKEN);
                out.print(address.getCity());
                out.print(TOKEN);
                out.print(address.getCountry());
                out.print(TOKEN);
                out.print(address.getPoBox());
                out.print(TOKEN);
                out.print(address.getZipcode());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Address> decode() {

        List<Address> addressList = new ArrayList<>();

        final String TOKEN = "::";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(addressFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Address address = new Address();

                int id = Integer.parseInt(stringParts[0]);
                address.setId(id);

                address.setFirstName(fixNull(stringParts[1]));
                address.setLastName(fixNull(stringParts[2]));
                address.setType(fixNull(stringParts[3]));
                address.setStreetAddress(fixNull(stringParts[4]));
                address.setState(fixNull(stringParts[5]));
                address.setCity(fixNull(stringParts[6]));
                address.setCountry(fixNull(stringParts[7]));
                address.setPoBox(fixNull(stringParts[8]));
                address.setZipcode(fixNull(stringParts[9]));

                addressList.add(address);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressBook.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return addressList;
    }

    public List<Address> searchByLastName(String lastName) {
        List<Address> soughtAddress = new ArrayList();

        for (Address address : addresses) {
            if (address.getLastName() != null && lastName != null) {
                if (address.getLastName().compareToIgnoreCase(lastName) == 0) {
                    soughtAddress.add(address);
                }
            }
        }

        return soughtAddress;
    }
    
    public String fixNull(String input){
        String returnValue = null;
        if (input.trim().equalsIgnoreCase("null"))
                input = null;
        else if (input.trim().equalsIgnoreCase(""))
            input = null;
        else returnValue = input;
        return returnValue;
    }

}
