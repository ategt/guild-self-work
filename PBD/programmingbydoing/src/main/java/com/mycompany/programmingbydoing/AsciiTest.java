/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

/**
 *
 * @author apprentice
 */
public class AsciiTest {

    public static void main(String[] args) {

        char spade = '\u2660';
        char diamond = '\u2666';
        char club = '\u2663';
        char heart = '\u2764';
        
        
        System.out.println("spade:" + spade);
        System.out.println("diamond:" + diamond);
        System.out.println("club:" + club);
        System.out.println("heart:" + heart);
        
        /*
        for (int x = 0; x < 512; x++) {
            char c = (char) x;
            System.out.println(x + ":" + c);
        }
        */
    }
}
