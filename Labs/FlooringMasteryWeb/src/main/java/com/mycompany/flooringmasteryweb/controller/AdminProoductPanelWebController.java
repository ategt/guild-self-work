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
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.ProductCommand;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/adminProductPanel")
public class AdminProoductPanelWebController {

    ProductDao productDao;
    StateDao stateDao;
    OrderDao orderDao;
    ConfigDao configDao;

    @Inject
    public AdminProoductPanelWebController(
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
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@ModelAttribute BasicOrderImpl basicOrder) {
//
//        Order order = orderDao.orderBuilder(basicOrder);
//        orderDao.create(order);
//
//        return "redirect:/";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String blank(Map model) {

        List<ProductCommand> productCommands = productCommandList();

        model.put("productCommands", productCommands);
        model.put("productCommand", new ProductCommand());

        return "editProduct";
    }

    private List<ProductCommand> productCommandList() {
        List<Product> products = productDao.getListOfProducts();
        products = productDao.sortByProductName(products);
        List<ProductCommand> productCommands = productDao.buildCommandProductList(products);
        return productCommands;
    }

    @RequestMapping(value = "/edit/{productName}", method = RequestMethod.GET)
    public String edit(@PathVariable("productName") String productName, Map model) {

        //List<Product> products = productDao.getListOfProducts();

        //products = productDao.sortByProductName(products);

        //List<ProductCommand> productCommands = productDao.buildCommandProductList(products);

        model.put("productCommands", productCommandList());
        //model.put("product", productDao.get(productName));
        model.put("productCommand", productDao.buildCommandProduct(productDao.get(productName)));

        return "editProduct";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute ProductCommand productCommand, BindingResult bindingResult, Map model) {

        //boolean productValid = productDao.validProductName(productCommand.getProductName());
       // boolean taxValid = true;

//        if (!productValid) {
//            bindingResult.rejectValue("productName", "error.user", "That Product Does Not Exist.");
//        }

        //return "redirect:/adminPanel/";
        //}
//
//    else {
//
//           
//
//        }
        //&& product.getProductTax() >= 0 && product.getProductTax() <= 100 ) {
        //bindingResult.rejectValue("productName", "error.user", "That Product Does Not Exist.");
//        if (productCommand.getProductTax() < 0) {
//            bindingResult.rejectValue("productTax", "error.user", "The Tax Can Not Be Less Than Zero.");
//            taxValid = false;
//        }
//
//        if (productCommand.getProductTax() > 100) {
//            bindingResult.rejectValue("productTax", "error.user", "The Tax Can Not Be That High.");
//            taxValid = false;
//        }
        if (bindingResult.hasErrors()) {

            //Product product = new Product();
//            product.setProductTax(productCommand.getProductTax());
//            product.setProductName(productCommand.getProductName());
            //ProductCommand product = productCommand;
            model.put("productCommand", productCommand);
            model.put("productCommands", productCommandList());

            //model.put("productError", !productValid);
            //model.put("taxError", !taxValid);

            return "editProduct";

        } else {
            //String enteredName = productCommand.getProductName();
            //String guessedName = productDao.bestGuessProductName(enteredName);
            //String productName = ProductUtilities.abbrFromProduct(guessedName);
            //ProductCommmand tempProductCommand = 
            //productCommand.setProductName(guessedName);
            
            Product product = productDao.resolveCommandProduct(productCommand);

            productDao.update(product);

            return "redirect:/adminPanel/";
        }

    }
//        String enteredName = product.getProductName();
//        String guessedName = ProductUtilities.bestGuessProductName(enteredName);
//        String productName = ProductUtilities.abbrFromProduct(guessedName);

//        product.setProductName(productName);
//
//        productDao.update(product);
//        
//    }
//
//    
//        else {
//
//            bindingResult.rejectValue("productName", "error.user", "That Product Does Not Exist.");
//
//    }
//
//    model.put (
//            
//
//    "products", productList());
//    model.put (
//            
//    "product", product);
    @RequestMapping(value = "/delete/{productName}", method = RequestMethod.GET)
    public String delete(@PathVariable("productName") String productName) {

        //orderDao.delete(orderDao.get(orderId));
        productDao.delete(productDao.get(productName));

        return "redirect:/adminPanel/";
    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String editSubmit(@ModelAttribute BasicOrderImpl basicOrder) {
//        Order order = orderDao.orderBuilder(basicOrder);
//        orderDao.update(order);
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//    public String show(@PathVariable("id") Integer orderId, Map model) {
//
//        Order order = orderDao.get(orderId);
//
//        model.put("order", order);
//
//        return "show";
//    }

}
