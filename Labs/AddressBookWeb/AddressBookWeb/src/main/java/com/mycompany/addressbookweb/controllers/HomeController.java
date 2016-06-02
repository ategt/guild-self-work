/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.controllers;

//import com.mycompany.dvdlibraryweb.interfaces.Dvd;
//import com.mycompany.dvdlibraryweb.interfaces.DvdLibrary;
import com.thesoftwareguild.interfaces.dto.Address;
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

    //private DvdLibrary dvdLibrary;
    private com.thesoftwareguild.interfaces.dao.AddressBookDao addressDao;
    
    @Inject
    public HomeController(com.thesoftwareguild.interfaces.dao.AddressBookDao addressDao) {
        this.addressDao = addressDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<Address> addresses = addressDao.list();
        //dvdLibrary.sortByLastName(contacts);

        model.put("addresses", addresses);

        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {


        return "redirect:/addressbook/search";
    }

}
