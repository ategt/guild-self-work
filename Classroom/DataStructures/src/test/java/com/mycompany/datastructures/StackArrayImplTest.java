/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datastructures;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class StackArrayImplTest {
    
    public StackArrayImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    
      @Test
    public void testPush() {
        
        System.out.println("push");
        String elementOne = "Bill";
        String elementTwo = "Steve";
        String elementThree = "Tim";
        String elementFour = "Dave";

        Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);
        
        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);
        instance.push(elementFour);
    
        int fourSize = instance.size();
        assertEquals(fourSize, 4);
        assertEquals(instance.isEmpty(), false);
        
        String resultFour = instance.pop();
        String resultThree = instance.pop();
        String resultTwo = instance.pop();
        String resultOne = instance.pop();
        
        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);
        
        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);
        
        String shouldBeNull = instance.pop();
        
        assertEquals(shouldBeNull, null);
        
        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);
        
        
        
        
    }

    
    /**
     * Test of push method, of class StackArrayImpl.
     */
    @Test
    public void testPushB() {
                
        System.out.println("pushB - All Nulls");
        String elementOne = null;
        String elementTwo = null;
        String elementThree = null;
        String elementFour = null;

        Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);
        
        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);
        instance.push(elementFour);
    
        int fourSize = instance.size();
        assertEquals(fourSize, 0);
        assertEquals(instance.isEmpty(), true);
        
        String resultFour = instance.pop();
        String resultThree = instance.pop();
        String resultTwo = instance.pop();
        String resultOne = instance.pop();
        
        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);
        
        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);
        
        String shouldBeNull = instance.pop();
        
        assertEquals(shouldBeNull, null);
        
        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);
        
        
    }

    /**
     * Test of pop method, of class StackArrayImpl.
     */
    @Test
    public void testPopC() {
        System.out.println("popC - Out of Order");
        
        String elementOne = "Bill";
        String elementTwo = "Steve";
        String elementThree = "Tim";
        String elementFour = "Dave";

        Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);
        
        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);
    
        int fourSize = instance.size();
        assertEquals(fourSize, 3);
        assertEquals(instance.isEmpty(), false);
        
        String resultThree = instance.pop();
        String resultTwo = instance.pop();

        instance.push(elementFour);
        String resultFour = instance.pop();

        String resultOne = instance.pop();
        
        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);
        
        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);
        
        String shouldBeNull = instance.pop();
        
        assertEquals(shouldBeNull, null);
        
        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);
        
        }

    /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testIsEmpty() {
        
        System.out.println("isEmpty");
        
        int sizeTest = 50;
        
        List<String> elementList = new ArrayList();
        String tempString = "";
        
        for (int i = 0; i < sizeTest; i++) {
            
            tempString = tempString + "z";
            elementList.add(tempString);
            
        }
        
        Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);
        
        for (String string : elementList) {
            instance.push(string);
        }
        
        int fourSize = instance.size();
        assertEquals(fourSize, sizeTest);
        assertEquals(instance.isEmpty(), false);
        
        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();
            
            String expected = elementList.get(i);
            
            assertEquals(expected, result);
            
        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);
        
        String shouldBeNull = instance.pop();
        
        assertEquals(shouldBeNull, null);
        
        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);
        
    }

    
        /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testIsEmptyC() {
        
        System.out.println("isEmpty");
        
        int sizeTest = 500;
        
        List<String> elementList = new ArrayList();
        String tempString = "";
        
        for (int i = 0; i < sizeTest; i++) {
            
            tempString = tempString + "z";
            elementList.add(tempString);
            
        }
        
        Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);
        
        for (String string : elementList) {
            instance.push(string);
        }
        
        int fourSize = instance.size();
        assertEquals(fourSize, sizeTest);
        assertEquals(instance.isEmpty(), false);
        
        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();
            
            String expected = elementList.get(i);
            
            assertEquals(expected, result);
            
        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);
        
        String shouldBeNull = instance.pop();
        
        assertEquals(shouldBeNull, null);
        
        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);
        
    }


    
    /**
     * Test of size method, of class StackArrayImpl.
     */
//    @Test
//    public void testSize() {
//        System.out.println("size");
//        StackArrayImpl instance = new StackArrayImpl();
//        int expResult = 0;
//        int result = instance.size();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
