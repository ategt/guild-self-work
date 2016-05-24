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
import java.text.Collator;
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
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdLibraryLambdaImplementation implements DvdLibrary {

    List<Dvd> dvdList;
    int nextId = 1;
    File dvdLibraryFile = new File("dvdData.txt");
    NoteDao noteDao;

    public DvdLibraryLambdaImplementation(NoteDao noteDao) {
        this(false, noteDao);
    }

    protected DvdLibraryLambdaImplementation(boolean isATest, NoteDao noteDao) {

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

        int highestId = dvdList.stream()
                .mapToInt(Dvd::getId)
                .max()
                .getAsInt();

        int startingId = 1;

        highestId = (startingId > highestId) ? startingId : highestId;

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try (PrintWriter out = new PrintWriter(new FileWriter(dvdLibraryFile))) {

            dvdList.stream()
                    .map(dvd -> covertToString(dvd, TOKEN, TOKENB))
                    .forEach((outputString) -> {
                        out.println(outputString);
                    });

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(DvdLibraryLambdaImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String covertToString(Dvd dvd, final String TOKEN, final String TOKENB) {
        String encodedLine = "";

        encodedLine += dvd.getId();
        encodedLine += TOKEN;
        encodedLine += dvd.getTitle();
        encodedLine += TOKEN;

        encodedLine += getFormattedReleaseDate(dvd);

        encodedLine += TOKEN;
        encodedLine += dvd.getMPAA();
        encodedLine += TOKEN;
        encodedLine += dvd.getDirectorsName();
        encodedLine += TOKEN;
        encodedLine += dvd.getStudio();
        encodedLine += TOKEN;

        encodedLine += getEncodeableNotes(dvd, TOKENB);

        return encodedLine;
    }

    private String getEncodeableNotes(Dvd dvd, final String TOKENB) {
        String encodedLine = "";
        List<Note> notes = dvd.getNotes();
        if (notes != null) {
            for (com.mycompany.dvdlibrary.interfaces.Note note : notes) {
                if (note != null) {
                    encodedLine += note.getId();
                    encodedLine += TOKENB;
                }
            }

        }
        return encodedLine;
    }

    private String getFormattedReleaseDate(Dvd dvd) {
        if (dvd.getReleaseDate() != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            return dateFormat.format(dvd.getReleaseDate());
        }
        return "";
    }

    private List<Dvd> decode() {

        List<Dvd> dvdList = new ArrayList<>();

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(dvdLibraryFile)))) {

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
            Logger.getLogger(DvdLibraryLambdaImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return dvdList;
    }

    @Override
    public List<Dvd> searchByTitle(String title) {
        List<Dvd> results = dvdList
                .stream()
                .filter(d -> d.getTitle() != null)
                .filter(
                        d -> d.getTitle()
                        .equalsIgnoreCase(title)
                )
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            results = dvdList
                    .stream()
                    .filter(d -> d.getTitle() != null)
                    .filter(
                            d -> d.getTitle().toLowerCase().contains(title.toLowerCase())
                    //.equalsIgnoreCase(title)
                    )
                    .collect(Collectors.toList());
        }

        return results;
    }

    public List<Dvd> searchByAge(Date date) {
        return dvdList
                .stream()
                .filter(d -> d.getReleaseDate() != null)
                .filter(d -> d.getReleaseDate().after(date))
                .collect(Collectors.toList());
    }

    public List<Dvd> searchByRating(String rating) {
        List<Dvd> results = null;

        if (rating == null) {
            results = dvdList
                    .stream()
                    .filter(d -> d.getMPAA() == null)
                    .collect(Collectors.toList());
        } else {
            results = dvdList
                    .stream()
                    .filter(d -> d.getMPAA() != null)
                    .filter(d -> d.getMPAA().equalsIgnoreCase(rating))
                    .collect(Collectors.toList());

            if (results.isEmpty()) {
                results = dvdList
                        .stream()
                        .filter(d -> d.getMPAA() != null)
                        .filter(d -> d.getMPAA().toLowerCase().contains(rating.toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return results;
    }

    public List<Dvd> searchByStudio(String studio) {
        List<Dvd> results = null;
        if (studio == null) {
            results = dvdList
                    .stream()
                    .filter(d -> d.getStudio() == null)
                    .collect(Collectors.toList());

        } else {
            results = dvdList
                    .stream()
                    .filter(d -> d.getStudio() != null)
                    .filter(d -> d.getStudio().equalsIgnoreCase(studio))
                    .collect(Collectors.toList());

            if (results.isEmpty()) {
                results = dvdList
                        .stream()
                        .filter(d -> d.getStudio() != null)
                        .filter(d -> d.getStudio().toLowerCase().contains(studio.toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return results;
    }

    public Date averageAge() {
        return averageAge(dvdList);
    }

    public Date averageAge(List<Dvd> testList) {
        Date date = new Date();
        Double averageMilliseconds
                = testList
                .stream()
                .filter(d -> d.getReleaseDate() != null)
                .map(Dvd::getReleaseDate)
                .mapToLong(Date::getTime)
                .average()
                .getAsDouble();

        Long milliseconds = Math.round(averageMilliseconds);
        date.setTime(milliseconds);

        return date;

    }

    public Dvd findNewestDvd() {
        return dvdList
                .stream()
                .filter(d -> d.getReleaseDate() != null)
                .max((dvd1, dvd2) -> dvd1.getReleaseDate().compareTo(dvd2.getReleaseDate()))
                .get();
    }

    public Dvd findOldestDvd() {
        return dvdList
                .stream()
                .filter(d -> d.getReleaseDate() != null)
                .min((dvd1, dvd2) -> dvd1.getReleaseDate().compareTo(dvd2.getReleaseDate()))
                .get();

    }

    public Float findAverageNumberOfNotes() {
        return findAverageNumberOfNotes(dvdList);
    }

    public Float findAverageNumberOfNotes(List<Dvd> testList) {

        Double testAverage = testList.stream()
                .mapToInt(d -> d.getNotes().size())
                .average()
                .getAsDouble();

        return testAverage.floatValue();
    }

    public java.util.Map<String /* Rating  */, List<Dvd>> searchByDirector(String director) {
        //return 
        java.util.Map<String, List<Dvd>> results = dvdList.stream()
                .filter(d -> d != null)
                .filter(d -> d.getDirectorsName() != null)
                .filter(d -> d.getMPAA() != null)
                .filter(d -> d.getDirectorsName().equalsIgnoreCase(director))
                .collect(Collectors.groupingBy(Dvd::getMPAA, Collectors.toList()));

        if (results.isEmpty()) {
            results = dvdList.stream()
                    .filter(d -> d != null)
                    .filter(d -> d.getDirectorsName() != null)
                    .filter(d -> d.getMPAA() != null)
                    .filter(d -> d.getDirectorsName().toLowerCase().contains(director))
                    .collect(Collectors.groupingBy(Dvd::getMPAA, Collectors.toList()));
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

    @Override
    public String searchingTechnique() {
        return "Currently Using Lambdas";
    }

}
