/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tipcalculator;

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
@WebServlet(name = "TipCalcController", urlPatterns = {"/TipCalcController"})
public class TipCalcController extends HttpServlet {

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
        request.setAttribute("hadError", false);

            String defaultTipPercentage = "15.00";
            String defaultBillValue = "$";
            
            request.setAttribute("billValueOutput", defaultBillValue);
            request.setAttribute("tipPercentageOutput", defaultTipPercentage);

            RequestDispatcher rd = request.getRequestDispatcher("tipCalc.jsp");
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

        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("hadError", false);

        String billValueString = request.getParameter("billValue");
        Double billValue = 0.0d;

        boolean hadError = false;

        try {
            boolean billValueError = true;
            request.setAttribute("billValueError", billValueError);

            request.setAttribute("billValueOutput", billValueString);
            
            String dollarSign = "\u0024";
            
            billValueString = billValueString.replace(dollarSign, "").trim();
            billValue = Double.parseDouble(billValueString);
            request.setAttribute("billValueOutput", billValue);

            if (billValue >= 0) {
                billValueError = false;
                request.setAttribute("billValueError", billValueError);

            } else {
                hadError = true;
            }

        } catch (NumberFormatException ex) {
                hadError = true;
        }

        String tipPercentageString = request.getParameter("tipPercentage");
        Double tipPercentage = 0.0d;

        try {
            boolean tipPercentageError = true;
            request.setAttribute("tipPercentageError", tipPercentageError);

            request.setAttribute("tipPercentageOutput", tipPercentageString);
            
            tipPercentageString = tipPercentageString.replace("%", "").trim();
            
            tipPercentage = Double.parseDouble(tipPercentageString);
            request.setAttribute("tipPercentageOutput", tipPercentage);

            if (tipPercentage >= 0) {
                tipPercentageError = false;
                request.setAttribute("tipPercentageError", tipPercentageError);

            } else {
                hadError = true;
            }

        } catch (NumberFormatException ex) {
                hadError = true;
        }

        // do validation here.
        if (hadError) {

            RequestDispatcher rd = request.getRequestDispatcher("tipCalc.jsp");
            rd.forward(request, response);
        } else {

            
            Double tipValue = billValue * ( tipPercentage / 100 );
            Double totalValue = billValue + tipValue;
            
                request.setAttribute("tipPercentage", tipPercentage);
                request.setAttribute("billValue", displayCurrency(request.getLocale(), billValue));
                request.setAttribute("tipValue", displayCurrency(request.getLocale(), tipValue));
                request.setAttribute("totalValue", displayCurrency(request.getLocale(), totalValue));

            
            RequestDispatcher rd = request.getRequestDispatcher("tcresponse.jsp");
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
    
    public String displayCurrency(Locale currentLocale, Double currencyAmount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(currencyAmount);
    }


}
