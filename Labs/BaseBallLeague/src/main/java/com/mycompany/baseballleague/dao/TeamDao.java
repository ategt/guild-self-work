/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.dao;

import com.mycompany.baseballleague.dto.Team;
import com.mycompany.baseballleague.dto.Player;
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
import java.util.regex.Pattern;

/**
 *
 * @author apprentice
 */
public class TeamDao {
    
    // CRUD
    private List<Team> teams = new ArrayList();
    private int nextId = 1;
    private File teamsDataFile = new File("teamsDataFile.txt");
    PlayerDao playerDao;

    public TeamDao() {
        this(new PlayerDao());
    }

    public TeamDao(PlayerDao playerDao) {

        this.playerDao = playerDao;
        teams = decode();
        if (teams == null) {
            teams = new ArrayList();
            System.out.println("The list was empty, making a new one.");
        }
        nextId = determineNextId();

    }

    public Team create(Team team) {
        team.setId(nextId);
        nextId++;
        teams.add(team);

        encode();

        return team;
    }

    public Team get(Integer id) {

        for (Team myTeam : teams) {
            if (myTeam.getId() == id) {
                return myTeam;
            }

        }
        return null;
    }

    public void update(Team team) {

        for (Team myTeam : teams) {

            if (myTeam.getId() == team.getId()) {
                teams.remove(myTeam);
                teams.add(team);
            }

        }

        encode();

    }

    public void delete(Team team) {

        Team found = null;

        for (Team myTeam : teams) {

            if (myTeam.getId() == team.getId()) {
                found = myTeam;
                break;
            }

        }

        if (found != null) {
            teams.remove(found);
        }

        encode();

    }

    public int size() {
        return teams.size();
    }

    public List<Team> getLeague() {

        return teams;
    }

    private int determineNextId() {
        int highestId = 1;

        for (Team team : teams) {
            if (highestId < team.getId()) {
                highestId = team.getId();
            }
        }

        highestId++;
        return highestId;

    }

    private void encode() {

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try {
            PrintWriter out = new PrintWriter(new FileWriter(teamsDataFile));

            for (Team myTeam : teams) {
                out.print(myTeam.getId());
                out.print(TOKEN);
                out.print(myTeam.getTeamName());

                out.print(TOKEN);

                if (myTeam.getPlayers() != null) {
                    if (myTeam.getPlayers().size() > 0) {
                        encodePlayerIds(myTeam, out, TOKENB);
                    }
                }
                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void encodePlayerIds(Team myTeam, PrintWriter out, final String TOKENB) {
        for (Player player : myTeam.getPlayers()) {
            if (player != null) {
                    out.print(player.getId());
                    out.print(TOKENB);

            }
        }
    }

    private List<Team> decode() {

        List<Team> teamList = new ArrayList<>();

        final String TOKEN = "::";
        final String TOKENB = ":||:";

        try {
            if ( !teamsDataFile.exists() )
                try {
                    teamsDataFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scanner sc = new Scanner(new BufferedReader(new FileReader(teamsDataFile)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Team team = new Team();

                int id = Integer.parseInt(stringParts[0]);
                team.setId(id);

                team.setTeamName(fixNull(stringParts[1]));

                if (playerDao == null) {
                    playerDao = new PlayerDao();
                }

                List<Player> playerList = team.getPlayers();
                if (playerList == null) {
                    playerList = new ArrayList();
                    team.setPlayers(playerList);
                }

                for (String part : stringParts) {
                    if (part.contains(TOKENB)) {
                        for (String playerIdString : part.split(Pattern.quote(TOKENB))) {
                            Integer playerId = null;
                            try {
                                playerId = Integer.parseInt(playerIdString);
                            } catch (NumberFormatException numFmtEx) {

                            }
                            if (playerId != null) {
                                playerList.add(playerDao.get(playerId));
                            }
                        }
                    }
                }

                teamList.add(team);

            }

            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return teamList;
    }

    private String fixNull(String lastName) {
        if (lastName.equalsIgnoreCase("null")) {
            lastName = null;
        }
        return lastName;
    }

}
