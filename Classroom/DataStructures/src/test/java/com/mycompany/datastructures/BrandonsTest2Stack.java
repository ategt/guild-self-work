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
public class BrandonsTest2Stack {
    
    public BrandonsTest2Stack() {
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


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void PushPopTest() {
        Stack<String> stringStack = new StackArrayImpl<>();
        int counter = 0;
        while (counter < 10000) {
            stringStack.push("Shaq");
            stringStack.push("Kobe");
            stringStack.push("LeBron");
            stringStack.push("Jordan");
            stringStack.push("CP3");
            counter++;
        }
            
        while (!stringStack.isEmpty()) {
            Assert.assertEquals("CP3", stringStack.pop());
            Assert.assertEquals("Jordan", stringStack.pop());
            Assert.assertEquals("LeBron", stringStack.pop());
            Assert.assertEquals("Kobe", stringStack.pop());
            Assert.assertEquals("Shaq", stringStack.pop());
        }
    }
    
    @Test
    public void IsEmptyTest() {
        Stack<String> stringStack = new StackArrayImpl<>();
        
        Assert.assertEquals(true, stringStack.isEmpty());
        
        stringStack.push("LeBron");
        
        Assert.assertEquals(false, stringStack.isEmpty());
    }
    
    @Test
    public void SizeTest() {
        Stack<String> stringStack = new StackArrayImpl<>();
        stringStack.push("Shaq");
        stringStack.push("Kobe");
        stringStack.push("LeBron");
        stringStack.push("Jordan");
        
        Assert.assertEquals(4, stringStack.size());
    }
}
