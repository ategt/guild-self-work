/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.util.List;
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

        data.remove(get(contact.getId()));
        data.add(contact);
    }

    @Override
    public void remove(Contact contact) {
        data.remove(get(contact.getId()));
    }

    @Override
    public Contact get(Integer id) {
        if (data.size() > 0){
        
        Contact contact = data.stream()
                .filter(a -> a.getId() == id)
                //.filter(Contact::getId == id)
                .collect(Collectors.toList())
                .get(0);
        
        return contact;
        }
        return null;
    }

    @Override
    public List<Contact> list() {
        return new java.util.ArrayList(data);
    }

}
