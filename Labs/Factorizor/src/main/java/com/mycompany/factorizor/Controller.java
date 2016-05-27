/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizor;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String factorizeNumberString = request.getParameter("factorizeNumber");

        //int maxAmountHeld = 0;
        //int rollNumberAtMaxAmountHeld = 0;
        //LuckySevensGameLog
        // Old try
        //com.mycompany.luckysevenswebapp.LuckySevensGameLogic LuckySevensGameLogic = new com.mycompany.luckysevenswebapp.LuckySevensGameLogic();
        int numberToFactorize = Integer.parseInt(factorizeNumberString);

//         rollCounter = 1;
//
//        //rollCounter = LuckySevensGameLogic.luckySevensGameLoop(rollCounter, startingBet);
//        // old try
//        //LuckySevensGameDTO gameObject =  LuckySevensGameLogic.
//        //luckySevensGameLoop(rollCounter, startingBet);
//        //luckySevensGame( rollCounter,  startingBet);
//        //String endingMessage = printEndingMessage( gameObject );
//        
//         rollCounter =  luckySevensGameLoop( rollCounter,  startingBet);
// 
//        String endingMessage = printEndingMessage( rollNumberAtMaxAmountHeld );
//        
//        //String endingMessage = " message " + startingBet;
        //printEndingMessage
        String message = run(numberToFactorize);
        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
        rd.forward(request, response);

//        
//        String myAnswer = request.getParameter("myAnswer");
//        String myReason = request.getParameter("myReason");
//
//        String messageToUser = "";
//        if ("no".equalsIgnoreCase(myAnswer)) {
//            messageToUser = "That is too bad, sorry to hear about " + myReason;
//        } else {
//            messageToUser = "Are you sure? SM is coming.";
//        }
//
//        List<Integer> numbers = new ArrayList();
//
//        for (int i = 0; i < 10; i++) {
//            numbers.add(i);
//        }
//
//        request.setAttribute("numbers", numbers);
//        request.setAttribute("message", messageToUser);
//        request.setAttribute("isGoing", true);
//
//        RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
//        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    int numberOfFactors = 0;
    int sumOfFactors = 0;

//    public void run() {
//        //if (consoleIO == null){
//        ApplicationContext ctx = com.mycompany.aop.ApplicationContextProvider.getApplicationContext();
//        ConsoleIO consoleIO = ctx.getBean("consoleIo", ConsoleIO.class);
//
//        run(consoleIO);
//    }
    //public void run(ConsoleIO consoleIO) {
    public String run(int factorizedNumber) {

        String output = "";

        numberOfFactors = 0;
        sumOfFactors = 0;

        //int factorizedNumber = consoleIO.getUserIntInputRange("Please Enter A Number To Be Factorized:", 1, Integer.MAX_VALUE);
        output += factorizingCalculationsLoop(factorizedNumber) + "a<br />";

        output += displayIsPerfectNumber(sumOfFactors, factorizedNumber) + "b<br />";

        output += displayIsPrimeNumber(numberOfFactors, factorizedNumber) + "c<br />";

        return output;

    }

    public String factorizingCalculationsLoop(int factorizedNumber) {
        //java.util.List<String> output = ArrayList();

        String output = "";

        for (int testNumber = 1; testNumber <= factorizedNumber; testNumber++) {
            String responseString = factorizingCalculations(factorizedNumber, testNumber);
           // if ("".equalsIgnoreCase(responseString.trim())) {
                output += responseString + "d<br />";
            //}
        }

        return output;

    }

    public String factorizingCalculations(int factorizedNumber, int testNumber) {
        String output = "";
        if (isNumberEvenlyDivisible(factorizedNumber, testNumber)) {

            numberOfFactors++;

            if (testNumber != factorizedNumber) {
                sumOfFactors += testNumber;
                output += testNumber + "+e<br />";
                //consoleIO.printStringToConsole(testNumber + "");
            }

        }

        return output;
    }

    /**
     * This method performs a modulus test on two numbers, divisor and dividend,
     * to determine if one is evenly divisible by the other or not. This is
     * defined as divisor divided by dividend with no remainder.
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static boolean isNumberEvenlyDivisible(int divisor, int dividend) {
        return divisor % dividend == 0;
    }

    //public void displayIsPrimeNumber(int numberOfFactors, ConsoleIO consoleIO, int factorizedNumber) {
    public String displayIsPrimeNumber(int numberOfFactors, int factorizedNumber) {
        String output = "";

        if (isPrimeNumber(numberOfFactors)) {
            output = factorizedNumber + " is a prime number.";
        } else {
            output = factorizedNumber + " is not a prime number.";
        }

        return output;
    }

    public String displayIsPerfectNumber(int sumOfFactors, int factorizedNumber) {
        String output = "";
        if (isPerfectNumber(sumOfFactors, factorizedNumber)) {
            output = factorizedNumber + " is a perfect number.";
        } else {
            output = factorizedNumber + " is not a perfect number.";
        }
        return output;
    }

    public static boolean isPrimeNumber(int numberOfFactors) {
        return numberOfFactors == 2;
    }

    public static boolean isPerfectNumber(int sumOfFactors, int factorizedNumber) {
        return sumOfFactors == factorizedNumber;
    }

    //@Override
    public String getName() {
        return "Factorizer";
    }

}
