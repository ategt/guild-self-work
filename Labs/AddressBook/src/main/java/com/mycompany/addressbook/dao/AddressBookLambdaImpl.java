/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

//import com.mycompany.addressbook.dto.Address;
import com.thesoftwareguild.interfaces.dto.Address;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import static java.util.stream.Collectors.groupingBy;
//import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressBookLambdaImpl implements com.thesoftwareguild.interfaces.dao.AddressBookDao {

    List<Address> addresses;
    int nextId = 1;
    File addressFile = new File("addressData.txt");

    public AddressBookLambdaImpl() {
        this(false);
    }

    protected AddressBookLambdaImpl(boolean isATest) {

        if (isATest) {
            addressFile = new File("testAddressData.txt");
        }

        addresses = decode();

//        if (addresses == null) {
//            addresses = new ArrayList();
//            System.out.println("The list was empty, making a new one.");
//        }
        nextId = determineNextId();
    }

    public Address create(Address address) {
        address.setId(nextId);
        nextId++;

        addresses.add(address);

        encode();

        return address;
    }

    @Override
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

    public void delete(Integer id) {
        Address found = null;

        for (Address currentAddress : addresses) {
            if (currentAddress.getId() == id ) {
                found = currentAddress;
                break;
            }
        }

        if (found != null) {
            addresses.remove(found);
        }

        encode();

    }

    @Override
    public List<Address> list() {

        return addresses;
    }

//    @Override
//    public int size() {
//        return addresses.size();
//    }
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
                out.print(address.getStreetNumber());
                out.print(TOKEN);
                out.print(address.getStreetName());
                out.print(TOKEN);
                out.print(address.getState());
                out.print(TOKEN);
                out.print(address.getCity());
                out.print(TOKEN);
                out.print(address.getZip());
                out.println("");

//                out.print(address.getCountry());
//                out.print(TOKEN);
//                out.print(address.getPoBox());
//                out.print(TOKEN);
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(com.thesoftwareguild.interfaces.dao.AddressBookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Address> decode() {

        List<Address> addressList = new ArrayList<>();

        final String TOKEN = "::";

        try {
            
            if ( !addressFile.exists() ) {
                addressFile.createNewFile();
            }
            Scanner sc = new Scanner(new BufferedReader(new FileReader(addressFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Address address = new Address();

                int id = Integer.parseInt(stringParts[0]);
                address.setId(id);

                address.setFirstName(fixNull(stringParts[1]));
                address.setLastName(fixNull(stringParts[2]));
                address.setStreetNumber(fixNull(stringParts[3]));
                address.setStreetName(fixNull(stringParts[4]));
                address.setState(fixNull(stringParts[5]));
                address.setCity(fixNull(stringParts[6]));
                address.setZip(fixNull(stringParts[7]));

                /*
                address.setPoBox(fixNull(stringParts[8]));
                address.setZipcode(fixNull(stringParts[9]));

                
                out.print(TOKEN);
                out.print(address.getStreetNumber());
                out.print(TOKEN);
                out.print(address.getStreetName());
                out.print(TOKEN);
                out.print(address.getState());
                out.print(TOKEN);
                out.print(address.getCity());
                out.print(TOKEN);
                out.print(address.getZip());
                
                 */
                addressList.add(address);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(com.thesoftwareguild.interfaces.dao.AddressBookDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddressBookLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return addressList;
    }

    @Override
    public List<Address> searchByLastName(String lastName) {

        return addresses
                .stream()
                .filter((Address a) -> lastName.equalsIgnoreCase(a.getLastName()))
                .collect(java.util.stream.Collectors.toList());

    }

    @Override
    public List<Address> searchByCity(String city) {

        return addresses
                .stream()
                .filter(a -> a.getCity().equalsIgnoreCase(city))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    //public Map<String /* City */, List<Address> /*Addresses Sorted By City*/> searchByState(String state) {
    public List<Address> searchByState(String state) {

        List<Address> secondAddressLambdaMess
                = addresses
                .stream()
                .filter((Address a) -> a.getState().equalsIgnoreCase(state))
                .collect(
                        java.util.stream.Collectors.toList()
                //                        groupingBy(
                //                                //(Address a) -> a.getCity(),  // This does the same as the line below it.
                //                                Address::getCity,
                //                                java.util.stream.Collectors.toList()
                //                        )
                );

        return secondAddressLambdaMess;

    }

    @Override
    public List<Address> searchByZip(String zipcode) {

        return addresses
                .stream()
                .filter(a -> a.getZip().equalsIgnoreCase(zipcode))
                .collect(java.util.stream.Collectors.toList());

    }

    
    public String fixNull(String input) {
        String returnValue = null;
        if (input.trim().equalsIgnoreCase("null")) {
            input = null;
        } else if (input.trim().equalsIgnoreCase("")) {
            input = null;
        } else {
            returnValue = input;
        }
        return returnValue;
    }

}
