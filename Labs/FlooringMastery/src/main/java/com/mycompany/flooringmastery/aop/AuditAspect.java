/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.aop;

import com.mycompany.flooringmastery.dao.AuditDao;
import com.mycompany.flooringmastery.dto.Audit;
import com.mycompany.flooringmastery.dto.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class AuditAspect {

    private ApplicationContext ctx;

    public AuditAspect() {
        
        //ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

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

    public void createAuditEntry(JoinPoint jp) throws Throwable {

        Order order = processJoinPoint(jp);
        System.out.println("Making an audit entry.");
        if (order != null) {
            String actionName = jp.getSignature().getName();
            Audit audit = buildAuditObject(order, actionName);
            AuditDao auditDao =  new AuditDao(new java.io.File("auditLog.txt")); //ctx.getBean("auditDao", AuditDao.class);
            auditDao.create(audit);
        }
    }

    public Audit buildAuditObject(Order order, String actionName) {

        Audit audit = new Audit();

        audit.setDate(order.getDate());
        audit.setOrderid(order.getId());
        audit.setActionPerformed(actionName);

        return audit;
    }

    public ApplicationContext getCtx() {
        return ctx;
    }

    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

}
