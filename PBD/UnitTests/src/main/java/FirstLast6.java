/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class FirstLast6 {

    public boolean firstLast6(int[] numbers) {

        // This method tests if the first or last int in an array is a '6'.
        boolean condition = false;

        if (numbers[0] == 6) {
            condition = true;
        }

        if (numbers[numbers.length - 1] == 6) {
            condition = true;
        }

        return condition;
    }

}
