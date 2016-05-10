/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import java.text.ParseException;
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
            Logger.getLogger(OrderDaoTest.class.getName()).log(Level.SEVERE, null, ex);
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

    
//    /**
//     * Test of create method, of class OrderDao.
//     */
//    @Test
//    public void testCreateB() {
//        System.out.println("create");
//        Order order = new Order();
//        OrderDao instance = new OrderDao(true);
//        Order expResult = order;
//        Order result = instance.create(order);
//        assertEquals(expResult, result);
//
//        int id = result.getId();
//        assertTrue(result.getId() != 0);
//        assertTrue(result.getId() >= instance.size());
//
//        // Test get method.
//        Order returnedOrder = instance.get(id);
//        assertEquals(returnedOrder, result);
//        instance.delete(order);
//
//        returnedOrder = instance.get(id);
//        assertEquals(returnedOrder, null);
//    }
//
//    /**
//     * Test of getAllOrderes method, of class OrderDao.
//     */
//    @Test
//    public void testGetAllOrderesB() {
//        System.out.println("getAllOrderes");
//        OrderDao instance = new OrderDao(true);
//
//        Order orderOne = new Order();
//        Order orderTwo = new Order();
//        Order orderThree = new Order();
//
//        instance.create(orderOne);
//        instance.create(orderTwo);
//        instance.create(orderThree);
//
//        assertTrue(instance.getAllOrderes().contains(orderTwo));
//
//        instance.delete(orderOne);
//        instance.delete(orderTwo);
//        instance.delete(orderThree);
//
//        int expSizeResult = 3;
//        int sizeResult = instance.size();
//        assertEquals(expSizeResult, sizeResult);
//
//        String lastName = "Steve";
//        List<Order> result = instance.searchByLastName(lastName);
//        assertEquals(true, result.isEmpty());
//
//        lastName = "Jones";
//        result = instance.searchByLastName(lastName);
//        assertEquals(false, result.isEmpty());
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testEncodeAndDecodeB() {
//
//        //Dvd dvd = new DvdImplementation();
//        OrderDao orderDao = new OrderDao(true);
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
//        String city = "Seville";
//        String country = "USA";
//        String type = "mailing";
//        String zipCode = "88775";
//        String poBox = "21";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        // Set some text in the note file.
//        //returnedOrder.setNoteString("This Is a test note.");
//        returnedOrder.setCity(city);
//        returnedOrder.setCountry(country);
//        returnedOrder.setType(type);
//        returnedOrder.setZipcode(zipCode);
//        returnedOrder.setPoBox(poBox);
//        returnedOrder.setFirstName(firstName);
//        returnedOrder.setStreetOrder(street);
//        returnedOrder.setState(state);
//
//        // Use the update method to save this new text to file.
//        orderDao.update(order);
//
//        // Load a new instance of the NoteDao.
//        OrderDao thirdDao = new OrderDao(true);
//
//        // Pull a note  using the id number recorded earlier.
//        Order thirdOrder = secondDao.get(id);
//
//        assertTrue(thirdOrder != null);
//
//        // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdOrder.getNoteString());
//        assertEquals(city, thirdOrder.getCity());
//        assertEquals(country, thirdOrder.getCountry());
//        assertEquals(type, thirdOrder.getType());
//        assertEquals(zipCode, thirdOrder.getZipcode());
//        assertEquals(poBox, thirdOrder.getPoBox());
//        assertEquals(firstName, thirdOrder.getFirstName());
//        assertEquals(street, thirdOrder.getStreetOrder());
//        assertEquals(state, thirdOrder.getState());
//
//        // Delete the test note.
//        secondDao.delete(thirdOrder);
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        OrderDao thirdDao = new OrderDao(true);
//        assertEquals(thirdDao.get(id), null);
//
//    }
//
//    /**
//     * Test of create method, of class OrderDao.
//     */
//    @Test
//    public void testCreateC() {
//        System.out.println("create");
//        Order order = new Order();
//        OrderDao instance = new OrderDao(true);
//        Order expResult = order;
//        Order result = instance.create(order);
//        assertEquals(expResult, result);
//
//        int id = result.getId();
//        assertTrue(result.getId() != 0);
//        assertTrue(result.getId() >= instance.size());
//
//        // Test get method.
//        Order returnedOrder = instance.get(id);
//        assertEquals(returnedOrder, result);
//        instance.delete(order);
//
//        returnedOrder = instance.get(id);
//        assertEquals(returnedOrder, null);
//    }
//
//    /**
//     * Test of getAllOrderes method, of class OrderDao.
//     */
//    @Test
//    public void testGetAllOrderesC() {
//        System.out.println("getAllOrderes");
//        OrderDao instance = new OrderDao(true);
//
//        Order orderOne = new Order();
//        Order orderTwo = new Order();
//        Order orderThree = new Order();
//
//        instance.create(orderOne);
//        instance.create(orderTwo);
//        instance.create(orderThree);
//
//        assertTrue(instance.getAllOrderes().contains(orderTwo));
//
//        instance.delete(orderOne);
//        instance.delete(orderTwo);
//        instance.delete(orderThree);
//
//        int expSizeResult = 3;
//        int sizeResult = instance.size();
//        assertEquals(expSizeResult, sizeResult);
//
//        String lastName = "Steve";
//        List<Order> result = instance.searchByLastName(lastName);
//        assertEquals(true, result.isEmpty());
//
//        lastName = "Jones";
//        result = instance.searchByLastName(lastName);
//        assertEquals(false, result.isEmpty());
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testEncodeAndDecodeC() {
//
//        //Dvd dvd = new DvdImplementation();
//        OrderDao orderDao = new OrderDao(true);
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
//        String city = "Seville";
//        String country = "USA";
//        String type = "mailing";
//        String zipCode = "88775";
//        String poBox = "21";
//        String firstName = "First and last name here";
//        String street = "3589 Street Road";
//        String state = "Michigan";
//
//        // Set some text in the note file.
//        //returnedOrder.setNoteString("This Is a test note.");
//        returnedOrder.setCity(city);
//        returnedOrder.setCountry(country);
//        returnedOrder.setType(type);
//        returnedOrder.setZipcode(zipCode);
//        returnedOrder.setPoBox(poBox);
//        returnedOrder.setFirstName(firstName);
//        returnedOrder.setStreetOrder(street);
//        returnedOrder.setState(state);
//
//        // Use the update method to save this new text to file.
//        orderDao.update(order);
//
//        // Load a new instance of the NoteDao.
//        OrderDao secondDao = new OrderDao(true);
//
//        // Pull a note  using the id number recorded earlier.
//        Order thirdOrder = secondDao.get(id);
//
//        assertTrue(thirdOrder != null);
//
//        // Check that the update method saved the new text.
//        //assertEquals("This Is a test note.", thirdOrder.getNoteString());
//        assertEquals(city, thirdOrder.getCity());
//        assertEquals(country, thirdOrder.getCountry());
//        assertEquals(type, thirdOrder.getType());
//        assertEquals(zipCode, thirdOrder.getZipcode());
//        assertEquals(poBox, thirdOrder.getPoBox());
//        assertEquals(firstName, thirdOrder.getFirstName());
//        assertEquals(street, thirdOrder.getStreetOrder());
//        assertEquals(state, thirdOrder.getState());
//
//        // Delete the test note.
//        secondDao.delete(thirdOrder);
//
//        // Load a third instance of the Dao and verify that 
//        // the note was deleted from the file.
//        OrderDao thirdDao = new OrderDao(true);
//        assertEquals(thirdDao.get(id), null);
//
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    /**
//     * Test of create method, of class OrderDao.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        Order order = null;
//        OrderDao instance = null;
//        Order expResult = null;
//        Order result = instance.create(order);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get method, of class OrderDao.
//     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Integer id = null;
//        OrderDao instance = null;
//        Order expResult = null;
//        Order result = instance.get(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class OrderDao.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Order order = null;
//        OrderDao instance = null;
//        instance.update(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class OrderDao.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Order order = null;
//        OrderDao instance = null;
//        instance.delete(order);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getList method, of class OrderDao.
//     */
//    @Test
//    public void testGetList() {
//        System.out.println("getList");
//        OrderDao instance = null;
//        List<Order> expResult = null;
//        List<Order> result = instance.getList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of size method, of class OrderDao.
//     */
//    @Test
//    public void testSize() {
//        System.out.println("size");
//        OrderDao instance = null;
//        int expResult = 0;
//        int result = instance.size();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchByDate method, of class OrderDao.
//     */
//    @Test
//    public void testSearchByDate() {
//        System.out.println("searchByDate");
//        Date date = null;
//        OrderDao instance = null;
//        List<Order> expResult = null;
//        List<Order> result = instance.searchByDate(date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isSameDay method, of class OrderDao.
//     */
//    @Test
//    public void testIsSameDay() {
//        System.out.println("isSameDay");
//        Date date1 = null;
//        Date date2 = null;
//        boolean expResult = false;
//        boolean result = OrderDao.isSameDay(date1, date2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
