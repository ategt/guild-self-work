/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbookweb.dao;

//import com.mycompany.addressbook.dto.Address;
import com.thesoftwareguild.interfaces.dto.Address;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import static java.util.stream.Collectors.groupingBy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressBookDbImpl implements com.thesoftwareguild.interfaces.dao.AddressBookDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_ADDRESS = "INSERT INTO addresses (first_name, last_name, street_number, street_name, city, state, zip) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE addresses SET first_name=?, last_name=?, street_number=?, street_name=?, city=?, state=?, zip=? WHERE id=?";
    private static final String SQL_DELETE_ADDRESS = "DELETE FROM addresses WHERE id =?";
    private static final String SQL_GET_ADDRESS = "SELECT * FROM addresses WHERE id =?";
    private static final String SQL_GET_ADDRESS_LIST = "SELECT * FROM addresses";

    public AddressBookDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

//
//    protected AddressBookDbImpl(boolean isATest) {
//
//        if (isATest) {
//            addressFile = new File("testAddressData.txt");
//        }
//
//        addresses = decode();
//
////        if (addresses == null) {
////            addresses = new ArrayList();
////            System.out.println("The list was empty, making a new one.");
////        }
//        nextId = determineNextId();
//    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Address create(Address address) {

        if (address == null)
            return null;
        
        //first_name, last_name, street_number, street_name, city, state, zip
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getState(),
                address.getZip());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        address.setId(id);

        return address;
    }

    @Override
    public Address get(Integer id) {
        if (id == null)
            return null;
        return jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), id);
    }

    public void update(Address address) {

        if (address == null)
            return;
            
        if (address.getId() != null && address.getId() > 0) {

            jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                    address.getFirstName(),
                    address.getLastName(),
                    address.getStreetNumber(),
                    address.getStreetName(),
                    address.getCity(),
                    address.getState(),
                    address.getZip(),
                    address.getId());
//        } else {
//            create(address);
        }
    }

    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, id);
    }

    @Override
    public List<Address> list() {
        return jdbcTemplate.query(SQL_GET_ADDRESS_LIST, new AddressMapper());
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

            Address address = new Address();

            address.setId(rs.getInt("id"));

            address.setFirstName(rs.getString("first_name"));
            address.setLastName(rs.getString("last_name"));
            address.setStreetNumber(rs.getString("street_number"));
            address.setStreetName(rs.getString("street_name"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));

            return address;
        }

    }

    private static final String SQL_SEARCH_ADDRESS_BY_LAST_NAME = "SELECT * FROM addresses WHERE last_name = ?";
    private static final String SQL_SEARCH_ADDRESS_BY_LAST_NAME_PARTS = "SELECT * FROM addresses WHERE last_name LIKE ?";

    @Override
    public List<Address> searchByLastName(String lastName) {

        List<Address> result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_LAST_NAME, new AddressMapper(), lastName);

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_LAST_NAME_PARTS, new AddressMapper(), lastName + "%");
        }

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_LAST_NAME_PARTS, new AddressMapper(), "%" + lastName + "%");
        }

//        List<Address> result = addresses
//                .stream()
//                .filter((Address a) -> a.getLastName().equalsIgnoreCase(lastName))
//                .collect(java.util.stream.Collectors.toList());
//
//        if (result.isEmpty()) {
//            result = addresses
//                    .stream()
//                    .filter((Address a) -> a.getLastName().toLowerCase().contains(lastName.toLowerCase()))
//                    .collect(java.util.stream.Collectors.toList());
//        }
        return result;
    }

    private static final String SQL_SEARCH_ADDRESS_BY_FIRST_NAME = "SELECT * FROM addresses WHERE first_name = ?";
    private static final String SQL_SEARCH_ADDRESS_BY_FIRST_NAME_PARTIAL = "SELECT * FROM addresses WHERE first_name LIKE ?";

    @Override
    public List<Address> searchByFirstName(String firstName) {

        List<Address> result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_FIRST_NAME, new AddressMapper(), firstName);

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_FIRST_NAME_PARTIAL, new AddressMapper(), firstName + "%");
        }

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_FIRST_NAME_PARTIAL, new AddressMapper(), "%" + firstName + "%");
        }

//        List<Address> result = addresses
//                .stream()
//                .filter((Address a) -> a.getLastName().equalsIgnoreCase(lastName))
//                .collect(java.util.stream.Collectors.toList());
//
//        if (result.isEmpty()) {
//            result = addresses
//                    .stream()
//                    .filter((Address a) -> a.getLastName().toLowerCase().contains(lastName.toLowerCase()))
//                    .collect(java.util.stream.Collectors.toList());
//        }
        return result;
    }

//    @Override
//    public List<Address> searchByFirstName(String firstName) {
//
//        List<Address> result = addresses
//                .stream()
//                .filter((Address a) -> a.getFirstName().equalsIgnoreCase(firstName))
//                .collect(java.util.stream.Collectors.toList());
//
//        if (result.isEmpty()) {
//            result = addresses
//                    .stream()
//                    .filter((Address a) -> a.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
//                    .collect(java.util.stream.Collectors.toList());
//        }
//
//        return result;
//    }
    private static final String SQL_SEARCH_ADDRESS_BY_CITY = "SELECT * FROM addresses WHERE city = ?";

    @Override
    public List<Address> searchByCity(String city) {

        List<Address> result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_CITY, new AddressMapper(), city);

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_CITY, new AddressMapper(), city + "%");
        }

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_CITY, new AddressMapper(), "%" + city + "%");
        }
        return result;
    }

    private static final String SQL_SEARCH_ADDRESS_BY_STATE = "SELECT * FROM addresses WHERE state = ?";

    @Override
    //public Map<String /* City */, List<Address> /*Addresses Sorted By City*/> searchByState(String state) {
    public List<Address> searchByState(String state) {

        List<Address> result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_STATE, new AddressMapper(), state);

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_STATE, new AddressMapper(), state + "%");
        }

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_STATE, new AddressMapper(), "%" + state + "%");
        }
        return result;

//        List<Address> secondAddressLambdaMess
//                = addresses
//                .stream()
//                .filter((Address a) -> a.getState().equalsIgnoreCase(state))
//                .collect(
//                        java.util.stream.Collectors.toList()
//                //                        groupingBy(
//                //                                //(Address a) -> a.getCity(),  // This does the same as the line below it.
//                //                                Address::getCity,
//                //                                java.util.stream.Collectors.toList()
//                //                        )
//                );
//
//        if (secondAddressLambdaMess.isEmpty()) {
//            secondAddressLambdaMess
//                    = addresses
//                    .stream()
//                    .filter((Address a) -> a.getState().toLowerCase().contains(state.toLowerCase()))
//                    .collect(
//                            java.util.stream.Collectors.toList()
//                    //                        groupingBy(
//                    //                                //(Address a) -> a.getCity(),  // This does the same as the line below it.
//                    //                                Address::getCity,
//                    //                                java.util.stream.Collectors.toList()
//                    //                        )
//                    );
//        }
        //   return secondAddressLambdaMess;
    }

    private static final String SQL_SEARCH_ADDRESS_BY_ZIPCODE = "SELECT * FROM addresses WHERE zip = ?";

    @Override
    public List<Address> searchByZip(String zipcode) {

        List<Address> result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_ZIPCODE, new AddressMapper(), zipcode);

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_ZIPCODE, new AddressMapper(), zipcode + "%");
        }

        if (result.isEmpty()) {
            result = jdbcTemplate.query(SQL_SEARCH_ADDRESS_BY_ZIPCODE, new AddressMapper(), "%" + zipcode + "%");
        }

        return result;

//        List<Address> result = addresses
//                .stream()
//                .filter(a -> a.getZip() != null)
//                .filter(a -> a.getZip().equalsIgnoreCase(zipcode))
//                .collect(java.util.stream.Collectors.toList());
//
//        if (result.isEmpty()) {
//            result = addresses
//                    .stream()
//                    .filter(a -> a.getZip() != null)
//                    .filter(a -> a.getZip().toLowerCase().contains(zipcode.toLowerCase()))
//                    .collect(java.util.stream.Collectors.toList());
//        }
//        return result;
    }

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
