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
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Your height in m: ");
        float height = keyboard.nextFloat();
        
        System.out.print("Your weight in kg: ");
        float weight = keyboard.nextFloat();
        
        System.out.println();
        
        System.out.println("Your BMI is " + weight / (height * height));
        
        
    }
    
}
