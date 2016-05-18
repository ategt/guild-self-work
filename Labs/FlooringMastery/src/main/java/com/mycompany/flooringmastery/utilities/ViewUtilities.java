/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.utilities;

/**
 *
 * @author apprentice
 */
public class ViewUtilities {

    public String bordermaker(java.util.List<String> content, int width) {

        int length = content.size();

        int widest = content.stream()
                //.mapToInt(a -> a.length())
                .mapToInt(String::length)
                .max()
                .getAsInt();
        
        
        
        

    }

    public String makeSpaces(int length) {
        char[] charArray = new char[length];
        java.util.Arrays.fill(charArray, ' ');
        String str = new String(charArray);
        return str;
    }
}
