/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.interfaces;

import com.mycompany.vendingmachine.dto.ItemImplementation;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface Inventory {

    ItemImplementation create(ItemImplementation item);

    void delete(ItemImplementation item);

    ItemImplementation get(Integer id);

    List<ItemImplementation> getList();

    int size();

    void update(ItemImplementation item);
    
}
