/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dto;

/**
 *
 * @author apprentice
 */
public class Note {
    private int id;
    private String noteString;

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
     * @return the noteString
     */
    public String getNoteString() {
        return noteString;
    }

    /**
     * @param noteString the noteString to set
     */
    public void setNoteString(String noteString) {
        this.noteString = noteString;
    }
}
