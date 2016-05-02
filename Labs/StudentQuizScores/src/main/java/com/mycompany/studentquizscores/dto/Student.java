/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores.dto;

/**
 *
 * @author apprentice
 */
public class Student {

    private java.util.List<QuizScore> QuizScores;
    private int studentId;
    private String studentName;

    public Student() {
    }

    public Student(java.util.List<QuizScore> QuizScores, int studentId, String studentName) {
        this.QuizScores = QuizScores;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public static java.util.HashMap<String, java.util.List<QuizScore>> loadStudentData() {

        java.util.HashMap<String, java.util.List<QuizScore>> scoreMap = new java.util.HashMap<>();

        java.util.List<QuizScore> scoreList = new java.util.ArrayList<>();
        scoreList.add(95);
        scoreList.add(100);
        scoreList.add(85);
        scoreList.add(70);
        scoreList.add(65);
        scoreList.add(81);

        scoreMap.put("Billy", scoreList);

        scoreList = new java.util.ArrayList<>();
        scoreList.add(70);
        scoreList.add(25);
        scoreList.add(15);
        scoreList.add(0);
        scoreList.add(0);
        scoreList.add(65);

        scoreMap.put("Lou", scoreList);

        scoreList = new java.util.ArrayList<>();
        scoreList.add(85);
        scoreList.add(65);
        scoreList.add(50);
        scoreList.add(48);
        scoreList.add(0);
        scoreList.add(70);

        scoreMap.put("Sally", scoreList);

        scoreList = new java.util.ArrayList<>();
        scoreList.add(0);
        scoreList.add(65);
        scoreList.add(50);
        scoreList.add(48);
        scoreList.add(0);
        scoreList.add(0);

        scoreMap.put("Charlie", scoreList);

        scoreList = new java.util.ArrayList<>();
        scoreList.add(0);
        scoreList.add(0);
        scoreList.add(0);
        scoreList.add(48);
        scoreList.add(30);
        scoreList.add(0);

        scoreMap.put("Transfer Student1", scoreList);

        return scoreMap;
    }

    /**
     * @return the QuizScores
     */
    public java.util.List<QuizScore> getQuizScores() {
        return QuizScores;
    }

    /**
     * @param QuizScores the QuizScores to set
     */
    public void setQuizScores(java.util.List<QuizScore> QuizScores) {
        this.QuizScores = QuizScores;
    }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
