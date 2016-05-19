/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.utilities;

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
public class ViewUtilitiesTest {
    
    public ViewUtilitiesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of bordermaker method, of class ViewUtilities.
     */
    @Test
    public void testBordermaker() {
        System.out.println("bordermaker");
        List<String> content = new ArrayList();
        content.add("ssc");
        content.add("ssq");
        
        int width = 7;
        char borderChar = '*';
        ViewUtilities instance = new ViewUtilities();
        String expResult = "*******\n" + 
                           "* ssc *\n"
                        +  "* ssq *\n"
                        +  "*******\n";
        String result = instance.bordermaker(content, width, borderChar);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthLeft method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthLeft() {
        System.out.println("stringToLengthLeft");
        String content = "";
        int desiredLength = 10;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "          ";
        String result = instance.stringToLengthLeft(content, desiredLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthLeft method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthLeftB() {
        System.out.println("stringToLengthLeft");
        String content = "8";
        int desiredLength = 11;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "8          ";
        String result = instance.stringToLengthLeft(content, desiredLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthRight method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthRight() {
        System.out.println("stringToLengthRight");
        String content = "";
        int desiredLength = 10;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "          ";
        String result = instance.stringToLengthRight(content, desiredLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthRight method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthRightB() {
        System.out.println("stringToLengthRight");
        String content = "8";
        int desiredLength = 10;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "         8";
        String result = instance.stringToLengthRight(content, desiredLength);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of stringToLengthRight method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthRightC() {
        System.out.println("stringToLengthRight");
        String content = "";
        int desiredLength = 0;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "";
        String result = instance.stringToLengthRight(content, desiredLength);
        assertEquals(expResult, result);
    }


    /**
     * Test of stringToLengthRight method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthRightD() {
        System.out.println("stringToLengthRight");
        String content = "x";
        int desiredLength = 0;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "x";
        String result = instance.stringToLengthRight(content, desiredLength);
        assertEquals(null, result);
    }


    /**
     * Test of stringToLengthCenter method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthCenter() {
        System.out.println("stringToLengthCenter");
        String content = "x";
        int desiredLength = 0;
        ViewUtilities instance = new ViewUtilities();
        String expResult = null;
        String result = instance.stringToLengthCenter(content, desiredLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthCenter method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthCenterA() {
        System.out.println("stringToLengthCenter");
        String content = "x";
        int desiredLength = 1;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "x";
        String result = instance.stringToLengthCenter(content, desiredLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthCenter method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthCenterB() {
        System.out.println("stringToLengthCenter");
        String content = "x";
        int desiredLength = 3;
        ViewUtilities instance = new ViewUtilities();
        String expResult = " x ";
        String result = instance.stringToLengthCenter(content, desiredLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLengthCenter method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthCenterC() {
        System.out.println("stringToLengthCenter");
        String content = " ";
        int desiredLength = 0;
        ViewUtilities instance = new ViewUtilities();
        String expResult = " ";
        String result = instance.stringToLengthCenter(content, desiredLength);
        assertEquals(null, result);
    }
    /**
     * Test of stringToLengthCenter method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthCenterD() {
        System.out.println("stringToLengthCenter");
        String content = " ";
        int desiredLength = 1;
        ViewUtilities instance = new ViewUtilities();
        String expResult = " ";
        String result = instance.stringToLengthCenter(content, desiredLength);
        assertEquals(expResult, result);
    }

        /**
     * Test of stringToLengthCenter method, of class ViewUtilities.
     */
    @Test
    public void testStringToLengthCenterE() {
        System.out.println("stringToLengthCenter");
        String content = "";
        int desiredLength = 5;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "     ";
        String result = instance.stringToLengthCenter(content, desiredLength);
        assertEquals(expResult, result);
    }


    
    /**
     * Test of makeSpaces method, of class ViewUtilities.
     */
    @Test
    public void testMakeSpaces() {
        System.out.println("makeSpaces");
        int length = 5;
        ViewUtilities instance = new ViewUtilities();
        String expResult = "     ";
        String result = instance.makeSpaces(length);
        assertEquals(expResult, result);
    }
    
}
