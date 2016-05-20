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
public class SMsTests {
    
    public SMsTests() {
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

    public void StackTest1() {

        Stack<String> stringStack = new StackArrayImpl<>();
        stringStack.push("Bill");
        stringStack.push("Jones");

        String pop1 = stringStack.pop();
        String pop2 = stringStack.pop();
        
        Assert.assertEquals("Jones",pop1);
        Assert.assertEquals("Bill",pop2);

    }
    
    
    @Test
    
    public void IsEmptyTest(){
    Stack<String> stringStack = new StackArrayImpl<>();
    
       
 //empty test -1       
        boolean isEmpty=stringStack.isEmpty();
        
        Assert.assertEquals(true,isEmpty);




//empty test -2
        
        stringStack.push("Dan");
        isEmpty=stringStack.isEmpty();
        Assert.assertEquals(false, isEmpty);
    
    
    }
    
    
}
