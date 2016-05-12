/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderDaoTest {

    java.io.File testFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersTestData.txt");

    public OrderDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao instance = new OrderDao(productDao, stateDao, true);

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

    /**
     * Test of getAllOrderes method, of class OrderDao.
     */
    @Test
    public void testGetAllOrderes() {
        System.out.println("getAllOrderes");

        java.io.File tempFile = new java.io.File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/OrdersTestData-temp.txt");
        testFile.renameTo(tempFile);

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao instance = new OrderDao(productDao, stateDao, true);

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
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        orderOne.setDate(date);
        orderTwo.setDate(secondDate);
        orderThree.setDate(thirdDate);
        orderFour.setDate(fourthDate);

        instance.create(orderOne);
        instance.create(orderTwo);
        instance.create(orderThree);
        instance.create(orderFour);

        int expSizeResult = 4;
        int sizeResult = instance.size();
        assertEquals(expSizeResult, sizeResult);

        assertTrue(instance.getList().contains(orderOne));
        assertTrue(instance.getList().contains(orderTwo));
        assertTrue(instance.getList().contains(orderThree));
        assertTrue(instance.getList().contains(orderFour));

        List<Order> result = instance.searchByDate(date);

        assertEquals(false, result.isEmpty());
        assertEquals(2, result.size());

        assertEquals(true, result.contains(orderOne));
        assertEquals(true, result.contains(orderTwo));
        assertEquals(false, result.contains(orderThree));
        assertEquals(false, result.contains(orderFour));

        instance.delete(orderOne);
        instance.delete(orderTwo);
        instance.delete(orderThree);
        instance.delete(orderFour);

        assertEquals(0, instance.getList().size());
        assertTrue(instance.getList().isEmpty());

        tempFile.renameTo(testFile);

    }

    @Test
    public void testEncodeAndDecode() {

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao orderDao = new OrderDao(productDao, stateDao, true);

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

        com.mycompany.flooringmastery.dto.State ohio = new com.mycompany.flooringmastery.dto.State();
        ohio.setState("OH");
        stateDao.create(ohio);

        com.mycompany.flooringmastery.dto.Product product = new com.mycompany.flooringmastery.dto.Product();
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
        ProductDao secondProductDao = new ProductDao(true);
        StateDao secondStateDao = new StateDao(true);
        OrderDao secondOrderDao = new OrderDao(secondProductDao, secondStateDao, true);

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
        ProductDao thirdProductDao = new ProductDao(true);
        StateDao thirdStateDao = new StateDao(true);
        OrderDao thirdOrderDao = new OrderDao(thirdProductDao, thirdStateDao, true);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    @Test
    public void testEncodeAndDecodeWithCommas() {

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao orderDao = new OrderDao(productDao, stateDao, true);

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

        com.mycompany.flooringmastery.dto.State ohio = new com.mycompany.flooringmastery.dto.State();
        ohio.setState("OH");
        stateDao.create(ohio);

        com.mycompany.flooringmastery.dto.Product product = new com.mycompany.flooringmastery.dto.Product();
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
        ProductDao secondProductDao = new ProductDao(true);
        StateDao secondStateDao = new StateDao(true);
        OrderDao secondOrderDao = new OrderDao(secondProductDao, secondStateDao, true);

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
        ProductDao thirdProductDao = new ProductDao(true);
        StateDao thirdStateDao = new StateDao(true);
        OrderDao thirdOrderDao = new OrderDao(thirdProductDao, thirdStateDao, true);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    @Test
    public void testEncodeAndDecodeWithDate() {

        boolean isATest = false;

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao orderDao = new OrderDao(productDao, stateDao, isATest);

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

        com.mycompany.flooringmastery.dto.State ohio = new com.mycompany.flooringmastery.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmastery.dto.Product product = new com.mycompany.flooringmastery.dto.Product();
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
        calendar.set(2000, Calendar.JANUARY, 1);

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
        ProductDao secondProductDao = new ProductDao(true);
        StateDao secondStateDao = new StateDao(true);
        OrderDao secondOrderDao = new OrderDao(secondProductDao, secondStateDao, isATest);

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
        ProductDao thirdProductDao = new ProductDao(true);
        StateDao thirdStateDao = new StateDao(true);
        OrderDao thirdOrderDao = new OrderDao(thirdProductDao, thirdStateDao, isATest);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    @Test
    public void testToString() {

        boolean isATest = true;

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao orderDao = new OrderDao(productDao, stateDao, isATest);

        // The true parameter in the Order Dao constructor signifies a test.
        //OrderDao orderDao = new OrderDao(true);
        Order order = new Order();

        // Create the file in the Dao.
        //Order returnedOrder = orderDao.create(order);


        com.mycompany.flooringmastery.dto.State ohio = new com.mycompany.flooringmastery.dto.State();
        ohio.setState("KC");
        stateDao.create(ohio);

        com.mycompany.flooringmastery.dto.Product product = new com.mycompany.flooringmastery.dto.Product();
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
        calendar.set(2000, Calendar.JANUARY, 1);

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
        java.io.File firstTestFile = new java.io.File("FirstResultTestFile.txt");
        firstTestFile.deleteOnExit();
        
        try (PrintWriter out = new PrintWriter(new FileWriter(firstTestFile))) {

            out.println(thirdOrderString);
            out.flush();
        } catch (IOException ex) {
            fail("IOException - " + ex.getMessage());
            //Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.io.File secondTestFile = new java.io.File("SecondResultTestFile.txt");
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

        java.io.File firstValidTestFile = new java.io.File("FirstExpectedTestFile.txt");
        java.io.File secondValidTestFile = new java.io.File("SecondExpectedTestFile.txt");

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
    public void testIfDateWillChangeWhichFileOrderIsIn() {

        // Hopefully no one ever has to call this method several times from an outside source.
        // I guess it is public, though.
        // I know what my luck looks like; best of luck future me.
        
        boolean isATest = true;

        ProductDao productDao = new ProductDao(true);
        StateDao stateDao = new StateDao(true);
        OrderDao orderDao = new OrderDao(productDao, stateDao, isATest);

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

        com.mycompany.flooringmastery.dto.State ohio = new com.mycompany.flooringmastery.dto.State();
        ohio.setState("IN");
        stateDao.create(ohio);

        com.mycompany.flooringmastery.dto.Product product = new com.mycompany.flooringmastery.dto.Product();
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
        calendar.set(2000, Calendar.JANUARY, 1);

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
        ProductDao secondProductDao = new ProductDao(true);
        StateDao secondStateDao = new StateDao(true);
        OrderDao secondOrderDao = new OrderDao(secondProductDao, secondStateDao, isATest);

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
        ProductDao thirdProductDao = new ProductDao(true);
        StateDao thirdStateDao = new StateDao(true);
        OrderDao thirdOrderDao = new OrderDao(thirdProductDao, thirdStateDao, isATest);

        //OrderDao thirdDao = new OrderDao(true);
        assertEquals(thirdOrderDao.get(id), null);

    }

    
    
}
