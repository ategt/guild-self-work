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
public class ConsoleIO {

    Scanner keyboard;

    public ConsoleIO() {
        this.keyboard = new Scanner(System.in);

    }
    
    

    public int getUserInput(String prompt) {
        int inputString = 0;
        //  Scanner keyboard = new Scanner(System.in);

        System.out.println(prompt);
        inputString = keyboard.next();

        return inputStr;
    }

    public int getUserIntInputRange(String prompt, int minValue, int maxValue) {
        int inputInt = 0;

        Scanner keyboard = new Scanner(System.in);

        System.out.println(prompt);

        inputInt = keyboard.nextInt();

        return inputInt;
    }

    public void printToConsole(String stringToPrint){
        System.out.println(stringToPrint);
    }
    
}
