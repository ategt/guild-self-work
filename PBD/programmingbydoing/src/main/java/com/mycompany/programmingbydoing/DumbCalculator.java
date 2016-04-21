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
public class DumbCalculator {
    public static void main(String[] args){
        float firstInput;
        float secondInput;
        float thirdInput;
        float result;
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("What is your first number? ");
        firstInput = keyboard.nextFloat();
        
        System.out.print("What is your second number? ");
        secondInput = keyboard.nextFloat();

        System.out.print("What is your third number? ");
        thirdInput = keyboard.nextFloat();
        
        result = ( firstInput + secondInput + thirdInput ) / 2;
        
        System.out.println("\n( " + firstInput + " + " + secondInput + " + " + thirdInput + " ) / 2 is... " + result);

    }
        
}
