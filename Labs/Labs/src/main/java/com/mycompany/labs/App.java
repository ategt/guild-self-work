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

        int input = consoleIO.getAbstractUserInput("Enter something here.", 1).intValue();

        System.out.println("Result: " + input);

//        LuckySevens ls = new LuckySevens();
//        ls.run();  // I do not have a run method yet.

    }
}
