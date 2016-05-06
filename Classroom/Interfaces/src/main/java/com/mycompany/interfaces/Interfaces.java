/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import java.util.*;

/**
 *
 * @author apprentice
 */
public class Interfaces {

    public static void main(String[] args) {
        Ball ball = new Ball();

        Television tv = new Television();

        ball.setColor("Green");
        tv.setColor("Black");

        printColor(ball);
        printColor(tv);

        List<Colorable> list = new ArrayList();

        list.add(tv);
        list.add(ball);

        for (Colorable item : list) {
            printColor(item);
        }
        
        Debuggable debuggableBall = ball;
        debuggableBall.logError("Big Problem!!");
        

    }

    public static void printColor(Colorable item) {
        System.out.println("My color is " + item.getColor());
    }
}
