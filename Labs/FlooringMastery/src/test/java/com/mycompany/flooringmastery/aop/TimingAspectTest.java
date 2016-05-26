
package com.mycompany.flooringmastery.aop;

import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.utilities.GenericMapFileIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

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

//    @Test
//    public void testSomeMethod() {
//        
//            File dummyFile = new File("fakeFile.file");
//            List<Product> dummyList = new ArrayList();
//            ApplicationContext ctx = new ClassPathXmlApplicationContext("testTimingAspectContext.xml");
//            GenericMapFileIO<Product> fileIo = ctx.getBean("dummyTimingTester", GenericMapFileIO.class);
//            
//            System.out.println("Starting.");
//
//            fileIo.doNothing();
//            
//            System.out.println("Stopping.");
//                    
//    }

    @Test
    public void testEncode() {
        
            File dummyFile = new File("fakeFile.file");
            List<Product> dummyList = new ArrayList();
            ApplicationContext ctx = new ClassPathXmlApplicationContext("testTimingAspectContext.xml");
            GenericMapFileIO<Product> fileIo = ctx.getBean("dummyTimingTester", GenericMapFileIO.class);
            
            System.out.println("Starting.");

            fileIo.encode(dummyFile, dummyList);
            
            System.out.println("Stopping.");
    }

    @Test
    public void testDecode() {
        
            File dummyFile = new File("fakeFile.file");
            ApplicationContext ctx = new ClassPathXmlApplicationContext("testTimingAspectContext.xml");
            GenericMapFileIO<Product> fileIo = ctx.getBean("dummyTimingTester", GenericMapFileIO.class);
            
            System.out.println("Starting.");

            Map<String, Product> emptyMap = fileIo.decode(dummyFile);
            Assert.isNull(emptyMap);
            
            System.out.println("Stopping.");
    }

}
