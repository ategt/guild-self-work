/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.interfaces.AbstractDao;
import com.mycompany.dvdlibrary.controller.DvdLibraryController;
import com.mycompany.dvdlibrary.dto.Dvd;
import com.mycompany.dvdlibrary.dto.DvdImplementation;
import com.mycompany.dvdlibrary.dto.Identifiable;
import com.mycompany.dvdlibrary.dto.Note;
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
public class DvdLibrary implements AbstractDao {

    List<Dvd> dvdList;
    int nextId = 1;
    File dvdLibraryFile = new File("dvdData.txt");
    AbstractDao noteDao;

    public DvdLibrary(AbstractDao noteDao) {
        this(false, noteDao);
    }

    protected DvdLibrary(boolean isATest, AbstractDao noteDao) {

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
    public Identifiable create(Identifiable dvd) {
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
    public void update(Identifiable dvd) {

        Identifiable foundDvd = null;

        for (Identifiable currentDvd : dvdList) {
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
    public void delete(Identifiable dvd) {
        Identifiable found = null;

        for (Identifiable currentDvd : dvdList) {
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
    public List<Identifiable> getList() {

        return dvdList;
    }

    @Override
    public int size() {
        return dvdList.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (Identifiable dvd : dvdList) {
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

            for (Identifiable iDvd : dvdList) {

                
                // Ask about doing this!!
                DvdImplementation dvd = (DvdImplementation) iDvd;
                
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
                    for (com.mycompany.dvdlibrary.dto.Note note : notes) {
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
            Logger.getLogger(DvdLibrary.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Dvd> decode() {

        List<Identifiable> dvdList = new ArrayList<>();

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(dvdLibraryFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                DvdImplementation dvd = new DvdImplementation();

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
                                Note tempNote = (Note) noteDao.get(noteId);
                                notes.add(tempNote);
                            }
                        }
                    }
                }

                dvd.setNotes(notes);

                dvdList.add(dvd);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DvdLibrary.class
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
