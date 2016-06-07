/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class ContactDaoImpl implements ContactDao {

    File dataFile = new File("contactListData.ser");
    List<Contact> data = new java.util.ArrayList();
    private int nextId = 1;

    public ContactDaoImpl() {
        //decode();

        determineNextId();
    }

    private void determineNextId() {
        try {
            nextId = data.stream()
                    .mapToInt(Contact::getId)
                    .max()
                    .getAsInt();
        } catch (java.util.NoSuchElementException ex) {
            nextId = 5;
        } catch (Exception ex) {
            nextId = 5;
        }
    }

    @Override
    public Contact add(Contact contact) {

        contact.setId(nextId++);

        data.add(contact);
        //encode();
        return contact;
    }

    @Override
    public void update(Contact contact) {

        this.remove(contact);
        data.add(contact);
        //encode();

    }

    @Override
    public void remove(Contact contact) {
        data.remove(get(contact.getId()));
        //encode();

    }

    @Override
    public Contact get(Integer id) {
        if (data.isEmpty()) {
            return null;
        } else {
            try {

                Contact contact = data.stream()
                        .filter(a -> a.getId() == id)
                        .findFirst()
                        .get();

                //.filter(Contact::getId == id)
                //.collect(Collectors.toList())
                //.get(0);
                //.
                return contact;
            } catch (NoSuchElementException noSuchElementException) {

                return null;
            }
        }
    }

    @Override
    public List<Contact> list() {
        return new java.util.ArrayList(data);
    }

    public List<Contact> sortByLastName(List<Contact> contacts) {

        contacts.sort(
                new Comparator<Contact>() {
            public int compare(Contact c1, Contact c2) {
                return c1.getLastName().compareTo(c2.getLastName());
            }
        });

        return contacts;
    }

    private void encode() {

        try (FileOutputStream fileOut = new FileOutputStream(dataFile)) {
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            for (Contact contact : data) {

                out.writeObject(contact);
                
            }
            
            
            out.close();
            //System.out.printf("Serialized data is saved in /tmp/employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void decode() {

        try {

            if (dataFile.exists()) {

                FileInputStream fileIn = new FileInputStream(dataFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                
                List<Contact> contactList = new ArrayList();
                
                Object objectData = null;
               // while( objectData = in.readObject() )
             
                if (objectData instanceof List) {
                    data = (List<Contact>) objectData;
                }
                
                
                
                in.close();
                fileIn.close();

            } else {
                dataFile.createNewFile();
            }

        } catch (IOException i) {
            i.printStackTrace();
            return;
       // } catch (ClassNotFoundException c) {
            //System.out.println("Employee class not found");
        //    c.printStackTrace();
        //    return;
        }
    }
}
