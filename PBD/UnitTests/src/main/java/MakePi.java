/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class MakePi {

    public static void main(String[] args) {
        
        MakePi mp = new MakePi();
        int[] mpArray = mp.makePi(3);
        
        for (int mpItem : mpArray){
            System.out.println(""+mpItem);
        }
        
        System.out.println("End!");
    }

    public int[] makePi(int n) {
        final int limit = 16;
        double piDouble = Math.PI;

        if (n > limit) {
            System.out.println("This method only supports input parameters up to a value of " + limit + ".");
            n = limit;
        }

        int[] piArray = new int[n];

        String piString = Double.toString(piDouble);

        String[] piStringArray = piString.replace(".", "").split("");

        for (int x = 0; x < piArray.length; x++) {

            // do array stuff here
            piArray[x] = Integer.parseInt(piStringArray[x]);
            //piReturnArray[x] = piFullArray[x];
        }
        return piArray;
    }
}
