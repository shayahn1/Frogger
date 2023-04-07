package com.example.frogger2340project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameWin extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView scoreTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_win);
        scoreTV = findViewById(R.id.scoreText);
        int score = getIntent().getExtras().getInt("score");
        scoreTV.setText(scoreTV.getText() + "" + score);
    }

    public void restart(View view) {
        Intent intent = new Intent(GameWin.this, PlayerConfig.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
