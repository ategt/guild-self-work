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
public class YourSchedule {
    public static void main(String[] args){
        String course1, course2, course3, course4, course5, course6, course7, course8;
        String teacher1, teacher2, teacher3, teacher4, teacher5, teacher6, teacher7, teacher8;
        
        int lineNumber = 1;
        
        course1 = "English III";
        course2 = "Precalculus";
        course3 = "Music Theory";
        course4 = "Biotechnology";
        course5 = "Principles of Technology I";
        course6 = "Latin II";
        course7 = "AP US History";
        course8 = "Business Computer Information Systems";
        
        teacher1 = "Ms. Lapan";
        teacher2 = "Mrs. Gideon";
        teacher3 = "Mr. Davis";
        teacher4 = "Ms. Palmer";
        teacher5 = "Ms. Garcia";
        teacher6 = "Mrs. Barnett";
        teacher7 = "Ms. Johannessen";
        teacher8 = "Mr. James";
        
        System.out.println("+--------------------------------------------------+");
        System.out.println("| " + lineNumber + " | " + course1 + " | " + teacher1 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course2 + " | " + teacher2 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course3 + " | " + teacher3 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course4 + " | " + teacher4 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course5 + " | " + teacher5 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course6 + " | " + teacher6 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course7 + " | " + teacher7 + " |");
        lineNumber++;
        System.out.println("| " + lineNumber + " | " + course8 + " | " + teacher8 + " |");
        lineNumber++;
        System.out.println("+--------------------------------------------------+");
    }
}