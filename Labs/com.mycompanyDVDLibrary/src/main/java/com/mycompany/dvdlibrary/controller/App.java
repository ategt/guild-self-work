/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        DvdLibraryController dvdLibraryController = (DvdLibraryController) ctx.getBean("dvdLibraryController", DvdLibraryController.class);

        dvdLibraryController.run();
        
        //  public DvdLibraryController(SimpleDateFormat dateFormat, ConsoleIO consoleIo, DvdLibrary dvdLibrary, NoteDaoImplementation noteDao) {
        //new DvdLibraryController().run();
    }
}
