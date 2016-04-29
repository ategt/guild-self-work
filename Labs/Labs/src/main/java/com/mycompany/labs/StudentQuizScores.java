/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StudentQuizScores {

    java.util.HashMap<String, java.util.ArrayList<Integer>> scoreMap = new java.util.HashMap<>();
    //java.util.ArrayList<Integer> scoreList = null;

    ConsoleIO consoleIo = new ConsoleIO();

    public StudentQuizScores() {
        loadStudentData();
    }

    public final void loadStudentData() {
        scoreMap = Students.loadStudentData();

    }

    public void run() {
        boolean keepRunning = true;

        while (keepRunning) {

            String menuString = "=Student Quiz Scores Main Menu=\n"
                    + "1. List Students\n"
                    + "2. Add Students\n"
                    + "3. Remove Students\n"
                    + "4. List Scores For A Student\n"
                    + "5. View Average\n"
                    + "6. View Class Average\n"
                    + "7. Find High Score\n"
                    + "8. Find Low Score\n"
                    + "9. Rename Student\n"
                    + "0. Exit\n";

            //Print menu
            //  consoleIo.printStringToConsole(menuString);
            //ask for input
            int input = consoleIo.getUserIntInputRange(menuString, 0, 9);

            switch (input) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    removeStudent();
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
                    renameStudent();
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    consoleIo.printStringToConsole("If you are see this message, something is wrong\nPlease contact the vender.");
            }

        }

    }

    public java.util.HashMap<Integer, String> listStudents() {
        Set<String> keySet = scoreMap.keySet();
        java.util.HashMap<Integer, String> studentNumberMap = new java.util.HashMap<>();

        String studentList = "Studen Names:\n";
        int counter = 0;

        for (String key : keySet) {
            // Integer value = scoreMap.get(key);
            counter++;
            studentList += counter + ") " + key + "\n";
            studentNumberMap.put(counter, key);
        }

        consoleIo.printStringToConsole(studentList);
        return studentNumberMap;
    }

    public void addStudent() {
        String studentName = "";
        boolean containsThatName = true;
        while (containsThatName) {
            //if (!containsThatName) {
            studentName = consoleIo.getUserStringInput("Please Enter A Students Name:");

            if (studentName.equals("1")) {
                containsThatName = false;
                //studentName = "";
            } else {
                containsThatName = scoreMap.containsKey(studentName);

                if (containsThatName) {
                    listStudents();
                    consoleIo.printStringToConsole("The Name You Have Entered is Already Taken.\nYou may choose another name or enter '1' to return to the main menu.");
                }
            }

        }

        if (!studentName.equals("1")) {
            //java.util.ArrayList<Integer> scoreList = scoreMap.get(studentName);
            java.util.ArrayList<Integer> scoreList = new java.util.ArrayList<>();

            if (scoreList != null) {
                boolean keepScoreing = true;
                while (keepScoreing) {
                    String scoreInput = consoleIo.getUserStringInput("Please Enter A Quiz Score To Continue or Any Letter to Exit");
                    boolean goodScore = false;
                    int scoreInt = 0;

                    try {
                        scoreInt = Integer.parseInt(scoreInput);
                        goodScore = true;
                    } catch (NumberFormatException numFormEx) {
                        // Exit code here.
                    }

                    if (goodScore) {
                        scoreList.add(scoreInt);
                    } else {
                        keepScoreing = false;
                        scoreMap.put(studentName, scoreList);
                    }
                }

            } else {
                consoleIo.printStringToConsole("Student Name \"" + studentName + "\" could not be found.");
            }
        }
    }

    public void renameStudent() {
        String studentName = askForNameOrNumber("Please Enter A Student's Name Or Number To Rename:");

        String newName = consoleIo.getUserStringInput("What would you like \"" + studentName + "\" to be changed to?");
        java.util.ArrayList<Integer> valueList = scoreMap.remove(studentName);
        scoreMap.put(newName, valueList);

    }

    public void removeStudent() {

        String studentName = askForNameOrNumber("Please Enter A Student's Name Or Number To Remove:");

        String confirm = consoleIo.getUserStringInput("You Are About To Remove \"" + studentName + "\" \n Please Enter \"Y\" to confirm. ");

        if (confirm.equals("Y")) {
            java.util.ArrayList<Integer> valueList = scoreMap.remove(studentName);

            if (valueList != null) {
                consoleIo.printStringToConsole("That name was succesfully removed.");

            } else {
                consoleIo.printStringToConsole("Student Name \"" + studentName + "\" could not be found.");

            }
        } else {
            consoleIo.printStringToConsole("Removal Aborted, No Action Has Been Performed.");
        }
    }

    public String askForNameOrNumber(String prompt) {
        java.util.HashMap<Integer, String> studentNumberMap = listStudents();
        String studentName = consoleIo.getUserStringInput(prompt);
        boolean isNumber = false;
        int studentNumber = 0;
        try {
            studentNumber = Integer.parseInt(studentName);
            isNumber = true;
        } catch (NumberFormatException numForEx) {

        }
        if (isNumber == true) {
            studentName = studentNumberMap.get(studentNumber);
        }
        return studentName;
    }

    public void listScores() {

        java.util.HashMap<Integer, String> studentNumberMap = listStudents();

        String studentName = consoleIo.getUserStringInput("\nPlease Enter A Student's Name or Number:");
        boolean isNumber = false;
        int studentNumber = 0;

        try {
            studentNumber = Integer.parseInt(studentName);
            isNumber = true;
        } catch (NumberFormatException numForEx) {

        }

        if (isNumber == true) {
            studentName = studentNumberMap.get(studentNumber);
        }

        java.util.ArrayList<Integer> scoreList = scoreMap.get(studentName);
        //java.util.ArrayList<Integer> scoreList = new java.util.ArrayList<>();

        if (scoreList != null) {

            int scoreCounter = 0;
            String scoreString = "Scores for " + studentName + ":\n";
            for (Integer score : scoreList) {
                scoreString += (++scoreCounter) + ") " + score + "\n";
            }

            consoleIo.printStringToConsole(scoreString);

        } else {
            consoleIo.printStringToConsole("Student Name \"" + studentName + "\" could not be found.");
        }

    }

    public void viewAverage() {

        String studentName = askForNameOrNumber("Please Enter A Student's Name:");

        java.util.ArrayList<Integer> scoreList = scoreMap.get(studentName);
        //java.util.ArrayList<Integer> scoreList = new java.util.ArrayList<>();
        
        if (scoreList != null) {

            int averageScore = arrayListAverager(scoreList);
            consoleIo.printStringToConsole("Average Quiz Score For " + studentName + ":\n" + averageScore + " points");

        } else {
            consoleIo.printStringToConsole("Student Name \"" + studentName + "\" could not be found.");
        }

    }

    public int arrayListAverager(ArrayList<Integer> scoreList) {
        int scoreCounter = 0;
        int totalPoints = 0;
        for (Integer score : scoreList) {
            totalPoints += score;
            scoreCounter++;
        }
        int averageScore = totalPoints / scoreCounter;
        return averageScore;
    }

    public void viewClassAverage() {
        Set<String> keySet = scoreMap.keySet();
        java.util.ArrayList<Integer> totalClassScore = new java.util.ArrayList<>();

        for (String key : keySet) {
            totalClassScore.addAll(scoreMap.get(key));
        }

        int classAverage = arrayListAverager(totalClassScore);

        consoleIo.printStringToConsole("The Class Average is " + classAverage + " points.");

    }

    public void fingLowestScore() {

        Set<String> keySet = scoreMap.keySet();
        //java.util.ArrayList<Integer> totalClassScore = new java.util.ArrayList<>();

        java.util.ArrayList<String> bottomStudents = new java.util.ArrayList<>();

        java.util.HashMap<String, Integer> bottomMap = new java.util.HashMap<>();

        Integer bottomScore = determineTheLowestScore(keySet);

        determineWhoHasThatScore(keySet, bottomScore, bottomStudents);

        countOccurancesOfScore(bottomStudents, bottomScore, bottomMap);

        String classLeaders = "The highest score was: " + bottomScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (String student : bottomStudents) {
            classLeaders += student + " - \t" + bottomMap.get(student) + "\n";
        }

        consoleIo.printStringToConsole(classLeaders);

    }

    public void findHighestScore() {

        Set<String> keySet = scoreMap.keySet();
        java.util.ArrayList<Integer> totalClassScore = new java.util.ArrayList<>();

        java.util.ArrayList<String> topStudents = new java.util.ArrayList<>();

        java.util.HashMap<String, Integer> topMap = new java.util.HashMap<>();

        Integer highestScore = determineTheHighestScore(keySet);

        determineWhoHasThatScore(keySet, highestScore, topStudents);

        countOccurancesOfScore(topStudents, highestScore, topMap);

        String classLeaders = "The highest score was: " + highestScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (String student : topStudents) {
            classLeaders += student + " - \t" + topMap.get(student) + "\n";
        }

        consoleIo.printStringToConsole(classLeaders);

    }

    public void countOccurancesOfScore(ArrayList<String> topStudents, Integer highestScore, HashMap<String, Integer> topMap) {
        for (String studentName : topStudents) {
            int occuranceCounter = 0;
            java.util.ArrayList<Integer> tempScore = scoreMap.get(studentName);

            for (Integer score : tempScore) {
                if (score == highestScore) {
                    occuranceCounter++;
                }
            }

            topMap.put(studentName, occuranceCounter);
        }
    }

    public void determineWhoHasThatScore(Set<String> keySet, Integer highestScore, ArrayList<String> topStudents) {
        // Iterate through Students
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = scoreMap.get(key);
            boolean isTiedForLeader = false;

            //java.util.ArrayList<Integer> topScores = new java.util.ArrayList<>();
            // Iterate through scores for that student
            for (Integer score : tempScore) {
                if (score == highestScore) {
                    //highestScore = score;
                    isTiedForLeader = true;
                    //
                }
            }

            if (isTiedForLeader) {
                topStudents.add(key);
            }
        }
    }

    public Integer determineTheHighestScore(Set<String> keySet) {
        // Find the highest Score
        Integer highestScore = 0;
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = scoreMap.get(key);

            //java.util.ArrayList<Integer> topScores = new java.util.ArrayList<>();
            for (Integer score : tempScore) {
                if (score > highestScore) {
                    highestScore = score;
                }
            }
        }
        return highestScore;
    }

    public Integer determineTheLowestScore(Set<String> keySet) {
        // Find the lowest Score
        Integer lowestScore = 0;
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = scoreMap.get(key);

            for (Integer score : tempScore) {
                if (score < lowestScore) {
                    lowestScore = score;
                }
            }
        }
        return lowestScore;
    }

}
