/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StudentQuizScores {
    
    java.util.HashMap<String, java.util.ArrayList<Integer>> scoreMap = new java.util.HashMap<>();
    java.util.ArrayList<Integer> scoreList = null;
    
    
    ConsoleIO consoleIo = new ConsoleIO();

    public void run() {
        boolean keepRunning = true;

        while (keepRunning) {

            String menuString = "=Student Quiz Scores Main Menu="
                    + "1. List Students"
                    + "2. Add Students"
                    + "3. Remove Students"
                    + "4. List Scores For A Student"
                    + "5. View Average"
                    + "6. View Class Average"
                    + "7. Find High Score"
                    + "8. Find Low Score"
                    + "9. Exit";

            //Print menu
            //  consoleIo.printStringToConsole(menuString);
            //ask for input
            int input = consoleIo.getUserIntInputRange(menuString, 1, 9);

            switch (input) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    addStudents();
                    break;
                case 3:
                    removeStudents();
                    break;
                case 4:
                    listScores();
                    break;
                case 5:
                    viewAverage();
                    break;
                case 6:
                    viewClassAverage();
                    break;
                case 7:
                    findHighestScore();
                    break;
                case 8:
                    fingLowestScore();
                    break;
                case 9:
                    keepRunning = false;
                    break;
                default:

            }

        }

    }
    
    public void listStudents(){
                Set<String> keySet = scoreMap.keySet();

                String studentList = "Studen Names:\n";
                int counter = 0;
                        
        for (String key : keySet) {
           // Integer value = scoreMap.get(key);
           counter++;
           studentList = counter + ") " + key + "\n";

        }

           consoleIo.printStringToConsole(studentList);
        
    }

    public void addStudents(){
        
       String input = consoleIo.getUserStringInput("Please Enter A Students Name:");
        consoleIo.getUserIntInputRange(input, 0, 0)
        
    }
}