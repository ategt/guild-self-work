/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

//import com.mycompanysts.dao.*;
import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public abstract class AddressDaoPatImpl implements AddressBookDao {
//
//    private String fileName;
//    private List<Address> addresses = new ArrayList();
//    
//    private int nextId = 1;
//    
//    public AddressDaoPatImpl(String fileName) {
//        this.fileName = fileName;
//        addresses = decode();
//        
//        for (Address myAddress : addresses) {
//            if (myAddress.getId() >= nextId) {
//                nextId = myAddress.getId() + 1;
//            }
//        }
//    }
//    
//    @Override
//    public Address create(Address address) {
//        address.setId(nextId);
//        nextId++;
//        addresses.add(address);
//        encode();
//        return address;
//    }
//    
//    @Override
//    public void update(Address address) {
//        Address found = null;
//        for (Address myAddress : addresses) {
//            if (myAddress.getId() == address.getId()) {
//                found = myAddress;
//                break;
//            }
//        }
//        addresses.remove(found);
//        addresses.add(address);
//        encode();
//    }
//    
//    @Override
//    public void delete(Integer id) {
//        //public void delete(Address address) {
//        Address found = null;
//        for (Address myAddress : addresses) {
//            if (myAddress.getId() == id) {
//                found = myAddress;
//                break;
//            }
//        }
//        addresses.remove(found);
//    }
//    
//    @Override
//    public Address get(Integer id) {
//        //public List<Address> getByID(int id) {
//        //encode();
//        Address addressById = new Address();
//        for (Address addressByIdTemp : addresses) {
//            if (addressByIdTemp.getId() == id) {
//                addressById = addressByIdTemp;
//            }
//        }
//        return addressById;
//    }
//
//    @Override
//    public List<Address> searchByLastName(String lastName) {
//        //encode();
//        List<Address> found = new ArrayList();
//        for (Address myAddress : addresses) {
//            
//            if (myAddress.getLastName().equals(lastName)) {
//                found.add(myAddress);
//            }
//        }
//        return found;
//    }
//    
//    @Override
//    public List<Address> list() {
//        return new ArrayList(addresses);
//    }
//    
//    @Override
//    public List<Address> searchByCity(String city) {
//        List<Address> found = new ArrayList();
//        for (Address myAddress : addresses) {
//            
//            if (myAddress.getCity().equals(city)) {
//                found.add(myAddress);
//            }
//        }
//        return found;
//    }
//    
//    @Override
//    public List<Address> searchByState(String state) {
//        List<Address> found = new ArrayList();
//        for (Address myAddress : addresses) {
//            
//            if (myAddress.getState().equals(state)) {
//                found.add(myAddress);
//            }
//        }
//        return found;
//    }
//    
//    @Override
//    public List<Address> searchByZip(String zip) {
//        List<Address> found = new ArrayList();
//        for (Address myAddress : addresses) {
//            
//            if (myAddress.getZip() == zip) {
//                found.add(myAddress);
//            }
//        }
//        return found;
//    }
//    
//    public void encode() {
//        final String TOKEN = "::";
//        
//        try {
//            PrintWriter out = new PrintWriter(new FileWriter(fileName));
//            for (Address myAddress : addresses) {
//                
//                out.print(myAddress.getId());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getFirstName());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getLastName());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getStreetNumber());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getStreetName());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getCity());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getState());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getZip());
////                out.print(TOKEN);
////
////                out.print(myAddress.getCountry());
//                out.println("");
//            }
//            out.flush();
//            out.close();
//            
//        } catch (IOException ex) {
//            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Error");
//        }
//    }
//    
//    public List<Address> decode() {
//        
//        List<Address> addresses = new ArrayList();
//        
//        try {
//            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
//            
//            while (sc.hasNextLine()) {
//                
//                String currentLine = sc.nextLine();
//                if (currentLine == null || currentLine.length() == 0) {
//                    continue;
//                }
//                String[] stringParts = currentLine.split("::");
//                
//                Address myAddress = new Address();
//                
//                int id = Integer.parseInt(stringParts[0]);
//                myAddress.setId(id);
//                myAddress.setFirstName(stringParts[1]);
//                myAddress.setLastName(stringParts[2]);
//
//
//                myAddress.setStreetNumber(stringParts[3]);
//                myAddress.setStreetName(stringParts[4]);
//                myAddress.setCity(stringParts[5]);
//                myAddress.setState(stringParts[6]);
//                myAddress.setZip(stringParts[7]);
//
//
//                addresses.add(myAddress);
//                
//            }
//            
//        } catch (FileNotFoundException ex) {
//            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Error...");
//        }
//        
//        return addresses;
//        
//    }
//    
}
