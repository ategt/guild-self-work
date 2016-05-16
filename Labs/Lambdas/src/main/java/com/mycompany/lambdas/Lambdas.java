/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class Lambdas {

    static List<Address> addresses = new ArrayList();

    public static void main(String[] args) {

        Address one = new Address();
        one.setCity("Akron");
        one.setStreetAddress("343 Main Street");

        addresses.add(one);

        Address two = new Address();
        two.setCity("Cleveland");
        two.setStreetAddress("343 Wall Street");

        addresses.add(two);

        Address three = new Address();
        three.setCity("Pittsburgh");
        three.setStreetAddress("343 First Street");

        addresses.add(three);

        List<Address> results;
        results = findAddressesInCity("Cleveland");

    }

    public static List<Address> findAddressesInCity(String city) {
        List<Address> results = new ArrayList();

        addresses.stream().filter((address) -> (address.getCity().equals(city))).forEach((address) -> {
            results.add(address);
        });

        return results;

    }

    public static List<Address> findAddressesInCityLambda(String city) {

        //addresses.stream().filter(Address a -> a.getCity().equals(city));
//        addresses.stream().filter( (Address a) -> {
//            return a.getCity().equals(city);
//                }
//        );
//        List<Address> found = new ArrayList();
//
//        addresses
//                .stream()
//                .filter(a -> a.getCity().equals(city))
//                .forEach(e -> found.add(e));

//        addresses
//                .stream()
//                .filter(a -> a.getCity().equals(city) || a.getCity().contains("Q"))
//                .forEach(e -> found.add(e));

        return addresses
                .stream()
                .filter(a -> a.getCity().equals(city))
                .collect(Collectors.toList());

    }

    
    public static List<Address> findAddressesInCityLaadfmbda(String city) {

        return addresses
                .stream()
                .filter(a -> a.getCity().equals(city))
                
               // .max(comparator)
                .mapToInt(Address::getNumberOfCharactersInCity)
                
                .collect(Collectors.toList());

    }

    
    
}
