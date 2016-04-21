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
public class SpaceBoxing {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Please enter your current earth weight: ");
        float weight = keyboard.nextFloat();

        System.out.println("\nI have information for the following planets:");
        System.out.println("\t1. Venus\t2. Mars\t\t3. Jupiter");
        System.out.println("\t4. Saturn\t5. Uranus\t6. Neptune\n");

        System.out.print("Which planet are you visiting? ");
        int planetId = keyboard.nextInt();

        System.out.println();

        float relativeGravity = 1.0f;

        if (planetId == 1) {
            relativeGravity = .78f;
        } else if (planetId == 2) {
            relativeGravity = .39f;
        } else if (planetId == 3) {
            relativeGravity = 2.65f;
        } else if (planetId == 4) {
            relativeGravity = 1.17f;
        } else if (planetId == 5) {
            relativeGravity = 1.05f;
        } else if (planetId == 6) {
            relativeGravity = 1.23f;
        } else {
            System.out.println("I have no record for this planet");
        }

        float convertedWeight = weight * relativeGravity;

        System.out.println("Your weight would be " + convertedWeight + " pounds on that planet.");

    }
}

