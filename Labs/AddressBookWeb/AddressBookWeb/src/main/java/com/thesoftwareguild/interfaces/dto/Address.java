package com.thesoftwareguild.interfaces.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


public class Address {

    
    @Min(0)
    private Integer id;
    
    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String firstName;

    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String lastName;

    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String streetName;

    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String streetNumber;

    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String city;

    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String state;

    @NotEmpty
    @NotNull
    @Size(min=1,max=45)
    private String zip;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}