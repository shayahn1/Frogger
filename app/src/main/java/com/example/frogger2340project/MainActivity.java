package com.example.frogger2340project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = (Button) findViewById(R.id.startButton);

        startGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlayerConfig.class);
            startActivity(intent);
        });
    }
}