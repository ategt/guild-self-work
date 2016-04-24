/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class Hangman {

    public static void main(String[] args) {
        String[] wordsToChooseFromArray = {"package",
            "garage",
            "outhouse",
            "deprecated",
            "warehouse",
            "ArrayList",
            "HashMap",
            "Abstract",
            "spaghetti"};

        Random random = new Random();
        Scanner keyboard = new Scanner(System.in);

        int wordIndex = random.nextInt(wordsToChooseFromArray.length);
        String wordToGuess = wordsToChooseFromArray[wordIndex].toLowerCase();

        int lengthOfWord = wordToGuess.length();

        char[] charsInWord = new char[lengthOfWord];

        int wrongGuesses = 0;
        int maxWrongGuesses = 6;

        char[] wrongLettersGuessed = new char[wrongGuesses];

        boolean keepPlaying = true;
        char charGuessed = 'a';

        int currentGuess = 0;
        boolean guessedRight = false;

        while (keepPlaying) {

            guessedRight = false;

            System.out.print("What is your guess?");
            charGuessed = keyboard.next().toLowerCase().charAt(0);
            System.out.println("");

            System.out.println("You have guessed:" + charGuessed);

            // Figure out if the guess is right or wrong.
            for (char testChar : charsInWord) {
                if (charGuessed == testChar) {
                    guessedRight = true;
                    break;
                }

            }

            // If wrong add to wrong letters.
            //      increment wrong counter.
            if (!guessedRight) {
                System.out.println("You have guessed wrong.");
                wrongGuesses++;

                // Add this guess to the list of wrong guesses.
                //wrongLettersGuessed
                for (int i = 0; i < wrongLettersGuessed.length; i++) {
                    if (wrongLettersGuessed[i] == '\u0000') {
                        wrongLettersGuessed[i] = charGuessed;
                        break;
                    }
                }

                System.out.println("You only have " + (maxWrongGuesses - wrongGuesses) + " left!");
            } else {
                System.out.println("You have guessed right!");

                for (int i = 0; i < charsInWord.length; i++) {
                    if (charsInWord[i] == charGuessed) {

                    }
                }

            }

            // If right fill in on display line thing.
            // Display everything
            if (wrongGuesses > maxWrongGuesses) {
                keepPlaying = false;
            }

        }

    }
}
