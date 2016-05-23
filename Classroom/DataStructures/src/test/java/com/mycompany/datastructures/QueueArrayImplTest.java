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

        for (int i = 0; i < sizeTest; i++) {

            String result = instance.dequeue();

            String expected = elementList.get(i);
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
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testWrapShrinker() {

        String elementOne = "One";
        String elementTwo = "Two";
        String elementThree = "Three";
        String elementFour = "Four";
        String elementFive = "Five";
        String elementSix = "Six";
        String elementSeven = "Seven";
        String elementEight = "Eight";
        String elementNine = "Nine";
        String elementTen = "Ten";
        String elementEleven = "Eleven";
        String elementTweleve = "Tweleve";
        String elementThirteen = "Thirteen";
        String elementFourteen = "Fourteen";
        String elementFifteen = "Fifteen";
        String elementSixteen = "Sixteen";

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);
        instance.enqueue(elementFour);
        instance.enqueue(elementFive);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 5);

        String result = instance.dequeue();
        assertEquals(elementOne, result);

        result = instance.dequeue();
        assertEquals(elementTwo, result);

        result = instance.dequeue();
        assertEquals(elementThree, result);

        result = instance.dequeue();
        assertEquals(elementFour, result);

        result = instance.dequeue();
        assertEquals(elementFive, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        instance.enqueue(elementSix);
        instance.enqueue(elementSeven);
        instance.enqueue(elementEight);
        instance.enqueue(elementNine);
        instance.enqueue(elementTen);
        instance.enqueue(elementEleven);
        instance.enqueue(elementTweleve);
        instance.enqueue(elementThirteen);
        instance.enqueue(elementFourteen);
        instance.enqueue(elementFifteen);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 10);

        result = instance.dequeue();
        assertEquals(elementSix, result);

        result = instance.dequeue();
        assertEquals(elementSeven, result);

        result = instance.dequeue();
        assertEquals(elementEight, result);

        result = instance.dequeue();
        assertEquals(elementNine, result);

        result = instance.dequeue();
        assertEquals(elementTen, result);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 5);

        result = instance.dequeue();
        assertEquals(elementEleven, result);

        result = instance.dequeue();
        assertEquals(elementTweleve, result);

        result = instance.dequeue();
        assertEquals(elementThirteen, result);

        result = instance.dequeue();
        assertEquals(elementFourteen, result);

        result = instance.dequeue();
        assertEquals(elementFifteen, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        result = instance.dequeue();
        assertEquals(null, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        instance.enqueue(elementSixteen);

        result = instance.dequeue();
        assertEquals(elementSixteen, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

    }

    @Test
    public void testWrapShrinkerB() {

        Queue<String> instance = new QueueArrayImpl(5);

        String elementOne = "One";
        String elementTwo = "Two";
        String elementThree = "Three";
        String elementFour = "Four";
        String elementFive = "Five";
        String elementSix = "Six";
        String elementSeven = "Seven";
        String elementEight = "Eight";
        String elementNine = "Nine";
        String elementTen = "Ten";
        String elementEleven = "Eleven";
        String elementTweleve = "Tweleve";
        String elementThirteen = "Thirteen";
        String elementFourteen = "Fourteen";
        String elementFifteen = "Fifteen";
        String elementSixteen = "Sixteen";

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);
        instance.enqueue(elementFour);
        instance.enqueue(elementFive);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 5);

        String result = instance.dequeue();
        assertEquals(elementOne, result);

        result = instance.dequeue();
        assertEquals(elementTwo, result);

        result = instance.dequeue();
        assertEquals(elementThree, result);

        result = instance.dequeue();
        assertEquals(elementFour, result);

        result = instance.dequeue();
        assertEquals(elementFive, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        instance.enqueue(elementSix);
        instance.enqueue(elementSeven);
        instance.enqueue(elementEight);
        instance.enqueue(elementNine);
        instance.enqueue(elementTen);
        instance.enqueue(elementEleven);
        instance.enqueue(elementTweleve);
        instance.enqueue(elementThirteen);
        instance.enqueue(elementFourteen);
        instance.enqueue(elementFifteen);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 10);

        result = instance.dequeue();
        assertEquals(elementSix, result);

        result = instance.dequeue();
        assertEquals(elementSeven, result);

        result = instance.dequeue();
        assertEquals(elementEight, result);

        result = instance.dequeue();
        assertEquals(elementNine, result);

        result = instance.dequeue();
        assertEquals(elementTen, result);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 5);

        result = instance.dequeue();
        assertEquals(elementEleven, result);

        result = instance.dequeue();
        assertEquals(elementTweleve, result);

        result = instance.dequeue();
        assertEquals(elementThirteen, result);

        result = instance.dequeue();
        assertEquals(elementFourteen, result);

        result = instance.dequeue();
        assertEquals(elementFifteen, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        result = instance.dequeue();
        assertEquals(null, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        instance.enqueue(elementSixteen);

        result = instance.dequeue();
        assertEquals(elementSixteen, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

    }

    @Test
    public void testWrapShrinkerC() {

        Queue<String> instance = new QueueArrayImpl(5);

        String elementOne = "One";
        String elementTwo = "Two";
        String elementThree = "Three";
        String elementFour = "Four";
        String elementFive = "Five";

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);
        instance.enqueue(elementFour);
        instance.enqueue(elementFive);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 5);

        String result = instance.dequeue();
        assertEquals(elementOne, result);

        result = instance.dequeue();
        assertEquals(elementTwo, result);

        result = instance.dequeue();
        assertEquals(elementThree, result);

        result = instance.dequeue();
        assertEquals(elementFour, result);

        result = instance.dequeue();
        assertEquals(elementFive, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        List<String> testList = new ArrayList();

        for (int i = 6; i < 200; i++) {
            String testString = new String();
            testList.add(testString);
            instance.enqueue(testString);
        }

        String lastTestString = new String();
        instance.enqueue(lastTestString);

        int expectedSize = 201 - 6;

        for (String testListItem : testList) {
            String secondPassResult = instance.dequeue();
            expectedSize--;
            assertEquals(testListItem, secondPassResult);
            int resultSize = instance.size();
            assertEquals(expectedSize, resultSize);
            assertEquals(instance.isEmpty(), false);
        }

        String thirdPassResult = instance.dequeue();
        expectedSize--;
        assertEquals(lastTestString, thirdPassResult);
        int resultSize = instance.size();
        assertEquals(expectedSize, resultSize);
        assertEquals(0, resultSize);
        assertEquals(instance.isEmpty(), true);

        result = instance.dequeue();
        assertEquals(null, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

    }

    @Test
    public void testWrapShrinkerD() {
        // Same as C only without the unique constructor.

        String elementOne = "One";
        String elementTwo = "Two";
        String elementThree = "Three";
        String elementFour = "Four";
        String elementFive = "Five";

        instance.enqueue(elementOne);
        instance.enqueue(elementTwo);
        instance.enqueue(elementThree);
        instance.enqueue(elementFour);
        instance.enqueue(elementFive);

        assertEquals(instance.isEmpty(), false);
        assertEquals(instance.size(), 5);

        String result = instance.dequeue();
        assertEquals(elementOne, result);

        result = instance.dequeue();
        assertEquals(elementTwo, result);

        result = instance.dequeue();
        assertEquals(elementThree, result);

        result = instance.dequeue();
        assertEquals(elementFour, result);

        result = instance.dequeue();
        assertEquals(elementFive, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

        List<String> testList = new ArrayList();

        for (int i = 6; i < 200; i++) {
            String testString = new String();
            testList.add(testString);
            instance.enqueue(testString);
        }

        String lastTestString = new String();
        instance.enqueue(lastTestString);

        int expectedSize = 201 - 6;

        for (String testListItem : testList) {
            String secondPassResult = instance.dequeue();
            expectedSize--;
            assertEquals(testListItem, secondPassResult);
            int resultSize = instance.size();
            assertEquals(expectedSize, resultSize);
            assertEquals(instance.isEmpty(), false);
        }

        String thirdPassResult = instance.dequeue();
        expectedSize--;
        assertEquals(lastTestString, thirdPassResult);
        int resultSize = instance.size();
        assertEquals(expectedSize, resultSize);
        assertEquals(0, resultSize);
        assertEquals(instance.isEmpty(), true);

        result = instance.dequeue();
        assertEquals(null, result);

        assertEquals(instance.isEmpty(), true);
        assertEquals(instance.size(), 0);

    }

    @Test
    public void testQueueRandom() {
        // Random enqueue and dequeue
        System.out.println("Random Queue Test");

        List<String> testList = new ArrayList();
        java.util.Random random = new java.util.Random();
        int expectedSize = 0;
        int highestTry = 0;

        for (int i = 0; i < 10000000; i++) {

            if (random.nextBoolean()) {

                String testString = String.valueOf(i);
                testList.add(testString);
                instance.enqueue(testString);
                expectedSize++;

                assertEquals(expectedSize, instance.size());
                if (expectedSize > highestTry) {
                    highestTry = expectedSize;
                }

            } else {

                String testString = instance.dequeue();
                expectedSize--;
                if (expectedSize < 0) {
                    expectedSize = 0;
                    assertEquals(instance.isEmpty(), true);
                }

                String expectedString;
                if (testList.isEmpty()) {
                    expectedString = null;
                } else {
                    expectedString = testList.get(0);
                }

                assertEquals(expectedString, testString);

                if (!testList.isEmpty()) {
                    testList.remove(0);
                }
                assertEquals(expectedSize, instance.size());
            }
        }
        System.out.println("Highest Random Size: " + highestTry);
    }
}
