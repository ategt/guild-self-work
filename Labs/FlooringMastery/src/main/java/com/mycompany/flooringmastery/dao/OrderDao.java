/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {

    String addLabels(Order order, final String TOKEN);

    String addLabels(String orderString, final String TOKEN);

    String addLabels(Order order, final String TOKEN, final String SECOND_TOKEN);

    /**
     * The first token is for seperating label-order pairs(it is a comma in the
     * file and a new line in the controller). The second token is for
     * seperating labels and orders (it would be the dash in the above example).
     *
     * @param orderString
     * @param TOKEN
     * @param SECOND_TOKEN
     * @return
     */
    String addLabels(String orderString, final String TOKEN, final String SECOND_TOKEN);

    Order create(Order order);

    void delete(Order order);

    Order get(Integer id);

    @Deprecated
    List<Order> getList();

    List<Date> listOrderDates();

    List<Integer> listOrderNumbers();

    void purgeTestFiles();

    List<Order> searchByDate(Date date);

    List<Order> searchByName(String orderName);

    int size();

    String toString(Order order);

    String toString(Order order, final String TOKEN);

    String toString(Order order, final String TOKEN, final String CSV_ESCAPE);

    void update(Order order);
    
}
