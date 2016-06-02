/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.controllers;

import com.mycompany.contactlist.dao.ContactDao;
import com.mycompany.contactlist.dto.Contact;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private ContactDao contactDao;

    @Inject
    public HomeController(ContactDao dao) {
        this.contactDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<Contact> contacts = contactDao.list();
        contactDao.sortByLastName(contacts);

        model.put("contacts", contacts);
        model.put("contact", new Contact());

        return "home";
    }

}
