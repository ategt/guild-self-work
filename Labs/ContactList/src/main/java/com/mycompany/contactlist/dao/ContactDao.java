/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ContactDao {
    public Contact add(Contact contact);
    public void update(Contact contact);
    public void remove(Contact contact);
    public Contact get(Integer id);
    public List<Contact> list();
    
    

}
