/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labs;

import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class GamebotApp {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("consoleApplicationContext.xml");
        
        //ConsoleIO consoleIo = new ConsoleIO();
        //FlooringMasteryController flooringMasteryController = ctx.getBean(FlooringMasteryController.class);
        //flooringMasteryController.run();
        
        ConsoleIO consoleIo = ctx.getBean("consoleIo", ConsoleIO.class);
        
        LuckySevens luckySevens = new LuckySevens();
        Factorizer factorizer = new Factorizer();
        InterestCalculator interestCalculator = new InterestCalculator();
        EnhancedInterestCalculator enhancedInterestCalculator = new EnhancedInterestCalculator();
        StudentQuizScores studentQuizScores = new StudentQuizScores();

        java.util.List<Game> games = new ArrayList();

        games.add(luckySevens);
        games.add(factorizer);
        games.add(interestCalculator);
        games.add(enhancedInterestCalculator);
        games.add(studentQuizScores);

        String menu = "Please choose a program to run.\n"
                + "1. Lucky Sevens\n"
                + "2. Factorizer\n "
                + "3. Interest Calculator\n"
                + "4. Enhanced Interest Calculator\n"
                + "5. Student Quiz Scores\n"
                + "0. Exit";

        boolean done = false;

        while (!done) {

            for (Game game : games) {
                consoleIo.printStringToConsole((games.indexOf(game) + 1) + ") Game name: " + game.getName());
            }

            String input = consoleIo.getUserStringInput("\nPlease enter a game name to play, Or type exit to exit.");
            boolean isNumber = false;
            int number = 0;

            try {
                number = Integer.parseInt(input);
                isNumber = true;
            } catch (NumberFormatException numFmtEx) {

            }

            if (isNumber) {
                if (number == 0) {
                    done = true;

                } else {
                    games.get(number - 1).run(consoleIo);
                }
            } else {

                for (Game game : games) {
                    if (input.equalsIgnoreCase(game.getName())) {
                        game.run(consoleIo);
                        break;
                    }

                }

                if ("exit".equalsIgnoreCase(input)) {
                    done = true;
                }
            }
        }

//            Game game = null;
//            
//            int choice = consoleIo.getUserIntInputRange(menu, 0, 5);
//        
//            switch(choice){
//                case 1:
//                    game = new LuckySevens();
//                    break;
//                case 2:
//                    game = new Factorizer();
//                    break;
//                case 3:
//                    game 
//                    
//            }
//            
    }
}
