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
public class LittleQuiz {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        int questionsCorrect = 0;
        
        System.out.print("Are you ready for a quiz?  ");
        String areYouReady = keyboard.next();
        
        System.out.println("Okay, here it comes!\n");
        
        System.out.println("Q1) What is the capital of Alaska?");
        System.out.println("\t1) Melbourne");
        System.out.println("\t2) Anchorage");
        System.out.println("\t3) Juneau");
        System.out.println();
        
        int userInput1 = keyboard.nextInt();
        
        if ( userInput1 == 3 ){
            System.out.println("That's right!\n");
            questionsCorrect++;
        } else {
            System.out.println("Close, it was Juneau.\n");
        }
        
        System.out.println("Q2) Can you store the value of \"cat\" in a variable type int?");
        System.out.println("1) yes");
        System.out.println("2) no");
        System.out.println("");
        
        int userInput2 = keyboard.nextInt();
        
        if ( userInput2 == 2 ){
            System.out.println("That's right!\n");
            questionsCorrect++;
        } else {
            System.out.println("Sorry, \"cat\" is a string. ints can only store numbers.");
        }
        
        System.out.println("Q3) What is the result of 9+6/3?");
        System.out.println("\t1) 5");
        System.out.println("\t2) 11");
        System.out.println("\t3) 15/3");
        System.out.println("");
        
        int userInput3 = keyboard.nextInt();
        
        if ( userInput3 == 2 ){
            System.out.println("That's right!\n");
            questionsCorrect++;
        } else {
            System.out.println("Sorry, the answer was 11.");
        }
        
        System.out.println("\n\n");
        
        System.out.println("Overall, you got " + questionsCorrect + " out of 3 correct.");
        System.out.println("Thanks for playing.");
        
    }
}
