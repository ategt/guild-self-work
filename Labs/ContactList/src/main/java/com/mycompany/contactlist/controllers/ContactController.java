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
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Contact create(@RequestBody Contact contact) {

        return contactDao.add(contact);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact show(@PathVariable("id") Integer contactId) {

        Contact contact = contactDao.get(contactId);
        
        return contact;
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editSubmit(@ModelAttribute Contact contact) {

        contactDao.update(contact);
        return "redirect:/";
    }

}
