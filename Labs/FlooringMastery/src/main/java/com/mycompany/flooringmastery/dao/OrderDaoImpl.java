/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.utilities.OrderDaoFileIOImplementation;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author apprentice
 */
public class OrderDaoImpl implements OrderDao {

     private ApplicationContext ctx;

    //public AuditAspect() {
        //ctx = ApplicationContextProvider.getApplicationContext.getBean("BeanId", MyBean.class);
        //ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
   
     private List<Order> orders;
    private int nextId;
    private StateDao stateDao;
    private ProductDao productDao;
    private ConfigDao configDao;
    private boolean isATest;
    private com.mycompany.flooringmastery.utilities.OrderDaoFileIO orderIo;
    

    public OrderDaoImpl(ProductDao productDao, StateDao stateDao) {
        this(productDao, stateDao, null);
    }

    public OrderDaoImpl(ProductDao productDao, StateDao stateDao, ConfigDao configDao) {

             ctx = com.mycompany.flooringmastery.aop.ApplicationContextProvider.getApplicationContext();
   
        this.productDao = productDao;
        this.stateDao = stateDao;

        if (configDao != null) {
            this.configDao = configDao;
            this.isATest = configDao.get().isInTestMode();
        } else {
            this.isATest = true;
        }

        this.orderIo = new OrderDaoFileIOImplementation(this, stateDao, productDao);

        init(configDao);
        
        //this.orderIo = ctx.getBean(type)
        //this.orderIo = new com.mycompany.flooringmastery.utilities.OrderDaoFileIOImplementation(this, stateDao, productDao);
        //this.orderIo = ctx.getBean(type)
    }

    private void init(ConfigDao configDao1) {
        try {

            File directoryToSearch = null;
            if (isATest) {
                directoryToSearch = configDao1.get().getTestDirectory();
            } else {
                directoryToSearch = configDao1.get().getOrdersDirectory();
            }

            List<Order> loadedOrders = new ArrayList();
            java.io.File[] orderFiles = lookForOrders(directoryToSearch);
            
            for (java.io.File orderFile : orderFiles) {
                if (!orderFile.getName().endsWith("00000000.txt")) {
                    loadedOrders.addAll(orderIo.decode(orderFile));
                }
            }

            java.util.Set<Integer> ids = new java.util.HashSet();
            loadedOrders.stream().forEach(order -> {
                ids.add(order.getId());
            });

            for (java.io.File orderFile : orderFiles) {
                if (orderFile.getName().endsWith("00000000.txt")) {
                    System.out.println("orderFile");
                    for (Order order : orderIo.decode(orderFile)) {
                        if (!ids.contains(order.getId())) {
                            loadedOrders.add(order);
                        }
                    }
                }
            }

            Set<Integer> orderNumbers = new HashSet();
            for (Order order : loadedOrders) {
                orderNumbers.add(order.getId());
            }

            if (orderNumbers.size() == loadedOrders.size()) {
            } else {
                System.out.println("ID Integrity Check Failed! \n " + orderNumbers.size() + " different order numbers but " + loadedOrders.size() + " orders.");
            }

            orders = loadedOrders;

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

    @Override
    public Order create(Order order) {
        order.setId(nextId);
        nextId++;

        orders.add(order);

        encode(order);

        return order;
    }

    @Override
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

    @Override
    public void update(Order order) {
        List<Order> foundOrders = new ArrayList();

        orders.stream()
                .filter(currentOrder -> currentOrder.getId() == order.getId())
                .forEach(currentOrder -> {
                    foundOrders.add(currentOrder);
                });

        foundOrders.stream()
                .forEach(f -> {
                    orders.remove(f);
                    encode(f.getDate());
                });
//        for (Order foundOrder : foundOrders) {
//            orders.remove(foundOrder);
//            encode(foundOrder.getDate());
//        }

        orders.add(order);
        encode(extractDate("Orders_00000000.txt"));
        encode(order);

    }

    @Override
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

        encode(extractDate("Orders_00000000.txt"));

        encode(oldDate);

    }

    @Deprecated
    @Override
    public List<Order> getList() {
        List<Order> copy = new ArrayList();
        copy.addAll(orders);
        return copy;
    }

    @Override
    public int size() {
        return orders.size();
    }

    @Override
    public java.util.List<Order> searchByDate(java.util.Date date) {
        java.util.List<Order> specificOrders = new ArrayList();

        orders.stream()
              .filter(o -> isSameDay(o.getDate(), date))
                      .forEach(o -> specificOrders.add(o));
                
        
//        for (Order order : orders) {
//            if (isSameDay(order.getDate(), date)) {
//                specificOrders.add(order);
//            }
//        }

        return specificOrders;
    }

    @Override
    public java.util.List<Integer> listOrderNumbers() {
        java.util.List<Integer> orderNumbers = new ArrayList();

        orders.stream()
                .forEach(o -> orderNumbers.add(o.getId()));
        
        Collections.sort(orderNumbers);
        
//        for (Order order : orders) {
//            orderNumbers.add(order.getId());
//        }

        return orderNumbers;
    }

    @Override
    public java.util.List<java.util.Date> listOrderDates() {
        java.util.List<java.util.Date> orderDates = new ArrayList();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd-MM-");

        java.util.Map<String, java.util.Date> dateMap = new java.util.HashMap();

        for (Order order : orders) {
            dateMap.putIfAbsent(fmt.format(order.getDate()), order.getDate());
        }

        orderDates.addAll(dateMap.values());

        java.util.Collections.sort(orderDates);

        return orderDates;
    }

    @Override
    public java.util.List<Order> searchByName(String orderName) {
        java.util.List<Order> specificOrders = new ArrayList();
        java.util.List<Order> closeOrders = new ArrayList();

        if (orderName == null)
            orderName = "";
        
        for (Order order : orders) {
            if (orderName.equalsIgnoreCase(order.getName())) {
                specificOrders.add(order);
            }

            if (order != null) {
                if (order.getName().toLowerCase().startsWith(orderName.toLowerCase()) || order.getName().toLowerCase().startsWith(orderName.toLowerCase())) {
                    closeOrders.add(order);
                }
            }
        }

        if (specificOrders.isEmpty()) {
            return closeOrders;
        } else {
            return specificOrders;
        }
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

    private void encode(Order order) {
        encode(order.getDate());
    }

    private void encode(java.util.Date date) {
        try {
            File file = null;
            if (isATest) {
                file = determineFile(configDao.get().getTestDirectory(), date); // testOrderDateFile;
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

    private void encode(java.io.File orderFile, List<Order> groupOfOrders) throws IOException {
        orderIo.encode(new PrintWriter(new FileWriter(orderFile)), groupOfOrders);
    }

    @Override
    public String toString(Order order) {
        return toString(order, "");
    }

    @Override
    public String toString(Order order, final String TOKEN) {
        final String CSV_ESCAPE = Pattern.quote("\\") + TOKEN;

        return toString(order, TOKEN, CSV_ESCAPE);
    }

    @Override
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
            String orderName = order.getName();
            if (order.getName().endsWith("\\")) {
                orderName = orderName + " ";
            }
            nameValue = orderName.replaceAll(TOKEN, CSV_ESCAPE).replaceAll("Q", "").replaceAll("E", "");
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

    @Override
    public String addLabels(Order order, final String TOKEN) {
        return addLabels(toString(order, TOKEN), TOKEN);
    }

    @Override
    public String addLabels(String orderString, final String TOKEN) {
        return addLabels(orderString, TOKEN, ": ");
    }

    @Override
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
    @Override
    public String addLabels(String orderString, final String TOKEN, final String SECOND_TOKEN) {

        String labeledOrderString = "";

        String[] orderParts = orderString.split(TOKEN);

        if (orderParts.length < 11) {
            System.out.println("Something Just went wrong on line 373 of the order DAO\n" + " - " + TOKEN + " - ");
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
            return determineFile(configDao.get().getOrdersDirectory(), date);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private File determineFile(File ordersDirectory, java.util.Date date) throws FileNotFoundException {
        String dateString;

        String orderDirectoryPath = "";

        if (ordersDirectory.isDirectory()) {
            orderDirectoryPath = ordersDirectory.getAbsolutePath();
        } else if (ordersDirectory.isFile()) {
            orderDirectoryPath = ordersDirectory.getParent();
        } else {
        }

        java.util.Date defaultDate = extractDate("Orders_00000000.txt");

        if (date == null || isSameDay(date, defaultDate)) {
            dateString = "00000000";
        } else {
            java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("MMddyyyy");
            dateString = fmt.format(date);
        }

        String prefix = "Orders_";
        String extension = ".txt";
        String dateFilePath = orderDirectoryPath + "/" + prefix + dateString + extension;

        return new File(dateFilePath.replaceAll("//", "/"));
    }

    public Date extractDate(String dateString) {
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

    @Override
    public void purgeTestFiles() {

        java.io.File[] testFiles = lookForOrders(configDao.get().getTestDirectory());
        for (java.io.File testFile : testFiles) {
            testFile.deleteOnExit();

        }
    }

}
