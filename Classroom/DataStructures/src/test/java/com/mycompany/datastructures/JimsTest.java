/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datastructures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JimsTest {
    
    public JimsTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


    @Test
    public void IntegerQueueArrayTest() {
        
        //Create a new Integer Instance and verify it is empty on creation
        Queue<Integer> intQueue = new QueueArrayImpl();        
        Assert.assertEquals(true, intQueue.isEmpty());
    //*************************************************************    
                
        Integer firstInteger = 10;
        Integer secondInteger = 5;
        Integer thirdInteger = 3;        
        
        //Add Items to the Instance and verify it is no longer empty
        intQueue.enqueue(firstInteger);
        intQueue.enqueue(secondInteger);
        intQueue.enqueue(thirdInteger);        
        Assert.assertEquals(false, intQueue.isEmpty());
    //*************************************************************
    
        //Check the Queue Size
        Assert.assertEquals(3, intQueue.size());
    //*************************************************************
    
        //Check the first Integer was dequeued correctly
        Integer firstDequeued = intQueue.dequeue();        
        Assert.assertEquals(firstInteger, firstDequeued);
        Assert.assertEquals(2, intQueue.size());
    //*************************************************************
    
        //Check the second Integer was dequeued correctly
        Integer secondDequeued = intQueue.dequeue();        
        Assert.assertEquals(secondInteger, secondDequeued);
        Assert.assertEquals(1, intQueue.size());
    //*************************************************************
        
        //Check the third Integer was dequeued correctly and is now empty
        Integer thirdDequeued = intQueue.dequeue();        
        Assert.assertEquals(thirdInteger, thirdDequeued);
        Assert.assertEquals(0, intQueue.size());
        Assert.assertEquals(true, intQueue.isEmpty());
    //**************************************************************        
        
    }
    
    @Test
    public void IntegerEnqueueAndDequeueTest() {
        //Create a new Integer Instance and verify it is empty on creation
        Queue<Integer> intQueue = new QueueArrayImpl();        
        Assert.assertEquals(true, intQueue.isEmpty());
    //*************************************************************    
                
        Integer firstInteger = 10;
        Integer secondInteger = 5;
        Integer thirdInteger = 3;
        Integer fourthInteger = 7;
        Integer fifthInteger = 15;
        
        
        //Add Items to the Instance and verify it is no longer empty
        intQueue.enqueue(firstInteger);
        intQueue.enqueue(secondInteger);
        intQueue.enqueue(thirdInteger);
        intQueue.enqueue(fourthInteger);
        intQueue.enqueue(fifthInteger);
        Assert.assertEquals(false, intQueue.isEmpty());
        Assert.assertEquals(5, intQueue.size());
    //*************************************************************
    
    //Check the Queue Size
        Assert.assertEquals(5, intQueue.size());
    //*************************************************************
    
        //Check the first Integer was dequeued correctly
        Integer firstDequeued = intQueue.dequeue();        
        Assert.assertEquals(firstInteger, firstDequeued);
        Assert.assertEquals(4, intQueue.size());
    //*************************************************************
    
        //Enqueue something and verify it is added to the back of the line
        Integer addInteger6 = 63;
        intQueue.enqueue(addInteger6);
        Assert.assertEquals(5, intQueue.size());
        
        //Check the second Integer was dequeued correctly
        Integer secondDequeued = intQueue.dequeue();        
        Assert.assertEquals(secondInteger, secondDequeued);
        Assert.assertEquals(4, intQueue.size());
    //*************************************************************
        
        //Check the third Integer was dequeued correctly
        Integer thirdDequeued = intQueue.dequeue();        
        Assert.assertEquals(thirdInteger, thirdDequeued);
        Assert.assertEquals(3, intQueue.size());
        
        //Check the fourth Integer was dequeued correctly
        Integer fourthDequeued = intQueue.dequeue();        
        Assert.assertEquals(fourthInteger, fourthDequeued);
        Assert.assertEquals(2, intQueue.size());
    //*************************************************************
        
        //Check the fifth Integer was dequeued correctly
        Integer fifthDequeued = intQueue.dequeue();        
        Assert.assertEquals(fifthInteger, fifthDequeued);
        Assert.assertEquals(1, intQueue.size());
        
        //Check the added Integer was dequeued correctly and is not empty 
        Integer added6Dequeued = intQueue.dequeue();        
        Assert.assertEquals(addInteger6, added6Dequeued);
        Assert.assertEquals(0, intQueue.size());
    }
    
    @Test
    public void IntegerEnqueueIncreaseSizeTest() {
        
        //Create a new Integer Instance and verify it is empty on creation
        Queue<Integer> intQueue = new QueueArrayImpl();        
        Assert.assertEquals(true, intQueue.isEmpty());
    //*************************************************************    
                
        Integer firstInteger = 10;
        Integer secondInteger = 5;
        Integer thirdInteger = 3;
        Integer fourthInteger = 7;
        Integer fifthInteger = 15;
        
        
        //Add Items to the Instance and verify it is no longer empty
        intQueue.enqueue(firstInteger);
        intQueue.enqueue(secondInteger);
        intQueue.enqueue(thirdInteger);
        intQueue.enqueue(fourthInteger);
        intQueue.enqueue(fifthInteger);
        Assert.assertEquals(false, intQueue.isEmpty());
        Assert.assertEquals(5, intQueue.size());
    //*************************************************************
        
        //Enqueue another item to ensure the array expands
        int addInteger6 = 63;
        intQueue.enqueue(addInteger6);
        Assert.assertEquals(6, intQueue.size());
        
        //Enqueue another item to ensure the array expands
        int addInteger7 = 17;
        intQueue.enqueue(addInteger6);
        Assert.assertEquals(7, intQueue.size());
    }
    
    @Test
    public void StringQueueArrayTest() {
        
        //Create a new Integer Instance and verify it is empty on creation
        Queue<String> stringQueue = new QueueArrayImpl();        
        Assert.assertEquals(true, stringQueue.isEmpty());
    //*************************************************************    
                
        String str1 = "String 1";
        String str2 = "String 2";
        String str3 = "String 3";        
        
        //Add Items to the Instance and verify it is no longer empty
        stringQueue.enqueue(str1);
        stringQueue.enqueue(str2);
        stringQueue.enqueue(str3);        
        Assert.assertEquals(false, stringQueue.isEmpty());
    //*************************************************************
    
        //Check the Queue Size
        Assert.assertEquals(3, stringQueue.size());
    //*************************************************************
    
        //Check the first Integer was dequeued correctly
        String dequeued1 = stringQueue.dequeue();        
        Assert.assertEquals(str1, dequeued1);
        Assert.assertEquals(2, stringQueue.size());
    //*************************************************************
    
        //Check the second Integer was dequeued correctly
        String dequeued2 = stringQueue.dequeue();        
        Assert.assertEquals(str2, dequeued2);
        Assert.assertEquals(1, stringQueue.size());
    //*************************************************************
        
        //Check the third Integer was dequeued correctly and is now empty
        String dequeued3 = stringQueue.dequeue(); 
        Assert.assertEquals(str3, dequeued3);
        Assert.assertEquals(0, stringQueue.size());
        Assert.assertEquals(true, stringQueue.isEmpty());
    //**************************************************************        
        
    }
    
    @Test
    public void StringEnqueueAndDequeueTest() {
        //Create a new Integer Instance and verify it is empty on creation
        Queue<String> stringQueue = new QueueArrayImpl();        
        Assert.assertEquals(true, stringQueue.isEmpty());
    //*************************************************************    
                
        String str1 = "String 1";
        String str2 = "String 2";
        String str3 = "String 3";
        String str4 = "String 4";
        String str5 = "String 5";
        
        
        //Add Items to the Instance and verify it is no longer empty
        stringQueue.enqueue(str1);
        stringQueue.enqueue(str2);
        stringQueue.enqueue(str3);
        stringQueue.enqueue(str4);
        stringQueue.enqueue(str5);
        Assert.assertEquals(false, stringQueue.isEmpty());
        Assert.assertEquals(5, stringQueue.size());
    //*************************************************************
    
    //Check the Queue Size
        Assert.assertEquals(5, stringQueue.size());
    //*************************************************************
    
        //Check the first Integer was dequeued correctly
        String dequeued1 = stringQueue.dequeue();        
        Assert.assertEquals(str1, dequeued1);
        Assert.assertEquals(4, stringQueue.size());
    //*************************************************************
    
        //Enqueue something and verify it is added to the back of the line
        String addString6 = "Added String 6";
        stringQueue.enqueue(addString6);
        Assert.assertEquals(5, stringQueue.size());
        
        //Check the second Integer was dequeued correctly
        String dequeued2 = stringQueue.dequeue();        
        Assert.assertEquals(str2, dequeued2);
        Assert.assertEquals(4, stringQueue.size());
    //*************************************************************
        
        //Check the third Integer was dequeued correctly
        String dequeued3 = stringQueue.dequeue();        
        Assert.assertEquals(str3, dequeued3);
        Assert.assertEquals(3, stringQueue.size());
        
        //Check the fourth Integer was dequeued correctly
        String dequeued4 = stringQueue.dequeue();        
        Assert.assertEquals(str4, dequeued4);
        Assert.assertEquals(2, stringQueue.size());
    //*************************************************************
        
        //Check the fifth Integer was dequeued correctly
        String dequeued5 = stringQueue.dequeue();        
        Assert.assertEquals(str5, dequeued5);
        Assert.assertEquals(1, stringQueue.size());
        
        //Check the added Integer was dequeued correctly and is not empty 
        String added6Dequeued = stringQueue.dequeue();        
        Assert.assertEquals(addString6, added6Dequeued);
        Assert.assertEquals(0, stringQueue.size());
    }
    
    @Test
    public void StringEnqueueIncreaseSizeTest() {
        
        //Create a new Integer Instance and verify it is empty on creation
        Queue<String> stringQueue = new QueueArrayImpl();        
        Assert.assertEquals(true, stringQueue.isEmpty());
    //*************************************************************    
                
        String str1 = "String 1";
        String str2 = "String 2";
        String str3 = "String 3";
        String str4 = "String 4";
        String str5 = "String 5";
        
        
        //Add Items to the Instance and verify it is no longer empty
        stringQueue.enqueue(str1);
        stringQueue.enqueue(str2);
        stringQueue.enqueue(str3);
        stringQueue.enqueue(str4);
        stringQueue.enqueue(str5);
        Assert.assertEquals(false, stringQueue.isEmpty());
        Assert.assertEquals(5, stringQueue.size());
    //*************************************************************
        
        //Enqueue another item to ensure the array expands
        String addString6 = "Add String 6";
        stringQueue.enqueue(addString6);
        Assert.assertEquals(6, stringQueue.size());
        
        //Enqueue another item to ensure the array expands
        String addString7 = "Add String 7";
        stringQueue.enqueue(addString7);
        Assert.assertEquals(7, stringQueue.size());
    }
}