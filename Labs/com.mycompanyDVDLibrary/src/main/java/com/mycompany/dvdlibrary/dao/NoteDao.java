/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.interfaces.AbstractDao;
import com.mycompany.dvdlibrary.dto.Identifiable;
import com.mycompany.dvdlibrary.dto.Note;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class NoteDao implements AbstractDao {

    private List<Identifiable> notes = new ArrayList();
    private int nextId = 1;
    File notesFile = new File("NotesData.txt");

    public NoteDao() {
        this(false);
    }

    protected NoteDao(boolean isATest) {

        if (isATest) {
            notesFile = new File("NotesTestData.txt");
        }

        notes = decode();

        if (notes == null) {
            notes = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public Identifiable create(Identifiable note) {
        note.setId(nextId);
        nextId++;

        notes.add(note);

        encode();

        return note;
    }

    public Identifiable get(Integer id) {

        for (Identifiable note : notes) {
            if (note != null) {
                if (note.getId() == id) {
                    return note;
                }
            }
        }

        return null;
    }

    public void update(Identifiable note) {

        for (Identifiable myNote : notes) {
            if (myNote.getId() == note.getId()) {
                notes.remove(myNote);
                notes.add(note);
            }

        }

        encode();

    }

    public void delete(Identifiable note) {
        Identifiable found = null;

        for (Identifiable currentNote : notes) {
            if (currentNote.getId() == note.getId()) {
                found = currentNote;
                break;
            }
        }

        if (found != null) {
            notes.remove(found);
        }

        encode();

    }

    public List<Identifiable> getList() {

        return notes;
    }

    public int size() {
        return notes.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (Identifiable note : notes) {
            if (highestId < note.getId()) {
                highestId = note.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        try {

            PrintWriter out = new PrintWriter(new FileWriter(notesFile));

            for (Identifiable reallyANote : notes) {

                Note note = (Note) reallyANote;

                out.print(note.getId());
                out.print(TOKEN);
                out.print(note.getNoteString());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Identifiable> decode() {

        List<Identifiable> noteList = new ArrayList<>();

        final String TOKEN = "::";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(notesFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Note note = new Note();

                int id = Integer.parseInt(stringParts[0]);
                note.setId(id);

                String content = stringParts[1];

                note.setNoteString(content);

                noteList.add(note);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return noteList;
    }

    @Override
    public List<Identifiable> searchByTitle(String title) {
        List<Identifiable> soughtDvd = new ArrayList();

        for (Identifiable dvd : notes) {
            if (dvd.getTitle() != null && title != null) {
                if (dvd.getTitle().compareToIgnoreCase(title) == 0) {
                    soughtDvd.add(dvd);
                }
            }
        }

        return soughtDvd;
    }

        @Override
    public String fixNull(String input) {
        String returnValue = null;
        if (input.trim().equalsIgnoreCase("null")) {
            input = null;
        } else if (input.trim().equalsIgnoreCase("")) {
            input = null;
        } else {
            returnValue = input;
        }
        return returnValue;
    }

    
}
