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
    private int id;
    private String studentName;

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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
