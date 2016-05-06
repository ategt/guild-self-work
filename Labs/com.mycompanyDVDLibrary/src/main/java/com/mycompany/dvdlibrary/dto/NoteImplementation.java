/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dto;

import com.mycompany.dvdlibrary.interfaces.Note;

/**
 *
 * @author apprentice
 */
public class NoteImplementation implements Note {
    private int id;
    private String noteString;

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

    /**
     * @return the noteString
     */
    @Override
    public String getNoteString() {
        return noteString;
    }

    /**
     * @param noteString the noteString to set
     */
    @Override
    public void setNoteString(String noteString) {
        this.noteString = noteString;
    }
}
