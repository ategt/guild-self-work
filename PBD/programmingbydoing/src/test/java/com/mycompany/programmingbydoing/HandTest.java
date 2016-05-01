/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programmingbydoing;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class HandTest {
    
    public HandTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Hand.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Card card = new Card(38);
        Hand instance = new Hand();
        instance.add(card);
        Card drawn = instance.getHand().get(0);
        org.junit.Assert.assertEquals(drawn, card);
    }




    /**
     * Test of flipAllCardsUp method, of class Hand.
     */
    @Test
    public void testFlipAllCardsUp() {
        System.out.println("flipAllCardsUp");
        Hand instance = new Hand();
        instance.add(new Card(5));
        
        instance.flipAllCardsUp();

        java.util.ArrayList<Card> handUp = instance.getHand();
        
        for (Card card : handUp){
            Assert.assertEquals(card.isFaceUp(), true);
        }
        
    }


}
