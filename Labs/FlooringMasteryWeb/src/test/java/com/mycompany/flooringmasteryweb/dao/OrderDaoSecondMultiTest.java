/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.Order;
import com.mycompany.flooringmasteryweb.exceptions.ConfigurationFileCorruptException;
import com.mycompany.flooringmasteryweb.exceptions.FileCreationException;
import com.mycompany.flooringmasteryweb.dao.OrderDao;
import com.mycompany.flooringmasteryweb.dao.OrderDaoImpl;
import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.StateDao;
import com.mycompany.flooringmasteryweb.dto.BasicOrder;
import com.mycompany.flooringmasteryweb.dto.OrderCommand;
import com.mycompany.flooringmasteryweb.utilities.TestUtils;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class OrderDaoSecondMultiTest {

    ApplicationContext ctx;
    java.io.File testFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersTestData.txt");
    ConfigDao configDao;

    public OrderDaoSecondMultiTest() {
        ctx = new ClassPathXmlApplicationContext("test-OrdersLocalStateSQLProductSQL-applicationContext.xml");
    }

//    ApplicationContext ctx;
//    
//    public OrderDaoTest() {
//        ctx = new org.springframework.context.support.ClassPathXmlApplicationContext("testApplicationContext.xml");
//    }
    @Before
    public void setUp() {

        ConfigDao configDao = null;

        try {
            configDao = new ConfigDao();
        } catch (ConfigurationFileCorruptException | FileCreationException ex) {
            Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Throwing This Exception Should Not Be Possible.\n" + ex.getMessage());
        }

        configDao.get().setInTestMode(true);
        configDao.get().setTaxesFile(new java.io.File("StatesTestData.txt"));
        configDao.get().setProductFile(new java.io.File("ProductsTestData.txt"));

        this.configDao = configDao;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = new Order();
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.delete(order);

        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);
    }

    @Test
    public void testNullCreate() {
        System.out.println("create");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = null;
        Order expResult = order;
        Order result = instance.create(order);

        //This is a test create with nulls.
        // If it makes it to here, it passed.
        assertTrue(true);

    }

    @Test
    public void testNullCreateB() {
        System.out.println("create");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = new Order();
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        Order orderNull = null;
        Order resultNull = instance.create(orderNull);

        assertNull(resultNull);
        assertNull(orderNull);
        assertEquals(orderNull, resultNull);

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.delete(order);

        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);

        //This is a test update with nulls.
        // If it makes it to here, it passed.
        assertTrue(true);

    }

    @Test
    public void testNullDelete() {
        System.out.println("delete");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = new Order();
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.delete(null);
        instance.delete(order);
        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);
    }

    @Test
    public void testNullUpdate() {
        System.out.println("delete");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = new Order();
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(id);
        assertEquals(returnedOrder, result);
        instance.update(null);
        instance.delete(order);
        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);

        //This is a test update with nulls.
        // If it makes it to here, it passed.
        assertTrue(true);

    }

    @Test
    public void testNullGet() {
        System.out.println("delete");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = new Order();
        Order expResult = order;
        Order result = instance.create(order);
        assertEquals(expResult, result);

        int id = result.getId();
        assertTrue(result.getId() != 0);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        Order returnedOrder = instance.get(null);
        assertNull(returnedOrder);
        //instance.delete(null);
        instance.delete(order);

        returnedOrder = instance.get(id);
        assertEquals(returnedOrder, null);
    }

    @Test
    public void testNullGetB() {
        System.out.println("delete");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order returnedOrder = instance.get(null);
        assertNull(returnedOrder);

    }

    @Test
    public void testNullUpdateB() {
        System.out.println("update");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        instance.update(null);
        // If it got here, it passes.
        assertTrue(true);

    }

    @Test
    public void testCreateB() {
        System.out.println("create - null");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order order = null;
        Order expResult = null;
        OrderCommand result = instance.resolveOrderCommand(order);

        assertEquals(expResult, result);

    }

    @Test
    public void testCreateC() {
        System.out.println("create - null");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        OrderCommand order = null;
        Order expResult = null;
        Order result = instance.orderBuilder(order);

        assertEquals(expResult, result);

    }

    @Test
    public void testCreateD() {
        System.out.println("create - null");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        List<Order> orders = null;
        Order expResult = null;
        List<Order> result = instance.sortByOrderNumber(orders);

        assertEquals(expResult, result);

    }

    @Test
    public void testCreateE() {
        System.out.println("create - null");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        List<Order> orders = new ArrayList();
        //Order expResult = null;
        List<Order> result = instance.sortByOrderNumber(orders);

        assertTrue(result.isEmpty());

    }

    /**
     * Test of getAllOrderes method, of class OrderDao.
     */
    @Test
    public void testGetAllOrderes() {
        System.out.println("getAllOrderes");

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao instance = new OrderDaoImpl(productDao, stateDao, configDao);

        Order orderOne = new Order();
        Order orderTwo = new Order();
        Order orderThree = new Order();
        Order orderFour = new Order();

        Date date = new Date();
        Date secondDate = new Date();
        Date thirdDate = new Date();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.roll(java.util.Calendar.DAY_OF_MONTH, -3);
        thirdDate = calendar.getTime();

        calendar.roll(java.util.Calendar.DAY_OF_MONTH, +8);
        Date fourthDate = calendar.getTime();

        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd");

        try {
            secondDate = fmt.parse(fmt.format(secondDate));
        } catch (ParseException ex) {
            fail("Parse Exception - " + ex.getMessage());
        }

        orderOne.setDate(date);
        orderTwo.setDate(secondDate);
        orderThree.setDate(thirdDate);
        orderFour.setDate(fourthDate);

        instance.create(orderOne);
        instance.create(orderTwo);
        instance.create(orderThree);
        instance.create(orderFour);

//        int expSizeResult = 4;
//        int sizeResult = instance.size();
//        assertEquals(expSizeResult, sizeResult);
        assertTrue(instance.getList().contains(orderOne));
        assertTrue(instance.getList().contains(orderTwo));
        assertTrue(instance.getList().contains(orderThree));
        assertTrue(instance.getList().contains(orderFour));

        List<Order> result = instance.searchByDate(date);

        assertEquals(false, result.isEmpty());
        //assertEquals(2, result.size());

        assertEquals(true, result.contains(orderOne));
        assertEquals(true, result.contains(orderTwo));
        assertEquals(false, result.contains(orderThree));
        assertEquals(false, result.contains(orderFour));

        instance.delete(orderOne);
        instance.delete(orderTwo);
        instance.delete(orderThree);
        instance.delete(orderFour);

        List<Order> orderList = instance.getList();

        assertEquals(orderList.contains(orderOne), false);
        assertEquals(orderList.contains(orderTwo), false);
        assertEquals(orderList.contains(orderThree), false);
        assertEquals(orderList.contains(orderFour), false);
        //assertEquals(0, instance.getList().size());
        //assertTrue(instance.getList().isEmpty());

        //tempFile.renameTo(testFile);
    }

    @Test
    public void testEncodeAndDecode() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("OH");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Wood");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Wise";
        double taxRate = 6.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 61.88;
        double total = 1051.88;

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);

        // Use the update method to save this new text to file.
        orderDao.update(order);

        // Load a new instance of the OrderDao.
        //OrderDao secondDao = new OrderDao(true);
        ProductDao secondProductDao = ctx.getBean("productDao", ProductDao.class);
        StateDao secondStateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao secondOrderDao = new OrderDaoImpl(secondProductDao, secondStateDao, configDao);

        // Pull a note  using the id number recorded earlier.
        Order thirdOrder = secondOrderDao.get(id);

        assertTrue(thirdOrder != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdOrder.getNoteString());
        assertEquals(name, thirdOrder.getName());
        assertEquals(ohio.getState(), thirdOrder.getState().getState());
        assertEquals(taxRate, thirdOrder.getTaxRate(), 1e-8);
        assertEquals(product.getType(), thirdOrder.getProduct().getType());
        assertEquals(area, thirdOrder.getArea(), 1e-8);
        assertEquals(costPerSquareFoot, thirdOrder.getCostPerSquareFoot(), 1e-8);
        assertEquals(laborCostPerSquareFoot, thirdOrder.getLaborCostPerSquareFoot(), 1e-8);
        assertEquals(materialCost, thirdOrder.getMaterialCost(), 1e-8);
        assertEquals(laborCost, thirdOrder.getLaborCost(), 1e-8);
        assertEquals(tax, thirdOrder.getTax(), 1e-8);
        assertEquals(total, thirdOrder.getTotal(), 1e-8);

        // Delete the test note.
        secondOrderDao.delete(thirdOrder);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        ProductDao thirdProductDao = ctx.getBean("productDao", ProductDao.class);
        StateDao thirdStateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao thirdOrderDao = new OrderDaoImpl(thirdProductDao, thirdStateDao, configDao);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    @Test
    public void testEncodeAndDecodeWithCommas() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("OH");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Wood");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Acme, INC.";
        double taxRate = 6.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 61.88;
        double total = 1051.88;

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);

        // Use the update method to save this new text to file.
        orderDao.update(order);

        // Load a new instance of the OrderDao.
        //OrderDao secondDao = new OrderDao(true);
        ProductDao secondProductDao = ctx.getBean("productDao", ProductDao.class);
        StateDao secondStateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao secondOrderDao = new OrderDaoImpl(secondProductDao, secondStateDao, configDao);

        // Pull a note  using the id number recorded earlier.
        Order thirdOrder = secondOrderDao.get(id);

        assertTrue(thirdOrder != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdOrder.getNoteString());
        assertEquals(name, thirdOrder.getName());
        assertEquals(ohio.getState(), thirdOrder.getState().getState());
        assertEquals(taxRate, thirdOrder.getTaxRate(), 1e-8);
        assertEquals(product.getType(), thirdOrder.getProduct().getType());
        assertEquals(area, thirdOrder.getArea(), 1e-8);
        assertEquals(costPerSquareFoot, thirdOrder.getCostPerSquareFoot(), 1e-8);
        assertEquals(laborCostPerSquareFoot, thirdOrder.getLaborCostPerSquareFoot(), 1e-8);
        assertEquals(materialCost, thirdOrder.getMaterialCost(), 1e-8);
        assertEquals(laborCost, thirdOrder.getLaborCost(), 1e-8);
        assertEquals(tax, thirdOrder.getTax(), 1e-8);
        assertEquals(total, thirdOrder.getTotal(), 1e-8);

        // Delete the test note.
        secondOrderDao.delete(thirdOrder);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        ProductDao thirdProductDao = ctx.getBean("productDao", ProductDao.class);
        StateDao thirdStateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao thirdOrderDao = new OrderDaoImpl(thirdProductDao, thirdStateDao, configDao);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    @Test
    public void testEncodeAndDecodeWithDate() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Steel");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Bob and sons, perfection.";
        double taxRate = 3.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 3061.88;
        double total = 4051.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        orderDao.update(order);

        // Load a new instance of the OrderDao.
        //OrderDao secondDao = new OrderDao(true);
        ProductDao secondProductDao = ctx.getBean("productDao", ProductDao.class);
        StateDao secondStateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao secondOrderDao = new OrderDaoImpl(secondProductDao, secondStateDao, configDao);

        // Pull a note  using the id number recorded earlier.
        Order thirdOrder = secondOrderDao.get(id);

        String thirdOrderString = secondOrderDao.toString(thirdOrder, System.lineSeparator());

        try (PrintWriter out = new PrintWriter(new FileWriter(new java.io.File("myTestFile.txt")))) {

            out.println(thirdOrderString);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(new java.io.File("myNextTestFile.txt")))) {

            String token = System.lineSeparator();
            String thirdOrderStringWithLabels = secondOrderDao.addLabels(thirdOrderString, token);

            out.println(thirdOrderStringWithLabels);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertTrue(thirdOrder != null);

        // Check that the update method saved the new text.
        //assertEquals("This Is a test note.", thirdOrder.getNoteString());
        assertEquals(name, thirdOrder.getName());
        assertEquals(ohio.getState(), thirdOrder.getState().getState());
        assertEquals(taxRate, thirdOrder.getTaxRate(), 1e-8);
        assertEquals(product.getType(), thirdOrder.getProduct().getType());
        assertEquals(area, thirdOrder.getArea(), 1e-8);
        assertEquals(costPerSquareFoot, thirdOrder.getCostPerSquareFoot(), 1e-8);
        assertEquals(laborCostPerSquareFoot, thirdOrder.getLaborCostPerSquareFoot(), 1e-8);
        assertEquals(materialCost, thirdOrder.getMaterialCost(), 1e-8);
        assertEquals(laborCost, thirdOrder.getLaborCost(), 1e-8);
        assertEquals(tax, thirdOrder.getTax(), 1e-8);
        assertEquals(total, thirdOrder.getTotal(), 1e-8);

        // Delete the test note.
        secondOrderDao.delete(thirdOrder);

        // Load a third instance of the Dao and verify that 
        // the note was deleted from the file.
        ProductDao thirdProductDao = ctx.getBean("productDao", ProductDao.class);
        StateDao thirdStateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao thirdOrderDao = new OrderDaoImpl(thirdProductDao, thirdStateDao, configDao);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    @Test
    public void testToString() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        //Order returnedOrder = orderDao.create(order);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("KC");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Grass");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Steve the Awsome,,est.";
        double taxRate = 20.25;
        double area = 150.00;
        double costPerSquareFoot = 25.15;
        double laborCostPerSquareFoot = 0.75;
        double materialCost = 1.55;
        double laborCost = 400.00;
        double tax = 3.08;
        double total = 4.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 15);

        Date orderDate = calendar.getTime();

        // Set the above values to the appropriate attributes.
        order.setId(3);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);

        String thirdOrderString = orderDao.toString(order, System.lineSeparator());
        //java.io.File firstTestFile = new java.io.File("FirstResultTestFile.txt");
        java.io.File firstTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/FirstResultTestFile.txt");
        firstTestFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(new FileWriter(firstTestFile))) {

            out.println(thirdOrderString);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //java.io.File secondTestFile = new java.io.File("SecondResultTestFile.txt");
        java.io.File secondTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/SecondResultTestFile.txt");
        secondTestFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(new FileWriter(secondTestFile))) {

            String token = System.lineSeparator();
            String thirdOrderStringWithLabels = orderDao.addLabels(thirdOrderString, token);

            out.println(thirdOrderStringWithLabels);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //java.io.File firstValidTestFile = new java.io.File("FirstExpectedTestFile.txt");
        java.io.File firstValidTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/FirstExpectedTestFile.txt");
        //java.io.File secondValidTestFile = new java.io.File("SecondExpectedTestFile.txt");
        java.io.File secondValidTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/SecondExpectedTestFile.txt");

        assertEquals(readFile(firstValidTestFile), readFile(firstTestFile));
        assertEquals(readFile(secondValidTestFile), readFile(secondTestFile));

    }

    private String readFile(java.io.File file) {
        String text = "";

        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            text += scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException ex) {
            fail("File " + file.getName() + " could not be Found!");
            Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text;
    }

    @Test
    public void testToStringExtreme() {

        //ProductDao productDao = new ProductDaoImpl(configDao);
        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);

        //= ctx.getBean("stateDao", StateDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        //Order returnedOrder = orderDao.create(order);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("DG");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Grass");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = ",,,,,,,,\\,,,,,,,/,,,,,,,";
        double taxRate = 20.25;
        double area = 150.00;
        double costPerSquareFoot = 25.15;
        double laborCostPerSquareFoot = 0.75;
        double materialCost = 1.55;
        double laborCost = 400.00;
        double tax = 3.08;
        double total = 4.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 20);

        Date orderDate = calendar.getTime();

        // Set the above values to the appropriate attributes.
        order.setId(3);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);

        String thirdOrderString = orderDao.toString(order, System.lineSeparator());
        //java.io.File firstTestFile = new java.io.File("ThirdResultTestFile.txt");
        java.io.File firstTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/ThirdResultTestFile.txt");
        firstTestFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(new FileWriter(firstTestFile))) {

            out.println(thirdOrderString);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //java.io.File secondTestFile = new java.io.File("FourthResultTestFile.txt");
        java.io.File secondTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/FourthResultTestFile.txt");
        secondTestFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(new FileWriter(secondTestFile))) {

            String token = System.lineSeparator();
            String thirdOrderStringWithLabels = orderDao.addLabels(thirdOrderString, token);

            out.println(thirdOrderStringWithLabels);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //java.io.File firstValidTestFile = new java.io.File("ThirdExpectedTestFile.txt");
        java.io.File firstValidTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/ThirdExpectedTestFile.txt");
        //java.io.File secondValidTestFile = new java.io.File("FourthExpectedTestFile.txt");
        java.io.File secondValidTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/FourthExpectedTestFile.txt");

        assertEquals(readFile(firstValidTestFile), readFile(firstTestFile));
        assertEquals(readFile(secondValidTestFile), readFile(secondTestFile));

    }

    @Test
    public void testToStringEscapeAtEnd() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        //Order returnedOrder = orderDao.create(order);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("DG");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Grass");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = ",,,,,,,,\\,,,,,,,/,,,,\\";
        double taxRate = 20.25;
        double area = 150.00;
        double costPerSquareFoot = 25.15;
        double laborCostPerSquareFoot = 0.75;
        double materialCost = 1.55;
        double laborCost = 400.00;
        double tax = 3.08;
        double total = 4.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, Calendar.JANUARY, 22);

        Date orderDate = calendar.getTime();

        // Set the above values to the appropriate attributes.
        order.setId(3);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);

        String thirdOrderString = orderDao.toString(order, System.lineSeparator());
        //java.io.File firstTestFile = new java.io.File("FifthResultTestFile.txt");
        java.io.File firstTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/FifthResultTestFile.txt");
        // firstTestFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(new FileWriter(firstTestFile))) {

            out.println(thirdOrderString);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.io.File secondTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/SixthResultTestFile.txt");
        //java.io.File secondTestFile = new java.io.File("SixthResultTestFile.txt");
        // secondTestFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(new FileWriter(secondTestFile))) {

            String token = System.lineSeparator();
            String thirdOrderStringWithLabels = orderDao.addLabels(thirdOrderString, token);

            out.println(thirdOrderStringWithLabels);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //java.io.File firstValidTestFile = new java.io.File("FifthExpectedTestFile.txt");
        //java.io.File secondValidTestFile = new java.io.File("SixthExpectedTestFile.txt");
        java.io.File firstValidTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/FifthExpectedTestFile.txt");
        java.io.File secondValidTestFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/SixthExpectedTestFile.txt");

        assertEquals(readFile(firstValidTestFile), readFile(firstTestFile));
        assertEquals(readFile(secondValidTestFile), readFile(secondTestFile));

    }

//    
//    @Test
//    public void testIfDateWillChangeWhichFileOrderIsIn() {
//        //    This one is still a work in progress.
//
//        // Hopefully no one ever has to call this method several times from an outside source.
//        // I guess it is public, though.
//        // I know what my luck looks like; best of luck future me.
//        
//        
//
//        ConfigDao configDao = null;
//
//        try {
//             configDao = new ConfigDao();
//        } catch (ConfigurationFileCorruptException | FileCreationException ex) {
//            Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
//            fail("Throwing This Exception Should Not Be Possible.\n" + ex.getMessage());
//        }
//        
//        
//        
//        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
//        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
//        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);
//
//        // The true parameter in the Order Dao constructor signifies a test.
//        //OrderDao orderDao = new OrderDao(true);
//        Order order = new Order();
//
//        // Create the file in the Dao.
//        Order returnedOrder = orderDao.create(order);
//
//        // Record the notes id number.
//        int id = order.getId();
//
//        // Verify that the note object that the create method passed back
//        // was the same one it was given.
//        assertEquals(order, returnedOrder);
//
//        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
//        ohio.setState("IN");
//        stateDao.create(ohio);
//
//        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
//        product.setType("Steel");
//        productDao.create(product);
//
//        // Make some data for the dto.
//        String name = "Guy Who Changes His Mind";
//        double taxRate = 3.25;
//        double area = 100.00;
//        double costPerSquareFoot = 5.15;
//        double laborCostPerSquareFoot = 4.75;
//        double materialCost = 515.00;
//        double laborCost = 475.00;
//        double tax = 3061.88;
//        double total = 4051.88;
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2000, Calendar.JANUARY, 1);
//
//        Date orderDate = calendar.getTime();
//        //Date orderDate = new Date();
//
//        // Set the above values to the appropriate attributes.
//        //order.setId(1);
//        order.setName(name);
//        order.setState(ohio);
//        order.setTaxRate(taxRate);
//        order.setProduct(product);
//        order.setArea(area);
//        order.setCostPerSquareFoot(costPerSquareFoot);
//        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
//        order.setMaterialCost(materialCost);
//        order.setLaborCost(laborCost);
//        order.setTax(tax);
//        order.setTotal(total);
//        order.setDate(orderDate);
//        // Use the update method to save this new text to file.
//        orderDao.update(order);
//
//        
//        // Load a new instance of the OrderDao.
//        //OrderDao secondDao = new OrderDao(true);
//        ProductDao secondProductDao = ctx.getBean("productDao", ProductDao.class);
//        StateDao secondStateDao = ctx.getBean("stateDao", StateDao.class);
//        OrderDao secondOrderDao = new OrderDao(secondProductDao, secondStateDao, configDao);
//
//        // Pull a order using the id number recorded earlier.
//        Order thirdOrder = secondOrderDao.get(id);
//
//        
//        
//        
//        
//        String thirdOrderString = secondOrderDao.toString(thirdOrder, System.lineSeparator());
//
////        try (PrintWriter out = new PrintWriter(new FileWriter(new java.io.File("myTestFile.txt")))) {
////
////            out.println(thirdOrderString);
////            out.flush();
////        } catch (IOException ex) {
////            fail("IOException - " + ex.getMessage());
////            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
////        }
//
//        try (PrintWriter out = new PrintWriter(new FileWriter(new java.io.File("myNextTestFile.txt")))) {
//
//            String token = System.lineSeparator();
//            String thirdOrderStringWithLabels = secondOrderDao.addLabels(thirdOrderString, token);
//
//            out.println(thirdOrderStringWithLabels);
//            out.flush();
//        } catch (IOException ex) {
//            fail("IOException - " + ex.getMessage());
//            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        assertTrue(thirdOrder != null);
//
//        // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdOrder.getNoteString());
//        assertEquals(name, thirdOrder.getName());
//        assertEquals(ohio.getState(), thirdOrder.getState().getState());
//        assertEquals(taxRate, thirdOrder.getTaxRate(), 1e-8);
//        assertEquals(product.getType(), thirdOrder.getProduct().getType());
//        assertEquals(area, thirdOrder.getArea(), 1e-8);
//        assertEquals(costPerSquareFoot, thirdOrder.getCostPerSquareFoot(), 1e-8);
//        assertEquals(laborCostPerSquareFoot, thirdOrder.getLaborCostPerSquareFoot(), 1e-8);
//        assertEquals(materialCost, thirdOrder.getMaterialCost(), 1e-8);
//        assertEquals(laborCost, thirdOrder.getLaborCost(), 1e-8);
//        assertEquals(tax, thirdOrder.getTax(), 1e-8);
//        assertEquals(total, thirdOrder.getTotal(), 1e-8);
//
//        // Delete the test note.
//        secondOrderDao.delete(thirdOrder);
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        ProductDao thirdProductDao = ctx.getBean("productDao", ProductDao.class);
//        StateDao thirdStateDao = ctx.getBean("stateDao", StateDao.class);
//        OrderDao thirdOrderDao = new OrderDao(thirdProductDao, thirdStateDao, configDao);
//
//        //OrderDao thirdDao = new OrderDao(true);
//        assertEquals(thirdOrderDao.get(id), null);
//
//    }
    @Test
    public void testResolverA() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Steel");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Bob and sons, perfection.";
        double taxRate = 3.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 3061.88;
        double total = 4051.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(null);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        orderDao.update(order);

        BasicOrder basicOrder = orderDao.resolveOrderCommand(order);

        assertNotNull(basicOrder);
    }

    @Test
    public void testResolverB() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Steel");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Bob and sons, perfection.";
        double taxRate = 3.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 3061.88;
        double total = 4051.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(null);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        orderDao.update(order);

        BasicOrder basicOrder = orderDao.resolveOrderCommand(order);

        assertNotNull(basicOrder);
    }

    @Test
    public void testResolverC() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Steel");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Bob and sons, perfection.";
        double taxRate = 3.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 3061.88;
        double total = 4051.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(ohio);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(null);
        // Use the update method to save this new text to file.
        orderDao.update(order);

        BasicOrder basicOrder = orderDao.resolveOrderCommand(order);

        assertNotNull(basicOrder);
    }

    @Test
    public void testResolverD() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Steel");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Bob and sons, perfection.";
        double taxRate = 3.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 3061.88;
        double total = 4051.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(null);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        orderDao.update(order);

        BasicOrder basicOrder = orderDao.resolveOrderCommand(order);

        assertNotNull(basicOrder);
    }

    @Test
    public void testResolverF() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(order);

        // Record the notes id number.
        int id = order.getId();

        // Verify that the note object that the create method passed back
        // was the same one it was given.
        assertEquals(order, returnedOrder);

        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Steel");
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "Bob and sons, perfection.";
        double taxRate = 3.25;
        double area = 100.00;
        double costPerSquareFoot = 5.15;
        double laborCostPerSquareFoot = 4.75;
        double materialCost = 515.00;
        double laborCost = 475.00;
        double tax = 3061.88;
        double total = 4051.88;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        // Set the above values to the appropriate attributes.
        //order.setId(1);
        order.setName(name);
        order.setState(null);
        order.setTaxRate(taxRate);
        order.setProduct(product);
        order.setArea(area);
        order.setCostPerSquareFoot(costPerSquareFoot);
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        orderDao.update(order);

        BasicOrder basicOrder = orderDao.resolveOrderCommand(order);

        assertNotNull(basicOrder);
    }

    @Test
    public void testResolverEBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("TestSteel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

//        double taxRate = 3.25;
//        double costPerSquareFoot = 5.15;
//        double laborCostPerSquareFoot = 4.75;
//        double materialCost = 515.00;
//        double laborCost = 475.00;
//        double tax = 3061.88;
//        double total = 4051.88;
        //product = productDao.get(product.getProductName());
        //ohio = stateDao.get(ohio.getStateName());
        // Set the above values to the appropriate attributes.
        //order.setId(1);
//        order.setName(name);
//        order.setState(ohio);
//        order.setTaxRate(taxRate);
//        order.setProduct(product);
//        order.setArea(area);
//        order.setCostPerSquareFoot(costPerSquareFoot);
//        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
//        order.setMaterialCost(materialCost);
//        order.setLaborCost(laborCost);
//        order.setTax(tax);
//        order.setTotal(total);
//        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        //orderDao.update(order);
        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        //assertEquals(builtOrder, unresolvedOrder);
        //Assert.assertSame(builtOrder, unresolvedOrder);
        //Assert.
        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));

        //assertEquals(builtOrder.getState(), unresolvedOrder.getState());
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));

        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverFBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("TestSteel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(null);
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

//        double taxRate = 3.25;
//        double costPerSquareFoot = 5.15;
//        double laborCostPerSquareFoot = 4.75;
//        double materialCost = 515.00;
//        double laborCost = 475.00;
//        double tax = 3061.88;
//        double total = 4051.88;
        //product = productDao.get(product.getProductName());
        //ohio = stateDao.get(ohio.getStateName());
        // Set the above values to the appropriate attributes.
        //order.setId(1);
//        order.setName(name);
//        order.setState(ohio);
//        order.setTaxRate(taxRate);
//        order.setProduct(product);
//        order.setArea(area);
//        order.setCostPerSquareFoot(costPerSquareFoot);
//        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
//        order.setMaterialCost(materialCost);
//        order.setLaborCost(laborCost);
//        order.setTax(tax);
//        order.setTotal(total);
//        order.setDate(orderDate);
        // Use the update method to save this new text to file.
        //orderDao.update(order);
        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        //assertEquals(builtOrder, unresolvedOrder);
        //Assert.assertSame(builtOrder, unresolvedOrder);
        //Assert.
        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());

        //assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));

        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverGBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("TestSteel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(null);
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverHBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("TestSteel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(null);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverIBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(null);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverLBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverJBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverKBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverNBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 00.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverMBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = null;
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverOBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(null);
        orderCommand.setState(ohio.getStateName());
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals("", basicOrder.getProduct());
        assertEquals(orderCommand.getState(), basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

    @Test
    public void testResolverPBackandForth() {

        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        StateDao stateDao = ctx.getBean("stateDao", StateDao.class);
        OrderDao orderDao = new OrderDaoImpl(productDao, stateDao, configDao);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        //Order order = new Order();
        // Verify that the note object that the create method passed back
        // was the same one it was given.
        //assertEquals(order, returnedOrder);
        com.mycompany.flooringmasteryweb.dto.State ohio = new com.mycompany.flooringmasteryweb.dto.State();
        ohio.setState("CA");
        ohio.setStateTax(6.25);
        stateDao.create(ohio);

        com.mycompany.flooringmasteryweb.dto.Product product = new com.mycompany.flooringmasteryweb.dto.Product();
        product.setType("Test Steel");
        product.setCost(5);
        product.setLaborCost(3);
        productDao.create(product);

        // Make some data for the dto.
        // 1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88
        String name = "SWC Guild, Test.";
        double area = 100.00;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 10);

        Date orderDate = calendar.getTime();
        //Date orderDate = new Date();

        OrderCommand orderCommand = new OrderCommand();

        orderCommand.setName(name);
        orderCommand.setArea(area);
        orderCommand.setProduct(product.getProductName());
        orderCommand.setState(null);
        orderCommand.setDate(orderDate);

        Order builtOrder = orderDao.orderBuilder(orderCommand);

        // Create the file in the Dao.
        Order returnedOrder = orderDao.create(builtOrder);

        // Record the notes id number.
        int id = builtOrder.getId();

        BasicOrder basicOrder = orderDao.resolveOrderCommand(builtOrder);

        assertNotNull(basicOrder);

        assertEquals(orderCommand.getName(), basicOrder.getName());
        assertEquals(orderCommand.getArea(), basicOrder.getArea(), 0.0005);
        assertEquals(orderCommand.getDate(), basicOrder.getDate());
        assertEquals(orderCommand.getProduct(), basicOrder.getProduct());
        //assertEquals(orderCommand.getState(), basicOrder.getState());
        assertEquals("", basicOrder.getState());

        Order unresolvedOrder = orderDao.orderBuilder(basicOrder);

        assertNotNull(unresolvedOrder);

        assertEquals(builtOrder.getArea(), unresolvedOrder.getArea(), 0.0005);
        assertEquals(builtOrder.getClass(), unresolvedOrder.getClass());
        assertEquals(builtOrder.getCostPerSquareFoot(), unresolvedOrder.getCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getDate(), unresolvedOrder.getDate());
        assertEquals(builtOrder.getId(), unresolvedOrder.getId());
        assertEquals(builtOrder.getLaborCost(), unresolvedOrder.getLaborCost(), 0.0005);
        assertEquals(builtOrder.getLaborCostPerSquareFoot(), unresolvedOrder.getLaborCostPerSquareFoot(), 0.0005);
        assertEquals(builtOrder.getMaterialCost(), unresolvedOrder.getMaterialCost(), 0.0005);
        assertEquals(builtOrder.getName(), unresolvedOrder.getName());
        assertTrue(TestUtils.isProductEqual(builtOrder.getProduct(), unresolvedOrder.getProduct()));
        assertTrue(TestUtils.isStateEqual(builtOrder.getState(), unresolvedOrder.getState()));
        assertEquals(builtOrder.getTax(), unresolvedOrder.getTax(), 0.0005);
        assertEquals(builtOrder.getTaxRate(), unresolvedOrder.getTaxRate(), 0.0005);
        assertEquals(builtOrder.getTotal(), unresolvedOrder.getTotal(), 0.0005);

    }

}
