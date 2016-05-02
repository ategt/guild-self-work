/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.io.*;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class StudentQuizScores {

    java.util.List<Student> studentIdList = new java.util.ArrayList<>();
    java.util.Map<String, Student> nameToStudentMap = new java.util.HashMap<>();
    File file = new File("testScoreData.txt");

    ConsoleIO consoleIo = new ConsoleIO();

    public StudentQuizScores() {
        loadStudentData();
    }

    public void saveTextFile() {
        saveTextFile(studentIdList);
    }

    public void saveTextFile(java.util.List<Student> studentIdList) {

        java.io.BufferedWriter bufferedWriter = null;
        try {

            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Student student : studentIdList) {
                if (student != null) {

                    String dataForFiling = student.getStudentId() + "\t" + student.getStudentName() + "\t";
                    String scores = "[";
                    for (Integer score : student.getQuizScores()) {
                        scores += score + ",";
                    }
                    scores += "]";
                    scores = scores.replace(",]", "]");
                    dataForFiling += scores;

                    bufferedWriter.write(dataForFiling + "\n");

                }
            }

        } catch (IOException ex) {
            System.out.println("Something went wrong during the writing process.");
            ex.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                System.out.println("The writer failed to close!!");
            }
        }

    }

    public String readTextFile() {
        String returnString = "";
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = reader.readLine()) != null) {

                returnString += line + "\n";

            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong!");
            ex.printStackTrace();

        }
        return returnString;
    }

    public final void loadStudentData() {

        java.util.Map<String, java.util.List<Integer>> scoreMap = Student.loadStudentData();

        populateStudentIdMap(scoreMap);

    }

    public void populateStudentIdMap(java.util.Map<String, java.util.List<Integer>> scoreMap) {
        Set<String> keySet = scoreMap.keySet();

        for (int x = 0; x < 205; x++) {
            Student student = null;
            studentIdList.add(student);
        }

        int studentId;

        for (String key : keySet) {

            studentId = getNewStudentId();

            Student newStudent = new Student(scoreMap.get(key), studentId, key);
            studentIdList.add(studentId, newStudent);

        }

    }

    public int getNewStudentId() {
        return getNewStudentId(studentIdList);
    }

    public int getNewStudentId(java.util.List<Student> studentIdMap) {
        Integer newId = 99;

        if (studentIdMap.size() > 0) {
            for (Student student : studentIdMap) {
                if (student != null) {
                    if (newId <= student.getStudentId()) {
                        newId = student.getStudentId() + 1;
                    }
                }
            }
        }

        return newId;
    }

    public void run() {
        boolean keepRunning = true;

        while (keepRunning) {

            String menuString = printMainMenu();

            keepRunning = chooseAnOption(menuString, keepRunning);

        }

    }

    public boolean chooseAnOption(String menuString, boolean keepRunning) {
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
                doOnExit();
                break;
            default:
                consoleIo.printStringToConsole("If you are see this message, something is wrong\n"
                        + "Please contact the vender.");
        }
        return keepRunning;
    }

    public String printMainMenu() {
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
        return menuString;
    }

    public void doOnExit() {
        saveTextFile();
    }

    public java.util.List<Student> listStudents() {
        return listStudents(studentIdList);

    }

    public java.util.List<Student> listStudents(java.util.List<Student> studentIdList) {
        java.util.List<Student> studentCounterList = new java.util.ArrayList<>();

        String studentList = "Student Names:  ID Number:\n";
        int counter = 0;
        for (Student student : studentIdList) {
            studentCounterList.add(null);
            if (student != null) {

                counter++;
                studentList += counter + ") " + student.getStudentName() + " - \t" + student.getStudentId() + "\n";
                studentCounterList.add(counter, student);
            }
        }

        consoleIo.printStringToConsole(studentList);
        return studentCounterList;
    }

    public void reBuildNameToStudentMap() {
        reBuildNameToStudentMap(studentIdList);
    }

    public void reBuildNameToStudentMap(java.util.List<Student> studentIdList) {

        for (Student student : studentIdList) {
            if (student != null) {

                nameToStudentMap.put(student.getStudentName(), student);
            }
        }
    }

    public void addStudent() {

        String studentName = consoleIo.getUserStringInput("Please Enter A Student's Name:");

        int newStudentId = getNewStudentId();

        consoleIo.printStringToConsole("This student will be ID number: " + newStudentId + "\n");

        java.util.List<Integer> scoreKeeper = generateAnListOfScores();

        Student newStudent = new Student(scoreKeeper, newStudentId, studentName);

        studentIdList.add(newStudentId, newStudent);
        reBuildNameToStudentMap();

    }

    public java.util.List<Integer> generateAnListOfScores() {
        java.util.List<Integer> scoreKeeper = new java.util.ArrayList<>();

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
                scoreKeeper.add(scoreInt);
            } else {
                keepScoreing = false;
            }
        }

        return scoreKeeper;
    }

    public void renameStudent() {

        Student student = advancedAskForStudent("Please Enter A Student's Name Or Number To Rename:");

        String newName = consoleIo.getUserStringInput("What would you like \"" + student.getStudentName() + "\" to be changed to?");
        student.setStudentName(newName);

    }

    public void removeStudent() {

        Student student = advancedAskForStudent("Please Enter A Student's Name Or Number To Remove:");

        String confirm = consoleIo.getUserStringInput("You Are About To Remove \"" + student.getStudentName() + "\" \n Please Enter \"Y\" to confirm. ");

        if (confirm.equals("Y")) {

            boolean success = studentIdList.remove(student);

            if (success) {
                consoleIo.printStringToConsole("That name was succesfully removed.");

            } else {
                consoleIo.printStringToConsole("Student Name \"" + student.getStudentName() + "\" could not be found.");

            }
        } else {
            consoleIo.printStringToConsole("Removal Aborted, No Action Has Been Performed.");
        }
    }

    public Student advancedAskForStudent() {

        return advancedAskForStudent("\nPlease Enter A Student's Name or Number:");
    }

    public Student advancedAskForStudent(String prompt) {
        int studentNumber = advancedAskForNameOrNumber(prompt);
        Student student = studentIdList.get(studentNumber);
        return student;
    }

    public int advancedAskForNameOrNumber(String prompt) {
        int theNumberChoosen = 0;
        java.util.List<Student> studentCounterList = listStudents();

        String studentName = consoleIo.getUserStringInput(prompt);
        boolean isNumber = false;
        int studentNumber = 0;
        try {
            studentNumber = Integer.parseInt(studentName);
            isNumber = true;
        } catch (NumberFormatException numForEx) {

        }

        if (isNumber == true) {
            if (studentNumber > 99) {
                // This must be the student's Id Number
                theNumberChoosen = studentNumber;
            } else {
                // This is probably the students counter Number
                theNumberChoosen = studentCounterList.get(studentNumber).getStudentId();
            }

        } else if (nameToStudentMap.containsKey(studentName)) {
            theNumberChoosen = nameToStudentMap.get(studentName).getStudentId();
        } else {
            consoleIo.printStringToConsole("The System could not find the name \"" + studentName + "\" in the database.");
        }
        return theNumberChoosen;
    }

    public void listScores() {

        Student student = advancedAskForStudent("\nPlease Enter A Student's Name or Number:");

        java.util.List<Integer> scoreList = student.getQuizScores();

        if (scoreList != null) {

            int scoreCounter = 0;
            String scoreString = "Scores for " + student.getStudentName() + ":\n";
            for (Integer score : scoreList) {
                scoreString += (++scoreCounter) + ") " + score + "\n";
            }

            consoleIo.printStringToConsole(scoreString);

        } else {
            consoleIo.printStringToConsole("Data for " + student.getStudentName() + " - " + student.getStudentId() + "\" could not be found.");
        }

    }

    public void viewAverage() {

        Student student = advancedAskForStudent();

        java.util.List<Integer> scoreList = student.getQuizScores();

        if (scoreList != null) {

            float averageScore = listAverager(scoreList);
            consoleIo.printStringToConsole("Average Quiz Score For " + student.getStudentName() + ":\n" + getRoundedFloat(averageScore) + " points");

        } else {
            consoleIo.printStringToConsole("Data for " + student.getStudentName() + " - " + student.getStudentId() + "\" could not be found.");
        }

    }

    public float listAverager(List<Integer> scoreList) {
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
        java.util.List<Integer> totalClassScore = new java.util.ArrayList<>();

        for (Student student : studentIdList) {
            if (student != null) {

                totalClassScore.addAll(student.getQuizScores());
            }
        }
        float classAverage = listAverager(totalClassScore);

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

        Integer bottomScore = determineTheLowestScore();

        java.util.List<Student> bottomStudents = determineWhoHasThatScore(bottomScore);

        String classLeaders = "The lowest score was: " + bottomScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (Student student : bottomStudents) {
            if (student != null) {

                classLeaders += student.getStudentName() + " - \t" + enumerateOccurancesOfScore(bottomScore, student) + "\n";
            }
        }
        consoleIo.printStringToConsole(classLeaders);

    }

    public void findHighestScore() {

        Integer highestScore = determineTheHighestScore();

        java.util.List<Student> topStudents = determineWhoHasThatScore(highestScore);

        String classLeaders = "The highest score was: " + highestScore + "\n"
                + "Students with this score and number of quizzes for each\n";
        for (Student student : topStudents) {
            if (student != null) {

                classLeaders += student.getStudentName() + " - \t" + enumerateOccurancesOfScore(highestScore, student) + "\n";
            }
        }
        consoleIo.printStringToConsole(classLeaders);

    }

    //public java.util.HashMap<Student, Integer> countOccurancesOfScore(Integer selectScore, ArrayList<Student> Students) {
    /**
     * This method counts the occurences of a select score across the entire
     * class.
     *
     * @param selectScore
     * @return
     */
    public int countOccurancesOfScore(Integer selectScore) {
        return countOccurancesOfScore(selectScore, studentIdList);
    }

    /**
     * This method counts occurances across an ArrayList of type Student.
     *
     * @param selectScore
     * @param students
     * @return
     */
    public int countOccurancesOfScore(Integer selectScore, List<Student> students) {
        int occuranceCounter = 0;

        for (Student student : students) {
            if (student != null) {
                java.util.List<Integer> tempScore = student.getQuizScores();

                for (Integer score : tempScore) {
                    if (Objects.equals(score, selectScore)) {
                        occuranceCounter++;
                    }
                }
            }
        }

        return occuranceCounter;

    }

    /**
     * This is the method that counts occurances for one student.
     *
     * @param selectScore
     * @param student
     * @return
     */
    public int enumerateOccurancesOfScore(Integer selectScore, Student student) {

        int occuranceCounter = 0;

        for (Integer score : student.getQuizScores()) {
            if (Objects.equals(score, selectScore)) {
                occuranceCounter++;
            }
        }

        return occuranceCounter;

    }

    public List<Student> determineWhoHasThatScore(int inputScore) {
        return determineWhoHasThatScore(studentIdList, inputScore);
    }

    public List<Student> determineWhoHasThatScore(List<Student> students, int inputScore) {
        List<Student> selectStudents = new ArrayList<>();

        // Iterate through Student
        for (Student student : students) {
            if (student != null) {

                java.util.List<Integer> tempScore = student.getQuizScores();
                boolean isTiedForLeader = false;

                for (Integer score : tempScore) {
                    if (Objects.equals(score, inputScore)) {
                        isTiedForLeader = true;
                    }
                }

                if (isTiedForLeader) {
                    selectStudents.add(student);
                }
            }
        }
        return selectStudents;
    }

    public Integer determineTheLowestScore() {

        return determineTheLowestScore(studentIdList);
    }

    public Integer determineTheLowestScore(java.util.List<Student> studentIdList) {
        // Find the lowest Score
        Integer lowestScore = 0;

        for (Student student : studentIdList) {
            if (student != null) {

                java.util.List<Integer> tempScore = student.getQuizScores();

                for (Integer score : tempScore) {
                    if (score < lowestScore) {
                        lowestScore = score;
                    }
                }
            }
        }
        return lowestScore;
    }

    public Integer determineTheHighestScore() {
        return determineTheHighestScore(studentIdList);
    }

    public Integer determineTheHighestScore(java.util.List<Student> studentIdList) {
        // Find the lowest Score
        Integer highestScore = 0;

        for (Student student : studentIdList) {
            if (student != null) {

                java.util.List<Integer> tempScore = student.getQuizScores();

                for (Integer score : tempScore) {
                    if (score > highestScore) {
                        highestScore = score;
                    }
                }
            }
        }
        return highestScore;
    }

}
