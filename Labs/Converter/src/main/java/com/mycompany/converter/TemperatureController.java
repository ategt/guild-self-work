/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "TemperatureController", urlPatterns = {"/TemperatureController"})
public class TemperatureController extends HttpServlet {

    public TemperatureController() {
        this.temperatureUnits = loadTemperatures();
    }

    ////    Fahrenheit	[°F] = [°C] × 9⁄5 + 32  	[°C] = ([°F] − 32) × 5⁄9
////    Kelvin      [K] = [°C] + 273.15             [°C] = [K] − 273.15
////    Rankine	[°R] = ([°C] + 273.15) × 9⁄5	[°C] = ([°R] − 491.67) × 5⁄9
////    Delisle	[°De] = (100 − [°C]) × 3⁄2	[°C] = 100 − [°De] × 2⁄3
////    Newton	[°N] = [°C] × 33⁄100            [°C] = [°N] × 100⁄33
////    Réaumur	[°Ré] = [°C] × 4⁄5              [°C] = [°Ré] × 5⁄4
////    Rømer	[°Rø] = [°C] × 21⁄40 + 7.5	[°C] = ([°Rø] − 7.5) × 40⁄21
    List<String> temperatureUnits;

    public Double celsiusToFahrenheit(Double temp) {
        return (temp * 9 / 5 + 32);
    }

    public Double fahrenheitToCelsius(Double temp) {
        return (temp - 32) * 5 / 9;
    }

    public Double kelvinToCelsius(Double temp) {
        return (temp - 273.15);
    }

    public Double celsiusToKelvin(Double temp) {
        return (temp + 273.15);
    }

    public Double rankineToCelsius(Double temp) {
        return (temp - 491.67) * 5 / 9;
    }

    public void preloadAttributesToPage(HttpServletRequest request) {
        preloadBehavior(request);
    }

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
        preloadBehavior(request);

        RequestDispatcher rd = request.getRequestDispatcher("temperature.jsp");
        rd.forward(request, response);

    }

    private void preloadBehavior(HttpServletRequest request) {
        request.setAttribute("hadError", false);

        //loadTemperatures();
        request.setAttribute("temperatureFromUnits", temperatureUnits);
        request.setAttribute("temperatureToUnits", temperatureUnits);
    }

    private List<String> loadTemperatures() {
        List<String> temperatureUnits = new ArrayList();

        temperatureUnits.add("Celsius");
        temperatureUnits.add("Fahrenheit");
        //temperatureUnits.add("Rankine");
        temperatureUnits.add("Kelvin");
        //this.temperatureUnits = temperatureUnits;
        return temperatureUnits;
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

        boolean hadError = false;
        //tempToConvert
        String temperatureFromUnit = request.getParameter("tempFromConversionType");
        String temperatureToUnit = request.getParameter("tempToConversionType");
        String temperatureInput = request.getParameter("tempToConvert");

        Double outputTemperature = 0.0d;
        Double inputTemperature = 0.0d;

        try {
            inputTemperature = Double.parseDouble(temperatureInput);
        } catch (NumberFormatException numberFormatException) {

            request.setAttribute("hadError", true);
            RequestDispatcher rd = request.getRequestDispatcher("temperature.jsp");
            rd.forward(request, response);

        }

        //switch (temperatureFromUnit) {
        //  case "Celsius":
        if ("Celsius".equalsIgnoreCase(temperatureFromUnit)) {

            switch (temperatureToUnit) {
                case "Celsius":
                    outputTemperature = inputTemperature;
                    break;
                case "Fahrenheit":
                    outputTemperature = this.celsiusToFahrenheit(inputTemperature);
                    break;
                case "Kelvin":
                    outputTemperature = this.kelvinToCelsius(inputTemperature);
                    break;
                default:
                    hadError = true;
                    break;
            }
        } else if (temperatureFromUnit.equalsIgnoreCase("Fahrenheit")) {
            //break;

            //case "Fahrenheit":
            switch (temperatureToUnit) {
                case "Celsius":
                    outputTemperature = this.fahrenheitToCelsius(inputTemperature);
                    break;
                case "Fahrenheit":
                    outputTemperature = inputTemperature;
                    break;
                case "Kelvin":
                    outputTemperature = this.celsiusToKelvin(this.fahrenheitToCelsius(inputTemperature));
                    break;
                default:
                    hadError = true;
                    break;
            }
            //  break;
        } else if (temperatureFromUnit.equalsIgnoreCase("Kelvin")) {

            //case "Kelvin":
            switch (temperatureToUnit) {
                case "Celsius":
                    outputTemperature = this.kelvinToCelsius(inputTemperature);
                    break;
                case "Fahrenheit":
                    outputTemperature = this.celsiusToFahrenheit(this.kelvinToCelsius(inputTemperature));
                    break;
                case "Kelvin":
                    outputTemperature = inputTemperature;
                    break;
                default:
                    hadError = true;
                    break;
            }
        } else {
            //break;
            //default:
            hadError = true;
            //break;
        }

        //}
        if (hadError) {
            request.setAttribute("hadError", hadError);
            RequestDispatcher rd = request.getRequestDispatcher("temperatureResponse.jsp");
            rd.forward(request, response);

        } else {
            request.setAttribute("outputTemperature", outputTemperature);
            request.setAttribute("temperatureInput", temperatureInput);
            request.setAttribute("temperatureFromUnit", temperatureFromUnit);
            request.setAttribute("temperatureToUnit", temperatureToUnit);

            RequestDispatcher rd = request.getRequestDispatcher("temperatureResponse.jsp");
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
