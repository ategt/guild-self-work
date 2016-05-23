/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olympian;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class OlympianApp {

    public static void main(String[] args) {
        //SkiJumper jumper = new SkiJumper();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

//        SkiJumper jumper = (SkiJumper) ctx.getBean("skiJumper");
//        
//        String eventReturn = jumper.competeInEvent();
////        System.out.println(eventReturn);
//        Event curlingEvent = new CurlingEvent();
//        //    Event skiJumpEvent = new SkiJumpEvent();
//        //Olympian olympian = new Olympian(skiJumpEvent);
//        Olympian olympian = new Olympian(curlingEvent);
//
//        olympian.competeInEvent();


        Olympian usaOlympian = ctx.getBean("usaSkiJumper", Olympian.class);
        usaOlympian.competeInEvent();

        Olympian curlingDude = ctx.getBean("curlingDude", Olympian.class);
        curlingDude.competeInEvent();

    }
}
