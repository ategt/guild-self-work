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
public class FlooringCalculator {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("What is the length of the floor? ");
        int floorLength = keyboard.nextInt();
        
        System.out.print("What is the width of the floor? ");
        int floorWidth = keyboard.nextInt();
        
        System.out.print("What is the cost per unit of flooring? ");
        float floorUnitCost = keyboard.nextFloat();
        
        float floorTotalCost = floorWidth * floorLength * floorUnitCost;
        
        System.out.println("The total cost of the flooring materials would be: " + floorTotalCost);
        
        //int incrementsOfLabor = (int) Math.ceil((floorWidth * floorLength) / 20 * 4);
        //int incrementsOfLabor = Double.intValue(Math.ceil((floorWidth * floorLength) / 20 * 4));
        
        int floorArea = floorWidth * floorLength;
        
        
        
        float incrementsOfLabor = (float) Math.ceil(floorArea / 20 * 4);
        
        
        
        float totalLaborCost = incrementsOfLabor * (86 / 4);
                
        System.out.println("This would be the labor cost of the floor: " + totalLaborCost);
        System.out.println("This would be the total cost of the floor : " + (totalLaborCost + floorTotalCost));
        
        
    }
}
