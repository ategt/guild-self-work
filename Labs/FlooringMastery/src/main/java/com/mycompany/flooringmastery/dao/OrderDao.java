/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class OrderDao {

    List<Order> orders;
    int nextId;
    File orderDataFile = new File("OrdersData.txt");
    StateDao stateDao;
    ProductDao productDao;

    public OrderDao(ProductDao productDao, StateDao stateDao) {
        this(productDao, stateDao, false);
    }

    protected OrderDao(ProductDao productDao, StateDao stateDao, boolean isATest) {

        this.productDao = productDao;
        this.stateDao = stateDao;

        if (isATest) {
            orderDataFile = new File("OrdersTestData.txt");
        }

        orders = decode();

        if (orders == null) {
            orders = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public Order create(Order order) {
        order.setId(nextId);
        nextId++;

        orders.add(order);

        encode();

        return order;
    }

    public Order get(Integer id) {

        for (Order order : orders) {
            if (order != null) {
                if (order.getId() == id) {
                    return order;
                }
            }
        }

        return null;
    }

    public void update(Order order) {
        Order found = null;

        for (Order currentOrder : orders) {
            if (currentOrder.getId() == order.getId()) {
                found = currentOrder;
                break;
            }
        }

        if (found != null) {
            orders.remove(found);
            orders.add(order);
        }

        encode();

    }

    public void delete(Order order) {
        Order found = null;

        for (Order currentOrder : orders) {
            if (currentOrder.getId() == order.getId()) {
                found = currentOrder;
                break;
            }
        }

        if (found != null) {
            orders.remove(found);
        }

        encode();

    }

    public List<Order> getList() {

        return orders;
    }

    public int size() {
        return orders.size();
    }

    public java.util.List<Order> searchByDate(java.util.Date date) {
        java.util.List<Order> specificOrders = new ArrayList();

        for (Order order : orders) {
            if (isSameDay(order.getDate(), date)) {
                specificOrders.add(order);
            }
        }

        return specificOrders;
    }

    public static boolean isSameDay(java.util.Date date1, java.util.Date date2) {
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    private int determineNextId() {
        int highestId = 100;

        for (Order order : orders) {
            if (highestId < order.getId()) {
                highestId = order.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = ",";
        final String DATAHEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost"
                + "PerSquareFoot,MaterialCost,LaborCost,Tax,Total";
        try {

            PrintWriter out = new PrintWriter(new FileWriter(orderDataFile));

            out.println(DATAHEADER);

            for (Order order : orders) {

                out.print(order.getId());
                out.print(TOKEN);
                out.print(order.getName());
                out.print(TOKEN);
                out.print(order.getState());
                out.print(TOKEN);
                out.print(order.getTaxRate());
                out.print(TOKEN);
                out.print(order.getProduct().getType());
                out.print(TOKEN);
                out.print(order.getArea());
                out.print(TOKEN);
                out.print(order.getCostPerSquareFoot());
                out.print(TOKEN);
                out.print(order.getLaborCostPerSquareFoot());
                out.print(TOKEN);
                out.print(order.getMaterialCost());
                out.print(TOKEN);
                out.print(order.getLaborCost());
                out.print(TOKEN);
                out.print(order.getTax());
                out.print(TOKEN);
                out.print(order.getTotal());

                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Order> decode() {

        List<Order> orderList = new ArrayList<>();

        final String TOKEN = ",";
        final String DATAHEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost"
                + "PerSquareFoot,MaterialCost,LaborCost,Tax,Total";

        try {

            if (!orderDataFile.exists()) {
                orderDataFile.createNewFile();
            }

            Scanner sc = new Scanner(new BufferedReader(new FileReader(orderDataFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                if (currentLine.equalsIgnoreCase(DATAHEADER)) {

                } else if (!currentLine.trim().isEmpty()) {

                    String[] stringParts = currentLine.split(TOKEN);

                    Order order = new Order();

                    String orderIdString = stringParts[0];

                    try {
                        int orderId = Integer.parseInt(orderIdString);
                        order.setId(orderId);
                    } catch (NumberFormatException numFmtEx) {

                    }

                    String name = stringParts[1];
                    order.setName(name);

                    String state = stringParts[2];
                    order.setState(stateDao.get(state));

                    try {

                        String taxRateString = stringParts[3];
                        double taxRate = Double.parseDouble(taxRateString);
                        order.setTaxRate(taxRate);

                    } catch (NumberFormatException numFmtEx) {

                    }
                    String productType = stringParts[4];

                    order.setProduct(productDao.get(productType));

                    try {
                        String areaString = stringParts[5];
                        double area = Double.parseDouble(areaString);
                        order.setArea(area);
                    } catch (NumberFormatException numFmtEx) {

                    }

                    try {
                        String costPerSquareFootString = stringParts[6];
                        double costPerSquareFoot = Double.parseDouble(costPerSquareFootString);
                        order.setCostPerSquareFoot(costPerSquareFoot);
                    } catch (NumberFormatException numFmtEx) {

                    }

                    try {
                        String laborCostPerSquareFootString = stringParts[7];
                        double laborCostPerSquareFoot = Double.parseDouble(laborCostPerSquareFootString);
                        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
                    } catch (NumberFormatException numFmtEx) {

                    }

                    try {

                        String materialCostString = stringParts[8];
                        double materialCost = Double.parseDouble(materialCostString);
                        order.setMaterialCost(materialCost);
                    } catch (NumberFormatException numFmtEx) {

                    }

                    try {

                        String laborCostString = stringParts[9];
                        double laborCost = Double.parseDouble(laborCostString);
                        order.setLaborCost(laborCost);

                    } catch (NumberFormatException numFmtEx) {

                    }
                    try {

                        String taxString = stringParts[10];
                        double tax = Double.parseDouble(taxString);
                        order.setTax(tax);

                    } catch (NumberFormatException numFmtEx) {

                    }
                    try {

                        String totalString = stringParts[11];
                        double total = Double.parseDouble(totalString);
                        order.setTotal(total);
                    } catch (NumberFormatException numFmtEx) {

                    }

                    orderList.add(order);
                }
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return orderList;
    }

}
