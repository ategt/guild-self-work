package com.mycompany.window;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class WindowEstimator {

    public static void main(String[] args) {

        // width of window = user input
        // length of window = user input
        float pricePerSQF = 3.50f;
        float pricePerLF = 2.25f;

        float Width = 0f;
        float Height = 0f;

        String widthInput = "";
        String heightInput = "";
        String sqfPriceInput = "";
        String lfPriceInput = "";

        final float MAX_WINDOW_HEIGHT = 25.5f;
        final float MAX_WINDOW_WIDTH = 18.75f;
        final float MIN_WINDOW_HEIGHT = 1.0f;
        final float MIN_WINDOW_WIDTH = 1.0f;

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the width of the window in Inches:");

        do {
            widthInput = sc.nextLine();
            Width = Float.parseFloat(widthInput);

            if (Width > MAX_WINDOW_WIDTH) {
                System.out.println("Your Width Entry must be less than eighteen and three quarters of an inch.");
                System.out.println("Please re-enter the Width of your window.");

            } else if (Width < MIN_WINDOW_WIDTH) {
                System.out.println("Your Width Entry must be more than one inch.");
                System.out.println("Please re-enter the Width of your window.");
            }
        } while ((Width > MAX_WINDOW_WIDTH) || (Width < MIN_WINDOW_WIDTH));

        System.out.println("Please enter the height of the window in Inches:");

        do {
            heightInput = sc.nextLine();
            Height = Float.parseFloat(heightInput);

            if (Height > MAX_WINDOW_HEIGHT) {
                System.out.println("Your Height Entry must be less than twenty five and one half inches.");
                System.out.println("Please re-enter the Height of your window.");
            } else if (Height < MIN_WINDOW_HEIGHT) {
                System.out.println("Your Height Entry must be more than one inch.");
                System.out.println("Please re-enter the Height of your window.");
            }
        } while ((Height > MAX_WINDOW_HEIGHT) || (Height < MIN_WINDOW_HEIGHT));

        System.out.println("Please enter your Price per Square Inch of glass:");

        sqfPriceInput = sc.nextLine();

        System.out.println("Please enter your Price per Linear Inch of Trim:");

        lfPriceInput = sc.nextLine();

        pricePerSQF = Float.parseFloat(sqfPriceInput);
        pricePerLF = Float.parseFloat(lfPriceInput);

        float SQInch = (Width * Height);
        float LinInch = ((Width * 2) + (Height * 2));

        float glassCost = (pricePerSQF * SQInch);
        float trimCost = (pricePerLF * LinInch);

        System.out.println("This is the window height " + Height + " inches");
        System.out.println("This is the window width " + Width + " inches");
        System.out.println("This is the area of the window " + SQInch + " Square Inches");
        System.out.println("This is the total Linear Inches of trim " + LinInch);
        System.out.println("This is the cost for the glass $" + glassCost);
        System.out.println("This is the cost for the trim $" + trimCost);

        float TotalCost = (glassCost + trimCost);

        System.out.println("This is the total cost for the window $" + TotalCost);

    }

}