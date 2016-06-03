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
import com.mycompany.flooringmasteryweb.dto.BasicOrderImpl;
import com.mycompany.flooringmasteryweb.dto.Order;
import com.mycompany.flooringmasteryweb.dto.OrderCommand;
import com.mycompany.flooringmasteryweb.utilities.DateUtilities;
import com.mycompany.flooringmasteryweb.utilities.StateUtilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@ModelAttribute BasicOrderImpl basicOrder) {
//
//        Order order = orderDao.orderBuilder(basicOrder);
//        orderDao.create(order);
//
//        return "redirect:/";
//    }
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("orderCommand") OrderCommand orderCommand, BindingResult bindingResult, Map model, HttpSession session) {

        String stateInput = orderCommand.getState();

        boolean stateValid = StateUtilities.validStateInput(stateInput);

        if (!stateValid) {
            bindingResult.rejectValue("state", "error.user", "That State Does Not Exist.");
        } else {

            String stateGuess = StateUtilities.bestGuessStateName(stateInput);
            String stateAbbreviation = StateUtilities.abbrFromState(stateGuess);

            if (stateDao.get(stateAbbreviation) == null) {
                bindingResult.rejectValue("state", "error.user", "The System Can Not Currently Handle Orders In That State. Please Call The Office To Place This Order.");
            }

        }
        
        //OrderCommand orderCommandTarget = 

        if (bindingResult.hasFieldErrors("date")) {

            //bindingResult.getFieldValue("date")
            FieldError dateFieldError = bindingResult.getFieldError("date");

            if (dateFieldError.isBindingFailure()) {

                String failedDate = dateFieldError.getRejectedValue().toString();

                String dateFormat = DateUtilities.determineDateFormat(failedDate);

                if (dateFormat != null) {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

                    try {
                        Date date = simpleDateFormat.parse(failedDate);

                        // If it makes it to here, then date parsing was succesful.
                        orderCommand.setDate(date);

                        SimpleDateFormat properDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String springFriendlyDate = properDateFormat.format(date);
                        session.setAttribute("date", springFriendlyDate);

                        OrderCommand orderCommandTarget = (OrderCommand) bindingResult.getTarget();
                        orderCommandTarget.setDate(date);

                        //dateFieldError.
                    } catch (ParseException ex) {

                    }

                }
            }

        }

//        boolean stateValid  = StateUtilities.validStateInput(stateCommand.getStateName());
//        boolean taxValid = true;
//        
//        if (!stateValid) {
//            bindingResult.rejectValue("stateName", "error.user", "That State Does Not Exist.");
//        }
        if (bindingResult.hasErrors()) {

//            Boolean nameError = false;
//            Boolean stateError = false;
//            Boolean areaError = false;
//            Boolean dateError = false;
//            Boolean productError = false;
            String[] fields = {"name", "state", "area", "date", "product"};

            //Map<String, Boolean> errorMap = new HashMap();
//            errorMap.put("date", dateError);
//            errorMap.put("name", nameError);
//            errorMap.put("state", stateError);
//            errorMap.put("area", areaError);
//            errorMap.put("product", productError);
            java.util.List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                for (String testField : fields) {
                    if (fieldError.getField().equalsIgnoreCase(testField)) {
                        model.put(testField + "Error", true);
                    }
                }
            }

            int errorCount = bindingResult.getErrorCount();

            
            
            loadTheOrdersList(model);
            
            model.put("orderCommand", orderCommand);
            
            return "home";
        } else {

            Order order = orderDao.orderBuilder(orderCommand);
            if (order.getId() == 0) {
                orderDao.create(order);
            } else {
                orderDao.update(order);
            }

            return "redirect:/";

        }

    }

    private void loadTheOrdersList(Map model) {
        // System.out.println("test");
        //if (bindingResult

        List<Order> orders = orderDao.getList();
        orders = orderDao.sortByOrderNumber(orders);
        model.put("orders", orders);
    }

//    @ModelAttribute("orderCommand")
//    public OrderCommand getOrderCommand(HttpSession session) {
//    //public void getOrderCommand(HttpSession session) {
//        OrderCommand orderCommand = new OrderCommand();
//
//        String possibleDateString = session.getAttribute("date").toString();
//
//        String dateFormat = DateUtilities.determineDateFormat(possibleDateString);
//
//        if (dateFormat != null) {
//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
//
//            try {
//                Date date = simpleDateFormat.parse(possibleDateString);
//
//                // If it makes it to here, then date parsing was succesful.
//                orderCommand.setDate(date);
//
//                SimpleDateFormat properDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String springFriendlyDate = properDateFormat.format(date);
//
//                session.setAttribute("date", springFriendlyDate);
//
//                //session.setAttribute(stateInput, springFriendlyDate);
//                //dateFieldError.
//            } catch (ParseException ex) {
//
//            }
//        }
//
////orderCommand.setDate(date);
////
////mr.setWelfare((String)session.getAttribute("welfare"));
////    mr.setSchool((String)session.getAttribute("school"));
////    mr.setTitle((String)session.getAttribute("title"));
////    mr.setDistrict((String)session.getAttribute("district"));
////    mr.setName((String)session.getAttribute("name"));
////    mr.setFile((String)session.getAttribute("file"));
////    mr.setQueue((String)session.getAttribute("queue"));
////    mr.setRequestor(getUser());
////    mr.setSchool_id((String)session.getAttribute("school_id"));
////    mr.setBorough_id((String)session.getAttribute("borough_id"));
////    mr.setRetiree((String)session.getAttribute("retiree"));
//        return orderCommand;
//
//    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer orderId, Map model) {

        //List<Contact> contacts = contactDao.list();
        Order order = orderDao.get(orderId);

        //contactDao.sortByLastName(contacts);
        //model.put("contacts", contacts);
        OrderCommand orderCommand = orderDao.resolveOrderCommand(order);

        model.put("orderCommand", orderCommand);
        loadTheOrdersList(model);

        return "home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute BasicOrderImpl basicOrder) {
        Order order = orderDao.orderBuilder(basicOrder);
        orderDao.update(order);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer orderId) {

        orderDao.delete(orderDao.get(orderId));

        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editSubmit(@ModelAttribute BasicOrderImpl basicOrder) {
        Order order = orderDao.orderBuilder(basicOrder);
        orderDao.update(order);
        return "redirect:/";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer orderId, Map model) {

        Order order = orderDao.get(orderId);

        model.put("order", order);

        return "show";
    }

}
