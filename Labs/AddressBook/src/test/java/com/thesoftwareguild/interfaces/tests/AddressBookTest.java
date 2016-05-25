/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.interfaces.tests;

import com.thesoftwareguild.interfaces.dao.AddressBookDao;
//import com.thesoftwareguild.interfaces.dao.AddressBookDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.thesoftwareguild.interfaces.dto.Address;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class AddressBookTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    AddressBookDao addressDao = ctx.getBean("addressDao", AddressBookDao.class);
    Address address = new Address();

    @Before
    public void setUp() {
        address.setId(100);
        address.setFirstName("Chris");
        address.setLastName("Stalder");
        address.setStreetNumber("614");
        address.setStreetName("East 4th street");
        address.setCity("Dover");
        address.setState("OH");
        address.setZip("44622");
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
    public void testCreate() {

        Address result = addressDao.create(address);

        Assert.assertTrue(addressDao.list().contains(result));
    }

    @Test
    public void testGet() {
        Address result = addressDao.get(100);

        Assert.assertTrue("Stalder".equals(result.getLastName()));
    }

    @Test
    public void testUpdate() {

        address.setFirstName("Craig");
        addressDao.update(address);
        Assert.assertEquals("Craig", address.getFirstName());
    }

    @Test
    public void testDelete() {

        int currentListSize = addressDao.list().size();
        addressDao.delete(100);
        int newListSize = addressDao.list().size();

        Assert.assertTrue(newListSize == (currentListSize - 1));
    }
}
