/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringcalculator;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
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

        RequestDispatcher rd = request.getRequestDispatcher("entry.jsp");
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

        //request.get
        
        String floorLengthString = request.getParameter("floorLength");
        String floorWidthString = request.getParameter("floorWidth");
        String floorUnitCostString = request.getParameter("floorUnitCost");
        
        
        int floorLength = Integer.parseInt(floorLengthString);
        int floorWidth = Integer.parseInt(floorWidthString);
        int floorUnitCost = Integer.parseInt(floorUnitCostString);
               
        
        
        float floorTotalCost = floorWidth * floorLength * floorUnitCost;
        
        
        request.setAttribute("materialCost", displayCurrency( request.getLocale(), floorTotalCost));

        
        int floorArea = floorWidth * floorLength;
        float incrementsOfLabor = (float) Math.ceil(floorArea / 20 * 4);
        float totalLaborCost = incrementsOfLabor * (86 / 4);
        float grandTotalCost = floorTotalCost + totalLaborCost;
        
        request.setAttribute("floorArea", floorArea);
        request.setAttribute("laborCost", displayCurrency( request.getLocale(), totalLaborCost));
        request.setAttribute("totalFloorCost", displayCurrency( request.getLocale(), grandTotalCost ) );

        RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
        rd.forward(request, response);
        
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

//    public String displayCurrency(Double currencyAmount){
//        
//        Locale currentLocale = httpServletRequest.getLocale();
//        
//        Locale locale = 
//        return displayCurrency( , currencyAmount);
//    }
    
    public String displayCurrency( Locale currentLocale , Float currencyAmount ) {

    //Double currencyAmount = new Double(9876543.21);
    Currency currentCurrency = Currency.getInstance(currentLocale);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);

    return currencyFormatter.format(currencyAmount);
//    System.out.println(
//        currentLocale.getDisplayName() + ", " +
//        currentCurrency.getDisplayName() + ": " +
//        currencyFormatter.format(currencyAmount));
}
    
}
