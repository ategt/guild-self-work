/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import com.mycompany.dvdlibrary.interfaces.DvdLibrary;
import com.mycompany.dvdlibrary.dao.DvdLibraryImplementation;
import com.mycompany.dvdlibrary.interfaces.NoteDao;
import com.mycompany.dvdlibrary.dao.NoteDaoImplementation;
import com.mycompany.dvdlibrary.interfaces.Dvd;
import com.mycompany.dvdlibrary.interfaces.Dvd;
import com.mycompany.dvdlibrary.dto.DvdImplementation;
import com.mycompany.dvdlibrary.interfaces.Note;
import com.mycompany.dvdlibrary.dto.NoteImplementation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    NoteDao noteDao = new NoteDaoImplementation();
    DvdLibrary dvdLibrary = new DvdLibraryImplementation(noteDao);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
                    + "7. Bonus Menu\n"
                    + "0. Exit\n";

            int userChoice = consoleIo.getUserIntInputRange(mainMenu, 0, 7);

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
                    findById();
                    break;
                case 7:
                    bonusMenu();
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
            dvdString += convertToString(dvd) + "\n";
        }

        consoleIo.printStringToConsole(dvdString);

        if (foundDvds.size() == 1) {
            String editInput = consoleIo.getUserStringInput("Would You Like To Edit This DVD Information?\n Press \"Y\" to edit.");
            if (editInput != null && foundDvds.get(0) != null) {
                if (editInput.equalsIgnoreCase("Y")) {
                    Dvd dvdToEdit = foundDvds.get(0);
                    editDvdInfo(dvdToEdit);
                    dvdLibrary.update(dvdToEdit);

                }
            }
        }

    }

    public void findById() {

        listAllDvds();
        int id = consoleIo.getUserIntInputPositive("Please Enter An ID Number To Find:");

        displayAndEdit(id);

    }

    public void displayAndEdit(int id) {
        Dvd dvd = dvdLibrary.get(id);
        consoleIo.printStringToConsole(convertToString(dvd));

        String editInput = consoleIo.getUserStringInput("Would You Like To Edit This DVD Information?\n Press \"Y\" to edit.");
        if (editInput != null) {
            if (editInput.equalsIgnoreCase("Y")) {
                editDvdInfo(dvd);
                dvdLibrary.update(dvd);

            }
        }
    }

    public void editDvdInfo(Dvd dvd) {
        if (dvd != null) {

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

            inputString = consoleIo.getUserStringInput("Please Enter Release Date in yyyy-MM-dd format:");

            Date date = null;

            if (inputString.equalsIgnoreCase("")) {
                inputString = null;
            } else if (inputString.equalsIgnoreCase("-")) {
                inputString = null;
                dvd.setReleaseDate(null);
            } else {

                try {
                    date = dateFormat.parse(inputString);
                } catch (ParseException ex) {
                    Logger.getLogger(DvdLibraryController.class.getName()).log(Level.SEVERE, null, ex);
                }

                dvd.setReleaseDate(date);
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

                Note note = new NoteImplementation();

                if (inputString.equalsIgnoreCase("")) {
                    inputString = null;
                    moreNotes = false;

                } else {

                    note.setNoteString(inputString);
                    noteDao.create(note);

                    List<Note> notes = dvd.getNotes();

                    if (notes == null) {
                        notes = new ArrayList();
                        dvd.setNotes(notes);
                    }

                    notes.add(note);

                }

            }

        }

    }

    private Dvd inputDvd() {
        Dvd newDvd = new DvdImplementation();

        editDvdInfo(newDvd);

        return newDvd;
    }

    private String convertToString(Dvd dvd) {

        String dvdInfo = dvd.getId() + ") " + dvd.getTitle() + "\n";
        if (dvd.getReleaseDate() != null) {
            dvdInfo += "Release Date: " + dateFormat.format(dvd.getReleaseDate()) + "\n";
        }
        if (dvd.getDirectorsName() != null) {
            dvdInfo += "Director's Name: " + dvd.getDirectorsName() + "\n";
        }
        if (dvd.getStudio() != null) {
            dvdInfo += "Studio: " + dvd.getStudio() + "\n";
        }
        if (dvd.getMPAA() != null) {
            dvdInfo += "Rating: " + dvd.getMPAA() + "\n";
        }

        if (dvd.getNotes() != null) {
            dvdInfo += "Notes:";
            for (Note note : dvd.getNotes()) {
                if (note != null) {
                    dvdInfo += "  " + note.getNoteString() + "\n";
                }
            }
        }

        return dvdInfo;
    }

    private void bonusMenu() {
        new AdditionalMenu( this, consoleIo, 
            noteDao,
            dvdLibrary).run();
    }

}
