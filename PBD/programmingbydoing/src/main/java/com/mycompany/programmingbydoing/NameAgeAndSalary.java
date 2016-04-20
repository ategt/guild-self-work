/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class NameAgeAndSalary {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Hello.  What is your name?");
        String userName = keyboard.next();
        
        
        System.out.println();
        System.out.println("Hi, " + userName + "! How old are you?");
        int userAge = keyboard.nextInt();
        
        System.out.println();
        System.out.println("So you're " + userAge + ", eh?  That's not old at all!");
        System.out.println("How much do you make, " + userName + "?");
        float userSalary = keyboard.nextFloat();
        
        System.out.println();
        System.out.println(userSalary + "! I hope that's per hour and not per year! LOL!");
       
    }
}
