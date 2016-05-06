/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.interfaces;

import java.util.List;

/**
 *
 * @author apprentice
 */
public interface Inventory {

    Item create(Item item);

    void delete(Item item);

    Item get(Integer id);

    List<Item> getList();

    int size();

    void update(Item item);
    
}
