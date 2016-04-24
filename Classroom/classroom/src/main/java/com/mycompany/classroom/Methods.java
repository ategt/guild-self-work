/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroom;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class Methods {

    public static void main(String[] args) {
//        helloWorld();
//
//        double pi = calculatePi();
//        System.out.println(pi);
        
        int operand1 = getUserInputInteger("Enter the first number to be added.");
        int operand2 = getUserInputInteger("Enter the second number to be added.");
        
        System.out.println(operand1 + operand2);

    }

    public static void helloWorld() {
        System.out.println("Hello World");

    }

    public static double calculatePi() {
        return 3.145;
    }

    public static void doNothing(int silly) {

    }

    public static int add1And1() {
        return 1 + 1;
    }

    public static int add1And2() {
        return 1 + 2;
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static int getUserInputInteger(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);

        String userInput = sc.nextLine();

        int input = Integer.parseInt(userInput);

        return input;
    }

}
