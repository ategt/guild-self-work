/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import com.mycompany.dvdlibrary.dao.DvdLibrary;
import com.mycompany.dvdlibrary.dao.NoteDao;
import com.mycompany.dvdlibrary.dto.Dvd;
import com.mycompany.dvdlibrary.dto.Note;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author apprentice
 */
public class DvdLibraryController {

    ConsoleIO consoleIo = new ConsoleIO();
    NoteDao noteDao = new NoteDao();
    DvdLibrary dvdLibrary = new DvdLibrary(noteDao);

    public void run() {

        boolean choseToExit = false;
        while (!choseToExit) {

            String mainMenu = "== Main Menu == \n"
                    + "1. Add DVD\n"
                    + "2. Remove DVD\n"
                    + "3. Number of DVD's in The Library\n"
                    + "4. List DVDs by Title\n"
                    + "5. Find DVD by Title\n"
                    + "6. Find DVD by ID Number\n"
                    + "0. Exit\n";

            int userChoice = consoleIo.getUserIntInputRange(mainMenu, 0, 6);

            switch (userChoice) {
                case 1:
                    addDvd();
                    break;
                case 2:
                    removeDvd();
                    break;
                case 3:
                    showDvdLibrarySize();
                    break;
                case 4:
                    listAllDvds();
                    break;
                case 5:
                    findByTitle();
                    break;
                case 6:
                    editById();
                    break;
                case 0:
                    choseToExit = true;
            }

        }
    }

    private void addDvd() {
        Dvd newDvd = inputDvd();
        dvdLibrary.create(newDvd);

    }

    public void removeDvd() {

        listAllDvds();

        int input = consoleIo.getUserIntInputPositive("Please Enter A DVD ID Number To Delete:");

        Dvd dvd = dvdLibrary.get(input);

        if (dvd != null) {
            consoleIo.printStringToConsole(convertToString(dvd));
            consoleIo.printStringToConsole("Are you sure you want to delete this dvd?");
            String confirm = consoleIo.getUserStringInput("Press \"1\" to continue or anything else to abort:");

            if (confirm.equals("1")) {
                dvdLibrary.delete(dvd);
            } else {
                consoleIo.printStringToConsole(" No Action Was Performed.");
            }
        } else {
            consoleIo.printStringToConsole(" That DVD Could Not Be Found In The Records.");
        }
    }

    public void showDvdLibrarySize() {
        consoleIo.printStringToConsole("  " + dvdLibrary.size() + " Entries in DVD Library.");
    }

    public void listAllDvds() {
        consoleIo.printStringToConsole("--- DVD Titles ---");
        for (Dvd dvd : dvdLibrary.getAllDvds()) {

            String dvdString = dvd.getId() + ") " + dvd.getTitle();
            
            consoleIo.printStringToConsole(dvdString);
        }

        consoleIo.printStringToConsole("");

    }

    public void findByTitle() {

        listAllDvds();
        String dvdTitle = consoleIo.getUserStringInput("Please Enter A Title To Find:");

        String dvdString = "";
        List<Dvd> foundDvds = dvdLibrary.searchByTitle(dvdTitle);

        for (Dvd dvd : foundDvds) {
            dvdString += convertToString(dvd);
        }

        consoleIo.printStringToConsole(dvdString);

        if (foundDvds.size() == 1) {
            String editInput = consoleIo.getUserStringInput("Would You Like To Edit This Address?\n Press \"Y\" to edit.");
            if (editInput != null && foundDvds.get(0) != null) {
                if (editInput.equalsIgnoreCase("Y")) {
                    Dvd dvdToEdit = foundDvds.get(0);
                    editDvdInfo(dvdToEdit);
                    dvdLibrary.update(dvdToEdit);

                }
            }
        }

    }

    public void editDvdInfo(Dvd dvd) {
        if (dvd != null) {

//            consoleIo.printStringToConsole("To Delete an Existing Entry, Enter a Dash \"-\".");
//            consoleIo.printStringToConsole("You May Leave Lines Empty And Return To Edit Them Later.");
//            consoleIo.printStringToConsole("Lines Left Blank Will Not Overwrite Existing Information.\n");

            String title = consoleIo.getUserStringInput("Please Enter a DVD Title:");
            if (title.equalsIgnoreCase("")) {

            } else if (title.equalsIgnoreCase("-")) {
                title = null;
                dvd.setTitle(title);
            } else {
                dvd.setTitle(title);

            }

            String inputString = consoleIo.getUserStringInput("Please Enter Director's Name:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                dvd.setDirectorsName(inputString);

            } else {

                dvd.setDirectorsName(inputString);
            }

            inputString = consoleIo.getUserStringInput("Please Enter Release Date:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                dvd.setReleaseDate(null);
            } else {
                //dvd.setReleaseDate(parseDate(inputString));
                dvd.setReleaseDate(inputString);
            }

            inputString = consoleIo.getUserStringInput("Please Enter MPAA rating:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                dvd.setMPAA(inputString);

            } else {

                dvd.setMPAA(inputString);
            }

            inputString = consoleIo.getUserStringInput("Please Enter Studio Name:");

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                dvd.setStudio(inputString);

            } else {

                dvd.setStudio(inputString);
            }

            boolean moreNotes = true;
            while (moreNotes) {

                inputString = consoleIo.getUserStringInput("Would you like to enter a note:\n You may enter a blank line when finished.");

                Note note = new Note();

                if (inputString.equalsIgnoreCase("")) {
                    inputString = null;
                    moreNotes = false;

                } else {

                    note.setNoteString(inputString);
                    noteDao.create(note);

                    List<Note> notes = dvd.getNotes();

                    if (notes == null) {
                        notes = new ArrayList();
                    }

                    notes.add(note);
                }

            }

        }

    }

    private Date parseDate(String inputString) {
        Date releaseDate = null;
        if (inputString != null) {
            DateFormat df = DateFormat.getDateInstance();

            try {
                releaseDate = df.parse(inputString.replaceAll(Pattern.quote("/"), Pattern.quote(".")));
            } catch (ParseException ex) {
                Logger.getLogger(DvdLibraryController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return releaseDate;
    }

    public Dvd inputDvd() {
        Dvd newDvd = new Dvd();

        editDvdInfo(newDvd);

        return newDvd;
    }

    private void editById() {
        listAllDvds();

        int idNumber = consoleIo.getUserIntInputPositive("Please Enter An ID Number To Find:");

        Dvd dvdToEdit = dvdLibrary.get(idNumber);

        editDvdInfo(dvdToEdit);
        dvdLibrary.update(dvdToEdit);

    }

    private String convertToString(Dvd dvd) {

        String dvdInfo = dvd.getId() + ") " + dvd.getTitle() + "\n";
        dvdInfo += "Release Date: " + dvd.getReleaseDate() + "\n";
        dvdInfo += "Director's Name: " + dvd.getDirectorsName() + "\n";
        dvdInfo += "Studio: " + dvd.getStudio() + "\n";
        dvdInfo += "Rating: " + dvd.getMPAA() + "\n";

        if (dvd.getNotes() != null) {
            dvdInfo += "Notes:";
            for (Note note : dvd.getNotes()) {
                dvdInfo += "  " + note.getNoteString() + "\n";
            }
        }

        return dvdInfo;
    }

}
