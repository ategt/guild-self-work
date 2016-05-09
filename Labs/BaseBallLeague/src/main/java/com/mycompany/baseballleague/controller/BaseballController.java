/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.controller;

import com.mycompany.baseballleague.dto.Player;
import com.mycompany.baseballleague.dao.PlayerDao;
import com.mycompany.baseballleague.dto.Team;
import com.mycompany.baseballleague.dao.TeamDao;

/**
 *
 * @author apprentice
 */
public class BaseballController {

    ConsoleIO consoleIo = new ConsoleIO();
    PlayerDao playerDao = new PlayerDao();
    TeamDao teamDao = new TeamDao(playerDao);

    public void run() {

        String mainMenu = "=============================\n"
                + "==       Main Menu         ==\n"
                + "=============================\n"
                + "\n"
                + " 1. Create Team\n"
                + " 2. Create A Player On A Team\n"
                + " 3. List All The Teams In The League\n"
                + " 4. List All Players On A Team\n"
                + " 5. List All The Players In The League\n"
                + " 6. Trade Player From One Team To Another\n"
                + " 7. Delete A Player\n"
                + " 8. Delete A Team\n"
                + " 0. Exit";

        boolean keepRunning = true;

        while (keepRunning) {

            int choice = consoleIo.getUserIntInputRange(mainMenu, 0, 8);

            switch (choice) {
                case 1:
                    addTeam();
                    break;
                case 2:
                    addPlayer();
                    break;
                case 3:
                    listTeams();
                    break;
                case 4:
                    listPlayersOnTeam();
                    break;
                case 5:
                    listAllPlayersInLeague();
                    break;
                case 6:
                    tradePlayer();
                    break;
                case 7:
                    deletePlayer();
                    break;
                case 8:
                    deleteTeam();
                    break;
                case 0:
                    keepRunning = false;
                    displayExit();
                    break;

            }

        }

    }

    private void addPlayer() {
        Player newPlayer = buildPlayer();

//        float playerAverage = consoleIo.getUserFloatRange("Please Enter The Players Batting Average:", 0, 1);
//        newPlayer.setBattingAverage(playerAverage);
        Team playersTeam = askForTeam("What Team Is This Player Associated With?");

        while (playersTeam == null) {
            listTeams();
            playersTeam = askForTeam("That Team Could Not Be Found\nPlease Select a Team From The List:");
        }

        addPlayerToTeam(playersTeam, newPlayer);

        confirmPlayerWasAdded(playersTeam, newPlayer);

    }

    private void addPlayerToTeam(Team playersTeam, Player newPlayer) {
        playersTeam.getPlayers().add(newPlayer);
        teamDao.update(playersTeam);
    }

    private Player buildPlayer() {
        Player newPlayer = new Player();
        String playerName = consoleIo.getUserStringInput("Please Enter The Player's Name:");
        newPlayer.setPlayerName(playerName);
        int playerNumber = consoleIo.getUserIntInputRange("Please Enter The Player's Number:", 0, 100);
        
        
        
        newPlayer.setPlayerNumber(playerNumber);
        playerDao.create(newPlayer);
        return newPlayer;
    }

    private void confirmPlayerWasAdded(Team playersTeam, Player newPlayer) {
        if (playersTeam.getPlayers().contains(newPlayer)) {
            consoleIo.printStringToConsole(newPlayer.getPlayerName() + " was successfully added to " + playersTeam.getTeamName());
        } else {
            consoleIo.printStringToConsole("An error occured while trying to add the player to the team. Please contact the vendor.");
        }
    }

    private void addTeam() {
        String newTeamName = consoleIo.getUserStringInput("Please Enter The Name Of The Team To Be Added.");

        Team team = new Team();
        team.setTeamName(newTeamName);
        team.setPlayers(new java.util.ArrayList());
        teamDao.create(team);

        
        while (consoleIo.getUserConfirmation("Would you like to add players to this team.")) {
            Player newPlayer = buildPlayer();

            Team playersTeam = team;
            addPlayerToTeam(playersTeam, newPlayer);

            confirmPlayerWasAdded(playersTeam, newPlayer);

        }

    }

    private void listTeams() {

        java.util.List<Team> league = teamDao.getLeague();

        for (Team team : league) {
            //if (team.getPlayers() == null ) team.setPlayers(new java.util.ArrayList());
            String teams = team.getId() + ") " + team.getTeamName() + " - " + team.getPlayers().size() + " Players";
            consoleIo.printStringToConsole(teams);

        }

    }

    private void listPlayersOnTeam() {

        Team team = askForTeam();

        listPlayersOnTeam(team);

    }

    private void listPlayersOnTeam(Team team) {
        if (team != null) {
            java.util.List<Player> players = team.getPlayers();

            for (Player player : players) {
                String playerStats = player.getId() + ") " + player.getPlayerName() + "\t" + player.getPlayerNumber() + "\t" + player.getBattingAverage();
                consoleIo.printStringToConsole(playerStats);

            }
        }
        consoleIo.printStringToConsole("");
    }

    private void tradePlayer() {

        Player player = askForPlayer();

        Team oldTeam = teamDao.get(player.getId());

        oldTeam.getPlayers().remove(player);

        Team newTeam = askForTeam("What team is that player being traded to?");

        newTeam.getPlayers().add(player);

        Player tradedPlayer = askForPlayer("Who is " + player.getPlayerName() + " being traded for?", newTeam);

        newTeam.getPlayers().remove(tradedPlayer);
        oldTeam.getPlayers().add(player);
        teamDao.update(newTeam);
        teamDao.update(oldTeam);

        String statusUpdate = "The " + oldTeam.getTeamName() + " just traded " + player.getPlayerName()
                + " to the " + newTeam.getTeamName() + " for " + tradedPlayer.getPlayerName() + ".";

        consoleIo.printStringToConsole(statusUpdate);

    }

    private void deletePlayer() {
        Player player = askForPlayer("Which player would you like to delete?");

        if (player != null) {
            playerDao.delete(player);
        } else {
            consoleIo.printStringToConsole("That player could not be found.");
        }
    }

    private void deleteTeam() {

        Team teamToBeDeleted = askForTeam("Which team would you like to delete?");
        if (teamToBeDeleted != null) {
            String confirmString = consoleIo.getUserStringInput("Are you sure you want to delete " + teamToBeDeleted.getTeamName() + "?\n Please Press \"Y\" to confirm, any other key to abort.");

            if (confirmString.equalsIgnoreCase("Y")) {
                teamDao.delete(teamToBeDeleted);
            } else {
                consoleIo.printStringToConsole("Operation aborted by user, No action was performed.");
            }
        } else {
            consoleIo.printStringToConsole("The team entered could not be found.");
        }
    }

    private void listAllPlayersInLeague() {
        for (Team team : teamDao.getLeague()) {
            listPlayersOnTeam(team);
        }
    }

    private Player askForPlayer() {
        return askForPlayer("Please Enter A Player's Name Or Number:");
    }

    private Player askForPlayer(String prompt) {
        return askForPlayer(prompt, null);
    }

    private Player askForPlayer(String prompt, Team team) {

        Player player = null;

        if (team != null) {
            listPlayersOnTeam(team);
        }

        String responseString = consoleIo.getUserStringInput(prompt);

        boolean isANumber = false;
        int responseNumber = 0;

        try {
            responseNumber = Integer.parseInt(responseString);
            isANumber = true;

        } catch (NumberFormatException numFmtEx) {

        }

        if (isANumber) {

            if (responseNumber < 100) {
                player = getPlayerByJerseyNumber(responseNumber);
            } else {
                player = playerDao.get(responseNumber);
            }

        } else {
            player = getPlayerByName(responseString);
        }

        return player;
    }

    private Player getPlayerByName(String playerName) {
        Player player = null;
        java.util.List<Player> playerList = new java.util.ArrayList();

        for (Player currentPlayer : playerDao.getList()) {

            if (currentPlayer.getPlayerName().trim().equalsIgnoreCase(playerName)) {
                playerList.add(currentPlayer);
            }

        }

        if (playerList.size() == 1) {
            player = playerList.get(0);
        } else if (playerList.size() == 0) {
            consoleIo.printStringToConsole("That Name could Not Be Found.");
        } else {
            player = getPlayerByIdNumber("Your search has returned more than one player with that name.\n Please select the player by ID Number.", playerList);
        }

        return player;
    }

    private Player getPlayerByIdNumber(String prompt, java.util.List<Player> playerList) {
        Player player = null;

        listPlayers(playerList);

        int inputInt = consoleIo.getUserIntInputPositive(prompt);

        player = playerDao.get(inputInt);

        return player;
    }

    private void listPlayers(java.util.List<Player> playerList) {

        for (Player player : playerList) {
            String playerStats = player.getId() + ") " + player.getPlayerName() + "\t" + player.getPlayerNumber() + "\t" + player.getBattingAverage();
            consoleIo.printStringToConsole(playerStats);
        }

        consoleIo.printStringToConsole("");
    }

    private Player getPlayerByJerseyNumber(int jerseyNumber) {
        Player player = null;

        for (Player currentPlayer : playerDao.getList()) {
            if (currentPlayer.getPlayerNumber() == jerseyNumber) {
                player = currentPlayer;
                break;
            }
        }

        return player;
    }

    private Team askForTeam() {
        return askForTeam("Please Enter A Team Name:");
    }

    private Team askForTeam(String prompt) {
        Team returnedTeam = null;

        String inputString = consoleIo.getUserStringInput(prompt);

        boolean isANumber = false;
        int responseNumber = 0;

        try {
            responseNumber = Integer.parseInt(inputString);
            isANumber = true;

        } catch (NumberFormatException numFmtEx) {

        }

        if (isANumber) {
            returnedTeam = teamDao.get(responseNumber);
        } else {

            for (Team team : teamDao.getLeague()) {

                if (inputString.trim().equalsIgnoreCase(team.getTeamName().trim())) {
                    returnedTeam = team;
                    break;
                }
            }
        }

        return returnedTeam;
    }

    public void displayExit() {
        consoleIo.printStringToConsole("Have a nice day.");
    }

}
