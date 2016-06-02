/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.controllers;

//import com.mycompany.dvdlibraryweb.dao.NoteDaoImplementation;
//import com.mycompany.dvdlibraryweb.dao.DvdLibraryLambdaImplementation;
//import com.mycompany.dvdlibraryweb.dao.DvdLibraryImplementation;
//import com.mycompany.dvdlibraryweb.dto.DvdImplementation;
//import com.mycompany.dvdlibraryweb.dto.NoteImplementation;
//import com.mycompany.dvdlibraryweb.interfaces.Dvd;
//import com.mycompany.dvdlibraryweb.interfaces.DvdLibrary;
import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/addressbook")
public class AddressBookController {

    private  com.thesoftwareguild.interfaces.dao.AddressBookDao addressDao;

    @Inject
    public AddressBookController( com.thesoftwareguild.interfaces.dao.AddressBookDao addressDao) {
        this.addressDao = addressDao;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //public String create(@ModelAttribute("dvdImpl") Dvd dvd) {
    public String create(@ModelAttribute Address address) {
        addressDao.create(address);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer contactId, Map model) {

        //List<Contact> contacts = contactDao.list();
        Address address = addressDao.get(contactId);

        //contactDao.sortByLastName(contacts);
        //model.put("contacts", contacts);
        model.put("address", address);

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute Address address) {
        addressDao.update(address);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer dvdId) {

        addressDao.delete(dvdId);

        return "redirect:/";
    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String editSubmit(@ModelAttribute Address address) {
//
//        addressDao.update(address);
//        return "redirect:/";
//    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchText") String searchText,
            Map model) {

        // List<Dvd> dvds = addressDao.getAllDvds();
        List<Address> addresses = null;
     //   addresses = addressDao.list();
        if ("searchByLastName".equalsIgnoreCase(searchBy)) {
            addresses = addressDao.searchByLastName(searchText);
            
        } else if ("searchByFirstName".equalsIgnoreCase(searchBy)) {
            addresses = addressDao.searchByFirstName(searchText);

        } else if ("searchByCity".equalsIgnoreCase(searchBy)) {
            addresses = addressDao.searchByCity(searchText);
       
        } else if ("searchByState".equalsIgnoreCase(searchBy)) {
            addresses = addressDao.searchByState(searchText);
        
        } else if ("searchByZip".equalsIgnoreCase(searchBy)) {
            //List<Dvd> dvds1 = addressDao.searchByDirector(searchText).values().stream().collect(Collectors.t).;
            addresses = addressDao.searchByZip(searchText);

        } else {

            addresses = addressDao.list();

        }
        //.getAllDvds();

       // addressDao.sortByTitle(addresses);

        model.put("addresses", addresses);

        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String blankSearch(Map model) {

        List<Address> addresses = addressDao.list();
        //addressDao.sortByTitle(dvds);

        model.put("addresses", addresses);

        return "search";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer dvdId, Map model) {

        Address address = addressDao.get(dvdId);
        List<Address> addresses = addressDao.list();

        
        
        model.put("address", address);
        model.put("addresses", addresses);

        return "show";
    }
    
    @RequestMapping(value = "/show/{id}/{lastFirst}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer dvdId, @PathVariable("lastFirst") Boolean lastNameFirst, Map model) {

        Address address = addressDao.get(dvdId);
        List<Address> addresses = addressDao.list();

        
        model.put("lastNameFirst", lastNameFirst);
        model.put("address", address);
        model.put("addresses", addresses);

        return "show";
    }

}
