package com.mycompany.luckysevenswebapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
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

        Integer startingBet = null;
        boolean validInput = true;
        final String dollarSign = "\u0024";

        try {
            startingBetString = startingBetString.replace(dollarSign, "").trim();
            startingBet = Math.round(Float.parseFloat(startingBetString));
            validInput = validateInput(startingBet);
        } catch (NumberFormatException numberFormatException) {
            validInput = false;
        }

        if (validInput) {
            LuckySevensGame game = new LuckySevensGame();
            LuckySevensGameLogic gameLogic = new LuckySevensGameLogic();
            
            game.setStartingBet(startingBet);
            game.setRollCounter(1);

            game = gameLogic.luckySevensGameLoop(game);

            request.setAttribute("startingBetValue", displayCurrency(request.getLocale(), game.getStartingBet()));
            request.setAttribute("totalRolls", game.getRollCounter());
            request.setAttribute("highestAmountWon", displayCurrency(request.getLocale(), game.getMaxAmountHeld()));
            request.setAttribute("rollCountAtHighestAmount", game.getRollNumberAtMaxAmountHeld());

            RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("inputInvalid", true);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
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

    //@Override
    public String getName() {
        return "Lucky Sevens";
    }
    
        public String displayCurrency(Locale currentLocale, int currencyAmount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(currencyAmount);
    }

    public boolean validateInput(Integer startingBet) {
        boolean validInput = true;

        if (startingBet == null || startingBet < 1) {
            validInput = false;
        }

        return validInput;
    }
}
