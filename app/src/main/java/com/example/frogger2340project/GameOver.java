package com.example.frogger2340project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView scoreTV;
    TextView hiscoreTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        scoreTV = findViewById(R.id.scoreText);
        hiscoreTV = findViewById(R.id.highScoreText);
        int score = getIntent().getExtras().getInt("score");
        scoreTV.setText(scoreTV.getText() + "" + score);
        sharedPreferences = getSharedPreferences("my_pref", 0);
        int highest = sharedPreferences.getInt("highest", 0);
        if (score > highest) {
            highest = score;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highest", highest);
            editor.commit();
        }
        hiscoreTV.setText(hiscoreTV.getText() + "" + highest);
    }

    public void restart(View view) {
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
