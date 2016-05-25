package com.mycompany.addressbook.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import address.dao.AddressDaoImpl;
//import com.thesoftwareguild.interfaces.dao.AddressDaoLamdaImpl;
//import address.dto.Address2;
//import com.thesoftwareguild.interfaces.dao.AddressBookDaoInterface;
import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;
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
public class AddressTest {
    
    public AddressTest() {
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
    public void lamtest() {
//        AddressDaoLamdaImpl im = new AddressDaoLamdaImpl();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //AddressBookDaoInterface im = ctx.getBean("addressDao", AddressBookDaoInterface.class);
        AddressBookDao im = (AddressBookDao) ctx.getBean("addressDao");
        
        //test get lnames
        List<Address> lastNames = im.searchByLastName("bennett");
        int size = lastNames.size();
        boolean arrayTest = true;
        if (size != 1) {
            arrayTest = false;
        }
        Assert.assertEquals(true, arrayTest);

        //test get
        Address test = im.get(1);
        boolean exists = true;
        if (test == null) {
            exists = false;
        }
        Assert.assertEquals(true, exists);
        
        

        List<Address> city = im.searchByCity("Tallmadge");
        if (city == null) {
            exists = false;
        }
        Assert.assertEquals(true, exists);
        
        //Test Address
        Address testUpdate = im.get(2);
        testUpdate.setCity("Miami");
        im.update(testUpdate);
        boolean update = true;
        if(!testUpdate.getCity().equals("Miami"))     {
            update = false;
        }
        Assert.assertEquals(true, update);

        
    }
}
