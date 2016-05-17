/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.controller;

import com.mycompany.dvdlibrary.dao.DvdLibraryImplementation;
import com.mycompany.dvdlibrary.dao.NoteDaoImplementation;
import com.mycompany.dvdlibrary.interfaces.Dvd;
import com.mycompany.dvdlibrary.interfaces.DvdLibrary;
import com.mycompany.dvdlibrary.interfaces.NoteDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
class AdditionalMenu {

    ConsoleIO consoleIo;
    NoteDao noteDao;
    DvdLibrary dvdLibrary;
    DvdLibraryController mainController;

    public AdditionalMenu(DvdLibraryController mainController, ConsoleIO consoleIo, NoteDao noteDao, DvdLibrary dvdLibrary) {

        this.consoleIo = consoleIo;
        this.noteDao = noteDao;
        this.dvdLibrary = dvdLibrary;
        this.mainController = mainController;

    }

    public void run() {

        String menu = " = Bonus Options Menu = \n"
                + "1. Average Age of A DVD\n"
                + "2. Average Number of Notes\n"
                + "3. Display Newest DVD\n"
                + "4. Display Oldest DVD\n"
                + "5. Search for DVD by Release Date\n"
                + "6. Search by Director\n"
                + "7. Search by Rating\n"
                + "8. Search by Studio\n"
                + "9. Search by Title\n"
                + "0. Return to Main Menu";

        boolean done = false;
        while (!done) {

            switch (consoleIo.getUserIntInputRange(menu, 0, 9)) {
                case 1:
                    displayAverageAge();
                    break;
                case 2:
                    displayAverageNumberOfNotes();
                    break;
                case 3:
                    displayNewestDvd();
                    break;
                case 4:
                    displayOldestDvd();
                    break;
                case 5:
                    displayDvdsByAge();
                    break;
                case 6:
                    displayDvdsByDirector();
                    break;
                case 7:
                    displayDvdsByRating();
                    break;
                case 8:
                    displayDvdsByStudio();
                    break;
                case 9:
                    displayDvdsByTitle();
                    break;
                case 0:
                    done = true;
                    break;

            }

        }

    }

    private void displayAverageAge() {
        java.util.Date date = dvdLibrary.averageAge();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String preface = "The Average Release Date For this Collection is :\n\t\t";
        consoleIo.printStringToConsole(preface + dateFormat.format(date));
    }

    private void displayAverageNumberOfNotes() {
        Float averageNumberOfNotes = dvdLibrary.findAverageNumberOfNotes();
        String preface = "The Average Number of Notes For this Collection Would Be :\n\t\t";
        consoleIo.printStringToConsole(preface + averageNumberOfNotes);
    }

    private void displayNewestDvd() {
        Dvd dvd = dvdLibrary.findNewestDvd();
        String preface = "The Newest Dvd In This Collection Would Be :\n\t\t";
        consoleIo.printStringToConsole(preface + dvd.getTitle() + "\t" + dvd.getReleaseDate());

        String response = consoleIo.getUserStringInput("Would You Like To See The Details For This Title? [Y/n]");

        if (response.equalsIgnoreCase("Y")) {
            mainController.displayAndEdit(dvd.getId());
        }
    }

    private void displayOldestDvd() {
        Dvd dvd = dvdLibrary.findOldestDvd();
        String preface = "The Newest Dvd In This Collection Would Be :\n\t\t";
        consoleIo.printStringToConsole(preface + dvd.getTitle() + "\t" + dvd.getReleaseDate());

        String response = consoleIo.getUserStringInput("Would You Like To See The Details For This Title? [Y/n]");

        if (response.equalsIgnoreCase("Y")) {
            mainController.displayAndEdit(dvd.getId());
        }
    }

    private void displayDvdsByAge() {
        try {
            String dateString = consoleIo.getUserStringInput("Please Enter A Date In \"DD-MM-YYYY\" Format");
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

            Date date = dateFormat.parse(dateString);

            java.util.List<Dvd> dvdList = dvdLibrary.searchByAge(date);

            String dvdSum = "";

            for (Dvd dvd : dvdList) {
                dvdSum += dvd.getId() + ") " + dvd.getTitle() + "\t" + dateFormat.format(dvd.getReleaseDate()) + "\n";
            }

            int inputInt = consoleIo.getUserIntInputPositive(dvdSum + "\nYou may enter an Id to view that Title or \"0\" to return to the menu.");

            if (inputInt != 0) {
                mainController.displayAndEdit(inputInt);
            }

        } catch (ParseException ex) {
            consoleIo.printStringToConsole("The System Could Not Understand That Date.");
        }

    }

    private void displayDvdsByDirector() {
        String director = consoleIo.getUserStringInput("Please Enter A Director's Name:");

        Map<String, List< Dvd>> mapListDvds = dvdLibrary.searchByDirector(director);
        String summary = "";

        for (String key : mapListDvds.keySet()) {
            summary += "Rating: " + key + "\n";

            for (Dvd dvd : mapListDvds.get(key)) {
                summary += "\t" + dvd.getId() + ") " + dvd.getTitle() + "\t" + dvd.getDirectorsName() + "\n";
            }

        }

        int inputInt = consoleIo.getUserIntInputPositive(summary + "\nYou may enter an Id to view that Title or \"0\" to return to the menu.");

        if (inputInt != 0) {
            mainController.displayAndEdit(inputInt);
        }

    }

    private void displayDvdsByRating() {
        String rating = consoleIo.getUserStringInput("Please Enter A Rating To Search For:");

        List< Dvd> listOfDvds = dvdLibrary.searchByRating(rating);
        String summary = "";

        for (Dvd dvd : listOfDvds) {
            summary += "\t" + dvd.getId() + ") " + dvd.getTitle() + "\t" + dvd.getMPAA() + "\t" + dvd.getDirectorsName() + "\n";
        }

        int inputInt = consoleIo.getUserIntInputPositive(summary + "\nYou may enter an Id to view that Title or \"0\" to return to the menu.");

        if (inputInt != 0) {
            mainController.displayAndEdit(inputInt);
        }

    }

    private void displayDvdsByStudio() {
        String studio = consoleIo.getUserStringInput("Please Enter A Studio To Search For:");

        List< Dvd> listOfDvds = dvdLibrary.searchByStudio(studio);
        String summary = "";

        for (Dvd dvd : listOfDvds) {
            summary += "\t" + dvd.getId() + ") " + dvd.getTitle() + "\t" + dvd.getStudio() + "\t" + dvd.getDirectorsName() + "\n";
        }

        int inputInt = consoleIo.getUserIntInputPositive(summary + "\nYou may enter an Id to view that Title or \"0\" to return to the menu.");

        if (inputInt != 0) {
            mainController.displayAndEdit(inputInt);
        }

    }

    private void displayDvdsByTitle() {
        mainController.findByTitle();
    }

}
