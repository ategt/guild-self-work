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
public class DeckOfCardsTest {

    public DeckOfCardsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shuffle method, of class DeckOfCards.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        DeckOfCards instance = new DeckOfCards();
        instance.shuffle();

        int sizeResult = instance.cardsRemaining();
        int sizeExpected = 52;

        Assert.assertEquals(sizeExpected, sizeResult);

        Card card = instance.draw();

        Assert.assertTrue(0 != card.getCardValue());
    }

    /**
     * Test of cardsRemaining method, of class DeckOfCards.
     */
    @Test
    public void testCardsRemaining() {
        System.out.println("cardsRemaining");
        DeckOfCards instance = new DeckOfCards();
        int expResult = 52;
        int result = instance.cardsRemaining();
        assertEquals(expResult, result);
    }

    /**
     * Test of draw method, of class DeckOfCards.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        DeckOfCards instance = new DeckOfCards();
        Card expResult = new Card(0);
        Card result = instance.draw();
        assertEquals(expResult.getCardString(), result.getCardString());
    }

}
