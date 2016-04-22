/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroom;

import java.util.Random;

/**
 *
 * @author apprentice
 */
public class ArrayTest {

    public static void main(String[] args) {

//        java.util.Random random = new java.util.Random();
//        
//        for ( int i = 0 ; i < 30 ; i++ ){
//            System.out.println("" + ( random.nextInt(2) + 1 ) );
//        }
//        


        // First min, max, and average without arrays.
        int gameScore1 = 2;
        int gameScore2 = 45;
        int gameScore3 = 10;
        int gameScore4 = 4;

        int maxScore = 0;
        int minScore = 0;
        int totalPoints = 0;

        if (minScore == 0) {
            minScore = gameScore1;
        }

        if (gameScore1 > maxScore) {
            maxScore = gameScore1;
        }

        if (gameScore1 < minScore) {
            minScore = gameScore1;
        }

        totalPoints += gameScore1;

        if (gameScore2 > maxScore) {
            maxScore = gameScore2;
        }

        if (gameScore2 < minScore) {
            minScore = gameScore2;
        }

        totalPoints += gameScore2;

        if (gameScore3 > maxScore) {
            maxScore = gameScore3;
        }

        if (gameScore3 < minScore) {
            minScore = gameScore3;
        }

        totalPoints += gameScore3;

        if (gameScore4 > maxScore) {
            maxScore = gameScore4;
        }

        if (gameScore4 < minScore) {
            minScore = gameScore4;
        }

        totalPoints += gameScore4;

        System.out.println("Total Points: " + totalPoints);
        System.out.println("Maximum Score: " + maxScore);
        System.out.println("Minimum Score: " + minScore);
        System.out.println("Average Score: " + (totalPoints / 4));

        System.out.println("");

        // Now min, max and average without arrays.
        int[] gameCounter = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] teamScore = new int[10];
        
        Random random = new Random();

        for (int i = 0; teamScore.length > i; i++) {
            //teamScore[i] = (int) Math.round(Math.random() * 50);
            teamScore[i] = random.nextInt(51);
        }

//        for (int score : teamScore) {
//            System.out.println(score + " points");
//        }
//
//        for (int game : gameCounter) {
//            System.out.println("Game number " + game);
//        }

        for (int i = 0; teamScore.length > i; i++) {
            System.out.println("Game:" + (gameCounter[i] + 1) + ", Score: " + teamScore[i]);
        }

        System.out.println("");

        for (int score : teamScore) {
            if (score > maxScore) {
                maxScore = score;
            }

            if (score < minScore) {
                minScore = score;
            }

            totalPoints += score;
        }

        System.out.println("Total Points: " + totalPoints);
        System.out.println("Maximum Score: " + maxScore);
        System.out.println("Minimum Score: " + minScore);
        System.out.println("Average Score: " + (totalPoints / teamScore.length));

        System.out.println("");

    }

}
