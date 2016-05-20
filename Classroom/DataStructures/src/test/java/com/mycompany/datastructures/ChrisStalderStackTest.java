package com.mycompany.datastructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.datastructures.StackArrayImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.datastructures.Stack;

/**
 *
 * @author Christopher Stalder
 */
public class ChrisStalderStackTest {

    public ChrisStalderStackTest() {
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
    public void testPushAndPop() {
        Stack<String> stringStack = new StackArrayImpl<>();

        stringStack.push("Bill");
        stringStack.push("Jones");

        String pop1 = stringStack.pop();
        String pop2 = stringStack.pop();

        Assert.assertEquals("Jones", pop1);
    }

    @Test
    public void testIsEmpty1() {
        Stack<String> stringStack = new StackArrayImpl<>();

        boolean result = stringStack.isEmpty();

        Assert.assertTrue(result);
    }

    @Test
    public void testIsEmpty2() {
        Stack<String> stringStack = new StackArrayImpl<>();
        stringStack.push("Bill");
        stringStack.push("Jones");

        boolean result = stringStack.isEmpty();

        Assert.assertFalse(result);
    }

    @Test
    public void testSize() {
        Stack<String> stringStack = new StackArrayImpl<>();
        stringStack.push("Bill");
        stringStack.push("Jones");

        int result = stringStack.size();

        Assert.assertEquals(2, result);
    }
}
