/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dto;

/**
 *
 * @author apprentice
 */
public class Address {

    private String firstName;
    private String lastName;
    private String type;
    private String streetAddress;
    private String state;
    private String city;
    private String country;
    private String poBox;
    private String zipcode;
    private int id;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the poBox
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * @param poBox the poBox to set
     */
    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    @Override
    public String toString() {
        String addressString = ""
                + id + ")";
        if (firstName != null) {
            addressString += "   " + firstName;
        }

        if (lastName != null) {
            addressString += " " + lastName;
        }

        if ((firstName != null || lastName != null)) {
            addressString += "\n";
        }

        if (type != null) {
            addressString += "   " + type + "\n";
        }
        if (poBox != null) {
            addressString += "   " + poBox + "\n";
        }
        if (streetAddress != null) {
            addressString += "   " + streetAddress + "\n";
        }

        if (city != null || state != null || zipcode != null) {
            addressString += "   ";

            if (city != null) {
                addressString += city + ", ";
            }

            if (state != null) {
                addressString += state + ", ";
            }

            if (zipcode != null) {
                addressString += zipcode;
            }

            addressString += "\n";

        }
        if (country != null) {
            addressString += "   " + country + "\n";
        }

        return addressString;
    }

}
