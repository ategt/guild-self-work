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
public class QueueArrayImplTest {
    
        Queue<String> instance;

    public QueueArrayImplTest() {
    }

    @Before
    public void setUp() {
        instance = new QueueArrayImpl();
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

        //Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);
        instance.enqueue(elementFour);

        int fourSize = instance.size();
        assertEquals(fourSize, 4);
        assertEquals(instance.isEmpty(), false);

        String resultOne = instance.dequeue();
        String resultTwo = instance.dequeue();
        String resultThree = instance.dequeue();
        String resultFour = instance.dequeue();

        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.dequeue();

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

        //Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);
        instance.enqueue(elementFour);

        int fourSize = instance.size();
        assertEquals(fourSize, 0);
        assertEquals(instance.isEmpty(), true);

        String resultOne = instance.dequeue();
        String resultTwo = instance.dequeue();
        String resultThree = instance.dequeue();
        String resultFour = instance.dequeue();


        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.dequeue();

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

        //Stack<String> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);

        int fourSize = instance.size();
        assertEquals(fourSize, 3);
        assertEquals(instance.isEmpty(), false);

        String resultOne = instance.dequeue();
        String resultTwo = instance.dequeue();
        instance.enqueue(elementFour);
        String resultThree = instance.dequeue();

        String resultFour = instance.dequeue();


        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.dequeue();

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

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        for (String string : elementList) {
            instance.enqueue(string);
        }

        int afterSize = instance.size();
        assertEquals(afterSize, sizeTest);
        assertEquals(instance.isEmpty(), false);

        //for (int i = sizeTest; i > 0; i--) {
        for (int i = 0; i < sizeTest; i++) {

            String result = instance.dequeue();

            String expected = elementList.get(i);
            System.out.println(i + ") " + result + "\t" + expected );
            assertEquals(expected, result);

        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.dequeue();

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
