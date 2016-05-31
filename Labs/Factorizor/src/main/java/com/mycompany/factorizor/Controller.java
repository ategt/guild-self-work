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
        boolean validInput = true;
        Integer numberToFactorize = null;

        try {
            numberToFactorize = Integer.parseInt(factorizeNumberString);
        } catch (NumberFormatException numberFormatException) {
            validInput = false;
        }

        if (numberToFactorize == null || numberToFactorize < 1) {
            validInput = false;
        }

        if (validInput) {
            
            FactorizerLogic factorizerLogic = new FactorizerLogic();
            NumberCharacteristics factorizerDto = factorizerLogic.run(numberToFactorize);
            //request.setAttribute("message", message);

            RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
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
        return "Short description";
    }// </editor-fold>

}
