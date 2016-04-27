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
public class SameFirstLastTest {

    public SameFirstLastTest() {
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
    public void testSameFirstLast1() {
        SameFirstLast sfl = new SameFirstLast();

        int[] testArray = {1, 2, 3};

        boolean result = sfl.sameFirstLast(testArray);

        Assert.assertEquals(result, false);

    }

    @Test
    public void testSameFirstLast2() {
        SameFirstLast sfl = new SameFirstLast();

        int[] testArray = {1, 2, 3, 1};

        boolean result = sfl.sameFirstLast(testArray);

        Assert.assertEquals(result, true);

    }

    @Test
    public void testSameFirstLast3() {
        SameFirstLast sfl = new SameFirstLast();

        int[] testArray = {1, 2, 1};

        boolean result = sfl.sameFirstLast(testArray);

        Assert.assertEquals(result, true);

    }

}
