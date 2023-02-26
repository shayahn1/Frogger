package com.example.frogger2340project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class PlayerConfig extends AppCompatActivity {

    private EditText playerNameText;
    private static String playerName;

    private String difficulty;
    private String spriteSelected;

    public void onDifficultyButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
        case R.id.Easy:
            if (checked) {
                difficulty = "Easy";
            }
            break;
        case R.id.Medium:
            if (checked) {
                difficulty = "Medium";
            }
            break;
        case R.id.Hard:
            if (checked) {
                difficulty = "Hard";
            }
            break;
        default:
            difficulty = null;
        }
    }

    public void onSpriteClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        playerNameText = findViewById(R.id.playerName);
        switch (view.getId()) {
        case R.id.greenFrog:
            if (checked) {
                spriteSelected = "G";
                //playerNameText.setFocusable(false);
            }
            break;
        case R.id.blueFrog:
            if (checked) {
                spriteSelected = "P";
                //playerNameText.setFocusable(false);
            }
            break;
        case R.id.purpleFrog:
            if (checked) {
                spriteSelected = "B";
                //playerNameText.setFocusable(false);
            }
            break;
        default:
            spriteSelected = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_config);

    }
    public void startGame(View view) {
        playerNameText = findViewById(R.id.playerName);
        playerName = playerNameText.getText().toString();
        if (isValidDifficulty(difficulty) && isValidSprite(spriteSelected) &&
                checkNullName(playerName) && checkWhitespaceName(playerName)
                && checkEmptyName(playerName)) {
            GameView gameView = new GameView(this, difficulty, spriteSelected);
            setContentView(gameView);
        }
    }

    /*public boolean validateDifficulty(String difficulty) {
        return difficulty != null;
    }

     */
    public static boolean isValidDifficulty(String difficulty) {
        return ((difficulty != null) && ((difficulty == "Easy") || (difficulty == "Medium")
                    || (difficulty == "Hard")));
    }

    public static boolean isValidSprite(String spriteSelected) {
        return spriteSelected != null;
    }

    public static boolean isValidName(String playerName) {
        return (playerName != null) & (!playerName.isEmpty()) && (!playerName.trim().isEmpty());
    }
    public static boolean checkNullName(String playerName) {
        return playerName != null;
    }

    public static boolean checkEmptyName(String playerName) {
        return !playerName.isEmpty();
    }

    public static boolean checkWhitespaceName(String playerName) {
        return !playerName.trim().isEmpty();
    }

    public static String getPlayerName() {
        return playerName;
    }
}