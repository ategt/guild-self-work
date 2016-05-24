/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.aop;

import com.mycompany.flooringmastery.dto.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author apprentice
 */
public class AuditAspect {

    public String log(ProceedingJoinPoint jp) throws Throwable {
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
        return result.toString() + " :Result is also modified";
    }

    private Order processJoinPoint(JoinPoint jp) {

        Object[] args = jp.getArgs();
        Order order = null;
        if (args[0] instanceof Order) {
            order = (Order) args[0];
        }
        
        if (args.length > 0) {
            System.out.print("Arguments passed: ");
            for (int i = 0; i < args.length; i++) {
                System.out.print("Arg" + (i + 1) + ":" + args[i]);
            }
        }

        return order;
    }

    public void createAuditEntry(ProceedingJoinPoint jp) throws Throwable {

        Order order = processJoinPoint(jp);
        System.out.println("Making an audit entry.");
        
        Audit audit = buildAuditObject(order);
        AuditDao
        
        
    }

    public Audit buildAuditObject( Order order ) {
        
        
        
    }
    
}
