/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

/**
 *
 * @author apprentice
 */
public class Students {

    static java.util.HashMap<String, java.util.ArrayList<Integer>> scoreMap = new java.util.HashMap<>();

    public static java.util.HashMap<String, java.util.ArrayList<Integer>> loadStudentData() {

        java.util.ArrayList<Integer> scoreList = new java.util.ArrayList<>();
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

}
