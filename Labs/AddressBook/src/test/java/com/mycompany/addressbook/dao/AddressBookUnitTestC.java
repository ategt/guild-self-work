package com.mycompany.addressbook.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.mycompany.addressbook.controller.AddressBookController;
//import com.mycompany.addressbook.dao.AddressBookDao;
//import com.mycompany.addressbook.dto.Address;
import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddressBookUnitTestC {
//    
//    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//    
//    AddressBookDao addressDao = (AddressBookDao) ctx.getBean("addressDao");
//            
//    
//    
//
//    public AddressBookUnitTestC() {
//    }
//
//    @Before
//    public void setUp() {
//        
//         try {
//            PrintWriter out = new PrintWriter(new FileWriter("addressbookTest.txt"));
//            
//            for(Address myAddress : address){
//                final String TOKEN = "::";
//                out.print(myAddress.getId());
//                out.print(TOKEN);
//                
//                out.print(myAddress.getFirstName() + TOKEN + myAddress.getLastName());
//                out.print(TOKEN);
//                
//                out.println( myAddress.getState() + TOKEN + myAddress.getCity() + TOKEN 
//                        + myAddress.getStreetNumber() + TOKEN + myAddress.getStreetName() + TOKEN + myAddress.getZip());
//                 
//            }
//            out.flush();
//            out.close();
//       } catch (IOException ex) {
//            Logger.getLogger(AddressBookDao.class.getName()).log(Level.SEVERE, null, ex);
//        
//      
//        
//        } 
//    }
//
//    @After
//    public void tearDown() {
//         try {
//            Scanner sc = new Scanner(new BufferedReader(new FileReader("addressbookTest.txt")));
//        
//            
//             
//            } catch (FileNotFoundException ex) {
//            Logger.getLogger(AddressBookDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    //AddressBookDao dao = new AddressBookDao();
//    Address dto = new Address();
////    AddressBookController controller = new AddressBookController();
//
//    List<Address> address = new ArrayList();
//
//    @Test
//    public void add() {
//
//        dto.setFirstName("Chris");
//        dto.setLastName("Stevens");
//        dto.setCity("Akron");
//        dto.setStreetNumber("1020230");
//        dto.setZip("44789");
//
//        
//        String firstName = dto.getFirstName();
//        String lastName = dto.getLastName();
//        String city = dto.getCity();
//        String streetNumber = dto.getStreetNumber();
//        String zip = dto.getZip();
//        
//        Assert.assertEquals("Chris", firstName);
//        Assert.assertEquals("Stevens", lastName);
//        Assert.assertEquals("Akron", city);
//        Assert.assertEquals("1020230", streetNumber);
//        Assert.assertEquals("44789", zip);
//
//        
////        Address firstName = dao.getAddressByFirstName(create.getFirstName());
////        Address lastName = dao.getAddressByLastName(create.getLastName());
//    }
//        
//        
//       
//    
//    
//   
//  
//        
//        
//    
//    @Test
//    public void getAddressByLastName(){
//        
//        boolean lastName = true;
//        
//      
//        String lastN = "Stevens";
//        
//        
//        dto.setLastName(lastN);
//        
//        
//        String name = dto.getLastName();
//        
//       
//        Assert.assertEquals(name, "Stevens");
//        
//    }

}
