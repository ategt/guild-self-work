/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringcalculator;

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
@WebServlet(name = "FlooringCalculator", urlPatterns = {"/FlooringCalculator"})
public class FlooringCalculator extends HttpServlet {

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

        RequestDispatcher rd = request.getRequestDispatcher("fail.jsp");
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

        String floorLengthString = request.getParameter("floorLength");
        String floorWidthString = request.getParameter("floorWidth");
        String floorUnitCostString = request.getParameter("floorUnitCost");

        request.setAttribute("widthError", true);
        request.setAttribute("lengthError", true);
        request.setAttribute("unitCostError", true);

        int floorLength = 0;
        int floorWidth = 0;
        int floorUnitCost = 0;
        boolean hadError = false;

        try {

            request.setAttribute("oldLength", floorLengthString);
            floorLength = Integer.parseInt(floorLengthString);
            request.setAttribute("oldLength", floorLength);
            if (floorLength >= 0 ){

            request.setAttribute("lengthError", false);
            } else {
                hadError = true;
            }
        } catch (NumberFormatException ex) {
            hadError = true;
        }

        try {

            request.setAttribute("oldWidth", floorWidthString);
            floorWidth = Integer.parseInt(floorWidthString);
            request.setAttribute("oldWidth", floorWidth);
            if ( floorWidth >= 0 ) {
            request.setAttribute("widthError", false);
            } else {
                hadError = true;
            }
        } catch (NumberFormatException ex) {
            hadError = true;
        }
        try {

            request.setAttribute("oldUnitCost", floorUnitCostString);
            floorUnitCost = Integer.parseInt(floorUnitCostString);
            request.setAttribute("oldUnitCost", floorUnitCost);
            if ( floorUnitCost >= 0 ) { 
            request.setAttribute("unitCostError", false);
            } else {
                hadError = true;
            }
        } catch (NumberFormatException ex) {
            hadError = true;
        }

        if (hadError == true) {

            request.setAttribute("hadError", hadError);

            RequestDispatcher rd = request.getRequestDispatcher("fail.jsp");
            rd.forward(request, response);
        } else {

            float floorTotalCost = floorWidth * floorLength * floorUnitCost;

            int floorArea = floorWidth * floorLength;
            float incrementsOfLabor = (float) Math.ceil(floorArea / 20 * 4);
            float totalLaborCost = incrementsOfLabor * (86 / 4);
            float grandTotalCost = floorTotalCost + totalLaborCost;

            request.setAttribute("materialCost", displayCurrency(request.getLocale(), floorTotalCost));
            request.setAttribute("floorArea", floorArea);
            request.setAttribute("laborCost", displayCurrency(request.getLocale(), totalLaborCost));
            request.setAttribute("totalFloorCost", displayCurrency(request.getLocale(), grandTotalCost));

            RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
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

    public String displayCurrency(Locale currentLocale, Float currencyAmount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(currencyAmount);
    }

}
