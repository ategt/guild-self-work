/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.utilities;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderFilterTest {
    
    public OrderFilterTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of accept method, of class OrderFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        
        File file = new File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery");
        OrderFilter instance = new OrderFilter();
        boolean expResult = false;
        
        boolean result = instance.accept(file);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of accept method, of class OrderFilter.
     */
    @Test
    public void testAcceptB() {
        System.out.println("accept");
        

        File file = new File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/Orders_01012000.txt");
        OrderFilter instance = new OrderFilter();
        boolean expResult = true;
        
        boolean result = instance.accept(file);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of accept method, of class OrderFilter.
     */
    @Test
    public void testAcceptC() {
        System.out.println("accept");
        

        File file = new File("/home/apprentice/_repos/adam.tegtmeier.self.work/Labs/FlooringMastery/Ordersdata.txt");
        OrderFilter instance = new OrderFilter();
        boolean expResult = false;
        
        boolean result = instance.accept(file);
        assertEquals(expResult, result);
    }
}
