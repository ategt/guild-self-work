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
public class DvdImplementation implements Dvd {

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
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the releaseDate
     */
    @Override
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    @Override
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the mPAA
     */
    @Override
    public String getMPAA() {
        return mPAA;
    }

    /**
     * @param mPAA the mPAA to set
     */
    @Override
    public void setMPAA(String mPAA) {
        this.mPAA = mPAA;
    }

    /**
     * @return the directorsName
     */
    @Override
    public String getDirectorsName() {
        return directorsName;
    }

    /**
     * @param directorsName the directorsName to set
     */
    @Override
    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    /**
     * @return the studio
     */
    @Override
    public String getStudio() {
        return studio;
    }

    /**
     * @param studio the studio to set
     */
    @Override
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * @return the notes
     */
    @Override
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    @Override
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    /**
     * @return the id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

}
