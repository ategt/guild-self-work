/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

/**
 *
 * @author apprentice
 */
public class ModulusAnimation {
	public static void main( String[] args ) throws Exception
	{
            
            int repeats = 20;
            int stepsPerSecond = 50;
            int numberOfFrames = 26;
            
		for ( int i=0; i<repeats*numberOfFrames; i++ )
		{
			if ( i%numberOfFrames == 0 )
				System.out.print("|-------------\r");
                        else if ( i%numberOfFrames == 1 )        
				System.out.print("-|------------\r");
                        else if ( i%numberOfFrames == 2 )        
				System.out.print("--|-----------\r");
                        else if ( i%numberOfFrames == 3 )        
				System.out.print("---|----------\r");
                        else if ( i%numberOfFrames == 4 )        
				System.out.print("----|---------\r");
                        else if ( i%numberOfFrames == 5 )        
				System.out.print("-----|--------\r");
                        else if ( i%numberOfFrames == 6 )        
				System.out.print("------|-------\r");
                        else if ( i%numberOfFrames == 7 )        
				System.out.print("-------|------\r");
                        else if ( i%numberOfFrames == 8 )        
				System.out.print("--------|-----\r");
                        else if ( i%numberOfFrames == 9 )        
				System.out.print("---------|----\r");
                        else if ( i%numberOfFrames == 10 )        
				System.out.print("----------|---\r");
                        else if ( i%numberOfFrames == 11 )        
				System.out.print("-----------|--\r");
                        else if ( i%numberOfFrames == 12 )        
				System.out.print("------------|-\r");
                        else if ( i%numberOfFrames == 13 )        
				System.out.print("-------------|\r");
                        else if ( i%numberOfFrames == 14 )        
				System.out.print("------------|-\r");
                        else if ( i%numberOfFrames == 15 )        
				System.out.print("-----------|--\r");
                        else if ( i%numberOfFrames == 16 )        
				System.out.print("----------|---\r");
                        else if ( i%numberOfFrames == 17 )        
				System.out.print("---------|----\r");
                        else if ( i%numberOfFrames == 18 )        
				System.out.print("--------|-----\r");
                        else if ( i%numberOfFrames == 19 )        
				System.out.print("-------|------\r");
                        else if ( i%numberOfFrames == 20 )        
				System.out.print("------|-------\r");
                        else if ( i%numberOfFrames == 21 )        
				System.out.print("-----|--------\r");
                        else if ( i%numberOfFrames == 22 )        
				System.out.print("----|---------\r");
                        else if ( i%numberOfFrames == 23 )        
				System.out.print("---|----------\r");
                        else if ( i%numberOfFrames == 24 )        
				System.out.print("--|-----------\r");
                        else if ( i%numberOfFrames == 25 )        
				System.out.print("-|------------\r");

                        
                        
                        
                        
			Thread.sleep(1000/stepsPerSecond);
		}
		
	}
}

