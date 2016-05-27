/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Map;
import java.util.Set;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class StateCapitals2Test {

    ApplicationContext ctx;

    public StateCapitals2Test() {
        this.ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of printCaptials method, of class StateCapitals2.
     */
//    @Test
//    public void testPrintCaptials() {
//        System.out.println("printCaptials");
//        StateCapitals2 instance = new StateCapitals2();
//        instance.printCaptials();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of printStatesAndCapitals method, of class StateCapitals2.
     */
//    @Test
//    public void testPrintStatesAndCapitals() {
//        System.out.println("printStatesAndCapitals");
//        StateCapitals2 instance = new StateCapitals2();
//        instance.printStatesAndCapitals();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of printStatesCapitalsAndAttributes method, of class StateCapitals2.
     * //
     */
//    @Test
//    public void testPrintStatesCapitalsAndAttributes_0args() {
//        System.out.println("printStatesCapitalsAndAttributes");
//        StateCapitals2 instance = new StateCapitals2();
//        instance.printStatesCapitalsAndAttributes();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of printStatesCapitalsAndAttributes method, of class StateCapitals2.
     * //
     */
//    @Test
//    public void testPrintStatesCapitalsAndAttributes_HashMap() {
//        System.out.println("printStatesCapitalsAndAttributes");
//        HashMap<String, Capital> localCapitalMap = null;
//        StateCapitals2 instance = new StateCapitals2();
//        instance.printStatesCapitalsAndAttributes(localCapitalMap);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of printStatesCapitalsAndAttributes method, of class StateCapitals2.
     * //
     */
//    @Test
//    public void testPrintStatesCapitalsAndAttributes_int() {
//        System.out.println("printStatesCapitalsAndAttributes");
//        int lowerLimit = 0;
//        StateCapitals2 instance = new StateCapitals2();
//        instance.printStatesCapitalsAndAttributes(lowerLimit);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getFilteredCapitalsMap method, of class StateCapitals2.
     */
    @Test
    public void testGetFilteredCapitalsMap() {
        System.out.println("getFilteredCapitalsMap");
        int lowerLimit = Integer.MAX_VALUE;
        //StateCapitals2 instance = new StateCapitals2();
        StateCapitals2 instance = ctx.getBean("stateCapitals2", com.mycompany.labs.StateCapitals2.class);
        Map expResult = null;
        Map result = instance.getFilteredCapitalsMap(lowerLimit);

        Set<String> keySet = result.keySet();

        java.util.Map<String, Capital> filteredMap = instance.getFilteredCapitalsMap(lowerLimit);

        Boolean testResult = true;

        for (String key : keySet) {

            if (filteredMap.get(key).getPopulation() > lowerLimit) {

                testResult = false;

            }
        }

        assertTrue(testResult);

        lowerLimit = 5000000;
        filteredMap = instance.getFilteredCapitalsMap(lowerLimit);

        testResult = true;

        for (String key : keySet) {

            if (filteredMap.get(key).getPopulation() > lowerLimit) {

                testResult = false;

            }
        }

        assertTrue(testResult);

        lowerLimit = 70;
        filteredMap = instance.getFilteredCapitalsMap(lowerLimit);

        testResult = true;

        for (String key : keySet) {

            if (filteredMap.get(key).getPopulation() > lowerLimit) {

                testResult = false;

            }
        }

        assertTrue(testResult);

    }

    /**
     * Test of printStates method, of class StateCapitals2.
     */
//    @Test
//    public void testPrintStates() {
//        System.out.println("printStates");
//        StateCapitals2 instance = new StateCapitals2();
//        instance.printStates();
//
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of buildCapital method, of class StateCapitals2.
     */
    @Test
    public void testBuildCapital() {
        System.out.println("buildCapital");
        String capitalName = "FakeCapital";
        //StateCapitals2 instance = new StateCapitals2();
        StateCapitals2 instance = ctx.getBean("stateCapitals2", com.mycompany.labs.StateCapitals2.class);

        Capital result = instance.buildCapital(capitalName);
        assertEquals(result.getName(), capitalName);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of loadCapitalsMap2 method, of class StateCapitals2.
     */
    @Test
    public void testLoadCapitalsMap2() {
        System.out.println("loadCapitalsMap2");
        //StateCapitals2 instance = new StateCapitals2();
        StateCapitals2 instance = ctx.getBean("stateCapitals2", com.mycompany.labs.StateCapitals2.class);
        instance.loadCapitalsMap2();

        java.util.HashMap<String, Capital> capitalMap = instance.getCapitalObjectMap();

        //capitalMap.get("Iowa");
        Assert.assertEquals(capitalMap.get("Iowa").getName(), "Des Moines");
        Assert.assertEquals(capitalMap.get("Ohio").getName(), "Columbus");
        Assert.assertEquals(capitalMap.get("Texas").getName(), "Austin");
        Assert.assertEquals(capitalMap.get("Wyoming").getName(), "Cheyenne");

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
