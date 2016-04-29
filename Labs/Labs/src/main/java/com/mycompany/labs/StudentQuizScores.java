/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
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
        scoreMap = Student.loadStudentData();

    }

    public void run() {
        boolean keepRunning = true;

        while (keepRunning) {

            //Print menu
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
                    findLowestScore();
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
            studentName = consoleIo.getUserStringInput("Please Enter A Students Name:");

            if (studentName.equals("1")) {
                containsThatName = false;
            } else {
                containsThatName = scoreMap.containsKey(studentName);

                if (containsThatName) {
                    listStudents();
                    consoleIo.printStringToConsole("The Name You Have Entered is Already Taken.\nYou may choose another name or enter '1' to return to the main menu.");
                }
            }

        }

        if (!studentName.equals("1")) {
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

        String studentName = askForNameOrNumber("\nPlease Enter A Student's Name or Number:");

        java.util.ArrayList<Integer> scoreList = scoreMap.get(studentName);

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

            float averageScore = arrayListAverager(scoreList);
            consoleIo.printStringToConsole("Average Quiz Score For " + studentName + ":\n" + getRoundedFloat(averageScore) + " points");

        } else {
            consoleIo.printStringToConsole("Student Name \"" + studentName + "\" could not be found.");
        }

    }

    public float arrayListAverager(ArrayList<Integer> scoreList) {
        int scoreCounter = 0;
        int totalPoints = 0;
        for (Integer score : scoreList) {
            totalPoints += score;
            scoreCounter++;
        }
        float averageScore = totalPoints / scoreCounter;
        return averageScore;
    }

    public void viewClassAverage() {
        Set<String> keySet = scoreMap.keySet();
        java.util.ArrayList<Integer> totalClassScore = new java.util.ArrayList<>();

        for (String key : keySet) {
            totalClassScore.addAll(scoreMap.get(key));
        }

        float classAverage = arrayListAverager(totalClassScore);

        String classStringAverage = getRoundedFloat(classAverage);

        consoleIo.printStringToConsole("The Class Average is " + classStringAverage + " points.");

    }

    public String getRoundedFloat(float classAverage) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        String classStringAverage = df.format(classAverage);
        return classStringAverage;
    }

    public void findLowestScore() {

        Set<String> keySet = scoreMap.keySet();

        java.util.ArrayList<String> bottomStudents = new java.util.ArrayList<>();

        Integer bottomScore = determineTheLowestScore(keySet);

        determineWhoHasThatScore(keySet, bottomScore, bottomStudents);

        java.util.HashMap<String, Integer> bottomMap = countOccurancesOfScore(bottomStudents, bottomScore);

        String classLeaders = "The highest score was: " + bottomScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (String student : bottomStudents) {
            classLeaders += student + " - \t" + bottomMap.get(student) + "\n";
        }

        consoleIo.printStringToConsole(classLeaders);

    }

    public void findHighestScore() {

        Set<String> keySet = scoreMap.keySet();

        java.util.ArrayList<String> topStudents = new java.util.ArrayList<>();

        Integer highestScore = determineTheHighestScore(keySet);

        determineWhoHasThatScore(keySet, highestScore, topStudents);

        java.util.HashMap<String, Integer> topMap = countOccurancesOfScore(topStudents, highestScore);

        String classLeaders = "The highest score was: " + highestScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (String student : topStudents) {
            classLeaders += student + " - \t" + topMap.get(student) + "\n";
        }

        consoleIo.printStringToConsole(classLeaders);

    }

    public java.util.HashMap<String, Integer> countOccurancesOfScore(ArrayList<String> topStudents, Integer highestScore) {
        java.util.HashMap<String, Integer> bottomMap = new java.util.HashMap<>();

        for (String studentName : topStudents) {
            int occuranceCounter = 0;
            java.util.ArrayList<Integer> tempScore = scoreMap.get(studentName);

            for (Integer score : tempScore) {
                if (Objects.equals(score, highestScore)) {
                    occuranceCounter++;
                }
            }

            bottomMap.put(studentName, occuranceCounter);
        }

        return bottomMap;

    }

    public void determineWhoHasThatScore(Set<String> keySet, Integer highestScore, ArrayList<String> topStudents) {
        // Iterate through Student
        for (String key : keySet) {

            java.util.ArrayList<Integer> tempScore = scoreMap.get(key);
            boolean isTiedForLeader = false;

            for (Integer score : tempScore) {
                if (Objects.equals(score, highestScore)) {
                    isTiedForLeader = true;
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
