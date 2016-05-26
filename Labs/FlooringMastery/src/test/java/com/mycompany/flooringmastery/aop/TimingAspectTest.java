
package com.mycompany.flooringmastery.aop;

import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.utilities.GenericMapFileIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        
       // try {
            File dummyFile = new File("fakeFile.file");
            List<Product> dummyList = new ArrayList();
            
            dummyList.add(new Product());
            dummyList.add(new Product());
            dummyList.add(new Product());
            dummyList.add(new Product());
 
            ApplicationContext ctx = new ClassPathXmlApplicationContext("testTimingAspectContext.xml");
            //GenericMapFileIO<Product> fileIo = ctx.getBean("dummyTimingTester", DummyFileIOImplementation.class);
            GenericMapFileIO<Product> fileIo = ctx.getBean("dummyTimingTester", GenericMapFileIO.class);
            //OrderDaoFileIO fileIo = ctx.getBean("dummyTimingTester", OrderDaoFileIO.class);
            //OrderDaoFileIO fileIo = ctx.getBean("dummyTimingTester", GenericMapFileIO.class);
            //OrderDaoFileIO<Object> fileIo = ctx.getBean("dummyTimingTester", GenericMapFileIO.class);
            
            System.out.println("Starting.");
            //fileIo.decode(dummyFile);
            //fileIo.encode(new PrintWriter(dummyFile), new java.util.ArrayList());
            fileIo.doNothing();
            fileIo.encode(dummyFile, dummyList);
            
            System.out.println("Stopping.");
            
            //fail("Just because.");
            //assertTrue(true);
//        } catch (FileNotFoundException ex) {
//            fail("This was not supposed to happen.");
//            Logger.getLogger(TimingAspectTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

}
