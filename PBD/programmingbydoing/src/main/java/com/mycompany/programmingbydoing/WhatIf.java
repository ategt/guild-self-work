/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

/**
 *
 * @author apprentice
 */
public class WhatIf {

    public static void main(String[] args) {

        int people = 20;
        int cats = people;
        int dogs = 15;

        if (people < cats) {
            System.out.println("Too many cats! The world is doomed!");
        }

        if (people > cats) {
            System.out.println("Not many cats! The world is saved!");
        }

        if (people < dogs) {
            System.out.println("The world is drooled on!");
        }

        if (people > dogs) {
            System.out.println("The world is dry!");
        }

        dogs += 5;

        if (people >= dogs) {
            System.out.println("People are greater than or equal to dogs.");
        }

        if (people <= dogs) {
            System.out.println("People are less than or equal to dogs.");
        }

        if (people == dogs) {
            System.out.println("People are dogs.");
        }
    }
}
