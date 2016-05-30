/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter;

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
@WebServlet(name = "ConverterController", urlPatterns = {"/ConverterController"})
public class ConverterController extends HttpServlet {

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

        RequestDispatcher rd = request.getRequestDispatcher("converter.jsp");
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
        request.setAttribute("hadError", true);

        // Evaluate input
        String conversionType = request.getParameter("conversionType");
        RequestDispatcher rd;

        switch (conversionType) {
            case "temperature":
                rd = request.getRequestDispatcher("temperature.jsp");
                break;
            case "currency":
                rd = request.getRequestDispatcher("currency.jsp");
                break;
            case "volume":
                rd = request.getRequestDispatcher("volume.jsp");
                break;
            case "mass":
                rd = request.getRequestDispatcher("mass.jsp");
                break;
            default:
                rd = request.getRequestDispatcher("converter.jsp");
                break;
        }

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
    }

// </editor-fold>
//    public void getTemperatureValues() {
//
//        List<String> temperatureConversionOptions = new ArrayList();
//
//        temperatureConversionOptions.add("Fahrenheit to Celsius");  // [°C] = ([°F] − 32) × 5⁄9
//        temperatureConversionOptions.add("Celsius to Fahrenheit");  // [°F] = [°C] × 9⁄5 + 32
//        temperatureConversionOptions.add("Celsius to Kelvin");      // [K] = [°C] + 273.15
//        temperatureConversionOptions.add("Kelvin to Celsius");      // [°C] = [K] − 273.15
//        temperatureConversionOptions.add("Kelvin to Fahrenheit");   // [°F] = [K] × 9⁄5 − 459.67
//        temperatureConversionOptions.add("Fahrenheit to Kelvin");   // [K] = ([°F] + 459.67) × 5⁄9
//
////                from Celsius                    to Celsius
////    Fahrenheit	[°F] = [°C] × 9⁄5 + 32  	[°C] = ([°F] − 32) × 5⁄9
////    Kelvin      [K] = [°C] + 273.15             [°C] = [K] − 273.15
////    Rankine	[°R] = ([°C] + 273.15) × 9⁄5	[°C] = ([°R] − 491.67) × 5⁄9
////    Delisle	[°De] = (100 − [°C]) × 3⁄2	[°C] = 100 − [°De] × 2⁄3
////    Newton	[°N] = [°C] × 33⁄100            [°C] = [°N] × 100⁄33
////    Réaumur	[°Ré] = [°C] × 4⁄5              [°C] = [°Ré] × 5⁄4
////    Rømer	[°Rø] = [°C] × 21⁄40 + 7.5	[°C] = ([°Rø] − 7.5) × 40⁄21
//    }

    
    
    
}
