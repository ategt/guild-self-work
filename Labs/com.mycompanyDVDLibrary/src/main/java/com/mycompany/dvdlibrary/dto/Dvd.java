/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Dvd {

    private String title;
    private Date releaseDate;
    private String mPAA;
    private String directorsName;
    private String studio;
    private List<Note> notes;
    private int id;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the mPAA
     */
    public String getMPAA() {
        return mPAA;
    }

    /**
     * @param mPAA the mPAA to set
     */
    public void setMPAA(String mPAA) {
        this.mPAA = mPAA;
    }

    /**
     * @return the directorsName
     */
    public String getDirectorsName() {
        return directorsName;
    }

    /**
     * @param directorsName the directorsName to set
     */
    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    /**
     * @return the studio
     */
    public String getStudio() {
        return studio;
    }

    /**
     * @param studio the studio to set
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * @return the notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
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
