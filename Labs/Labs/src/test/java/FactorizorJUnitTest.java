/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.labs.Factorizer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class FactorizorJUnitTest {

    public FactorizorJUnitTest() {
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
    public void testFactorizer() {

        Factorizer factorizer = new Factorizer();

        boolean result = Factorizer.isNumberEvenlyDivisible(5, 3);

        Assert.assertEquals(result, false);

        //boolean result = Factorizer.isNumberEvenlyDivisible(5, 3);
        Assert.assertEquals(Factorizer.isNumberEvenlyDivisible(2, 2), true);

        Assert.assertEquals(Factorizer.isNumberEvenlyDivisible(10, 2), true);

        Assert.assertEquals(Factorizer.isNumberEvenlyDivisible(5, 2), false);

        Assert.assertEquals(Factorizer.isNumberEvenlyDivisible(100, 10), true);

        Assert.assertEquals(Factorizer.isPerfectNumber(55, 55), true);

        Assert.assertEquals(Factorizer.isPerfectNumber(55, 7), false);

        Assert.assertEquals(Factorizer.isPrimeNumber(2), true);
        Assert.assertEquals(Factorizer.isPrimeNumber(35), false);

    }

}
