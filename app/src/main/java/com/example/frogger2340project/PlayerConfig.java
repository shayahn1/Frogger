package com.example.frogger2340project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class PlayerConfig extends AppCompatActivity {

    EditText playerNameText;
    public String playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_config);


        playerNameText = findViewById(R.id.playerName);
        playerName = playerNameText.getText().toString();
    }
}