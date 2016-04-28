/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class Collections {

    public static void main(String[] args) {
//
//        List myList = new ArrayList();
//
//        myList.add(1);
//        myList.add(23);
//        myList.add("Billy");
//
//        Iterator it = myList.iterator();
//
//        while (it.hasNext()) {
//            int value = (int) it.next();
//            System.out.println(value);
//        }
//
//        for (Object value : myList) {
//
//            if (value instanceof String) {
//                // do string things
//            } else if (value instanceof Integer) {
//                // do int things
//            }
//
//            System.out.println(value);
//        }
//
//        List<String> stringList = new ArrayList<>();
//
//        stringList.add("Bill");
//        stringList.add("Sally");
//        stringList.add("Jones");
//        //stringList.add(2);
//
//        for (String myString : stringList) {
//            System.out.println(myString);
//        }
//
//        stringList.remove("Jones");
//        stringList.remove(3);
//
//        Iterator<String> its = stringList.iterator();
//
//        while (its.hasNext()) {
//            String theString = its.next();
//        }

        Map<String, Integer> populations = new HashMap();

//        myMap.put(1, "x");
//        myMap.put("x", 2);
        populations.put("USA", 300000000);
        populations.put("Canada", 35000000);
        populations.put("UK", 60000000);
        populations.put("Japan", 1300000);

        System.out.println("Map size is:" + populations.size());

        int usaValue = populations.get("USA");

        Set<String> keySet = populations.keySet();

        for (String key : keySet) {
            Integer value = populations.get(key);

            System.out.println(key + " : " + value);
        }

        System.out.println("++++++++++++");

        java.util.Collection<Integer> values = populations.values();

        for (Integer value : values) {
            System.out.println(value);

        }
        
        
        int arre[] = new int[6];
        int [] arra = new int[6];
       // int arrb[] arrb = new int[6];
        //int arrc[] = int [6] new;
        
        
        
        
        
    }

    private List<String> getStringList() {
        List<String> myStringList = new ArrayList();
        return myStringList;
    }
}
