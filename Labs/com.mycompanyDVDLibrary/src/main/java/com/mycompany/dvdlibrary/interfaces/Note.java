/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.interfaces;

/**
 *
 * @author apprentice
 */
public interface Note {

    /**
     * @return the id
     */
    int getId();

    /**
     * @return the noteString
     */
    String getNoteString();

    /**
     * @param id the id to set
     */
    void setId(int id);

    /**
     * @param noteString the noteString to set
     */
    void setNoteString(String noteString);
    
}
