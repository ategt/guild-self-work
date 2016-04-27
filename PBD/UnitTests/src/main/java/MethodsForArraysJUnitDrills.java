/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class MethodsForArraysJUnitDrills {

    public boolean commonEnd(int[] a, int[] b) {
        boolean testPassed = false;

        if (a.length > 0 && b.length > 0) {

            if (a[0] == b[0]) {
                testPassed = true;
            }

            if (a[a.length - 1] == b[b.length - 1]) {
                testPassed = true;
            }

        }

        return testPassed;
    }

    public int sum(int[] numbers) {
        int total = 0;

        for (int number : numbers) {
            total += number;
        }

        return total;
    }

    public int[] rotateLeft(int[] numbers) {
        int[] returnArray = new int[numbers.length];

        int endHolder = numbers[0];

        for (int x = 0; x < returnArray.length - 1; x++) {
            returnArray[x] = numbers[x + 1];
        }

        returnArray[returnArray.length - 1] = endHolder;

        return returnArray;
    }

    public int[] make2(int[] a, int[] b) {
        int[] returnArray = new int[2];
        int counter = 0;

        //System.out.println("Entering First Array");
        for (int x = 0; x < a.length && x < returnArray.length; x++) {
            returnArray[x] = a[x];

            // System.out.println(a[x] + " : " + returnArray[x]);
            // System.out.println("a[" + x + "] : Ret[" + (x) + "]");
            counter = x + 1;

        }

        for (int x = 0; x < b.length && counter + x < returnArray.length; x++) {
            returnArray[counter + x] = b[x];
            //  System.out.println(b[x] + " : " + returnArray[counter + x]);
            //  System.out.println("b[" + x + "] : Ret[" + (counter + x) + "]");
        }

        // System.out.println("Exiting make2.");
        return returnArray;
    }
}
