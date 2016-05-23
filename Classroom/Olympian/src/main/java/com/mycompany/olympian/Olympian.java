/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olympian;

/**
 *
 * @author apprentice
 */
public class Olympian {

    private Event event;
    private String country = "USA";
    
    public Olympian(Event event){
        this.event = event;
    }

    public String competeInEvent(){
        System.out.println("Now Competeing For " + country + ":");
        return event.compete();
    }
    
    
    
    
    
}
