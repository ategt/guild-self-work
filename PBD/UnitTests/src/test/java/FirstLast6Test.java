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
public class FirstLast6Test {
    
    public FirstLast6Test() {
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
    public void testFirstLast6(){
        
        FirstLast6 fl6 = new FirstLast6();
        
        int[] testArray = {1,2,6};
        
        boolean result = fl6.firstLast6(testArray);
        
        Assert.assertEquals(result, true);

    }
    @Test
    public void testFirstLast6a(){
        FirstLast6 fl6 = new FirstLast6();
        
        int[] testArray = {6,1,2,3};
        
        boolean result = fl6.firstLast6(testArray);
        
        Assert.assertEquals(result, true);

        
    }
    @Test
    public void testFirstLast6b(){
        FirstLast6 fl6 = new FirstLast6();
        
        int[] testArray = {13,6,1,2,3};
        
        boolean result = fl6.firstLast6(testArray);
        
        Assert.assertEquals(result, false);

        
    }
}
