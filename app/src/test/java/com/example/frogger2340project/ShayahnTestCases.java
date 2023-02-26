package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;

import android.widget.RadioButton;

import org.junit.Test;

// import androidx.test.ext.junit.runners.AndroidJUnit4;

// @RunWith(AndroidJUnit4.class)
public class ShayahnTestCases {

    @Test
    public void onDifficultyButtonClicked() {
        MainActivity activity = new MainActivity();
        PlayerConfig config = new PlayerConfig();
        RadioButton rb = (RadioButton) activity.findViewById(R.id.Easy);
        config.onDifficultyButtonClicked(rb);
        assertEquals("Easy", config.getDifficulty());
    }

    @Test
    public void onSpriteClicked() {
    }

    @Test
    public void startGame() {
    }
}