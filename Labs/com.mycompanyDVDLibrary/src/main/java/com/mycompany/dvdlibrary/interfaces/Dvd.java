/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.interfaces;

import com.mycompany.dvdlibrary.interfaces.Note;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface Dvd {

    /**
     * @return the directorsName
     */
    String getDirectorsName();

    /**
     * @return the id
     */
    int getId();

    /**
     * @return the mPAA
     */
    String getMPAA();

    /**
     * @return the notes
     */
    List<Note> getNotes();

    /**
     * @return the releaseDate
     */
    Date getReleaseDate();

    /**
     * @return the studio
     */
    String getStudio();

    /**
     * @return the title
     */
    String getTitle();

    /**
     * @param directorsName the directorsName to set
     */
    void setDirectorsName(String directorsName);

    /**
     * @param id the id to set
     */
    void setId(int id);

    /**
     * @param mPAA the mPAA to set
     */
    void setMPAA(String mPAA);

    /**
     * @param notes the notes to set
     */
    void setNotes(List<Note> notes);

    /**
     * @param releaseDate the releaseDate to set
     */
    void setReleaseDate(Date releaseDate);

    /**
     * @param studio the studio to set
     */
    void setStudio(String studio);

    /**
     * @param title the title to set
     */
    void setTitle(String title);
    
}
