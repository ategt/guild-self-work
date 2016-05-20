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
public class BrennensTests {
    
    public BrennensTests() {
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
    public void EnqueueDequeueTest() {
        Queue<String> stringQueue = new QueueArrayImpl<>();
        int counter = 0;
        while (counter < 10000) {
            stringQueue.enqueue("Shaq");
            stringQueue.enqueue("Kobe");
            stringQueue.enqueue("LeBron");
            stringQueue.enqueue("Jordan");
            counter++;
        }
            
        while (!stringQueue.isEmpty()) {
            Assert.assertEquals("Shaq", stringQueue.dequeue());
            Assert.assertEquals("Kobe", stringQueue.dequeue());
            Assert.assertEquals("LeBron", stringQueue.dequeue());
            Assert.assertEquals("Jordan", stringQueue.dequeue());
        }
    }
    
    @Test
    public void IsEmptyTest() {
        Queue<String> stringQueue = new QueueArrayImpl<>();
        
        Assert.assertEquals(true, stringQueue.isEmpty());
        
        stringQueue.enqueue("LeBron");
        
        Assert.assertEquals(false, stringQueue.isEmpty());
    }
    
    @Test
    public void SizeTest() {
        Queue<String> stringQueue = new QueueArrayImpl<>();
        stringQueue.enqueue("Shaq");
        stringQueue.enqueue("Kobe");
        stringQueue.enqueue("LeBron");
        stringQueue.enqueue("Jordan");
        
        Assert.assertEquals(4, stringQueue.size());
    }
}
