/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class DateTest {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = new java.util.Date();
        //java.util.Date date1;
        ConsoleIO consoleIo = new ConsoleIO();
        
        String datestring = consoleIo.getUserStringInput("Date Test?:");

        //String datestring=dateFormat.format(date);
        try {
            date1 = dateFormat.parse(datestring);
            System.out.print(date1);
        } catch (ParseException ex) {
            Logger.getLogger(DateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
