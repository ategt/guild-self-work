/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.aop;

import com.mycompany.flooringmastery.controller.FlooringMasteryController;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.utilities.DummyFileIOImplementation;
import com.mycompany.flooringmastery.utilities.GenericMapFileIO;
import java.io.File;
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
public class TimingAspectTest {

    public TimingAspectTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        
        File dummyFile = new File("fakeFile.file");

        ApplicationContext ctx = new ClassPathXmlApplicationContext("testTimingAspectContext.xml");
        GenericMapFileIO<Product> fileIo = ctx.getBean("dummyTimingTester", DummyFileIOImplementation.class);
        
        //fileIo.decode(dummyFile);

    }

}
