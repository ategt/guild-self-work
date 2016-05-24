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
    private String olympiansName = "";
    
    public Olympian(Event event, String olympiansName){
        this.event = event;
        this.olympiansName = olympiansName;
    }

    public String competeInEvent(){
        System.out.println("Now Competeing For " + country + ": " + olympiansName);
        return event.compete();
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
}
