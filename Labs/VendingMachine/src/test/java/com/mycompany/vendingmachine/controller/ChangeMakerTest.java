/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dto.Change;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ChangeMakerTest {
    
    public ChangeMakerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeChange method, of class ChangeMaker.
     */
    @Test
    public void testMakeChange() {
        int balance = 41;
        ChangeMaker instance = new ChangeMaker();
        
        int quarters = 1;
        int dimes = 1;
        int nickles = 1;
        int pennies = 1;
        
        Change result = instance.makeChange(balance);
        
        assertEquals(quarters, result.getQuarters());
        assertEquals(dimes, result.getDimes());
        assertEquals(nickles, result.getNickles());
        assertEquals(pennies, result.getPennies());
    }
    

    /**
     * Test of makeChange method, of class ChangeMaker.
     */
    @Test
    public void testMakeChangeB() {
        int balance = 44;
        ChangeMaker instance = new ChangeMaker();
        
        int quarters = 1;
        int dimes = 1;
        int nickles = 1;
        int pennies = 4;
        
        Change result = instance.makeChange(balance);
        
        assertEquals(quarters, result.getQuarters());
        assertEquals(dimes, result.getDimes());
        assertEquals(nickles, result.getNickles());
        assertEquals(pennies, result.getPennies());
    }
    
    /**
     * Test of makeChange method, of class ChangeMaker.
     */
    @Test
    public void testMakeChangeC() {
        int balance = 100;
        ChangeMaker instance = new ChangeMaker();
        
        int quarters = 4;
        int dimes = 0;
        int nickles = 0;
        int pennies = 0;
        
        Change result = instance.makeChange(balance);
        
        assertEquals(quarters, result.getQuarters());
        assertEquals(dimes, result.getDimes());
        assertEquals(nickles, result.getNickles());
        assertEquals(pennies, result.getPennies());
    }
    


}
