/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.labs.ConsoleIO;
import com.mycompany.labs.Factorizer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FactorizorJUnitTest {

    
 ApplicationContext ctx;
 
    public FactorizorJUnitTest() {
        
          ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        
        //ConsoleIO consoleIo = new ConsoleIO();
        //FlooringMasteryController flooringMasteryController = ctx.getBean(FlooringMasteryController.class);
        //flooringMasteryController.run();
        
        //ConsoleIO consoleIo = ctx.getBean("consoleIo", ConsoleIO.class);
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
    public void testFactorizer() {

        //Factorizer factorizer = new Factorizer();
        Factorizer factorizer = ctx.getBean("factorizer", Factorizer.class);

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
