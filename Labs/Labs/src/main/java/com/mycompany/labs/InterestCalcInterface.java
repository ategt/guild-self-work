/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.List;

/**
 *
 * @author apprentice
 */
public interface InterestCalcInterface {

    List<CompoundingPeriod> buildCompoundingPeriodList(float annualInterestRate, int compoundingsPerYear, float initialYears, float initialInvestment);

    String getName();

    void run();
    
}
