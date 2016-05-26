/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
                response.setContentType("text/html;charset=UTF-8");

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

    
        int maxAmountHeld = 0;
    int rollNumberAtMaxAmountHeld = 0;

    public void run() {

        //ConsoleIO consoleIO = new ConsoleIO();

        //int startingBet = 0;
        maxAmountHeld = 0;
        rollNumberAtMaxAmountHeld = 0;

        int startingBet = consoleIO.getUserIntInputRange("How many Dollars would you like to bet?",
                4, 15000, "Betting that amount does not seem like a good idea.");

        int rollCounter = 1;

        rollCounter = luckySevensGameLoop(rollCounter, startingBet);

        printEndingMessage(rollCounter, consoleIO);
    }

    public void printEndingMessage(int rollCounter, ConsoleIO consoleIO) {
        consoleIO.printStringToConsole("You are broke after " + --rollCounter + " rolls.\n");
        consoleIO.printStringToConsole("You should have quit after " + rollNumberAtMaxAmountHeld + " rolls when you had $" + maxAmountHeld + ".");
    }

    public int luckySevensGameLoop(int rollCounter, int startingBet) {
        int diceRoll;
        for (int currentBalance = startingBet; currentBalance > 0; rollCounter++) {

            diceRoll = rollDice();

            currentBalance = adjustCurrentBalance(diceRoll, currentBalance);

            updateHighBalance(currentBalance, rollCounter);
        }
        return rollCounter;
    }

    public void updateHighBalance(int currentBalance, int rollCounter) {
        if (currentBalance > maxAmountHeld) {
            maxAmountHeld = currentBalance;
            rollNumberAtMaxAmountHeld = rollCounter;
        }
    }

    public int adjustCurrentBalance(int diceRoll, int currentBalance) {
        if (isAWinner(diceRoll)) {
            currentBalance += 4;
        } else {
            currentBalance -= 1;
        }
        return currentBalance;
    }

    public boolean isAWinner(int diceRoll) {
        return (diceRoll == 7);
    }

    public int rollDice() {

        // Roll the virtual dice here.
        int dieOne = rollOneDie();
        int dieTwo = rollOneDie();

        return dieOne + dieTwo;

    }

    public int rollOneDie() {
        return (int) Math.ceil(Math.random() * 6);

    }

//    @Override
//    public String getName() {
//        return "Lucky Sevens";
//    }
    
    
}
