/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.interfaces;

import com.mycompany.dvdlibrary.interfaces.Note;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface NoteDao {

    Note create(Note note);

    void delete(Note note);

    Note get(Integer id);

    List<Note> getList();

    int size();

    void update(Note note);
    
}
