/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.utilities;

import java.io.*;

/**
 *
 * @author apprentice
 */
public class OrderFilter implements FileFilter {

    private final String extension = "txt";

    private final String orderPrefix = "orders_";

    @Override
    public boolean accept(File file) {
//        
//                String fileName = file.getName();
//        String lowerCaseFileName = fileName.toLowerCase();
//        
//        boolean containsExtension = false;
//        
//        if (lowerCaseFileName.endsWith(extension)){
//            System.out.println("contains extension");
//            containsExtension = true;
//        }else{
//            System.out.println("does not contain extension");
//        }
//        
//        boolean containsPrefix = false;
//        
//        if (lowerCaseFileName.startsWith(orderPrefix)){
//            System.out.println("contains Prefix");
//            containsPrefix = true;
//        }else{
//            System.out.println("does not contain prefix");    
//        }
//        
//        System.out.println("File Name: " + fileName + " evaluates as " + ( containsExtension && containsPrefix));
//        
        //return ( containsExtension && containsPrefix);

        
        return (file.getName().toLowerCase().endsWith(extension) && file.getName().toLowerCase().startsWith(orderPrefix));
    }
}
