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

        for (Address myAddress : addresses) {
            if (myAddress.getId() == address.getId()) {
                addresses.remove(myAddress);
                addresses.add(address);
            }

        }

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

                address.setFirstName(stringParts[1]);
                address.setLastName(stringParts[2]);
                address.setType(stringParts[3]);
                address.setStreetAddress(stringParts[4]);
                address.setState(stringParts[5]);
                address.setCity(stringParts[6]);
                address.setCountry(stringParts[7]);
                address.setPoBox(stringParts[8]);
                address.setZipcode(stringParts[9]);

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
        List<Address> soughtAddress = null;

        for (Address address : addresses) {
            if (address.getLastName().compareToIgnoreCase(lastName) == 0) {
                soughtAddress.add(address);

            }

        }

        return soughtAddress;
    }

}
