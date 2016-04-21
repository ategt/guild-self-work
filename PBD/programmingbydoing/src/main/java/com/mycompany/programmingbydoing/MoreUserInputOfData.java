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
public class MoreUserInputOfData {
    public static void main(String[] args){
        
        String firstName;
        String lastName;
        int grade;
        int studentId;
        String loginName;
        float gradePointAverage;
                       
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Please enter the following information so I can sel it for a profit!\n");
        
        System.out.print("First name:");
        firstName = keyboard.nextLine();
        
        System.out.print("Last name:");
        lastName = keyboard.nextLine();
        
        System.out.print("Grade (9-12):");
        grade = keyboard.nextInt();
        
        System.out.print("Student ID:");
        studentId = keyboard.nextInt();
        
        System.out.print("Login:");
        loginName = keyboard.next();
        
        System.out.print("GPA (0.0-4.0):");
        gradePointAverage = keyboard.nextFloat();
        
        
        System.out.println("");
        System.out.println("\tLogin:\t" + loginName);
        System.out.println("\tID:\t" + studentId);
        System.out.println("\tName:\t" + lastName + ", " + firstName);
        System.out.println("\tGPA:\t" + gradePointAverage);
        System.out.println("\tGrade:\t" + grade);
        
        
    }
}
