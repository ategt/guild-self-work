/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague;

/**
 *
 * @author apprentice
 */
public class BaseballController {
    
    ConsoleIO consoleIo = new ConsoleIO();
    PlayerDao playerDao = new PlayerDao();
    TeamDao teamDao = new TeamDao(playerDao);
    
    
    
    
    
}
