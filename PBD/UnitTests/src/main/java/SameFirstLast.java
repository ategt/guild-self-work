/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class SameFirstLast {

    public boolean sameFirstLast(int[] numbers) {

        // This method tests if the first and last int in an array are equal'.
        boolean condition = false;

        if (numbers.length > 0) {

            if (numbers[0] == numbers[numbers.length - 1]) {
                condition = true;
            }

        }

        return condition;
    }

}
