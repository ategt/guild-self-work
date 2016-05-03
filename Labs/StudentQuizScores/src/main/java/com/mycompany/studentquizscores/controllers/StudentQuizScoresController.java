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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class StudentQuizScoresController {

    ConsoleIO consoleIo = new ConsoleIO();
    private QuizScoreDao quizScoreDao = new QuizScoreDao();
    private StudentDao studentDao = new StudentDao(quizScoreDao);

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
        addNewQuizScoreLoop(myStudent);
        studentDao.update(myStudent);

    }

    private void listStudents() {
        consoleIo.printStringToConsole(getNameAndNumber(studentDao.getList()));
    }

    private void removeStudent() {

        int id = consoleIo.getUserIntInputRange(getNameAndNumber(studentDao.getList()) + "Please enter student's number.", 0, Integer.MAX_VALUE);
        Student myStudent = studentDao.get(id);

        if (myStudent != null) {
            studentDao.delete(myStudent);
        }

    }

    private void addNewQuizScoreLoop(Student student) {

        boolean keepScoreing = true;
        while (keepScoreing) {
            keepScoreing = addNewQuizScore(keepScoreing, student);
        }

    }

    private boolean addNewQuizScore(boolean keepScoreing, Student student) {
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
            QuizScore quizScore = new QuizScore();
            quizScore.setQuizScore(scoreInt);

            List<QuizScore> scores = student.getQuizScores();

            if (scores == null) {
                scores = new ArrayList();
                student.setQuizScores(scores);
                //System.out.println(scores);
            }

            scores.add(quizScore);

            quizScoreDao.create(quizScore);
        } else {
            keepScoreing = false;
        }
        return keepScoreing;
    }

    private void exitApp() {
        consoleIo.printStringToConsole("Have a nice day.");

    }

    public String getNameAndNumber(List<Student> students) {
        String nameAndNumber = "";

        for (Student myStudent : students) {
            nameAndNumber += myStudent.getId() + "\t" + myStudent.getStudentName() + "\n";

        }

        return nameAndNumber;
    }

    private void listScores() {

        int id = consoleIo.getUserIntInputRange(getNameAndNumber(studentDao.getList()) + "Please enter student's number.", 0, Integer.MAX_VALUE);
        Student myStudent = studentDao.get(id);

        if (myStudent != null) {

            String scoreString = "Scores for " + myStudent.getStudentName() + ":\n";
            int scoreCounter = 0;

            for (QuizScore quizScore : myStudent.getQuizScores()) {
                if (quizScore != null) {
                    scoreString += (++scoreCounter) + ") " + quizScore.getQuizScore() + "\n";
                }
            }
            consoleIo.printStringToConsole(scoreString);

        } else {
            consoleIo.printStringToConsole("Student ID Number: " + id + " could not be found.");
        }

    }

    public void viewAverage() {

        int id = consoleIo.getUserIntInputRange(getNameAndNumber(studentDao.getList()) + "Please enter student's number.", 0, Integer.MAX_VALUE);
        Student myStudent = studentDao.get(id);

        if (myStudent != null) {

            //        Student student = advancedAskForStudent();
            //
            //        java.util.List<Integer> scoreList = student.getQuizScores();
            List<QuizScore> scoreList = myStudent.getQuizScores();

            if (scoreList != null) {

                float averageScore = listAverager(scoreList);
                consoleIo.printStringToConsole("Average Quiz Score For " + myStudent.getStudentName() + ":\n" + getRoundedFloat(averageScore) + " points");

            } else {
                consoleIo.printStringToConsole("Data for " + myStudent.getStudentName() + " - " + myStudent.getId() + "\" could not be found.");
            }

        } else {
            consoleIo.printStringToConsole("Student ID Number: " + id + " could not be found.");
        }

    }

    public void viewClassAverage() {
        java.util.List<QuizScore> totalClassScore = new java.util.ArrayList<>();

        for (Student student : studentDao.getList()) {
            if (student != null) {

                totalClassScore.addAll(student.getQuizScores());

            }
        }
        float classAverage = listAverager(totalClassScore);

        String classStringAverage = getRoundedFloat(classAverage);

        consoleIo.printStringToConsole("The Class Average is " + classStringAverage + " points.");

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

    public Integer determineTheHighestScore() {
        // Find the lowest Score
        Integer highestScore = 0;

        for (Student student : studentDao.getList()) {
            if (student != null) {

                java.util.List<QuizScore> tempScore = student.getQuizScores();

                for (QuizScore quizScore : tempScore) {
                    if (quizScore.getQuizScore() > highestScore) {
                        highestScore = quizScore.getQuizScore();
                    }
                }
            }
        }
        return highestScore;
    }

    public Integer determineTheLowestScore() {
        // Find the lowest Score
        Integer lowestScore = 0;

        for (Student student : studentDao.getList()) {
            if (student != null) {

                java.util.List<QuizScore> tempScore = student.getQuizScores();

                for (QuizScore quizScore : tempScore) {
                    if (quizScore.getQuizScore() < lowestScore) {
                        lowestScore = quizScore.getQuizScore();
                    }
                }
            }
        }
        return lowestScore;
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

    public List<Student> determineWhoHasThatScore(int inputScore) {
        List<Student> selectStudents = new ArrayList<>();

        // Iterate through Student
        for (Student student : studentDao.getList()) {
            if (student != null) {

                java.util.List<QuizScore> tempScore = student.getQuizScores();
                boolean isTiedForLeader = false;

                for (QuizScore quizScore : tempScore) {
                    if (Objects.equals(quizScore.getQuizScore(), inputScore)) {
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

    public int enumerateOccurancesOfScore(Integer selectScore, Student student) {

        int occuranceCounter = 0;

        for (QuizScore quizScore : student.getQuizScores()) {
            if (Objects.equals(quizScore.getQuizScore(), selectScore)) {
                occuranceCounter++;
            }
        }

        return occuranceCounter;

    }

    public void renameStudent() {

        //Student student = advancedAskForStudent("Please Enter A Student's Name Or Number To Rename:");
        int id = consoleIo.getUserIntInputRange(getNameAndNumber(studentDao.getList()) + "Please enter student's number.", 0, Integer.MAX_VALUE);
        Student myStudent = studentDao.get(id);

        if (myStudent != null) {

            String newName = consoleIo.getUserStringInput("What would you like \"" + myStudent.getStudentName() + "\" to be changed to?");
            myStudent.setStudentName(newName);
            studentDao.update(myStudent);
        } else {
            consoleIo.printStringToConsole("Student ID Number: " + id + " could not be found.");
        }

    }

    public float listAverager(List<QuizScore> scoreList) {
        int scoreCounter = 0;
        int totalPoints = 0;
        for (QuizScore quizScore : scoreList) {
            totalPoints += quizScore.getQuizScore();
            scoreCounter++;
        }
        float averageScore = totalPoints / scoreCounter;
        return averageScore;
    }

    public String getRoundedFloat(float classAverage) {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        String classStringAverage = df.format(classAverage);
        return classStringAverage;
    }

}
