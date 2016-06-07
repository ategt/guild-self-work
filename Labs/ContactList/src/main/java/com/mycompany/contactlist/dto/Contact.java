/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contactlist.dto;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author apprentice
 */
public class Contact implements Serializable {

    private int id;

    @NotEmpty(message = "You Must Supply A First Name")
    private String firstName;

    @NotEmpty(message = "You Must Supply A Last Name")
    private String lastName;

    private String company;

    @NotEmpty(message = "You Must Supply An Email")
    private String email;

    @NotEmpty(message = "You Must Supply A Phone Number")
    private String phone;

    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    
    private Date lastContacted;

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
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the lastContacted
     */
    public Date getLastContacted() {
        return lastContacted;
    }

    /**
     * @param lastContacted the lastContacted to set
     */
    public void setLastContacted(Date lastContacted) {
        this.lastContacted = lastContacted;
    }

}
