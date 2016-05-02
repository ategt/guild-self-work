/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.studentquizscores.dao;

import com.mycompany.studentquizscores.dto.QuizScore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class QuizScoreDaoTest {

    public QuizScoreDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class QuizScoreDao.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        QuizScore quizScore = new QuizScore();
        QuizScoreDao instance = new QuizScoreDao();
        QuizScore result = instance.create(quizScore);
        int id = result.getId();
        assertTrue(result.getId() != null);
        assertTrue(result.getId() >= instance.size());

        // Test get method.
        QuizScore returnedQuizScore = instance.get(id);
        assertEquals(returnedQuizScore, result);

    }



    /**
     * Test of size method, of class QuizScoreDao.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        QuizScoreDao instance = new QuizScoreDao();
        int expResult = 0;
        int result = instance.size();
        assertTrue(expResult < result);
    }

}
