/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores.controllers;

import com.mycompany.studentquizscores.dao.QuizScoreDao;
import com.mycompany.studentquizscores.dao.StudentDao;
import com.mycompany.studentquizscores.dto.QuizScore;
import com.mycompany.studentquizscores.dto.Student;

/**
 *
 * @author apprentice
 */
public class StudentQuizScoresController {

    ConsoleIO consoleIo = new ConsoleIO();
    private StudentDao studentDao = new StudentDao();
    private QuizScoreDao quizScoreDao = new QuizScoreDao();

    public void run() {
        boolean keepRunning = true;

        while (keepRunning) {

            String menuString = printMainMenu();

            keepRunning = chooseAnOption(menuString, keepRunning);

        }

    }

    private boolean chooseAnOption(String menuString, boolean keepRunning) {
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
//            case 4:
//                listScores();
//                break;
//            case 5:
//                viewAverage();
//                break;
//            case 6:
//                viewClassAverage();
//                break;
//            case 7:
//                findHighestScore();
//                break;
//            case 8:
//                findLowestScore();
//                break;
//            case 9:
//                renameStudent();
//                break;
            case 0:
                keepRunning = false;
                exitApp();
                break;
            default:
                consoleIo.printStringToConsole("If you are see this message, something is wrong\n"
                        + "Please contact the vender.");
        }
        return keepRunning;
    }

    private String printMainMenu() {
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

    private void addStudent() {
        String firstName = consoleIo.getUserStringInput("Please enter new student's name");

        Student myStudent = new Student();
        myStudent.setStudentName(firstName);

        studentDao.create(myStudent);
        addNewQuizScoreLoop();
        
    }

    private void listStudents() {
        consoleIo.printStringToConsole(studentDao.getNameAndNumber());
    }

    private void removeStudent() {

        int id = consoleIo.getUserIntInputRange(studentDao.getNameAndNumber() + "Please enter student's number.", 0, studentDao.size());
        Student myStudent = studentDao.get(id);

        if (myStudent != null) {
            studentDao.delete(myStudent);
        }

    }

    private void addNewQuizScoreLoop() {

        boolean keepScoreing = true;
        while (keepScoreing) {
            keepScoreing = addNewQuizScore(keepScoreing);
        }

    }

    private boolean addNewQuizScore(boolean keepScoreing) {
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
            //scoreKeeper.add(scoreInt);
            QuizScore quizScore = new QuizScore();
            quizScore.setQuizScore(scoreInt);
            quizScoreDao.create(quizScore);
        } else {
            keepScoreing = false;
        }
        return keepScoreing;
    }

    private void exitApp() {
        consoleIo.printStringToConsole("Have a nice day.");

    }

}
