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
public class MakePiTest {
    
    public MakePiTest() {
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
    public void testMakePi(){
        
        MakePi makePi = new MakePi();
        
        int[] piArray = makePi.makePi(3);
        
        int[] testArray = {3,1,4};
        
        Assert.assertArrayEquals(testArray, piArray);
        
    }
    
}
