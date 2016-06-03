/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.controller;

//import com.mycompany.dvdlibraryweb.interfaces.Dvd;
//import com.mycompany.dvdlibraryweb.interfaces.DvdLibrary;
//import com.thesoftwareguild.interfaces.dto.Address;
import com.mycompany.flooringmasteryweb.dao.ConfigDao;
import com.mycompany.flooringmasteryweb.dao.OrderDao;
import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.StateDao;
import com.mycompany.flooringmasteryweb.dto.BasicOrder;
import com.mycompany.flooringmasteryweb.dto.BasicOrderImpl;
import com.mycompany.flooringmasteryweb.dto.Order;
import com.mycompany.flooringmasteryweb.dto.OrderCommand;
import com.mycompany.flooringmasteryweb.dto.StateCommand;
import com.mycompany.flooringmasteryweb.utilities.StateUtilities;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    //private DvdLibrary dvdLibrary;
    // private com.thesoftwareguild.interfaces.dao.AddressBookDao addressDao;
    ProductDao productDao;
    StateDao stateDao;
    OrderDao orderDao;
    ConfigDao configDao;

    @Inject
    public HomeController(
            ProductDao productDao,
            StateDao stateDao,
            OrderDao orderDao,
            ConfigDao configDao
    //com.thesoftwareguild.interfaces.dao.AddressBookDao addressDao
    ) {

        this.productDao = productDao;
        this.stateDao = stateDao;
        this.orderDao = orderDao;
        this.configDao = configDao;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        //List<Address> addresses = addressDao.list();
        //dvdLibrary.sortByLastName(contacts);
        List<Order> orders = orderDao.getList();

        orders = orderDao.sortByOrderNumber(orders);

        putBlankOrder(model);
        model.put("orders", orders);

        return "home";
    }

    public void putBlankOrder(Map model) {
        OrderCommand orderCommand = new OrderCommand();
        orderCommand.setId(0);

        model.put("orderCommand", orderCommand);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {

        return "redirect:/addressbook/search";
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String redirectToAdminPanel() {

        return "redirect:/adminPanel/";
    }

    @RequestMapping(value = "/adminStatePanel", method = RequestMethod.GET)
    public String stateBlank() {
        return "redirect:/adminStatePanel/";
    }

    @RequestMapping(value = "/adminProductPanel", method = RequestMethod.GET)
    public String productBlank() {
        return "redirect:/adminProductPanel/";
    }

}
