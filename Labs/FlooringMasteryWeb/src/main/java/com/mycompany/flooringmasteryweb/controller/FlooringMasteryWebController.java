/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.controller;

import com.mycompany.flooringmasteryweb.dao.ConfigDao;
import com.mycompany.flooringmasteryweb.dao.OrderDao;
import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.StateDao;
import com.mycompany.flooringmasteryweb.dto.BasicOrder;
import com.mycompany.flooringmasteryweb.dto.Order;
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
@RequestMapping(value = "/FlooringMaster")
public class FlooringMasteryWebController {

    ProductDao productDao;
    StateDao stateDao;
    OrderDao orderDao;
    ConfigDao configDao;

    @Inject
    public FlooringMasteryWebController(
            ProductDao productDao,
            StateDao stateDao,
            OrderDao orderDao,
            ConfigDao configDao
    ) {

        this.productDao = productDao;
        this.stateDao = stateDao;
        this.orderDao = orderDao;
        this.configDao = configDao;

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute BasicOrder basicOrder) {
        
        Order order = orderDao.orderBuilder(basicOrder);
        orderDao.create(order);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer orderId, Map model) {

        //List<Contact> contacts = contactDao.list();
        Order order = orderDao.get(orderId);

        //contactDao.sortByLastName(contacts);
        //model.put("contacts", contacts);
        model.put("order", order);

        return "edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Order order) {
        orderDao.update(order);

        return "redirect:/";
    }

}
