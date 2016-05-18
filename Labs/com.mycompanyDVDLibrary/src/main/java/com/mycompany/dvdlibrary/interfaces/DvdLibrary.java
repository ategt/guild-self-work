/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.interfaces;

import com.mycompany.dvdlibrary.interfaces.Dvd;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DvdLibrary {

    Date averageAge();
    Date averageAge(List<Dvd> listOfDvdObjects);
    
    Dvd create(Dvd dvd);

    void delete(Dvd dvd);

    Float findAverageNumberOfNotes();
    Float findAverageNumberOfNotes(List<Dvd> listOfDvdObjects);

    Dvd findNewestDvd();

    Dvd findOldestDvd();

    String fixNull(String input);

    Dvd get(Integer id);

    List<Dvd> getAllDvds();

    List<Dvd> searchByAge(Date date);

    Map<String, List<Dvd>> searchByDirector(String director);

    List<Dvd> searchByRating(String rating);

    List<Dvd> searchByStudio(String studio);

    List<Dvd> searchByTitle(String title);

    int size();

    void update(Dvd dvd);
    
    String searchingTechnique();

    
}
