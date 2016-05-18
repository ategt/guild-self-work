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

    public List<Dvd> searchByAge(Date date) {
        List<Dvd> result = new ArrayList();

        for (Dvd dvd : dvdList) {

            if (dvd != null && dvd.getReleaseDate() != null && dvd.getReleaseDate().after(date)) {
                result.add(dvd);
            }

        }

        return result;
    }

    public List<Dvd> searchByRating(String rating) {
        List<Dvd> result = new ArrayList();

        for (Dvd dvd : dvdList) {

            if (dvd != null && dvd.getMPAA() != null && dvd.getMPAA().equalsIgnoreCase(rating)) {
                result.add(dvd);
            }

        }

        return result;
    }

    public List<Dvd> searchByStudio(String studio) {
        List<Dvd> result = new ArrayList();

        for (Dvd dvd : dvdList) {
            if (dvd != null && dvd.getStudio() != null) {
                if (dvd.getStudio().equalsIgnoreCase(studio)) {
                    result.add(dvd);
                }
            }
        }
        return result;
    }

    public Date averageAge() {
        return averageAge(dvdList);
    }

    public Date averageAge(List<Dvd> testList) {
        Date date = new Date();
        long milliseconds = 0;
        long dvdCounter = 0;
        long averageMills;

        for (Dvd dvd : testList) {
            if (dvd != null && dvd.getReleaseDate() != null) {
                milliseconds += dvd.getReleaseDate().getTime();
                dvdCounter++;
            }
        }

        averageMills = (milliseconds / dvdCounter);
        date.setTime(averageMills);

        return date;
    }

    public Dvd findNewestDvd() {
        Date date = new Date();
        date.setTime(0);
        Dvd newestDvd = null;

        for (Dvd dvd : dvdList) {

            if (dvd.getReleaseDate() != null && dvd.getReleaseDate().after(date)) {
                date = dvd.getReleaseDate();
                newestDvd = dvd;
            }

        }

        return newestDvd;
    }

    public Dvd findOldestDvd() {
        Date date = new Date();
        Dvd oldestDvd = null;

        for (Dvd dvd : dvdList) {

            if (dvd.getReleaseDate() != null && dvd.getReleaseDate().before(date)) {
                date = dvd.getReleaseDate();
                oldestDvd = dvd;
            }

        }

        return oldestDvd;
    }

    public Float findAverageNumberOfNotes() {
        return findAverageNumberOfNotes(dvdList);
    }

    public Float findAverageNumberOfNotes(List<Dvd> testList) {
        Float noteCount = 0f;
        Float dvdCount = 0f;

        for (Dvd dvd : testList) {

            noteCount += dvd.getNotes().size();
            dvdCount++;

        }

        return (noteCount / dvdCount);
    }

    public java.util.Map<String /* Rating  */, List<Dvd>> searchByDirector(String director) {

        java.util.Map<String, List<Dvd>> results = new java.util.HashMap();
        java.util.List<Dvd> directedDvds = new ArrayList();

        for (Dvd dvd : dvdList) {
            if (dvd != null && dvd.getDirectorsName() != null && dvd.getDirectorsName().equalsIgnoreCase(director)) {
                directedDvds.add(dvd);
            }
        }

        String token = "\n\t\n";
        String rating = "";
        for (Dvd dvd : directedDvds) {
            if (!rating.contains(dvd.getMPAA())) {
                rating += dvd.getMPAA() + token;
            }
        }

        for (String rate : rating.split(token)) {

            if (rate.trim().length() > 0) {
                results.put(rate, searchByRating(rate));
            }

        }

        return results;
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

    public String searchingTechnique() {
        return "Currently Using For Loops";
    }
}
