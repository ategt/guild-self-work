/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

/**
 *
 * @author apprentice
 */
public class Television implements Colorable {

    
    private String color;
    
    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
    return this.color;
    }
    
}
