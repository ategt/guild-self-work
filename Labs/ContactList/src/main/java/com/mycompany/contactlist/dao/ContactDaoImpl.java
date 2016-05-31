/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class ContactDaoImpl implements ContactDao {

    List<Contact> data = new java.util.ArrayList();
    private int nextId = 1;

    @Override
    public Contact add(Contact contact) {
        contact.setId(nextId++);

        data.add(contact);
        return contact;
    }

    @Override
    public void update(Contact contact) {

        this.remove(contact);
        data.add(contact);
    }

    @Override
    public void remove(Contact contact) {
        data.remove(get(contact.getId()));
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

}
