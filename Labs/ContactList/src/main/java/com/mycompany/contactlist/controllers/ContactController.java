/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.controllers;

import com.mycompany.contactlist.dao.ContactDao;
import com.mycompany.contactlist.dto.Contact;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    private ContactDao contactDao;

    @Inject
    public ContactController(ContactDao dao) {
        this.contactDao = dao;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Contact contact) {
        contactDao.add(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer contactId, Map model) {

        //List<Contact> contacts = contactDao.list();
        Contact contact = contactDao.get(contactId);

        //contactDao.sortByLastName(contacts);

        //model.put("contacts", contacts);
        model.put("contact", contact);

        return "edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Contact contact) {
        contactDao.update(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer contactId) {

        contactDao.remove(contactDao.get(contactId));

        return "redirect:/";
    }

    @RequestMapping(value= "/edit", method=RequestMethod.POST)
    public String editSubmit( @ModelAttribute Contact contact ) {
        
        contactDao.update(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer contactId, Map model) {

        Contact contact = contactDao.get(contactId);
        
        model.put("contact", contact);
        
        return "show";
    }

    

}
