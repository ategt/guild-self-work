/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class Inventory {

    List<Item> items;
    int nextId;
    File inventoryFile = new File("ItemsData.txt");

    ;

    public Inventory() {
        this(false);
    }

    protected Inventory(boolean isATest) {

        if (isATest) {
            inventoryFile = new File("ItemsTestData.txt");
        }

        items = decode();

        if (items == null) {
            items = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public Item create(Item item) {
        item.setId(nextId);
        nextId++;

        items.add(item);

        encode();

        return item;
    }

    public Item get(Integer id) {

        for (Item item : items) {
            if (item != null) {
                if (item.getId() == id) {
                    return item;
                }
            }
        }

        return null;
    }

    public void update(Item item) {
        Item found = null;

        for (Item currentItem : items) {
            if (currentItem.getId() == item.getId()) {
                found = currentItem;
                break;
            }
        }

        if (found != null) {
            items.remove(found);
            items.add(item);
        }

        encode();

    }

    public void delete(Item item) {
        Item found = null;

        for (Item currentItem : items) {
            if (currentItem.getId() == item.getId()) {
                found = currentItem;
                break;
            }
        }

        if (found != null) {
            items.remove(found);
        }

        encode();

    }

    public List<Item> getList() {

        return items;
    }

    public int size() {
        return items.size();
    }

    private int determineNextId() {
        int highestId = 1;

        for (Item item : items) {
            if (highestId < item.getId()) {
                highestId = item.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        try {

            PrintWriter out = new PrintWriter(new FileWriter(inventoryFile));

            for (Item item : items) {

                out.print(item.getId());
                out.print(TOKEN);
                out.print(item.getItemName());
                out.print(TOKEN);
                out.print(item.getItemCost());
                out.print(TOKEN);
                out.print(item.getQuantityInInventory());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Item> decode() {

        List<Item> itemList = new ArrayList<>();

        final String TOKEN = "::";

        try {

            if (!inventoryFile.exists()) {
                inventoryFile.createNewFile();
            }

            Scanner sc = new Scanner(new BufferedReader(new FileReader(inventoryFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Item item = new Item();

                int id = Integer.parseInt(stringParts[0]);
                item.setId(id);

                String content = stringParts[1];

                item.setItemName(content);

                content = stringParts[2];

                try {

                    int cost = Integer.parseInt(content);
                    item.setItemCost(cost);

                } catch (NumberFormatException numFmtEx) {

                }

                content = stringParts[3];

                try {

                    int cost = Integer.parseInt(content);
                    item.setQuantityInInventory(cost);

                } catch (NumberFormatException numFmtEx) {

                }

                itemList.add(item);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inventory.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Inventory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return itemList;
    }

}
