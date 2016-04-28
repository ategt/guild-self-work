/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {

        ConsoleIO consoleIO = new ConsoleIO();
        for (int x = 0; x < 20; x++) {
            int input = consoleIO.getUserIntInputRange("Enter:", 3, 99);
            System.out.println("Result: " + input);
        }

    }
}
