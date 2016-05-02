/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.files;

//import com.sun.istack.internal.logging.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class Files {

    public static void main(String[] args) {

        writeFile();

        readFile();
    }

    public static void readFile() {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("fileName.txt")));
            
            while (sc.hasNextLine()){
                
                String currentLine = sc.nextLine();
                System.out.println(currentLine);

            }
            
            sc.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeFile() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("fileName.txt"));
            out.println("Test line here.");
            out.println("This is the second line.");
            out.println("This is line three.");

            // This line writes everything in the buffer to I/O.
            out.flush();
            
            // This line closes the file I/O.
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}
