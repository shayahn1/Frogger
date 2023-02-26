package com.example.frogger2340project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PlayerConfigTest {

    @Test
    public void checkDifficultyValidationSuccess() {
        assertTrue(PlayerConfig.isValidDifficulty("Easy"));
        assertTrue(PlayerConfig.isValidDifficulty("Medium"));
        assertTrue(PlayerConfig.isValidDifficulty("Hard"));
    }

    @Test
    public void checkDifficultyValidationFailureNull() {
        assertFalse(PlayerConfig.isValidDifficulty(null));
    }

    @Test
    public void checkDifficultyValidationFailureInvalid() {
        assertFalse(PlayerConfig.isValidDifficulty("jlfksj"));
    }

    @Test
    public void checkNameValidationSuccess() {
        assertTrue(PlayerConfig.isValidName("             jiji"));
    }

    @Test
    public void checkNameValidationFailureNull() {
        try {
            PlayerConfig.isValidName(null);
        } catch (NullPointerException e) {
            return;
        }
    }

    @Test
    public void checkNameValidationFailureWhitespace() {
        assertFalse(PlayerConfig.isValidName("                "));
    }

    @Test
    public void checkNameValidationFailureEmpty() {
        assertFalse(PlayerConfig.isValidName(""));
    }

    @Test
    public void checkSpriteValidationSuccess() {
        assertTrue(PlayerConfig.isValidSprite("G"));
        assertTrue(PlayerConfig.isValidSprite("B"));
        assertTrue(PlayerConfig.isValidSprite("P"));
    }

    @Test
    public void checkSpriteValidationFailureNull() {
        assertFalse(PlayerConfig.isValidSprite(null));
    }

    @Test
    public void checkSpriteValidationFailureInvalid() {
        assertFalse(PlayerConfig.isValidSprite("R"));
    }
}