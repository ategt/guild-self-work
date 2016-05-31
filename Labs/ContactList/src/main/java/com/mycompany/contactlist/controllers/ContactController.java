/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.controllers;

import com.mycompany.contactlist.dao.ContactDao;
import com.mycompany.contactlist.dao.ContactDaoImpl;
import com.mycompany.contactlist.dto.Contact;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam int id, Map model) {
        //contactDao.add(contact);
        //public String home(Map model) {

        List<Contact> contacts = contactDao.list();
        Contact contact = contactDao.get(id);

        contactDao.sortByLastName(contacts);

        //ContactDaoImpl.compareLastName(contact, contact));
        model.put("contacts", contacts);
        model.put("contact", contact);

        return "edit";
        //return "home";
        //return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Contact contact) {
        contactDao.update(contact);

        //model.put("contacts", contacts);
        //model.put("contact", contact);
        //return "edit";
        //return "home";
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id) {

        //contactDao.get(id);
        contactDao.remove(contactDao.get(id));

        //return "redirect:/";
        return "redirect:/";
    }

}
