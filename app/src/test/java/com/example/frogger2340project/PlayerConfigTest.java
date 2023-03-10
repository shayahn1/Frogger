package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PlayerConfigTest {

    //Kendall Taylor
    @Test
    public void checkDifficultyValidationSuccess() {
        assertTrue(PlayerConfig.isValidDifficulty("Easy"));
        assertTrue(PlayerConfig.isValidDifficulty("Medium"));
        assertTrue(PlayerConfig.isValidDifficulty("Hard"));
    }

    //Kendall Taylor
    @Test
    public void checkDifficultyValidationFailureNull() {
        assertFalse(PlayerConfig.isValidDifficulty(null));
    }

    //Kendall Taylor
    @Test
    public void checkDifficultyValidationFailureInvalid() {
        assertFalse(PlayerConfig.isValidDifficulty("jlfksj"));
    }

    //Yunsu Kim
    @Test
    public void checkNameValidationSuccess() {
        assertTrue(PlayerConfig.isValidName("             jiji"));
    }

    //Yunsu Kim
    @Test
    public void checkNameValidationFailureNull() {
        try {
            PlayerConfig.isValidName(null);
        } catch (NullPointerException e) {
            return;
        }
    }

    //Yunsu Kim
    @Test
    public void checkNameValidationFailureWhitespace() {
        assertFalse(PlayerConfig.isValidName("                "));
    }

    //Yunsu Kim
    @Test
    public void checkNameValidationFailureEmpty() {
        assertFalse(PlayerConfig.isValidName(""));
    }

    //Aidan Pek
    @Test
    public void checkSpriteValidationSuccess() {
        assertTrue(PlayerConfig.isValidSprite("G"));
        assertTrue(PlayerConfig.isValidSprite("B"));
        assertTrue(PlayerConfig.isValidSprite("P"));
    }

    //Laltih Siripurapu
    @Test
    public void checkSpriteValidationFailureNull() {
        assertFalse(PlayerConfig.isValidSprite(null));
    }

    //Kendall Taylor
    @Test
    public void checkSpriteValidationFailureInvalid() {
        assertFalse(PlayerConfig.isValidSprite("R"));
    }

    //Laltih Siripurapu
    @Test
    public void checkLivesCount() {
        assertEquals(GameView.livesCount("Easy"), 3, 0);
        assertEquals(GameView.livesCount("Medium"), 2, 0);
        assertEquals(GameView.livesCount("Hard"), 1, 0);
    }
}