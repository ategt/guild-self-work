/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.interfaces;

import com.mycompany.dvdlibrary.dto.Identifiable;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AbstractDao {

    Identifiable create(Identifiable dvd);

    void delete(Identifiable dvd);

    String fixNull(String input);

    Identifiable get(Integer id);

    List<Identifiable> getList();

    List<Identifiable> searchByTitle(String title);

    int size();

    void update(Identifiable dvd);
    
}
