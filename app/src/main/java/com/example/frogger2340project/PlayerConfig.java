package com.example.frogger2340project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

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
        switch (view.getId()) {
        case R.id.greenFrog:
            if (checked) {
                spriteSelected = "G";
            }
            break;
        case R.id.blueFrog:
            if (checked) {
                spriteSelected = "P";
            }
            break;
        case R.id.purpleFrog:
            if (checked) {
                spriteSelected = "B";
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
        if (difficulty != null && spriteSelected != null && !playerName.isEmpty()
                && playerName != null && !playerName.trim().isEmpty()) {
            GameView gameView = new GameView(this, difficulty, spriteSelected);
            setContentView(gameView);
        }
    }

    public static String getPlayerName() {
        return playerName;
    }

    public String getDifficulty() {return difficulty;}
}