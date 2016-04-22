/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class Factorizer {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        boolean isValidNumber = false;
        int factorizedNumber = 0;
        
        int primeNumberHolder = 0;
        int perfectNumberHolder = 0;
        
        while (!isValidNumber) {

            System.out.println("Please enter an integer to be factored.");
            factorizedNumber = keyboard.nextInt();
            
            if ( factorizedNumber > 0 ){
                isValidNumber = true;
            } else {
                isValidNumber = false;
                System.out.println("That input is not supported by this program.");
            }
        }
        
        for ( int result = 1 ; result <= factorizedNumber ; result++  ){
            System.out.println("Testing " + result);
            if ( factorizedNumber % result == 0 ){
                System.out.println(result);
                
                primeNumberHolder++;
                
                if ( result != factorizedNumber ) 
                    perfectNumberHolder += result;
                
            }
            
        }
        
        System.out.println("perfectNumberHolder: " + perfectNumberHolder);
        
        if ( perfectNumberHolder == factorizedNumber ){
            System.out.println(factorizedNumber + " is a perfect number.");
        } else {
            System.out.println(factorizedNumber + " is not a perfect number.");
        }

        System.out.println("primeNumberHolder: " + primeNumberHolder);
        
        if ( primeNumberHolder == 2 ){
            System.out.println(factorizedNumber + " is a prime number.");
        } else {
            System.out.println(factorizedNumber + " is not a prime number.");            
        }

    }
}
