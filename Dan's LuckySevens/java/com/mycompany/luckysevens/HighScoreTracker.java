/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.luckysevens;

import static com.mycompany.luckysevens.LuckySevens.checkForSeven;
import static com.mycompany.luckysevens.LuckySevens.diceRollandAdd;

/**
 *
 * @author apprentice
 */
public class HighScoreTracker {
    
    public double[] getScores(double winnings, double rolls){
        
        double highScore;
        double highRoll;
        double[] highScores= new double[3];
        
        
        highScore = winnings;
        
         for (rolls = 1; winnings > 0; rolls++) {

            int diceTotal = diceRollandAdd();

            winnings = checkForSeven(diceTotal, winnings);

            //track the high score
            if (winnings >= highScore) {
                highScore = winnings;
                highRoll = rolls;
                
                highScores[0] = highScore;
                highScores[1] = highRoll;

            }//end score tracker
            
                highScores[2] = rolls;
        }//end for loop
         
         return highScores;
         
    }
}
