/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryweb.dto;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class ProductCommand {
    
    @NotEmpty(message="You must enter a product name.")
    private String type;
    
    @Min(0)
    private double cost;
    
    @Min(0)
    private double laborCost;
    private int id;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public String getProductName() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setProductName(String type) {
        this.type = type;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the cost
     */
    public double getProductCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setProductCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the laborCost
     */
    public double getLaborCost() {
        return laborCost;
    }

    /**
     * @param laborCost the laborCost to set
     */
    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
