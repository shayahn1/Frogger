package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;


public class KendallTestCases {

    @Test
    public void checkDifficultyValidationSuccess() {
        assertTrue(PlayerConfig.isValidDifficulty("Medium"));
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
}