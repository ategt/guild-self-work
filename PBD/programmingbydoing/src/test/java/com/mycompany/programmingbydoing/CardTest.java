/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class CardTest {

    public CardTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateSuitNumber method, of class Card.
     */
    @Test
    public void testGenerateSuitNumber_Integer() {
        System.out.println("generateSuitNumber");
        Integer value = 5;
        Card instance = new Card(5);
        int expResult = 0;
        int result = instance.generateSuitNumber(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardValue method, of class Card.
     */
    @Test
    public void testGetCardValue() {
        System.out.println("getCardValue");
        Card instance = new Card(25);
        int expResult = 25;
        int result = instance.getCardValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCardNumber method, of class Card.
     */
    @Test
    public void testGetCardNumber() {
        System.out.println("getCardNumber");
        Card instance = new Card(25);
        int expResult = 13;
        int result = instance.getCardNumber();
        assertEquals(expResult, result);
    }

}
