package com.example.frogger2340project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class PlayerConfig extends AppCompatActivity {

    EditText playerNameText;
    String playerName;

    String difficulty;
    String spriteSelected;

    public void onDifficultyButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.Easy:
                if(checked) {
                    difficulty = "E";
                }
                break;
            case R.id.Medium:
                if(checked) {
                    difficulty = "M";
                }
                break;
            case R.id.Hard:
                if(checked) {
                    difficulty = "H";
                }
                break;
        }
    }

    public void onSpriteClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.greenFrog:
                if(checked) {
                    spriteSelected = "G";
                }
                break;
            case R.id.blueFrog:
                if(checked) {
                    spriteSelected = "B";
                }
                break;
            case R.id.purpleFrog:
                if(checked) {
                    spriteSelected = "P";
                }
                break;
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
        if (difficulty != null && spriteSelected != null && !playerName.isEmpty() && playerName != null && !playerName.trim().isEmpty()) {
            GameView gameView = new GameView(this);
            setContentView(gameView);
        }
    }
}