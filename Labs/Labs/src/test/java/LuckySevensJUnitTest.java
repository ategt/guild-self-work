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
public class LuckySevensJUnitTest {

    public LuckySevensJUnitTest() {
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
    public void testLuckySevens() {

        com.mycompany.labs.LuckySevens ls = new com.mycompany.labs.LuckySevens();

        boolean expected = false;
        boolean result = ls.isAWinner(5);

        Assert.assertEquals(result, expected);

        Assert.assertEquals(ls.isAWinner(7), true);

        Assert.assertEquals(ls.isAWinner(1), false);

        Assert.assertEquals(ls.isAWinner(12), false);

        
        // Test that the random number is in a range.
        int minValue = 1;
        int maxValue = 6;

        for (int x = 0; x < 20; x++) {
            int rollOneDieValue = ls.rollOneDie();

            Assert.assertTrue(rollOneDieValue <= 6 && rollOneDieValue >= 1);
        }

        
        // Test that the random number is between a range of 1 and 12.
        minValue = 1;
        maxValue = 12;

        for (int x = 0; x < 20; x++) {
            int rollOneDieValue = ls.rollDice();

            Assert.assertTrue(rollOneDieValue <= 12 && rollOneDieValue >= 1);
        }

        
        int valueReturned = ls.adjustCurrentBalance(7, 100);
        
        Assert.assertEquals(valueReturned, 104);

        valueReturned = ls.adjustCurrentBalance(3, 100);
        
        Assert.assertEquals(valueReturned, 99);

    }
}
