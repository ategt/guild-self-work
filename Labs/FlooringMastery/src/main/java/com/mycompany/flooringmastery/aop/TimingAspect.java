/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.aop;

import java.util.Calendar;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

    public void logStartTime(JoinPoint jp) {
        long timeInMills = Calendar.getInstance().getTimeInMillis();

        System.out.println("Start Time in Mills:" + timeInMills);

    }

    public void logStopTime(JoinPoint jp) {
        long timeInMills = Calendar.getInstance().getTimeInMillis();

        System.out.println("Stop Time in Mills:" + timeInMills);

    }

    public void logStartAndStopTimeForEncode(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Spring AOP: Around advice");
        Object[] args = jp.getArgs();
        if (args.length > 0) {
            System.out.print("Arguments passed: ");
            for (int i = 0; i < args.length; i++) {
                System.out.print("Arg" + (i + 1) + ":" + args[i]);
                args[i] = ":Spring AOP removed the argument";
            }
        }
        
        Object result = jp.proceed(args);
        //return result.toString() + " :Result is also modified";
    }

//    public Map<String, T> logStartAndStopTimeForDecode(ProceedingJoinPoint jp) throws Throwable {
//        System.out.println("Spring AOP: Around advice");
//        Object[] args = jp.getArgs();
//        if (args.length > 0) {
//            System.out.print("Arguments passed: ");
//            for (int i = 0; i < args.length; i++) {
//                System.out.print("Arg" + (i + 1) + ":" + args[i]);
//                args[i] = ":Spring AOP removed the argument";
//            }
//        }
//        Object result = jp.proceed(args);
//        return result.toString() + " :Result is also modified";
//    }
}
