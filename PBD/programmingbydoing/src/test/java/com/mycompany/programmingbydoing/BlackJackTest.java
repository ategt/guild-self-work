/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class BlackJackTest {
    
    public BlackJackTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of applyWinOrLoseLogic method, of class BlackJack.
     */
    @Test
    public void testApplyWinOrLoseLogic() {
        System.out.println("applyWinOrLoseLogic");

        Hand player = new Hand();
        Card card = new Card(10);
        player.add(card);
        
        player.add(new Card(0));

        Hand dealer = new Hand();
        
        dealer.add(new Card(10));
        dealer.add(new Card(12));

        BlackJack instance = new BlackJack();

        instance.calculateTotalPoints(dealer);
        instance.calculateTotalPoints(player);


        ConsoleIO consoleIo = new ConsoleIO();

        boolean expResult = true;
        boolean result = instance.applyWinOrLoseLogic(player, consoleIo, dealer);


        assertEquals(expResult, result);

     





        player = new Hand();
        player.add(new Card(12));
        player.add(new Card(11));

        dealer = new Hand();
        
        dealer.add(new Card(10));
        dealer.add(new Card(12));


        expResult = false;
        result = instance.applyWinOrLoseLogic(player, consoleIo, dealer);
        assertEquals(expResult, result);





    }

    /**
     * Test of calculateTotalPoints method, of class BlackJack.
     */
    @Test
    public void testCalculateTotalPoints() {
        System.out.println("calculateTotalPoints");
        Hand hand = new Hand();
        Card card = new Card(10);
        hand.add(card);
        
        hand.add(new Card(0));
        BlackJack instance = new BlackJack();
        instance.calculateTotalPoints(hand);

        int expected = 21;
        int result = hand.getTotalPoints();
        Assert.assertEquals(expected, result);
        
        
    }


    /**
     * Test of convertToPoints method, of class BlackJack.
     */
    @Test
    public void testConvertToPoints() {
        System.out.println("convertToPoints");
        BlackJack instance = new BlackJack();
        int cardValue = 12;
        boolean aceHigh = false;
        int expResult = 10;
        int result = instance.convertToPoints(cardValue, aceHigh);
        assertEquals(expResult, result);
        
        cardValue = 5;
        aceHigh = false;
        expResult = 5;
        result = instance.convertToPoints(cardValue, aceHigh);
        assertEquals(expResult, result);

        cardValue = 5;
        aceHigh = true;
        expResult = 5;
        result = instance.convertToPoints(cardValue, aceHigh);
        assertEquals(expResult, result);
    
        cardValue = 1;
        aceHigh = true;
        expResult = 11;
        result = instance.convertToPoints(cardValue, aceHigh);
        assertEquals(expResult, result);
    
        cardValue = 1;
        aceHigh = false;
        expResult = 1;
        result = instance.convertToPoints(cardValue, aceHigh);
        assertEquals(expResult, result);
    
    }
}
