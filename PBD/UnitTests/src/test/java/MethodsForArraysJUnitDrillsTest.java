/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class MethodsForArraysJUnitDrillsTest {

    public MethodsForArraysJUnitDrillsTest() {
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
    public void testCommonEnd3() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {1, 2, 3};
        int[] testArray2 = {7, 3};

        boolean result = methodsForArraysJUnitDrills.commonEnd(testArray1, testArray2);

        Assert.assertEquals(result, true);

    }

    @Test
    public void testCommonEnd1() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {1, 2, 3};
        int[] testArray2 = {7, 3, 2};

        boolean result = methodsForArraysJUnitDrills.commonEnd(testArray1, testArray2);

        Assert.assertEquals(result, false);

    }

    @Test
    public void testCommonEnd2() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {1, 2, 3};
        int[] testArray2 = {1, 3};

        boolean result = methodsForArraysJUnitDrills.commonEnd(testArray1, testArray2);

        Assert.assertEquals(result, true);

    }

    @Test
    public void testSum1() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray = {1, 2, 3};
        int expectedResult = 6;

        int result = methodsForArraysJUnitDrills.sum(testArray);

        Assert.assertEquals(result, expectedResult);

    }

    @Test
    public void testSum2() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray = {5, 11, 2};
        int expectedResult = 18;

        int result = methodsForArraysJUnitDrills.sum(testArray);

        Assert.assertEquals(result, expectedResult);

    }

    @Test
    public void testSum3() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray = {7, 0, 0};
        int expectedResult = 7;

        int result = methodsForArraysJUnitDrills.sum(testArray);

        Assert.assertEquals(result, expectedResult);

    }

    @Test
    public void testRotateLeft1() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray = {1, 2, 3};
        int[] expectedResult = {2, 3, 1};

        int[] result = methodsForArraysJUnitDrills.rotateLeft(testArray);

        Assert.assertArrayEquals(result, expectedResult);

    }

    @Test
    public void testRotateLeft2() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray = {5, 11, 9};
        int[] expectedResult = {11, 9, 5};

        int[] result = methodsForArraysJUnitDrills.rotateLeft(testArray);

        Assert.assertArrayEquals(result, expectedResult);

    }

    @Test
    public void testRotateLeft3() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray = {7, 0, 0};
        int[] expectedResult = {0, 0, 7};

        int[] result = methodsForArraysJUnitDrills.rotateLeft(testArray);

        Assert.assertArrayEquals(result, expectedResult);

    }

    @Test
    public void testMake2a() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {4, 5};
        int[] testArray2 = {1, 2, 3};

        int[] expectedResult = {4, 5};

        int[] result = methodsForArraysJUnitDrills.make2(testArray1, testArray2);

        Assert.assertArrayEquals(result, expectedResult);

    }

    @Test
    public void testMake2b() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {4};
        int[] testArray2 = {1, 2, 3};

        int[] expectedResult = {4, 1};

        int[] result = methodsForArraysJUnitDrills.make2(testArray1, testArray2);

        Assert.assertArrayEquals(result, expectedResult);

    }

    @Test
    public void testMake2c() {
        MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {};
        int[] testArray2 = {1, 2};

        int[] expectedResult = {1, 2};

        int[] result = methodsForArraysJUnitDrills.make2(testArray1, testArray2);

        Assert.assertArrayEquals(result, expectedResult);

    }

}
