/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.interfaces;

import com.mycompany.dvdlibrary.interfaces.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdLibrary {

    Dvd create(Dvd dvd);

    void delete(Dvd dvd);

    String fixNull(String input);

    Dvd get(Integer id);

    List<Dvd> getAllDvds();

    List<Dvd> searchByTitle(String title);

    int size();

    void update(Dvd dvd);
    
}
