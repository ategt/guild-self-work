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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author apprentice
 */
public class OrderDao {

    private List<Order> orders;
    private int nextId;
    //private File orderDataFile = new File("OrdersData.txt");
    private File orderDataFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersData.txt");
    private File testOrderDateFile = new File("OrdersTestData.txt");
    private StateDao stateDao;
    private ProductDao productDao;
    private boolean isATest;

    public OrderDao(ProductDao productDao, StateDao stateDao) {
        this(productDao, stateDao, false);
    }

    protected OrderDao(ProductDao productDao, StateDao stateDao, boolean isATest) {
        //protected OrderDao(ProductDao productDao, StateDao stateDao, java.io.File ) {

        this.productDao = productDao;
        this.stateDao = stateDao;
        this.isATest = isATest;

        try {

            if (isATest) {
                orderDataFile = testOrderDateFile;
                orders = decode(orderDataFile);
            } else {
                List<Order> loadedOrders = new ArrayList();
                java.io.File[] orderFiles = lookForOrders();
                for (java.io.File orderFile : orderFiles) {
                    if (!orderFile.getName().endsWith("00000000.txt")) {
                        loadedOrders.addAll(decode(orderFile));
                    }
                }

                if (orders == null) {
                    orders = loadedOrders;
                } else {
                    orders.addAll(loadedOrders);
                }

//                for (java.io.File orderFile : orderFiles) {
//                    if (orderFile.getName().endsWith("00000000.txt")){
//                        if (get()){
//                    //loadedOrders.addAll(decode(orderFile));
//                }}}
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        encode(order);

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

        Date oldDate = null;
        if (found != null) {
            oldDate = found.getDate();

            orders.remove(found);
            orders.add(order);
        }

        encode(oldDate);

        encode(order);

    }

    public void delete(Order order) {
        Order found = null;

        for (Order currentOrder : orders) {
            if (currentOrder.getId() == order.getId()) {
                found = currentOrder;
                break;
            }
        }

        Date oldDate = null;
        if (found != null) {
            oldDate = found.getDate();
            orders.remove(found);
        }

        encode(oldDate);
        encode(order);

    }

    public List<Order> getList() {
        List<Order> copy = new ArrayList();
        copy.addAll(orders);
        return copy;
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

    private static boolean isSameDay(java.util.Date date1, java.util.Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd");

        if (date1 == null || date2 == null) {
            return false;
        }
        return fmt.format(date1).equals(fmt.format(date2));
    }

    private int determineNextId() {
        int highestId = 0;

        for (Order order : orders) {
            if (highestId < order.getId()) {
                highestId = order.getId();
            }
        }

        highestId++;
        return highestId++;
    }

//    private void encode() {
//        encode(orderDataFile);
//    }
//    private void encode(PrintWriter printWriter) {
//        encode(printWriter, orders);
//    }
    private void encode(Order order) {
        //determineFile(order.getDate());
        // This was the source of a lot of problems earlier.

        encode(order.getDate());
    }

    private void encode(java.util.Date date) {
        try {
            File file = null;
            if (isATest) {
                file = testOrderDateFile;
            } else {
                file = determineFile(date);
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            encode(file, searchByDate(date));

        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Something just went quite wrong in the file creation/encoding method!! ");
        }

    }

    //private void encode(PrintWriter printWriter, List<Order> groupOfOrders) {
    //}
    private void encode(java.io.File orderFile, List<Order> groupOfOrders) throws IOException {
        encode(new PrintWriter(new FileWriter(orderFile)), groupOfOrders);
    }

    private void encode(PrintWriter printWriter, List<Order> groupOfOrders) {

        final String TOKEN = ",";
        //final String CSV_ESCAPE = Pattern.quote("\\") + TOKEN;
        final String DATAHEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost"
                + "PerSquareFoot,MaterialCost,LaborCost,Tax,Total";

        try (PrintWriter out = printWriter) {
            out.println(DATAHEADER);

            for (Order order : groupOfOrders) {

                //toString(out, order, TOKEN, nameValue, stateName, productName);
                String orderString = toString(order, TOKEN);

                out.println(orderString);
            }

            out.flush();
        }

    }

    public String toString(Order order) {
        return toString(order, "");
    }

    public String toString(Order order, final String TOKEN) {
        final String CSV_ESCAPE = Pattern.quote("\\") + TOKEN;

        return toString(order, TOKEN, CSV_ESCAPE);
    }

    //private String toString(final PrintWriter out, Order order, final String TOKEN, String nameValue, String stateName, String productName) {
    public String toString(Order order, final String TOKEN, final String CSV_ESCAPE) {

        String stateName = "null";
        if (order.getState() == null) {
            stateName = "null";
        } else if (order.getState().getState() != null) {
            stateName = order.getState().getState().replaceAll(TOKEN, CSV_ESCAPE);
        }

        String productName = "null";
        if (order.getProduct() != null) {
            if (order.getProduct().getType() != null) {

                productName = order.getProduct().getType().replaceAll(TOKEN, CSV_ESCAPE);

            }
        }

        String nameValue = null;
        if (order.getName() != null) {
            nameValue = order.getName().replaceAll(TOKEN, CSV_ESCAPE).replaceAll("Q", "").replaceAll("E", "");
        }

        return toString(order, TOKEN, nameValue, stateName, productName);
    }

    private String toString(Order order, final String TOKEN, String nameValue, String stateName, String productName) {

        String orderString = "";

        orderString += order.getId();
        orderString += TOKEN;
        orderString += nameValue;
        orderString += TOKEN;
        orderString += stateName;
        orderString += TOKEN;
        orderString += order.getTaxRate();
        orderString += TOKEN;
        orderString += productName;
        orderString += TOKEN;
        orderString += order.getArea();
        orderString += TOKEN;
        orderString += order.getCostPerSquareFoot();
        orderString += TOKEN;
        orderString += order.getLaborCostPerSquareFoot();
        orderString += TOKEN;
        orderString += order.getMaterialCost();
        orderString += TOKEN;
        orderString += order.getLaborCost();
        orderString += TOKEN;
        orderString += order.getTax();
        orderString += TOKEN;
        orderString += order.getTotal();

        return orderString;
    }

    public String addLabels(Order order, final String TOKEN) {
        return addLabels(toString(order, TOKEN), TOKEN);
    }

    public String addLabels(String orderString, final String TOKEN) {
        return addLabels(orderString, TOKEN, ": ");
    }

    public String addLabels(Order order, final String TOKEN, final String SECOND_TOKEN) {
        return addLabels(toString(order, TOKEN), TOKEN, SECOND_TOKEN);
    }

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
    public String addLabels(String orderString, final String TOKEN, final String SECOND_TOKEN) {

//        if (TOKEN.isEmpty()) {
//            TOKEN = System.lineSeparator();
//        }
        String labeledOrderString = "";

        String[] orderParts = orderString.split(TOKEN);

//        for (int x = 0 ; x < orderParts.length ; x++){
//            orderParts[x] = orderParts[x].replaceAll(TOKEN, System.lineSeparator()) ;
//            
//        }
//        
        //TOKEN.isEmpty() || 
        
        if (orderParts.length < 11) {
            System.out.println("Something Just went wrong on line 373 of the order DAO\n" + " - " + TOKEN + " - " );
        } else {

            labeledOrderString += "Order ID#" + SECOND_TOKEN + orderParts[0] + TOKEN;
            labeledOrderString += "Customer Name" + SECOND_TOKEN + orderParts[1] + TOKEN;
            labeledOrderString += "State" + SECOND_TOKEN + orderParts[2] + TOKEN;
            labeledOrderString += "Tax Rate" + SECOND_TOKEN + orderParts[3] + TOKEN;
            labeledOrderString += "Product Name" + SECOND_TOKEN + orderParts[4] + TOKEN;
            labeledOrderString += "Area" + SECOND_TOKEN + orderParts[5] + TOKEN;
            labeledOrderString += "Cost Per Square Foot" + SECOND_TOKEN + orderParts[6] + TOKEN;
            labeledOrderString += "Labor Cost Per Square Foot" + SECOND_TOKEN + orderParts[7] + TOKEN;
            labeledOrderString += "Material Cost" + SECOND_TOKEN + orderParts[8] + TOKEN;
            labeledOrderString += "Labor Cost" + SECOND_TOKEN + orderParts[9] + TOKEN;
            labeledOrderString += "Tax" + SECOND_TOKEN + orderParts[10] + TOKEN;
            labeledOrderString += "Total" + SECOND_TOKEN + orderParts[11];
        }
        return labeledOrderString;

    }

    private List<Order> decode() throws FileNotFoundException, IOException {
        return decode(orderDataFile);
    }

    private List<Order> decode(java.io.File orderFile) throws FileNotFoundException, IOException {

        if (!orderFile.exists()) {
            orderFile.createNewFile();
        }

        String dateString = orderFile.getName();

        return decode(new BufferedReader(new FileReader(orderFile)), dateString);

    }

    private List<Order> decode(BufferedReader bufferedReader, String dateString) {

        List<Order> orderList = new ArrayList<>();

        final String TOKEN = ",";
        final String CSV_ESCAPE = Pattern.quote("\\") + TOKEN;
        final String CSV_ESCAPE_TEMP = "::==::";

        final String DATAHEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost"
                + "PerSquareFoot,MaterialCost,LaborCost,Tax,Total";

        java.util.Date orderDate = extractDate(dateString);

        try (Scanner sc = new Scanner(bufferedReader)) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                if (currentLine.equalsIgnoreCase(DATAHEADER)) {

                } else if (!currentLine.trim().isEmpty()) {

                    String[] stringParts = currentLine.replaceAll(CSV_ESCAPE, CSV_ESCAPE_TEMP).split(TOKEN);

                    for (String string : stringParts) {
                        System.out.print(string + " - ");
                    }
                    System.out.println("");

                    for (int x = 0; x < stringParts.length; x++) {
                        stringParts[x] = stringParts[x].replaceAll(CSV_ESCAPE_TEMP, TOKEN);
                    }

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

                    order.setDate(orderDate);

                    orderList.add(order);
                }
            }
        }

        return orderList;
    }

    private java.io.File[] lookForOrders() {
        return lookForOrders(orderDataFile.getParentFile());
    }

    private java.io.File[] lookForOrders(java.io.File file) {
        if (file.isDirectory()) {
            java.io.File[] orderFiles = file.listFiles(new com.mycompany.flooringmastery.utilities.OrderFilter());
            return orderFiles;
        } else {
            return null;
        }
    }

    private File determineFile(java.util.Date date) {
        try {
            return determineFile(orderDataFile, date);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("Something just went very wrong in the determineFile Method!!!");
        }
        return null;
    }

    private File determineFile(File ordersDirectory, java.util.Date date) throws FileNotFoundException {
        //File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersData.txt")
        String dateString;

        String orderDirectoryPath = "";

        if (ordersDirectory.isDirectory()) {
            orderDirectoryPath = ordersDirectory.getAbsolutePath();
        } else if (ordersDirectory.isFile()) {
            orderDirectoryPath = ordersDirectory.getParent();

        } else {
            //throw new FileNotFoundException();
        }

        if (date == null) {
            dateString = "00000000";
        } else {
            java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("MMddyyyy");
            dateString = fmt.format(date);
        }

        String prefix = "Orders_";
        String extension = ".txt";
        String dateFilePath = orderDirectoryPath + prefix + dateString + extension;

        return new File(dateFilePath);
    }

    private Date extractDate(String dateString) {
        Date date = null;
        if (dateString.toLowerCase().contains("test")) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, Calendar.JANUARY, 1);
            date = calendar.getTime();
        } else {
            String simplifiedDateString = dateString.replaceAll("Orders_", "").replaceAll(".txt", "");
            java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("MMddyyyy");

            String regex = "[0-9]+";

            try {
                if (simplifiedDateString.matches(regex)) {
                    date = fmt.parse(simplifiedDateString);

                } else {
                    System.out.println("Date String: " + dateString + "\nDate unparsable exception thrown here!!!!!\n");
                }
            } catch (ParseException ex) {
                Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return date;
    }

}
