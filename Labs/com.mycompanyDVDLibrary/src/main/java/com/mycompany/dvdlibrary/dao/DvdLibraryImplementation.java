/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.interfaces.NoteDao;
import com.mycompany.dvdlibrary.interfaces.DvdLibrary;
import com.mycompany.dvdlibrary.controller.DvdLibraryController;
import com.mycompany.dvdlibrary.interfaces.Dvd;
import com.mycompany.dvdlibrary.dto.DvdImplementation;
import com.mycompany.dvdlibrary.interfaces.Note;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author apprentice
 */
public class DvdLibraryImplementation implements DvdLibrary {

    List<Dvd> dvdList;
    int nextId = 1;
    File dvdLibraryFile = new File("dvdData.txt");
    NoteDao noteDao;

    public DvdLibraryImplementation(NoteDao noteDao) {
        this(false, noteDao);
    }

    protected DvdLibraryImplementation(boolean isATest, NoteDao noteDao) {

        this.noteDao = noteDao;

        if (isATest) {
            dvdLibraryFile = new File("testDvdData.txt");
        }

        dvdList = decode();

        if (dvdList == null) {
            dvdList = new ArrayList();
        }

        nextId = determineNextId();
    }

    @Override
    public Dvd create(Dvd dvd) {
        dvd.setId(nextId);
        nextId++;

        dvdList.add(dvd);

        encode();

        return dvd;
    }

    @Override
    public Dvd get(Integer id) {

        for (Dvd dvd : dvdList) {
            if (dvd.getId() == id) {
                return dvd;
            }

        }

        return null;
    }

    @Override
    public void update(Dvd dvd) {

        Dvd foundDvd = null;

        for (Dvd currentDvd : dvdList) {
            if (currentDvd.getId() == dvd.getId()) {
                foundDvd = currentDvd;
                break;
            }

        }
        dvdList.remove(foundDvd);
        dvdList.add(dvd);

        encode();

    }

    @Override
    public void delete(Dvd dvd) {
        Dvd found = null;

        for (Dvd currentDvd : dvdList) {
            if (currentDvd.getId() == dvd.getId()) {
                found = currentDvd;
                break;
            }
        }

        if (found != null) {
            dvdList.remove(found);
        }

        encode();

    }

    @Override
    public List<Dvd> getAllDvds() {

        return dvdList;
    }

    @Override
    public int size() {
        return dvdList.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (Dvd dvd : dvdList) {
            if (highestId < dvd.getId()) {
                highestId = dvd.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try {

            PrintWriter out = new PrintWriter(new FileWriter(dvdLibraryFile));

            for (Dvd dvd : dvdList) {

                out.print(dvd.getId());
                out.print(TOKEN);
                out.print(dvd.getTitle());
                out.print(TOKEN);

                if (dvd.getReleaseDate() != null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    out.print(dateFormat.format(dvd.getReleaseDate()));
                }

                out.print(TOKEN);
                out.print(dvd.getMPAA());
                out.print(TOKEN);
                out.print(dvd.getDirectorsName());
                out.print(TOKEN);
                out.print(dvd.getStudio());
                out.print(TOKEN);

                List<Note> notes = dvd.getNotes();

                if (notes != null) {
                    for (com.mycompany.dvdlibrary.interfaces.Note note : notes) {
                        if (note != null) {
                            out.print(note.getId());
                            out.print(TOKENB);
                        }
                    }

                }

                out.println("");

            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(DvdLibraryImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Dvd> decode() {

        List<Dvd> dvdList = new ArrayList<>();

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(dvdLibraryFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Dvd dvd = new DvdImplementation();

                int id = Integer.parseInt(stringParts[0]);
                dvd.setId(id);

                dvd.setTitle(fixNull(stringParts[1]));

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;

                if (fixNull(stringParts[2]) != null) {
                    try {
                        date = dateFormat.parse(stringParts[2]);
                    } catch (ParseException ex) {
                        Logger.getLogger(DvdLibraryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                dvd.setReleaseDate(date);

                dvd.setMPAA(fixNull(stringParts[3]));
                dvd.setDirectorsName(fixNull(stringParts[4]));
                dvd.setStudio(fixNull(stringParts[5]));

                List<Note> notes = new ArrayList();

                for (String part : stringParts) {
                    if (part.contains(TOKENB)) {
                        for (String noteIdString : part.split(Pattern.quote(TOKENB))) {
                            Integer noteId = null;
                            try {
                                noteId = Integer.parseInt(noteIdString);
                            } catch (NumberFormatException numFmtEx) {

                            }
                            if (noteId != null) {
                                notes.add(noteDao.get(noteId));
                            }
                        }
                    }
                }

                dvd.setNotes(notes);

                dvdList.add(dvd);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DvdLibraryImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return dvdList;
    }

    @Override
    public List<Dvd> searchByTitle(String title) {
        List<Dvd> soughtDvd = new ArrayList();

        for (Dvd dvd : dvdList) {
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
