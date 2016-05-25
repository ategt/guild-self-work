/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.aop;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author apprentice
 */
public class TimingAspect {

    private ApplicationContext ctx;

    public TimingAspect() {
        ctx = com.mycompany.flooringmastery.aop.ApplicationContextProvider.getApplicationContext();
    }

}
