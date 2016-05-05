/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague;

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
public class PlayerDao {
    
    List<Player> players;
    int nextId;
    File playerDataFile = new File("PlayersData.txt");

    ;

    public PlayerDao () {
        this(false);
    }

    protected PlayerDao (boolean isATest) {

        if (isATest) {
            playerDataFile = new File("PlayersTestData.txt");
        }

        players = decode();

        if (players == null) {
            players = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }

        nextId = determineNextId();
    }

    public Player create(Player player) {
        player.setId(nextId);
        nextId++;

        players.add(player);

        encode();

        return player;
    }

    public Player get(Integer id) {

        for (Player player : players) {
            if (player != null) {
                if (player.getId() == id) {
                    return player;
                }
            }
        }

        return null;
    }

    public void update(Player player) {
        Player found = null;

        for (Player currentPlayer : players) {
            if (currentPlayer.getId() == player.getId()) {
                found = currentPlayer;
                break;
            }
        }

        if (found != null) {
            players.remove(found);
            players.add(player);
        }

        encode();

    }

    public void delete(Player player) {
        Player found = null;

        for (Player currentPlayer : players) {
            if (currentPlayer.getId() == player.getId()) {
                found = currentPlayer;
                break;
            }
        }

        if (found != null) {
            players.remove(found);
        }

        encode();

    }

    public List<Player> getList() {

        return players;
    }

    public int size() {
        return players.size();
    }

    private int determineNextId() {
        int highestId = 100;

        for (Player player : players) {
            if (highestId < player.getId()) {
                highestId = player.getId();
            }
        }

        highestId++;
        return highestId++;
    }

    private void encode() {

        final String TOKEN = "::";
        try {

            PrintWriter out = new PrintWriter(new FileWriter(playerDataFile));

            for (Player player : players) {

                out.print(player.getId());
                out.print(TOKEN);
                out.print(player.getPlayerName());
                out.print(TOKEN);
                out.print(player.getPlayerNumber());
                out.print(TOKEN);
                out.print(player.getBattingAverage());
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(PlayerDao .class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<Player> decode() {

        List<Player> playerList = new ArrayList<>();

        final String TOKEN = "::";

        try {

            if (!playerDataFile.exists()) {
                playerDataFile.createNewFile();
            }

            Scanner sc = new Scanner(new BufferedReader(new FileReader(playerDataFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Player player = new Player();

                int id = Integer.parseInt(stringParts[0]);
                player.setId(id);

                String content = stringParts[1];

                player.setPlayerName(content);

                content = stringParts[2];

                try {

                    int playerNumber = Integer.parseInt(content);
                    player.setPlayerNumber(playerNumber);

                } catch (NumberFormatException numFmtEx) {

                }

                String battingAverage = stringParts[3];

                try {

                    float battingAverageFloat = Float.parseFloat(battingAverage);
                    player.setBattingAverage(battingAverageFloat);

                } catch (NumberFormatException numFmtEx) {

                }

                playerList.add(player);
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerDao .class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerDao .class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return playerList;
    }

}
