/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

/**
 *
 * @author apprentice
 */
public class Capital {
    private String name;
    private int population;
    private int squareMilage;
    
   // public Capital(){
        
    //}
    
    public Capital(String name, int population, int squareMilage){
        this.name = name;
        this.population = population;
        this.squareMilage = squareMilage;
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return the squareMilage
     */
    public int getSquareMilage() {
        return squareMilage;
    }

    /**
     * @param squareMilage the squareMilage to set
     */
    public void setSquareMilage(int squareMilage) {
        this.squareMilage = squareMilage;
    }
    
}
