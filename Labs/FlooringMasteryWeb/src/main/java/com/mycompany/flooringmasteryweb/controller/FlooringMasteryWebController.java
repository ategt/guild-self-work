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
import com.mycompany.flooringmasteryweb.dto.BasicOrderImpl;
import com.mycompany.flooringmasteryweb.dto.Order;
import com.mycompany.flooringmasteryweb.dto.OrderCommand;
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.State;
import com.mycompany.flooringmasteryweb.utilities.DateUtilities;
import com.mycompany.flooringmasteryweb.utilities.StateUtilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    @ResponseBody
//    public Order createWithAjax(@RequestBody Order order) {
//
//        return orderDao.create(order);
//    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order showWithAjax(@PathVariable("id") Integer orderId) {

        Order contact = orderDao.get(orderId);

        return contact;
    }

//
//    @RequestMapping(value = "/", method = RequestMethod.PUT)
//    @ResponseBody
//    public Order editSubmitWithAjax(@RequestBody Order order) {
//
//        orderDao.update(order);
//
//        return order;
//    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public OrderCommand update(@Valid @RequestBody OrderCommand orderCommand, BindingResult bindingResult) {

        validateInputs(orderCommand, bindingResult);

        if (bindingResult.hasErrors()) {
            return null;
        } else {
            Order order = orderDao.orderBuilder(orderCommand);

            Order orderTemp = order;

            if (order.getId() == 0) {
                orderTemp = orderDao.create(order);
            } else {
                orderDao.update(order);
            }

            return orderDao.resolveOrderCommand(orderTemp);
        }
    }

    private void validateInputs(OrderCommand orderCommand, BindingResult bindingResult) {
        validateState(orderCommand, bindingResult);
        validateProduct(orderCommand, bindingResult);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteWithAjax(@PathVariable("id") Integer orderId) {

        orderDao.delete(orderDao.get(orderId));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public OrderCommand createWithAjax(@Valid @RequestBody OrderCommand orderCommand, BindingResult bindingResult) {

        //validateInputs(orderCommand, bindingResult);

        if (bindingResult.hasErrors()) {
            return null;
        } else {
            Order order = orderDao.orderBuilder(orderCommand);

            Order orderTemp = order;

            if (order.getId() == 0) {
                orderTemp = orderDao.create(order);
            } else {
                orderDao.update(order);
            }

            return orderDao.resolveOrderCommand(orderTemp);
        }
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("orderCommand") OrderCommand orderCommand, BindingResult bindingResult, Map model, HttpSession session) {

        validateInputs(orderCommand, bindingResult);

        if (bindingResult.hasFieldErrors("date")) {

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

                    } catch (ParseException ex) {

                    }

                }
            }

        }

        if (bindingResult.hasErrors()) {

            String[] fields = {"name", "state", "area", "date", "product"};

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

    private void validateProduct(OrderCommand orderCommand, BindingResult bindingResult) {
        String productInput = orderCommand.getProduct();

        boolean productValid = productDao.validProductName(productInput);

        if (!productValid) {
            bindingResult.rejectValue("product", "error.user", "We Do not Carry That Product.");
        } else {

            String productGuess = productDao.bestGuessProductName(productInput);

            if (productDao.get(productGuess) == null) {
                bindingResult.rejectValue("product", "error.user", "We Do not Carry That Product.");
            } else {
                orderCommand.setProduct(productGuess);
            }

        }
    }

    private void validateState(OrderCommand orderCommand, BindingResult bindingResult) {
        String stateInput = orderCommand.getState();

        boolean stateValid = StateUtilities.validStateInput(stateInput);

        if (!stateValid) {
            bindingResult.rejectValue("state", "error.user", "That State Does Not Exist.");
        } else {

            String stateGuess = StateUtilities.bestGuessStateName(stateInput);
            String stateAbbreviation = StateUtilities.abbrFromState(stateGuess);

            if (stateDao.get(stateAbbreviation) == null) {
                bindingResult.rejectValue("state", "error.user", "The System Can Not Currently Handle Orders In That State. Please Call The Office To Place This Order.");
            } else {
                orderCommand.setState(stateAbbreviation);
            }

        }
    }

    private void loadTheOrdersList(Map model) {

        List<Order> orders = orderDao.getList();
        orders = orderDao.sortByOrderNumber(orders);
        model.put("orders", orders);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer orderId, Map model) {

        Order order = orderDao.get(orderId);

        OrderCommand orderCommand = orderDao.resolveOrderCommand(order);

        model.put("orderCommand", orderCommand);
        loadTheOrdersList(model);

        return "home";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer orderId, Map model) {

        Order order = orderDao.get(orderId);

        OrderCommand orderCommand = orderDao.resolveOrderCommand(order);

        model.put("orderCommand", orderCommand);
        model.put("order", order);
        loadTheOrdersList(model);

        return "displayOrder";
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Map model) {

        loadTheOrdersList(model);

        return "searchOrder";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("searchBy") String searchBy, @RequestParam("searchText") String searchText, Map model) {
        List<Order> orders = orderDao.getList();
        Boolean error = false;
        Boolean dateError = false;

        if ("searchByOrderNumber".equalsIgnoreCase(searchBy)) {
            try {
                Integer inputInt = Integer.parseInt(searchText);
                orders = orderDao.searchByOrderNumber(inputInt);
            } catch (NumberFormatException numberFormatException) {
                error = true;
            }

        } else if ("searchByDate".equalsIgnoreCase(searchBy)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                Date orderDate = simpleDateFormat.parse(searchText);
                orders = orderDao.searchByDate(orderDate);
            } catch (ParseException ex) {
                error = true;
                dateError = true;
            }

        } else if ("searchByName".equalsIgnoreCase(searchBy)) {
            orders = orderDao.searchByName(searchText);
        } else if ("searchByProduct".equalsIgnoreCase(searchBy)) {
            String productGuess = productDao.bestGuessProductName(searchText);

            if (productGuess == null) {
                error = true;
            } else {
                Product product = productDao.get(productGuess);
                orders = orderDao.searchByProduct(product);
            }

        } else if ("searchByState".equalsIgnoreCase(searchBy)) {

            String stateGuess = StateUtilities.bestGuessStateName(searchText);

            if (stateGuess == null) {
                error = true;
            } else {
                State state = stateDao.get(stateGuess);
                orders = orderDao.searchByState(state);
            }

        }

        model.put("orders", orders);
        model.put("error", error);
        model.put("dateError", dateError);

        return "searchOrder";

    }

}
