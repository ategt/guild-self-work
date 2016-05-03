/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores.dao;

import com.mycompany.studentquizscores.dto.QuizScore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class QuizScoreDao {

    private List<QuizScore> quizScores = new ArrayList();
    private int nextId = 1;
    File quizScoresFile = new File("QuizScoresData.txt");

    public QuizScoreDao() {

        quizScores = decode();

        if (quizScores == null) {
            quizScores = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public QuizScore create(QuizScore quizScore) {
        quizScore.setId(nextId);
        nextId++;

        quizScores.add(quizScore);

        encode();

        return quizScore;
    }

    public QuizScore get(Integer id) {

        for (QuizScore quizScore : quizScores) {
            if (quizScore.getId() == id) {
                return quizScore;
            }

        }

        return null;
    }

    public void update(QuizScore quizScore) {

        for (QuizScore myQuizScore : quizScores) {
            if (myQuizScore.getId() == quizScore.getId()) {
                quizScores.remove(myQuizScore);
                quizScores.add(quizScore);
            }

        }

        encode();

    }

    public void delete(QuizScore quizScore) {
        QuizScore found = null;

        for (QuizScore currentQuizScore : quizScores) {
            if (currentQuizScore.getId() == quizScore.getId()) {
                found = currentQuizScore;
                break;
            }
        }

        if (found != null) {
            quizScores.remove(found);
        }

        encode();

    }
    
    public List<QuizScore> getList(){
        
        return quizScores;
    }

    public int size() {
        return quizScores.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (QuizScore quizScore : quizScores) {
            if (highestId < quizScore.getId()) {
                highestId = quizScore.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        //File testFile = quizScoresFile;
        try {
//            if (testFile.exists()) {
//                String absolutePath = testFile.getAbsolutePath();
//                String name = testFile.getName();
//
//                for (int counter = 0; counter < 10; counter++) {
//                    testFile = new File(absolutePath.replace(name, name + counter));
//
//                    //counter++;
//                    if (testFile.exists()) {
//                        break;
//                    }
//                }
//            }

            PrintWriter out = new PrintWriter(new FileWriter(quizScoresFile));

            for (QuizScore quizScore : quizScores) {

                out.print(quizScore.getId());
                out.print(TOKEN);
                out.print(quizScore.getQuizScore());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(QuizScoreDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<QuizScore> decode() {

        List<QuizScore> quizScoreList = new ArrayList<>();

        final String TOKEN = "::";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(quizScoresFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                QuizScore quizScore = new QuizScore();

                int id = Integer.parseInt(stringParts[0]);
                quizScore.setId(id);
                

                int score = -1;
                try {
                    score = Integer.parseInt(stringParts[1]);
                }catch(NumberFormatException numFrmEx){
                    
                }
                
                quizScore.setQuizScore(score);

                quizScoreList.add(quizScore);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuizScoreDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return quizScoreList;
    }
    
    public float getAverageQuizScore(){
       // float averageScore = 0f;
        float totalPoints = 0f;
        float totalQuizes = 0f;
        
        for ( QuizScore quizScore : quizScores ){
            totalPoints += quizScore.getQuizScore();
            totalQuizes++;
         }
        
        return ( totalPoints / totalQuizes );
    }

}
