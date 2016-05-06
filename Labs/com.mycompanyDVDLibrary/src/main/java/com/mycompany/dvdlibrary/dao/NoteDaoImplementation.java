/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.interfaces.NoteDao;
import com.mycompany.dvdlibrary.interfaces.Note;
import com.mycompany.dvdlibrary.dto.NoteImplementation;
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
public class NoteDaoImplementation implements NoteDao {

    private List<Note> notes = new ArrayList();
    private int nextId = 1;
    File notesFile = new File("NotesData.txt");

    public NoteDaoImplementation() {
        this(false);
    }

    protected NoteDaoImplementation(boolean isATest) {

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

    @Override
    public Note create(Note note) {
        note.setId(nextId);
        nextId++;

        notes.add(note);

        encode();

        return note;
    }

    @Override
    public Note get(Integer id) {

        for (Note note : notes) {
            if (note != null) {
                if (note.getId() == id) {
                    return note;
                }
            }
        }

        return null;
    }

    @Override
    public void update(Note note) {

        for (Note myNote : notes) {
            if (myNote.getId() == note.getId()) {
                notes.remove(myNote);
                notes.add(note);
            }

        }

        encode();

    }

    @Override
    public void delete(Note note) {
        Note found = null;

        for (Note currentNote : notes) {
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

    @Override
    public List<Note> getList() {

        return notes;
    }

    @Override
    public int size() {
        return notes.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (Note note : notes) {
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

            for (Note note : notes) {

                out.print(note.getId());
                out.print(TOKEN);
                out.print(note.getNoteString());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(NoteDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Note> decode() {

        List<Note> noteList = new ArrayList<>();

        final String TOKEN = "::";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(notesFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Note note = new NoteImplementation();

                int id = Integer.parseInt(stringParts[0]);
                note.setId(id);

                String content = stringParts[1];

                note.setNoteString(content);

                noteList.add(note);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteDaoImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return noteList;
    }

}
