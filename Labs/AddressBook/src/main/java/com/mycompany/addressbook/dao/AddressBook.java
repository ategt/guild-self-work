/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.dao;

import com.mycompany.addressbook.dto.Address;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface AddressBook {

    Address create(Address address);

    void delete(Address address);

    String fixNull(String input);

    Address get(Integer id);

    List<Address> getAllAddresses();

    List<Address> searchByCity(String city);

    List<Address> searchByLastName(String lastName);

    Map<String /* City */, List<Address>>  searchByState(String state);

    List<Address> searchByZipcode(String zipcode);

    int size();

    void update(Address address);
    
}
