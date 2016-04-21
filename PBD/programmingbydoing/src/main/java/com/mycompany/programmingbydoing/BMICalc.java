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
public class BMICalc {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        //  First part of PBD-20
//        System.out.print("Your height in m: ");
//        float height = keyboard.nextFloat();
//        
//        System.out.print("Your weight in kg: ");
//        float weight = keyboard.nextFloat();
//        

        // Second part of PBD-20
//        System.out.println();
//        System.out.print("Your height in inches: ");
//        float imperialHeight = keyboard.nextFloat();
//        
//        System.out.print("Your weight in pounds: ");
//        float imperialWeight = keyboard.nextFloat();
//        

        // Third part of PBD-20
        System.out.print("Your height (feet only): ");
        float imperialHeightInFeet = keyboard.nextFloat();

        System.out.print("Your height (inches only): ");
        float imperialHeightInInches = keyboard.nextFloat();

        System.out.print("Your weight in pounds: ");
        float imperialWeight = keyboard.nextFloat();

        float imperialHeight = ( imperialHeightInFeet * 12 ) + imperialHeightInInches;
        
        float height = imperialHeight / 39.370f;
        float weight = imperialWeight / 2.2f;

        System.out.println("Your BMI is " + weight / (height * height));

    }

}
