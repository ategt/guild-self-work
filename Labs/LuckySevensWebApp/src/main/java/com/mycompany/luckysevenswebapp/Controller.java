package com.mycompany.luckysevenswebapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
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
        String startingBetString = request.getParameter("startingBet");

        //int maxAmountHeld = 0;
        //int rollNumberAtMaxAmountHeld = 0;
        //LuckySevensGameLog
        // Old try
        //com.mycompany.luckysevenswebapp.LuckySevensGameLogic LuckySevensGameLogic = new com.mycompany.luckysevenswebapp.LuckySevensGameLogic();
        int startingBet = Integer.parseInt(startingBetString);

        int rollCounter = 1;

        //rollCounter = LuckySevensGameLogic.luckySevensGameLoop(rollCounter, startingBet);
        // old try
        //LuckySevensGameDTO gameObject =  LuckySevensGameLogic.
        //luckySevensGameLoop(rollCounter, startingBet);

        //luckySevensGame( rollCounter,  startingBet);
        //String endingMessage = printEndingMessage( gameObject );
        
        //String endingMessage = printEndingMessage(  );
        String endingMessage = " message ";

        request.setAttribute("message", endingMessage);

        RequestDispatcher rd = request.getRequestDispatcher("firstResponse.jsp");
        rd.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Lucky Sevens";
    }// </editor-fold>
}
//    @Override
//    public String getName() {
//        return "Lucky Sevens";
//    }
//    private String printEndingMessage(LuckySevensGameDTO gameObject) {
//        
//        return "You are broke after " + (gameObject.getRollCounter()-1) + " rolls.<br />" + 
//        "You should have quit after " + gameObject.getRollNumberAtMaxAmountHeld() + 
//                " rolls when you had $" + gameObject.getMaxAmountHeld() + ".";
//    
//    }
//    private String printEndingMessage(int rollCounter, int rollNumberAtMaxAmountHeld, int maxAmountHeld) {
//
//        return "You are broke after " + (rollCounter - 1) + " rolls.<br />"
//                + "You should have quit after " + rollNumberAtMaxAmountHeld
//                + " rolls when you had $" + maxAmountHeld + ".";
//
//    }
////
////    public LuckySevensGameDTO luckySevensGame(int rollCounter, int startingBet) {
////
////        int maxAmountHeld = 0;
////        int rollNumberAtMaxAmountHeld = 0;
////
////        LuckySevensGameDTO gameObject = new LuckySevensGameDTO();
////
////        int diceRoll;
////
////        gameObject.setStartingBet(startingBet);
////
////        for (int currentBalance = startingBet; currentBalance > 0; rollCounter++) {
////
////            diceRoll = rollDice();
////
////            currentBalance = adjustCurrentBalance(diceRoll, currentBalance);
////
////            updateHighBalance(currentBalance, rollCounter);
////
////        }
////
////        gameObject.setMaxAmountHeld(maxAmountHeld);
////        gameObject.setRollNumberAtMaxAmountHeld(rollNumberAtMaxAmountHeld);
////        gameObject.setRollCounter(rollCounter);
////
////        return gameObject;
////    }
//
//    public int luckySevensGameLoop(int rollCounter, int startingBet) {
//        int diceRoll;
//        for (int currentBalance = startingBet; currentBalance > 0; rollCounter++) {
//
//            diceRoll = rollDice();
//
//            currentBalance = adjustCurrentBalance(diceRoll, currentBalance);
//
//            updateHighBalance(currentBalance, rollCounter);
//        }
//        return rollCounter;
//    }
//
//    public void updateHighBalance(int currentBalance, int rollCounter) {
////        if (currentBalance > maxAmountHeld) {
////            maxAmountHeld = currentBalance;
////            rollNumberAtMaxAmountHeld = rollCounter;
////        }
//    }
//
//    public int adjustCurrentBalance(int diceRoll, int currentBalance) {
//        if (isAWinner(diceRoll)) {
//            currentBalance += 4;
//        } else {
//            currentBalance -= 1;
//        }
//        return currentBalance;
//    }
//
//    public boolean isAWinner(int diceRoll) {
//        return (diceRoll == 7);
//    }
//
//    public int rollDice() {
//
//        // Roll the virtual dice here.
//        int dieOne = rollOneDie();
//        int dieTwo = rollOneDie();
//
//        return dieOne + dieTwo;
//
//    }
//
//    public int rollOneDie() {
//        return (int) Math.ceil(Math.random() * 6);
//    }
//
//    //rollNumberAtMaxAmountHeld 
//}
